package com.nagarjuna.windows.event.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;

/*
 * 
"application", "system","security","Cisco AnyConnect Secure Mobility Client" | foreach { $events = Get-EventLog
-ComputerName OSI-L-0329 -LogName $_  -After ([datetime]'07/12/2017 12:06:00 pm') -Before ([datetime]'07/12/2017 12:58:00 pm'); $
events | Add-Member -MemberType NoteProperty -Name LogName -Value (Get-Culture).TextInfo.ToTitleCase($_); $events } | Export-Csv -
Path "C:\Users\nraja\eventlog.csv" -Delimiter "|" -NoTypeInformation
 */

public class GetWindowsLog {
	
	
	private static String getEventTypes(List<String> eventTypes) {
		
		StringBuilder listOfEventTypes = new StringBuilder();
		
		if (eventTypes != null && eventTypes.size() > 0) {
			listOfEventTypes.append("\""+eventTypes.get(0)+"\"");
		}
				
		for (int i = 1; i < eventTypes.size(); i++) {			
			listOfEventTypes.append(",\""+eventTypes.get(i)+"\"");
		}
		
		System.out.println("List of Event Types :: " + listOfEventTypes.toString());
		
		return listOfEventTypes.toString();
	}

	public static String constructPowerShellEventLogScript(
			String remoteComputerName, List<String> eventTypes ,String fromDate, String toDate, List<String> entryTypes) {

		String SPACE = " ";

		StringBuilder powerShellEventLog = new StringBuilder(getEventTypes(eventTypes));
		powerShellEventLog.append(" | foreach { $events =");
		powerShellEventLog.append("Get-EventLog");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("-ComputerName");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append(remoteComputerName);
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("-LogName");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("$_");
		powerShellEventLog.append(SPACE);
		if(fromDate != null && !fromDate.isEmpty()) {
			powerShellEventLog.append("-After");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("([datetime]'");
			powerShellEventLog.append(fromDate);
			powerShellEventLog.append("')");
			powerShellEventLog.append(SPACE);
		}		
		powerShellEventLog.append("-Before");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("([datetime]'");
		powerShellEventLog.append(toDate);
		powerShellEventLog.append("')");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("; $events }");
		//powerShellEventLog.append("; $events | Add-Member -MemberType NoteProperty -Name LogName -Value (Get-Culture).TextInfo.ToTitleCase($_); $events }");
		if (entryTypes != null && entryTypes.size() > 0) {
			powerShellEventLog.append("|");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("Where-Object {");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("$_.EntryType -like");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("'" + entryTypes.get(0) + "'");

			for (int i = 1; i < entryTypes.size(); i++) {
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("-or");
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("$_.EntryType -like");
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("'" + entryTypes.get(i) + "'");
				powerShellEventLog.append(SPACE);
			}
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("}");
			powerShellEventLog.append(" | Export-Csv -Path \"windows_logs.csv\" -Delimiter \"|\" -NoTypeInformation");

		}
		System.out.println("Powershell Windows Event Log Script :: " + powerShellEventLog.toString());
		return powerShellEventLog.toString();
	}

	public static void main(String[] args) {

		// -after ([datetime]'3/15/2013 12:06:00 pm')
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String remoteComputerName = "OSI-L-0329";
		// Date fromDate = sdf.parse("01/07/2017 01:37:56");
		// Date toDate = sdf.parse("01/07/2017 02:37:56");
		List<String> entryTypes = new ArrayList<String>();
		entryTypes.add("Error");
		entryTypes.add("Warning");
		//entryTypes.add("Information");
		//entryTypes.add("FailureAudit");
		//entryTypes.add("SuccessAudit");
		
		List<String> eventTypes = new ArrayList<String>();
		eventTypes.add("Application");
		eventTypes.add("System");
		eventTypes.add("Security");
		//eventTypes.add("Cisco AnyConnect Secure Mobility Client");
		
		PowerShell powerShell = null;
		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;
		String fromDate = null;
		String toDate = null;

		try {

			input = new FileInputStream("windows-logs.properties");			
			prop.load(input);			
			fromDate = prop.getProperty("EVENT_LOG_FROMDATE");
			toDate = "07/17/2017 12:58:00 pm";
			System.out.println("EVENT LOG FROM DATE" + prop.getProperty("EVENT_LOG_FROMDATE"));		
			
			Map<String, String> config = new HashMap<String, String>();
			config.put("remoteMode", "true");
			config.put("maxWait", "60000");
			
			if (OSDetector.isWindows()) {
				powerShell = PowerShell.openSession().configuration(config);
				powerShell.executeCommand(constructPowerShellEventLogScript(
						remoteComputerName, eventTypes,
						fromDate, toDate,
						entryTypes));				
			}			
			
			output = new FileOutputStream("windows-logs.properties");
			
			prop.setProperty("EVENT_LOG_FROMDATE", toDate);
			prop.store(output,null);			
			
		} catch (PowerShellNotAvailableException  pe) {
			pe.printStackTrace();
			System.out.println("PowerShell is not available in the system. Please contact Administrator" + pe.getMessage());
		} catch (IOException  e) {
			e.printStackTrace();
			System.out.println("IO Exception. Please contact Administrator" + e.getMessage());
		}  catch (Exception  e) {
			e.printStackTrace();
			System.out.println("Unknow Exception. Please contact Administrator" + e.getMessage());
		} finally {
			if(powerShell != null) {
				powerShell.executeCommand("exit");
				//powerShell.executeCommand("exit");
				powerShell.close();
			}
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		}
	}

}
