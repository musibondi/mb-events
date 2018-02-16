package org.musibondi.events.model;

public enum EventType {

    PUBLIC("public"),
    PRIVATE("private");

    private final String type;

    private EventType(String type){
        this.type =  type;
    }

    public String getType() {
        return type;
    }

    public static EventType getBy(String type){
        for(EventType eventType : values()){
            if( eventType.getType().equalsIgnoreCase(type)){
                return eventType;
            }
        }
        return null;
    }
}
