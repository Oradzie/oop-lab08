package it.unibo.deathnote.impl;

public class Pair {
    
    private String cause;
    private String details;
    
    public Pair(String cause, String details) {
        this.cause = cause;
        this.details = details;
    }
    
    public String getCause() {
        return this.cause;
    }
    
    public String getDetails() {
        return this.details;
    }
    
    public boolean setCause(String cause) {
        if (this.cause != DeathNoteImpl.DEFAULT_CAUSE) {
            return false;
        }
        this.cause = cause;
        return true;
    }
    
    public boolean setDetails(String details) {
        if (!this.cause.equals("")) {
            return false;
        }
        this.details = details;
        return true;
    }
}
