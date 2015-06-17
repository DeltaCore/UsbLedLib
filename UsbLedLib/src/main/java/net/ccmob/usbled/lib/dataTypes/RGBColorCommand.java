package net.ccmob.usbled.lib.dataTypes;


public class RGBColorCommand extends Command {

    public RGBColorCommand(int addr, int stripe, int r, int g, int b) {
        super(2, addr);
        this.setCmdData1(r);
        this.setCmdData2(g);
        this.setCmdData3(b);
        this.setCmdData4(stripe);
    }
    
    public RGBColorCommand(int addr, int stripe, RGBColor c) {
        super(2, addr);
        this.setCmdData1(c.getR());
        this.setCmdData2(c.getG());
        this.setCmdData3(c.getB());
        this.setCmdData4(stripe);
    }

	public void setColor(RGBColor color){
        this.setCmdData1(color.getR());
        this.setCmdData2(color.getG());
        this.setCmdData3(color.getB());
    }
	
	public void setColor(int r, int g, int b){
        this.setCmdData1(r);
        this.setCmdData2(g);
        this.setCmdData3(b);
    }
	
	public void setCommandAddress(int id){
		this.setAddress(id);
	}
	
	public void setStripeAddress(int id){
		this.setCmdData4(id);
	}
	
	public RGBColor getColor(){
	    return new RGBColor(this.getCmdData1(), this.getCmdData2(), this.getCmdData3());
	}
	
}