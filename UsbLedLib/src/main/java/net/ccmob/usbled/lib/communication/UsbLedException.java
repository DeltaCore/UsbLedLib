package net.ccmob.usbled.lib.communication;

public class UsbLedException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3226793420468398446L;
    
    private UsbLedState cause;
    
    public UsbLedException(UsbLedState cause) {
        this.setCause(cause);
    }
    
    public UsbLedException(String message, UsbLedState cause) {
        super(message);
        this.setCause(cause);
    }

    public UsbLedException(String message) {
        super(message);
    }

    public UsbLedException(Throwable cause) {
        super(cause);
        
    }

    public UsbLedException(String message, Throwable cause) {
        super(message, cause);
        
    }

    public UsbLedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * @return the cause
     */
    public UsbLedState getState() {
        return cause;
    }

    /**
     * @param cause the cause to set
     */
    private void setCause(UsbLedState cause) {
        this.cause = cause;
    }
    
}
