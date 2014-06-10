package net.ccmob.usbled.lib.dataTypes;

public class Command {
	
	public static final char startByte = '*';
	private int address = 0;
	private int cmd = 0;
	private int cmdData1 = 0;
	private int cmdData2 = 0;
	private int cmdData3 = 0;
	private int cmdData4 = 0;
	public static final char endByte = '#'; 
	
	protected Command(int cmd, int address) {
		this.setCmd(cmd);
		this.setAddress(address);
	}
	
	/**
	 * @return the startbyte
	 */
	public static char getStartbyte() {
		return startByte;
	}

	/**
	 * @return the address
	 */
	public int getAddress() {
		return address;
	}

	/**
	 * @return the cmd
	 */
	public int getCmd() {
		return cmd;
	}

	/**
	 * @return the cmdData1
	 */
	public int getCmdData1() {
		return cmdData1;
	}

	/**
	 * @return the cmdData2
	 */
	public int getCmdData2() {
		return cmdData2;
	}

	/**
	 * @return the cmdData3
	 */
	public int getCmdData3() {
		return cmdData3;
	}

	/**
	 * @return the cmdData4
	 */
	public int getCmdData4() {
		return cmdData4;
	}

	/**
	 * @return the endbyte
	 */
	public static char getEndbyte() {
		return endByte;
	}

	/**
	 * @param address the address to set
	 */
	protected void setAddress(int address) {
		this.address = address;
	}

	/**
	 * @param cmd the cmd to set
	 */
	protected void setCmd(int cmd) {
		this.cmd = cmd;
	}

	/**
	 * @param cmdData1 the cmdData1 to set
	 */
	protected void setCmdData1(int cmdData1) {
		this.cmdData1 = cmdData1;
	}

	/**
	 * @param cmdData2 the cmdData2 to set
	 */
	protected void setCmdData2(int cmdData2) {
		this.cmdData2 = cmdData2;
	}

	/**
	 * @param cmdData3 the cmdData3 to set
	 */
	protected void setCmdData3(int cmdData3) {
		this.cmdData3 = cmdData3;
	}

	/**
	 * @param cmdData4 the cmdData4 to set
	 */
	protected void setCmdData4(int cmdData4) {
		this.cmdData4 = cmdData4;
	}

	public byte[] toByteArray(){
		byte[] arr = new byte[8];
		arr[0] = startByte;
		arr[1] = (byte) this.getAddress();
		arr[2] = (byte) this.getCmd();
		arr[3] = (byte) this.getCmdData1();
		arr[4] = (byte) this.getCmdData2();
		arr[5] = (byte) this.getCmdData3();
		arr[6] = (byte) this.getCmdData4();
		arr[7] = endByte;
		return arr;
	}
	
	public void transfer(Command cmd){
		this.setAddress(cmd.getAddress());
		this.setCmd(cmd.getCmd());
		this.setCmdData1(cmd.getCmdData1());
		this.setCmdData2(cmd.getCmdData2());
		this.setCmdData3(cmd.getCmdData3());
		this.setCmdData4(cmd.getCmdData4());
	}
	
	public Command copy(){
		Command cmd = new Command(0,0);
		cmd.transfer(this);
		return cmd;
	}
	
}
