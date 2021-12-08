package com.nagarjuna.windows.event.log;

import java.io.Serializable;

public class EventLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String eventID = "";
	private String machineName = "";
	private String data = "";
	private String index = "";
	private String category = "";
	private String categoryNumber = "";
	private String entryType = "";
	private String message = "";
	private String source = "";
	private String replacementStrings = "";
	private String instanceId = "";
	private String timeGenerated = "";
	private String timeWritten = "";
	private String userName = "";
	private String site = "";
	private String container = "";
	
	public EventLog(String eventID, String machineName, String data,
			String index, String category, String categoryNumber,
			String entryType, String message, String source,
			String replacementStrings, String instanceId, String timeGenerated,
			String timeWritten, String userName, String site, String container) {
		super();
		this.eventID = eventID;
		this.machineName = machineName;
		this.data = data;
		this.index = index;
		this.category = category;
		this.categoryNumber = categoryNumber;
		this.entryType = entryType;
		this.message = message;
		this.source = source;
		this.replacementStrings = replacementStrings;
		this.instanceId = instanceId;
		this.timeGenerated = timeGenerated;
		this.timeWritten = timeWritten;
		this.userName = userName;
		this.site = site;
		this.container = container;
	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryNumber() {
		return categoryNumber;
	}

	public void setCategoryNumber(String categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getReplacementStrings() {
		return replacementStrings;
	}

	public void setReplacementStrings(String replacementStrings) {
		this.replacementStrings = replacementStrings;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getTimeGenerated() {
		return timeGenerated;
	}

	public void setTimeGenerated(String timeGenerated) {
		this.timeGenerated = timeGenerated;
	}

	public String getTimeWritten() {
		return timeWritten;
	}

	public void setTimeWritten(String timeWritten) {
		this.timeWritten = timeWritten;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	@Override
	public String toString() {
		return "EventLog [eventID=" + eventID + ", machineName=" + machineName
				+ ", data=" + data + ", index=" + index + ", category="
				+ category + ", categoryNumber=" + categoryNumber
				+ ", entryType=" + entryType + ", message=" + message
				+ ", source=" + source + ", replacementStrings="
				+ replacementStrings + ", instanceId=" + instanceId
				+ ", timeGenerated=" + timeGenerated + ", timeWritten="
				+ timeWritten + ", userName=" + userName + ", site=" + site
				+ ", container=" + container + "]";
	}
	
	
		
}
