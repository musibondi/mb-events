package org.musibondi.events.model;

import java.time.LocalDateTime;
import java.util.List;

public class EventData {

    private String eventId;
    private List<User> ownerList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Tag> tags;
    private String playlistId;
    protected EventType type;
    protected EventStatus status;
    private List<User> attendeeList;

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public List<User> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<User> ownerList) {
        this.ownerList = ownerList;
    }

    public List<User> getAttendeeList() {
        return attendeeList;
    }

    public void setAttendeeList(List<User> attendeeList) {
        this.attendeeList = attendeeList;
    }

}
