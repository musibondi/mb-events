package org.musibondi.events.response;

public class BaseResponse {

	private Integer statusCode;
	private String statusMessage;
	private DataResponse data;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public DataResponse getData() {
		return data;
	}
	public void setData(DataResponse data) {
		this.data = data;
	}
	
	
}
