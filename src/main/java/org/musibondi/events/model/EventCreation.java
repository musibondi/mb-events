package org.musibondi.events.model;

import java.sql.Date;
import java.util.List;

public class EventCreation {
	
    private String userId;
    private Date startDate;
    private Date endDate;
    private List<Tag> tags;
    private String playlistId;
    private String type;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
	@Override
	public String toString() {
		return "EventCreation [userId=" + userId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", tags=" + tags + ", playlistId="
				+ playlistId + ", type=" + type + "]";
	}
	
	

}
