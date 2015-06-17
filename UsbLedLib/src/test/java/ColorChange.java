import net.ccmob.usbled.lib.programs.BaseProgram;

public class ColorChange extends BaseProgram {

    private int speed = 500;

    public ColorChange() {
        super("ColorChange");
    }

    @Override
    public void execute() {
        this.setSpeed(10);
        for (int i = 0; i < 256; i++) {
            this.setAddress(1, 1);
            this.updateColor(i, 0, 255 - i);
            this.setAddress(1, 2);
            this.updateColor(i, 0, 255 - i);
            sleep(this.getSpeed());
        }
        for (int i = 0; i < 256; i++) {
            this.setAddress(1, 1);
            this.updateColor(255 - i, i, 0);
            this.setAddress(1, 2);
            this.updateColor(255 - i, i, 0);
            sleep(this.getSpeed());
        }
        for (int i = 0; i < 256; i++) {
            this.setAddress(1, 1);
            this.updateColor(0, 255 - i, i);
            this.setAddress(1, 2);
            this.updateColor(0, 255 - i, i);
            sleep(this.getSpeed());
        }
    }
    
    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed
     *            the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
