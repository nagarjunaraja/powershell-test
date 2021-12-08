package com.nagarjuna.windows.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerShellCommand {
	
	 public static void main(String[] args) {
		 
	 StringBuilder windowsUpdateCommand = new StringBuilder(
				"powershell.exe Invoke-Command -ComputerName ");
	 windowsUpdateCommand.append("OSI-L-0329");
	 windowsUpdateCommand.append(" -ScriptBlock { ((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()).QueryHistory(0,((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()).GetTotalHistoryCount()) ");
	 windowsUpdateCommand.append(" | Select-Object Date,@{name=\"Status\"; expression={switch($_.resultcode){ 1 {\"In Progress\"}; 2 {\"Succeeded\"}; 3 {\"Succeeded With Errors\"}; 4 {\"Failed\"}; 5 {\"Aborted\"} }}} }");
	 //windowsUpdateCommand.append(" | Export-Csv -Path \"E:\\Grid_Director\\EclipseWorkspace\\WindowsEventLog\\OSI-L-0329_windows_last_update.csv\" -Delimiter \"|\" -NoTypeInformation");
	System.out.println("Windows Last Update Date :: " + windowsUpdateCommand.toString());


	  //String command = "powershell.exe  your command";
	  //Getting the version
	  //String command = "powershell.exe  Get-WmiObject -ComputerName OSI-L-0329 -Class win32_processor | Measure-Object -property LoadPercentage -Average | Select Average";
	  // Executing the command
	  try {
	  Process powerShellProcess = Runtime.getRuntime().exec(windowsUpdateCommand.toString());
	  // Getting the results
	  powerShellProcess.getOutputStream().close();
	  } catch (Exception e) {
		  e.printStackTrace();
		  System.out.println(e.getMessage());
	  }
	 /* String line;
	  System.out.println("Standard Output:");
	  BufferedReader stdout = new BufferedReader(new InputStreamReader(
	    powerShellProcess.getInputStream()));
	  while ((line = stdout.readLine()) != null) {
	   System.out.println(line);
	  }
	  stdout.close();
	  System.out.println("Standard Error:");
	  BufferedReader stderr = new BufferedReader(new InputStreamReader(
	    powerShellProcess.getErrorStream()));
	  while ((line = stderr.readLine()) != null) {
	   System.out.println(line);
	  }
	  stderr.close();
	  System.out.println("Done");
*/
	 }

	}