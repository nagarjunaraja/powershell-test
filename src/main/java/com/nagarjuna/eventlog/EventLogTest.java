package com.nagarjuna.eventlog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class EventLogTest {
	
	public static void main(String[] args) throws Exception {
		
		//Get-EventLog -ComputerName OSI-L-0329 -LogName System -After "06/25/2017" -Before "06/28/2017" 
		//| Where-Object {$_.EntryType -like 'Error' -or $_.EntryType -like 'Warning'}
		
		StringBuilder sb = new StringBuilder("Get-EventLog -LogName System -After \"06/25/2017\" -Before \"06/28/2017\"");
		sb.append(" | Where-Object {$_.EntryType -like 'Error' -or $_.EntryType -like 'Warning'} | Format-Table TimeWritten, Source, EventID, Message");
		
		String content = "";
		
		 if (OSDetector.isWindows()) {
	            PowerShell powerShell = PowerShell.openSession();
	            PowerShellResponse response = powerShell.executeCommand(sb.toString());

	            content = response.getCommandOutput();
	            System.out.println("List Directory:" + content);

	            //Assert.assertTrue(response.getCommandOutput().contains("LastWriteTime"));

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
