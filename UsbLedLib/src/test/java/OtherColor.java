import net.ccmob.usbled.lib.programs.BaseProgram;

public class OtherColor extends BaseProgram {

	public OtherColor() {
		super("OtherColorChange");
	}

	@Override
	public void execute() {
		for (int i = 0; i < 256; i++) {
			this.updateColor(i, 0, 0);
			sleep(10);
		}
		for (int i = 0; i < 256; i++) {
			this.updateColor(255 - i, 0, 0);
			sleep(10);
		}
		for (int i = 0; i < 256; i++) {
			this.updateColor(0, i, 0);
			sleep(10);
		}
		for (int i = 0; i < 256; i++) {
			this.updateColor(0, 255 - i, 0);
			sleep(10);
		}
		for (int i = 0; i < 256; i++) {
			this.updateColor(0, 0, i);
			sleep(10);
		}
		for (int i = 0; i < 256; i++) {
			this.updateColor(0, 0, 255 - i);
			sleep(10);
		}
	}

}
