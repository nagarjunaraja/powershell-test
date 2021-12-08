package com.nagarjuna.eventlog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class EventLog {

	public static void main(String[] args) throws Exception {
		
		//Get-EventLog -ComputerName OSI-L-0329 -LogName System -After "06/25/2017" -Before "06/28/2017" 
		//| Where-Object {$_.EntryType -like 'Error' -or $_.EntryType -like 'Warning'}
	
		String remoteComputerName = "OSI-L-0329";
		String eventLogType = "System";
		String FromDate = "06/28/2017";
		String toDate = "06/30/2017";
		String SPACE = " ";
		List<String> entryTypes =  new ArrayList<String> ();
		entryTypes.add("Error");
		entryTypes.add("Warning");
		
		StringBuilder powerSC = new StringBuilder("Get-EventLog");
		powerSC.append(SPACE);
		powerSC.append("-ComputerName");
		powerSC.append(SPACE);
		powerSC.append(remoteComputerName);
		powerSC.append(SPACE);
		powerSC.append("-LogName");
		powerSC.append(SPACE);
		powerSC.append(eventLogType);
		powerSC.append(SPACE);
		powerSC.append("-After");
		powerSC.append(SPACE);
		powerSC.append(FromDate);
		powerSC.append(SPACE);
		powerSC.append("-Before");
		powerSC.append(SPACE);
		powerSC.append(toDate);
		powerSC.append(SPACE);
		if(entryTypes != null && entryTypes.size() > 0) {
			powerSC.append("|");
			powerSC.append(SPACE);
			powerSC.append("Where-Object {");
			powerSC.append(SPACE);
			powerSC.append("$_.EntryType -like");
			powerSC.append(SPACE);
			powerSC.append("'"+entryTypes.get(0)+"'");
			
			for (int i = 1; i < entryTypes.size(); i++) {
				powerSC.append(SPACE);
				powerSC.append("-or");
				powerSC.append(SPACE);
				powerSC.append("$_.EntryType -like");
				powerSC.append(SPACE);
				powerSC.append("'"+entryTypes.get(i)+"'");
				powerSC.append(SPACE);	
			}
			
					
			powerSC.append(SPACE);
			powerSC.append("}");
			//powerSC.append("| Format-Table TimeWritten, Source, EventID, Message");
			
			
		}
		
		System.out.println("PowerShell Script ==> " + powerSC.toString());
		
		PowerShell powerShell = null;
		PowerShellResponse response = null;
		String content = null;
		
			if (OSDetector.isWindows()) {
				
				// Increase timeout to give enough time to the script to finish			
				Map<String, String> config = new HashMap<String, String>();
				config.put("remoteMode", "false");
				config.put("maxWait", "80000");
				
				powerShell = PowerShell.openSession().configuration(config);

				response = powerShell.executeCommand(powerSC
						.toString());

				// System.out.println("List Processes:" +
				// response.getCommandOutput());
				
				content = response.getCommandOutput();

				System.out.println("List Processes:" + response.getCommandOutput());
				
				powerShell.executeCommand("exit");
				powerShell.executeCommand("exit");
				
				System.out.println("Closing powershell..");
				
				powerShell.close();
			
			}
			
			File file = new File("E://Grid_Director//EclipseWorkspace//EventLog//eventlog.txt");		
			
			 try (FileOutputStream fop = new FileOutputStream(file)) {

					// if file doesn't exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}

					// get the content in bytes
					byte[] contentInBytes = content.getBytes();

					fop.write(contentInBytes);
					fop.flush();
					fop.close();

					System.out.println("Done");

				} catch (IOException e) {
					e.printStackTrace();
				}
	}
}
