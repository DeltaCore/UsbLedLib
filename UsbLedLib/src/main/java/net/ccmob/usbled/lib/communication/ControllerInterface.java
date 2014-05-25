package net.ccmob.usbled.lib.communication;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ControllerInterface {

    private static ControllerInterface instance;
    private static Device deviceInstance;
    
    private SerialPort device = null;
    public static String port = "COM1";
    
    private static boolean connected = false;

    public ControllerInterface() {

    }

    public static void connect(String portName) throws UsbLedException {
        if (ControllerInterface.getInstance() == null) {
            instance = new ControllerInterface();
            if (portName != null && portName.isEmpty() == false)
                ControllerInterface.port = portName;
            SerialPort dev = new SerialPort(port);
            try {
                dev.openPort();
            } catch (SerialPortException e) {
                if (e.getExceptionType().equals(SerialPortException.TYPE_PORT_BUSY)) {
                    throw new UsbLedException("Failed to open device port on " + port + ". Device already in use.", UsbLedState.DEVICE_ALREADY_IN_USE);
                } else if (e.getExceptionType().equals(SerialPortException.TYPE_PORT_NOT_FOUND)) {
                    throw new UsbLedException("Failed to open device port on " + port + ". Device not found.", UsbLedState.DEVICE_NOT_FOUND);
                }
            }
            try {
                dev.setParams(1024000, 8, 1, 0);
            } catch (SerialPortException e) {
                throw new UsbLedException("Failed to set device parameters.", UsbLedState.DEVICE_PARAMETERS_FAILED);
            }
            instance.setSerialDevice(dev);
            ControllerInterface.setConnected(true);
        }
    }

    public static void disconnect() {
        if (ControllerInterface.getInstance() == null)
            return;
        if (getInstance().getSerialDevice() == null)
            return;
        if (getInstance().getSerialDevice().isOpened()) {
            try {
                getInstance().getSerialDevice().closePort();
                ControllerInterface.setConnected(false);
                ControllerInterface.instance = null;
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the instance
     */
    public static ControllerInterface getInstance() {
        return instance;
    }

    /**
     * @return the device
     */
    public SerialPort getSerialDevice() {
        return device;
    }

    /**
     * @return the port
     */
    public static String getPort() {
        return port;
    }

    /**
     * @param device
     *            the device to set
     */
    private void setSerialDevice(SerialPort device) {
        this.device = device;
    }

    /**
     * @param port
     *            the port to set
     */
    public static void setPort(String port) {
        ControllerInterface.port = port;
    }

    /**
     * @return the deviceInstance
     */
    public static Device getDeviceInstance() {
        return deviceInstance;
    }

    /**
     * @param deviceInstance the deviceInstance to set
     */
    public static void setDeviceInstance(Device deviceInstance) {
        ControllerInterface.deviceInstance = deviceInstance;
    }

    /**
     * @return the connected
     */
    public static boolean isConnected() {
        return connected;
    }

    /**
     * @param connected the connected to set
     */
    private static void setConnected(boolean connected) {
        ControllerInterface.connected = connected;
    }

}
