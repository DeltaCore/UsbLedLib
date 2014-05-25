package net.ccmob.usbled.lib.communication;

import jssc.SerialPortException;
import net.ccmob.usbled.lib.dataTypes.Command;

public class Device {

    private static Device instance;
    
    public static void start(){
        if(instance == null){
            setInstance(new Device());
        }
    }
    
    /**
     * @return the instance
     */
    public static Device getInstance() {
        if(instance == null){
            Device.start();
        }
        return instance;
    }

    /**
     * @param instance the instance to set
     */
    public static void setInstance(Device instance) {
        Device.instance = instance;
    }
    
    public void handleCommand(Command cmd) throws UsbLedException{
        if(!ControllerInterface.isConnected())
            throw new UsbLedException("No device connected.", UsbLedState.DEVICE_NOT_CONNECTED);
        try {
            ControllerInterface.getInstance().getSerialDevice().writeBytes(cmd.toByteArray());
        } catch (SerialPortException e) {
            if(e.getExceptionType() == SerialPortException.TYPE_PORT_NOT_OPENED){
                throw new UsbLedException("Device port not opened: " + ControllerInterface.port, UsbLedState.DEVICE_NOT_CONNECTED);
            }
        }
    }
    
}
