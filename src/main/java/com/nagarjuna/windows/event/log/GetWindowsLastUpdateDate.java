package com.nagarjuna.windows.event.log;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

/*
Get-WmiObject -ComputerName OSI-L-0329 -Class win32_reliabilityRecords -filter "sourcename = 'Microsoft-Windows-WindowsUpdateClient'" 
-ErrorAction SilentlyContinue | select @{LABEL = "date";EXPRESSION = {$_.ConvertToDateTime($_.timegenerated)}}, @{LABEL = 'Update';EXPRESSION = {$_.message}} 
| FT -AutoSize -Wrap
 */
/*
 * 
 * Invoke-Command -ComputerName OSI-L-0329 -ScriptBlock { ((New-Object -ComObject "Microsoft.Update.Session").CreateUpdateSearcher()).QueryHistory(0,
 * ((New-Object -ComObject "Microsoft.Update.Session").CreateUpdateSearcher()).GetTotalHistoryCount()) | 
 * Select-Object Date,@{name="Status"; expression={switch($_.resultcode){ 1 {"In Progress"}; 2 {"Succeeded"}; 3 {"Succeeded With Errors"}; 
 * 4 {"Failed"}; 5 {"Aborted"} }}} }
 */
public class GetWindowsLastUpdateDate {
	
	
	private static String constrcutWindowLastUpdateDateScript(String remoteComputerName) {

		StringBuilder windowsLastUpdateDatePowershell = new StringBuilder(
				"Invoke-Command -ComputerName ");
		windowsLastUpdateDatePowershell.append(remoteComputerName);
		windowsLastUpdateDatePowershell.append(" -ScriptBlock { ((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()).QueryHistory(0,((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()).GetTotalHistoryCount()) ");
		windowsLastUpdateDatePowershell.append(" | Select-Object Date,@{name=\"Status\"; expression={switch($_.resultcode){ 1 {\"In Progress\"}; 2 {\"Succeeded\"}; 3 {\"Succeeded With Errors\"}; 4 {\"Failed\"}; 5 {\"Aborted\"} }}} }");
		windowsLastUpdateDatePowershell.append(" | Export-Csv -Path \""+remoteComputerName+"windows_last_update.csv\" -Delimiter \"|\" -NoTypeInformation");
		System.out.println("Windows Last Update Date :: " + windowsLastUpdateDatePowershell.toString());
		return windowsLastUpdateDatePowershell.toString();
	}

	public static void main(String[] args)
			throws PowerShellNotAvailableException {

		PowerShell powerShell = null;
		PowerShellResponse response = null;
		String remoteComputerName = "OSI-L-0329";

		try {
			if (OSDetector.isWindows()) {
				powerShell = PowerShell.openSession();
				response = powerShell
						.executeCommand(constrcutWindowLastUpdateDateScript(remoteComputerName));
				System.out
						.println("response :: " + response.getCommandOutput());
			}
		} catch (PowerShellNotAvailableException pe) {
			pe.printStackTrace();
			System.out
					.println("PowerShell is not available in the system. Please contact Administrator"
							+ pe.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unknow Exception. Please contact Administrator"
					+ e.getMessage());
		} finally {
			if (powerShell != null) {
				powerShell.close();
			}
		}
	}

}
