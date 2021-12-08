package com.nagarjuna.windows.event.log;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class PowershellTest {
	
	public static void main(String[] args) {
		
		PowerShell powerShell = null;
		   try {
		       //Creates PowerShell session (we can execute several commands in the same session)
		       powerShell = PowerShell.openSession();
		       
		       PowerShellResponse response = 
		    		   powerShell.executeCommand("(Get-Counter -ComputerName OSI-L-0329 -Counter \"\\Network Interface(*)\\Bytes Received/sec\").CounterSamples | Select CookedValue");
		       
		      //System.out.println("Bytes Received/sec:" + response.getCommandOutput());
		       
		       String bytesReceived = response.getCommandOutput();
		       String[] s1 = bytesReceived.split("\n");
		       float sum=0.0f;
		       for(int i=3;i<s1.length;i++){
		    	   if(!s1[i].trim().isEmpty()) {		    		  
		    		 sum=sum+Float.parseFloat(s1[i]);
		    	   }
		     }
		       System.out.println("Cumulative Bytes Received/sec: " + sum);
		       
		       response = 
		    		   powerShell.executeCommand("(Get-Counter -ComputerName OSI-L-0329 -Counter \"\\Network Interface(*)\\Bytes Sent/sec\").CounterSamples | Select CookedValue");
		       
		       //System.out.println("Bytes sent/sec:" + response.getCommandOutput());
		       
		       String bytesSent = response.getCommandOutput();
		       s1 = bytesSent.split("\n");
		       sum=0.0f;
		       for(int i=3;i<s1.length;i++){
		    	   if(!s1[i].trim().isEmpty()) {
		    		 sum=sum+Float.parseFloat(s1[i]);
		    	   }
		     }
		       System.out.println("Cumulative Bytes sent/sec: " + sum);
		       
		      
		       
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
