package org.musibondi.events.model;

public class Tag {

	private String tagName;
	
	public Tag(String tagName) {
		super();
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + "]";
	}

	
}
