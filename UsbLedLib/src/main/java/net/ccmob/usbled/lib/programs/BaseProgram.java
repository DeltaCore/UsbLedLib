package net.ccmob.usbled.lib.programs;

import java.util.ArrayList;

import net.ccmob.usbled.lib.communication.Device;
import net.ccmob.usbled.lib.communication.UsbLedException;
import net.ccmob.usbled.lib.dataTypes.RGBColor;
import net.ccmob.usbled.lib.dataTypes.RGBColorCommand;

public abstract class BaseProgram implements Runnable{

    private boolean running;
    private boolean finished;
    protected Thread thread = null;
    private String id;
    private ArrayList<ProgramStateChangeListener> listener = new ArrayList<ProgramStateChangeListener>();
    
    private RGBColorCommand cmd = new RGBColorCommand(1, 1, 0, 0, 0);
    
    public BaseProgram(String id) {
        this.setId(id);
        //ProgramHandler.getInstance().addProgram(this);
    }
    
    public void onExecute(){
        this.thread = new Thread(this);
        this.thread.setName("UsbLedBGT[ID|" + this.getId() + "]");
        this.thread.start();
        System.out.println("Running " + this.getId());
    }
    
    public abstract void execute();
    
    @Override
    public void run() {
        this.setRunning(true);
        this.setFinished(false);
        queueStateChange(ProgramState.RUNNING);
        execute();
        this.setFinished(true);
        this.setRunning(false);
        queueStateChange(ProgramState.DONE);
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void stop(){
        this.setRunning(false);
        queueStateChange(ProgramState.FINISHING);
    }

    /**
     * @return the thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * @param thread the thread to set
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    protected void sleep(int ms){
        try{
            Thread.sleep(ms);
        }catch(Exception e){}
    }

    /**
     * @return the finished
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished the finished to set
     */
    private void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * @return the listener
     */
    protected ArrayList<ProgramStateChangeListener> getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    protected void setListener(ArrayList<ProgramStateChangeListener> listener) {
        this.listener = listener;
    }
    
    public void addProgramStateChangeListener(ProgramStateChangeListener listener){
        this.getListener().add(listener);
    }
    
    public void removeProgramStateChangeListener(ProgramStateChangeListener listener){
        this.getListener().remove(listener);
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.getListener().clear();
    }
    
    protected void queueStateChange(ProgramState state){
        for(ProgramStateChangeListener l : this.getListener()){
            l.onStateChange(this, state);
        }
    }
    
    protected void updateColor(int r, int g, int b){
        cmd.setColor(r,g,b);
        try {
            Device.getInstance().handleCommand(cmd);
        } catch (UsbLedException e) {
            e.printStackTrace();
        }
    }
    
    protected void updateColor(RGBColor color){
        cmd.setColor(color);
        try {
            Device.getInstance().handleCommand(cmd);
        } catch (UsbLedException e) {
            e.printStackTrace();
        }
    }
    
    protected void setAddress(int pcbAdress, int stripe){
        cmd.setCommandAddress(pcbAdress);
        cmd.setStripeAddress(stripe);
    }
    
}
