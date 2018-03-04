package org.musibondi.events.request;

import org.musibondi.events.model.EventData;
import org.musibondi.events.model.EventType;
import org.musibondi.events.model.Tag;
import org.musibondi.events.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class EventCreationRequest {

    private User owner;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Tag> tags;
    private String playlistId;
    private String type;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
