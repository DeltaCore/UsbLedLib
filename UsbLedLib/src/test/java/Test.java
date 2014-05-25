import java.awt.Color;

import net.ccmob.usbled.lib.communication.ControllerInterface;
import net.ccmob.usbled.lib.communication.Device;
import net.ccmob.usbled.lib.communication.UsbLedException;
import net.ccmob.usbled.lib.dataTypes.RGBColor;
import net.ccmob.usbled.lib.dataTypes.RGBColorCommand;


public class Test {

    public Test() {
        
    }

    public static void main(String[] args) {
        try {
            ControllerInterface.connect("COM1");
        } catch (UsbLedException e) {
            e.printStackTrace();
        }
        System.out.println("Connected");
        RGBColor color = new RGBColor(Color.BLACK);
        RGBColor one = new RGBColor(0,1,1);
        RGBColorCommand cmd = new RGBColorCommand(1, 1, 0, 0, 0);
        for(int i = 0;i<255;i++){
            color.add(one);
            cmd.setColor(color);
            try {
                Device.getInstance().handleCommand(cmd);
            } catch (UsbLedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i = 0;i<255;i++){
            color.sub(one);
            cmd.setColor(color);
            try {
                Device.getInstance().handleCommand(cmd);
            } catch (UsbLedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ControllerInterface.disconnect();
        System.out.println("Disconnected");
    }

}
