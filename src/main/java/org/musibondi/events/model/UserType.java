package org.musibondi.events.model;

public enum UserType {

    OWNER("owner"),
    ATTENDEE("attendee");

    private final String name;

    private UserType (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UserType getType(String name){
        UserType ut = null;
        for(int i = 0;i<UserType.values().length;i++){
            if(UserType.values()[i].getName().equalsIgnoreCase(name)){
                ut = UserType.values()[i];
                break;
            }
        }
        return ut;
    }

}
