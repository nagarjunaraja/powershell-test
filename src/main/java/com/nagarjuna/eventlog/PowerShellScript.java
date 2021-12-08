package com.nagarjuna.eventlog;

import java.util.HashMap;
import java.util.Map;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class PowerShellScript {
	
	public static void main(String[] args) {
		
		PowerShellResponse response = null;
		PowerShell powerShell = null;
		
		   try {
		       //Creates PowerShell session
		       powerShell = PowerShell.openSession();
		       //Increase timeout to give enough time to the script to finish
		       Map<String, String> config = new HashMap<String, String>();
		       config.put("maxWait", "80000");
		       
		       //Execute script
		       response = powerShell.configuration(config).executeScript("powershell.ps1");
		       
		       //Print results if the script
		       System.out.println("Script output:" + response.getCommandOutput());
		   } catch(PowerShellNotAvailableException ex) {
		       //Handle error when PowerShell is not available in the system
		       //Maybe try in another way?
		   } finally {
		       //Always close PowerShell session to free resources.
		       if (powerShell != null)
		         powerShell.close();
		   }
	}

}
