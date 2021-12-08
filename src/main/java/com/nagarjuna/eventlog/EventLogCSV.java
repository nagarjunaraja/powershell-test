package com.nagarjuna.eventlog;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;

public class EventLogCSV {
	
	public static void main(String[] args) throws PowerShellNotAvailableException {
		
		StringBuilder sb = new StringBuilder("Get-EventLog -ComputerName OSI-L-0329 -LogName System -After 06/30/2017 -Before 06/30/2017 ");
		sb.append(" | Where-Object { $_.EntryType -like 'Error'} ");
		sb.append(" | Select-Object EventID,MachineName,Data,Message");		
		sb.append(" | Export-Csv -Path \"eventlog_csv.csv\" -Delimiter \"|\" -NoTypeInformation");
		
		try {
		
		if (OSDetector.isWindows()) {
            PowerShell powerShell = PowerShell.openSession();
            powerShell.executeCommand(sb.toString());

            powerShell.close();
        }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
