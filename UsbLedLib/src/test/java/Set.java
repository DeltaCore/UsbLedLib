import net.ccmob.usbled.lib.programs.BaseProgram;


public class Set extends BaseProgram {

	public Set() {
		super("Set Color");
	}

	@Override
	public void execute() {
		this.updateColor(255, 0, 0);
	}

}
