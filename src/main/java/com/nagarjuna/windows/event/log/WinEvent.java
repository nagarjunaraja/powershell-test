package com.nagarjuna.windows.event.log;

public class WinEvent {
	
	private String message = "";
	private String id = "";
	private String version = "";
	private String qualifiers = "";
	private String level = "";
	private String task = "";
	private String opcode = "";
	private String keywords = "";
	private String recordId = "";
	private String providerName = "";
	private String providerId = "";
	private String logName = "";
	private String processId = "";
	private String threadId = "";
	private String machineName = "";
	private String userId = "";
	private String timeCreated = "";
	private String activityId = "";
	private String relatedActivityId = "";
	private String containerLog = "";
	private String matchedQueryIds = "";
	private String bookmark = "";
	private String levelDisplayName = "";
	private String opcodeDisplayName = "";
	private String taskDisplayName = "";
	private String keywordsDisplayNames = "";
	private String properties = "";
	
	
	
	public WinEvent(String message, String id, String version,
			String qualifiers, String level, String task, String opcode,
			String keywords, String recordId, String providerName,
			String providerId, String logName, String processId,
			String threadId, String machineName, String userId,
			String timeCreated, String activityId, String relatedActivityId,
			String containerLog, String matchedQueryIds, String bookmark,
			String levelDisplayName, String opcodeDisplayName,
			String taskDisplayName, String keywordsDisplayNames,
			String properties) {
		super();
		this.message = message;
		this.id = id;
		this.version = version;
		this.qualifiers = qualifiers;
		this.level = level;
		this.task = task;
		this.opcode = opcode;
		this.keywords = keywords;
		this.recordId = recordId;
		this.providerName = providerName;
		this.providerId = providerId;
		this.logName = logName;
		this.processId = processId;
		this.threadId = threadId;
		this.machineName = machineName;
		this.userId = userId;
		this.timeCreated = timeCreated;
		this.activityId = activityId;
		this.relatedActivityId = relatedActivityId;
		this.containerLog = containerLog;
		this.matchedQueryIds = matchedQueryIds;
		this.bookmark = bookmark;
		this.levelDisplayName = levelDisplayName;
		this.opcodeDisplayName = opcodeDisplayName;
		this.taskDisplayName = taskDisplayName;
		this.keywordsDisplayNames = keywordsDisplayNames;
		this.properties = properties;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getQualifiers() {
		return qualifiers;
	}
	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getThreadId() {
		return threadId;
	}
	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getRelatedActivityId() {
		return relatedActivityId;
	}
	public void setRelatedActivityId(String relatedActivityId) {
		this.relatedActivityId = relatedActivityId;
	}
	public String getContainerLog() {
		return containerLog;
	}
	public void setContainerLog(String containerLog) {
		this.containerLog = containerLog;
	}
	public String getMatchedQueryIds() {
		return matchedQueryIds;
	}
	public void setMatchedQueryIds(String matchedQueryIds) {
		this.matchedQueryIds = matchedQueryIds;
	}
	public String getBookmark() {
		return bookmark;
	}
	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}
	public String getLevelDisplayName() {
		return levelDisplayName;
	}
	public void setLevelDisplayName(String levelDisplayName) {
		this.levelDisplayName = levelDisplayName;
	}
	public String getOpcodeDisplayName() {
		return opcodeDisplayName;
	}
	public void setOpcodeDisplayName(String opcodeDisplayName) {
		this.opcodeDisplayName = opcodeDisplayName;
	}
	public String getTaskDisplayName() {
		return taskDisplayName;
	}
	public void setTaskDisplayName(String taskDisplayName) {
		this.taskDisplayName = taskDisplayName;
	}
	public String getKeywordsDisplayNames() {
		return keywordsDisplayNames;
	}
	public void setKeywordsDisplayNames(String keywordsDisplayNames) {
		this.keywordsDisplayNames = keywordsDisplayNames;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "WinEvent [message=" + message + ", id=" + id + ", version="
				+ version + ", qualifiers=" + qualifiers + ", level=" + level
				+ ", task=" + task + ", opcode=" + opcode + ", keywords="
				+ keywords + ", recordId=" + recordId + ", providerName="
				+ providerName + ", providerId=" + providerId + ", logName="
				+ logName + ", processId=" + processId + ", threadId="
				+ threadId + ", machineName=" + machineName + ", userId="
				+ userId + ", timeCreated=" + timeCreated + ", activityId="
				+ activityId + ", relatedActivityId=" + relatedActivityId
				+ ", containerLog=" + containerLog + ", matchedQueryIds="
				+ matchedQueryIds + ", bookmark=" + bookmark
				+ ", levelDisplayName=" + levelDisplayName
				+ ", opcodeDisplayName=" + opcodeDisplayName
				+ ", taskDisplayName=" + taskDisplayName
				+ ", keywordsDisplayNames=" + keywordsDisplayNames
				+ ", properties=" + properties + "]";
	}
	
	

}
