package com.nagarjuna.eventlog;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class GetProcess {
	
	public static void main(String[] args) {
		
		 PowerShellResponse response = PowerShell.executeSingleCommand("Get-Service"); 
		 //Print results
		   System.out.println("List Processes:" + response.getCommandOutput());
	}

}
