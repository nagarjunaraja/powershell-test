package com.nagarjuna.windows.event.log;

public enum EventLogType {
	
	SYSTEM("System"),
    APPLICATION("Application"),
    SECURITY("Security"),
    SETUP("Setup");

	private String logType;

	EventLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }
}
