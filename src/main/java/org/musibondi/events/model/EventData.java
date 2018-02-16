package org.musibondi.events.model;

import org.musibondi.events.service.EventService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public class EventData {

    private String eventId;
    private String userId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Tag> tags;
    private String playlistId;
    protected EventType type;
    protected EventStatus status;

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserId() {
        return userId;
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

    @Override
    public String toString() {
        return "EventData{" +
                "eventId='" + eventId + '\'' +
                ", userId='" + userId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", tags=" + tags +
                ", playlistId='" + playlistId + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
