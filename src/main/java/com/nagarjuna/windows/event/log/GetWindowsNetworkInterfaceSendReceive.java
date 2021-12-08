package com.nagarjuna.windows.event.log;

import java.util.HashMap;
import java.util.Map;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

/*
(Get-Counter -ComputerName OSI-L-0329 -Counter \"\\Network Interface(*)\\Bytes Received/sec\").CounterSamples | Select CookedValue
(Get-Counter -ComputerName OSI-L-0329 -Counter \"\\Network Interface(*)\\Bytes Sent/sec\").CounterSamples | Select CookedValue
 */

public class GetWindowsNetworkInterfaceSendReceive {
	
	/**
	 * Network Interface Send
	 * @param remoteComputerName
	 * @return
	 */
	private static String getWindowsNetworkInterfaceSend(String remoteComputerName) {

		StringBuilder windowsNetworkInterfaceSend = new StringBuilder("(Get-Counter -ComputerName ");
		windowsNetworkInterfaceSend.append(remoteComputerName);
		windowsNetworkInterfaceSend.append(" -Counter \"\\Network Interface(*)\\Bytes Sent/sec\").CounterSamples | Select CookedValue");
				System.out.println("Windows Network Interface Send :: " + windowsNetworkInterfaceSend.toString());
		return windowsNetworkInterfaceSend.toString();
	}
	
	/**
	 * Network Interface Receive
	 * @param remoteComputerName
	 * @return
	 */
	private static String getWindowsNetworkInterfaceReceive(String remoteComputerName) {

		StringBuilder windowsNetworkInterfaceReceive = new StringBuilder("(Get-Counter -ComputerName ");
		windowsNetworkInterfaceReceive.append(remoteComputerName);
		windowsNetworkInterfaceReceive.append(" -Counter \"\\Network Interface(*)\\Bytes Received/sec\").CounterSamples | Select CookedValue");
				System.out.println("Windows Network Interface Receive :: " + windowsNetworkInterfaceReceive.toString());
		return windowsNetworkInterfaceReceive.toString();
	}
	
	public static float getCumulativeSendOrReceieveValue(String bytesReceiveOrSend) {
		
		 String[] s1 = bytesReceiveOrSend.split("\n");
	       float cumulative=0.0f;
	       for(int i=3;i<s1.length;i++){
	    	   if(!s1[i].trim().isEmpty()) {		    		  
	    		   cumulative =  cumulative + Float.parseFloat(s1[i]);
	    	   }
	     }
		
	   return cumulative;
	}

	public static void main(String[] args)
			throws PowerShellNotAvailableException {

		PowerShell powerShell = null;
		PowerShellResponse response = null;
		String remoteComputerName = "OSI-L-0329";

		try {
			if (OSDetector.isWindows()) {
				powerShell = PowerShell.openSession();
				Map<String, String> config = new HashMap<String, String>();
			       config.put("maxWait", "80000");
				response = powerShell
						.configuration(config).executeCommand(getWindowsNetworkInterfaceReceive(remoteComputerName));
				
				float bytesReceived = getCumulativeSendOrReceieveValue(response.getCommandOutput());			      
			       
				System.out.println("Cumulative Bytes Received/sec: " + bytesReceived);
		       
		        response = powerShell.configuration(config).executeCommand(getWindowsNetworkInterfaceSend(remoteComputerName));		       

		        float bytesSent = getCumulativeSendOrReceieveValue(response.getCommandOutput());		
		     
		       System.out.println("Cumulative Bytes Sent/sec: " + bytesSent);
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
