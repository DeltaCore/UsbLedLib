package net.ccmob.usbled.lib.dataTypes;

public class ColorCommand extends Command{
	
	public ColorCommand(int addr, int stripe, char color, int value) {
		super(1, addr);
		this.setCmdData1(color);
		this.setCmdData2(value);
		this.setCmdData4(stripe);
	}
	
	public char getColor(){
	    return (char) this.getCmdData1();
	}
	
	public int getValue(){
	    return this.getCmdData2();
	}
	
}
