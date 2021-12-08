package com.nagarjuna.windows.event.log;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class GetWindowsUsage {
	
	
	private static String constrcutWindowCPUUtilizationScript(String remoteComputerName) {

		StringBuilder windowsCPUUtilizationPowershell = new StringBuilder(
				"Get-WmiObject -ComputerName ");
		windowsCPUUtilizationPowershell.append(remoteComputerName);
		windowsCPUUtilizationPowershell
				.append(" -Class win32_processor | Measure-Object -property LoadPercentage -Average | Select Average");
		windowsCPUUtilizationPowershell.append(" | Export-Csv -Path \"C:\\Users\\nraja\\Desktop\\Test\\CPUUtlization.csv\" -Delimiter \"|\" -NoTypeInformation");
	
		System.out.println("Windows CPU Utlization :: " + windowsCPUUtilizationPowershell.toString());
		return windowsCPUUtilizationPowershell.toString();
	}

	public static void main(String[] args)
			throws InterruptedException, IOException {

		PowerShell powerShell = null;
		PowerShellResponse response = null;
		String remoteComputerName = "OSI-L-0329";
		while(true) 
			{
				try {
					if (OSDetector.isWindows()) {
						Map<String, String> config = new HashMap<String, String>();
					       config.put("maxWait", "10000");
						powerShell = PowerShell.openSession().configuration(config);
						response = powerShell
								.executeCommand(constrcutWindowCPUUtilizationScript(remoteComputerName));
						
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
				
				CSVReader reader = new CSVReader(new FileReader("C:\\Users\\nraja\\Desktop\\Test\\CPUUtlization.csv"));
		        List<String[]> windowsLastUpdateEntries = reader.readAll();
		        
		        if(windowsLastUpdateEntries.isEmpty()){
		        	System.out.println("EMPTY..............");
		        }
		        
		        reader.close();
				
				//Thread.sleep(10000);
			}
	}

}
