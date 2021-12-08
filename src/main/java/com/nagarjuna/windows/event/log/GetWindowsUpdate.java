package com.nagarjuna.windows.event.log;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class GetWindowsUpdate {
	
	
	private static String constrcutWindowUpdateScript(String remoteComputerName) {

		StringBuilder windowsUpdatePowershell = new StringBuilder(
				"Invoke-Command -ComputerName ");
		windowsUpdatePowershell.append(remoteComputerName);
		windowsUpdatePowershell
				.append(" -ScriptBlock { [System.TimeZoneInfo]::ConvertTimeFromUtc((New-Object -ComObject Microsoft.Update.AutoUpdate).Results.LastInstallationSuccessDate,[System.TimeZoneInfo]::FindSystemTimeZoneById((Get-WmiObject win32_timezone).StandardName)) }");
		System.out.println("Windows Update :: " + windowsUpdatePowershell.toString());
		return windowsUpdatePowershell.toString();
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
						.executeCommand(constrcutWindowUpdateScript(remoteComputerName));
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
