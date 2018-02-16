package org.musibondi.events.model;

public enum EventStatus {

    PLANNING("planning"),
    RUNNING("running"),
    ABORTED("aborted"),
    FINISHED("finished");


    private final String status;

    private EventStatus(String status){
        this.status =  status;
    }

    public String getStatus() {
        return status;
    }

    public static EventStatus getBy(String status){
        for(EventStatus eventStatus : values()){
            if( eventStatus.getStatus().equalsIgnoreCase(status)){
                return eventStatus;
            }
        }
        return null;
    }
}
