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

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
