package com.nagarjuna.windows.event.log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;

/**
 * Get-WinEvent -ComputerName OSI-L-0329 -FilterHashtable @{LogName = 'Setup'; StartTime='07/04/2015 12:06:00am'; EndTime='07/04/2017 12:06:00 pm' } |  
 * Where-Object {$_.LevelDisplayName -eq 'Information'} |
 * Export-Csv -Path "C:\Users\nraja\wineventlog_setup.csv" -Delimiter "|" -NoTypeInformation
 */


public class GetWinEvent {

	public static String constructPowerShellEventLogScript(
			String remoteComputerName, EventLogType eventLogType,
			String fromDate, String toDate, List<String> entryTypes) {

		String SPACE = " ";

		StringBuilder powerShellEventLog = new StringBuilder("Get-WinEvent");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("-ComputerName");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append(remoteComputerName);
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("-FilterHashtable");
		powerShellEventLog.append(SPACE);
		powerShellEventLog.append("@{LogName = '");
		powerShellEventLog.append(eventLogType);
		powerShellEventLog.append("';");
		powerShellEventLog.append("StartTime='");
		powerShellEventLog.append(fromDate);
		powerShellEventLog.append("'; EndTime='");
		powerShellEventLog.append(toDate);
		powerShellEventLog.append("'}");
		powerShellEventLog.append(SPACE);
		if (entryTypes != null && entryTypes.size() > 0) {
			powerShellEventLog.append("|");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("Where-Object {");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("$_.LevelDisplayName -like");
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("'" + entryTypes.get(0) + "'");

			for (int i = 1; i < entryTypes.size(); i++) {
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("-or");
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("$_.LevelDisplayName -like");
				powerShellEventLog.append(SPACE);
				powerShellEventLog.append("'" + entryTypes.get(i) + "'");
				powerShellEventLog.append(SPACE);
			}
			powerShellEventLog.append(SPACE);
			powerShellEventLog.append("}");
			powerShellEventLog.append(" | Export-Csv -Path \"winevent_setup.csv\" -Delimiter \"|\" -NoTypeInformation");

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
		entryTypes.add("Information");
		//entryTypes.add("FailureAudit");
		//entryTypes.add("SuccessAudit");
		
		PowerShell powerShell = null;
		Properties prop = new Properties();
		InputStream input = null;
		OutputStream output = null;
		String fromDate = null;
		String toDate = null;

		try {

			input = new FileInputStream("windows-logs.properties");			
			prop.load(input);			
			fromDate = prop.getProperty("SETUP_FROMDATE");
			toDate = "07/04/2017 12:06:00 pm";
			System.out.println("SETUP_FROMDATE" + prop.getProperty("SETUP_FROMDATE"));			
			
			if (OSDetector.isWindows()) {
				powerShell = PowerShell.openSession();
				powerShell.executeCommand(constructPowerShellEventLogScript(
						remoteComputerName, EventLogType.SETUP,
						fromDate, toDate,
						entryTypes));				
			}			
			
			output = new FileOutputStream("windows-logs.properties");
			
			prop.setProperty("SETUP_FROMDATE", toDate);
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
