package com.nagarjuna.windows.event.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Windows EventLog Definition
 * "LogName"|"EventID"|"MachineName"|"Data"|"Index"|"Category"|"CategoryNumber"|"EntryType"|"Message"|"Source"|"ReplacementStrings"|"InstanceId"
 * |"TimeGenerated"|"TimeWritten"|"UserName"|"Site"|"Container"
 * 
 *
 */

public class WindowsLogCSVReader {
	
	public static void main(String[] args) throws IOException {

        CSVReader reader = new com.opencsv.CSVReader(new FileReader("OSI-L-0329-windows_logs.csv"),'|');
        List<String[]> windowsLogEntries = reader.readAll();
        List<WindowsLog> windowsLogs = new ArrayList<WindowsLog>();
        
        windowsLogEntries.remove(0);
        
        for(String[] nextFields : windowsLogEntries) {

        	WindowsLog windowsLog = new WindowsLog(
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
        	
        	windowsLogs.add(windowsLog);

         }
        
        System.out.println("Windows Logs Count ::" + windowsLogs.size());
        
        reader.close();
        
        for (Iterator<WindowsLog> iterator = windowsLogs.iterator(); iterator.hasNext();) {
			WindowsLog windowsLog = iterator.next();
			System.out.println(windowsLog.getEventID());
			
		}
        
        
        
    }

}
