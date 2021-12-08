package com.nagarjuna.windows.event.log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RemoteWindowsLastUpdate {
	
	public static void main(String[] args) {
		
		 StringBuilder windowsUpdateCommand = new StringBuilder(
					"powershell $user='nraja'; $pass='osius@2016'; $passwd=ConvertTo-SecureString -AsPlainText $pass -Force; $cred=New-Object System.Management.Automation.PSCredential -ArgumentList $user, $passwd; Invoke-Command -ComputerName ");
		 windowsUpdateCommand.append("osi-l-0329");
		 windowsUpdateCommand.append(" -ScriptBlock { ((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()) } -credential $cred ");
		 //windowsUpdateCommand.append(" | Select-Object Date,@{name=\"Status\"; expression={switch($_.resultcode){ 1 {\"In Progress\"}; 2 {\"Succeeded\"}; 3 {\"Succeeded With Errors\"}; 4 {\"Failed\"}; 5 {\"Aborted\"} }}} } -credential $cred ");
		// windowsUpdateCommand.append(" | Export-Csv -Path \"E:\\Grid_Director\\EclipseWorkspace\\WindowsEventLog\\OSI-L-0329_windows_last_update.csv\" -Delimiter \"|\" -NoTypeInformation ");

		 try {
			  Process powerShellProcess = Runtime.getRuntime().exec("powershell $user='nraja'; $pass='osius@2016'; $passwd=ConvertTo-SecureString -AsPlainText $pass -Force; $cred=New-Object System.Management.Automation.PSCredential -ArgumentList $user, $passwd; Invoke-command -ComputerName osi-l-0329 -ScriptBlock {Get-EventLog -LogName Application} -credential $cred");
			  //Process powerShellProcess = Runtime.getRuntime().exec("powershell $user='nraja'; $pass='osius@2016'; $passwd=ConvertTo-SecureString -AsPlainText $pass -Force; $cred=New-Object System.Management.Automation.PSCredential -ArgumentList $user, $passwd; Invoke-Command -ComputerName osi-l-0329 -ScriptBlock { ((New-Object -ComObject \"Microsoft.Update.Session\").CreateUpdateSearcher()) } -credential $cred");
			  // Process powerShellProcess = Runtime.getRuntime().exec(windowsUpdateCommand.toString());
			  //System.out.println(windowsUpdateCommand.toString());
			  
			  String line;
			  System.out.println("Standard Output:");
			  BufferedReader stdout = new BufferedReader(new InputStreamReader(
			    powerShellProcess.getInputStream()));
			  while ((line = stdout.readLine()) != null) {
			   System.out.println(line);
			  }
			  stdout.close();
			  powerShellProcess.getOutputStream().close();
			  } catch (Exception e) {
				  e.printStackTrace();
				  System.out.println(e.getMessage());
			  } 

		
	}
	

}
