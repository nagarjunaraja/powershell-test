package com.nagarjuna.windows.event.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Windows EventLog Definition
 * 
 * "EventID"|"MachineName"|"Data"|"Index"|"Category"|"CategoryNumber"|"EntryType"|"Message"|"Source"
 * |"ReplacementStrings"|"InstanceId"|"TimeGenerated"|"TimeWritten"|"UserName"|"Site"|"Container"
 * 
 *
 */

public class WindowsEventLogCSVReader {
	
	public static void main(String[] args) throws IOException {

        CSVReader reader = new com.opencsv.CSVReader(new FileReader("eventlog_test.csv"),'|');
        List<String[]> eventLogEntries = reader.readAll();
        List<EventLog> eventlogs = new ArrayList<EventLog>();
        
        eventLogEntries.remove(0);
        
        for(String[] nextFields : eventLogEntries) {

        	EventLog eventLog = new EventLog(
        			nextFields[0],
        			nextFields[1],
        			nextFields[2],
        			nextFields[3],
        			nextFields[4],
        			nextFields[5], 
        			nextFields[6],
        			nextFields[7],
        			nextFields[8],
        			nextFields[9],
        			nextFields[10],
        			nextFields[11],
        			nextFields[12],
        			nextFields[13],
        			nextFields[14],
        			nextFields[15]);
        	
        	eventlogs.add(eventLog);

         }
        
        System.out.println("Event Logs Count ::" + eventlogs.size());
        
        reader.close();
        
        for (Iterator<EventLog> iterator = eventlogs.iterator(); iterator.hasNext();) {
			EventLog eventLog = iterator.next();
			System.out.println(eventLog.toString());
			
		}
        
        
        
    }

}
