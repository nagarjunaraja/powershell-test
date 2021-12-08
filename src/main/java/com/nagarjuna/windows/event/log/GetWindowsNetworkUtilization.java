package com.nagarjuna.windows.event.log;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

/*
Get-WmiObject -ComputerName OSI-L-0329 -class Win32_PerfFormattedData_Tcpip_NetworkInterface | 
Select-Object Name,BytesTotalPersec,CurrentBandWidth,@{n='Network Utilization';e={(($_.BytesTotalPersec * 8) / $_.CurrentBandWidth) * 100}} |
 Export-Csv "C:\Users\nraja\network_utilization.csv" -Delimiter "|" -NoTypeInformation
 */

public class GetWindowsNetworkUtilization {
	
	/**
	 * Network Utilization Formula ((BytesTotalPerSec * 8)/CurrentBandwidth) * 100
	 * @param remoteComputerName
	 * @return
	 */
	private static String constrcutWindowNetworkUtilizationScript(String remoteComputerName) {

		StringBuilder windowsLastUpdateDatePowershell = new StringBuilder(
				"Get-WmiObject -ComputerName ");
		windowsLastUpdateDatePowershell.append(remoteComputerName);
		windowsLastUpdateDatePowershell
				.append(" -Class Win32_PerfFormattedData_Tcpip_NetworkInterface |");
		windowsLastUpdateDatePowershell.append(" Select-Object Name,BytesTotalPersec,CurrentBandWidth,@{n='Network Utilization';e={(($_.BytesTotalPersec * 8) / $_.CurrentBandWidth) * 100}}");
		windowsLastUpdateDatePowershell.append(" | Export-Csv -Path \"network_utilization.csv\" -Delimiter \"|\" -NoTypeInformation");
		System.out.println("Windows Network Utlization Update Date :: " + windowsLastUpdateDatePowershell.toString());
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
						.executeCommand(constrcutWindowNetworkUtilizationScript(remoteComputerName));
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
