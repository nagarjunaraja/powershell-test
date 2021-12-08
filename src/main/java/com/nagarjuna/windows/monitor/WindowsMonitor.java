package com.nagarjuna.windows.monitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

public class WindowsMonitor  {
	
	//Inputs from configuration
	String powershellScript = "Get-WmiObject -ComputerName OSI-L-0329 -Class win32_processor | Measure-Object -property LoadPercentage -Average | Select Average"; 
	byte[] bytesEncoded = Base64.encodeBase64(powershellScript.getBytes());
	
	private void createPowershellFile(String fileName) throws IOException {				
		byte[] valueDecoded= Base64.decodeBase64(bytesEncoded);
		System.out.println("Decoded value is " + new String(valueDecoded));		
		String powershellFilePath  = System.getProperty("user.dir")+"\\"+fileName+".ps1";
		System.out.println("PowershellFilePath " + powershellFilePath);
		File file = new File(powershellFilePath);
		
		//FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
		//FileLock lock = channel.lock();

		try  {
			System.out.println("File name : " + file.getAbsolutePath());
			FileOutputStream fop = new FileOutputStream(file);

			if (!file.exists()) {
				file.createNewFile();
			}
			//if(channel.isOpen())
			
			fop.write(valueDecoded);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//channel.lock().release();
		}
		 
	}
	
	private  void deletePowershellFile(String fileName) {
		
		String powershellFilePath  = System.getProperty("user.dir")+"\\"+fileName+".ps1";
		try{

    		File file = new File(powershellFilePath);
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
		} catch (Exception e){

    		e.printStackTrace();
    		System.out.println("Delete Exception . " + e.getMessage());
			
		}
	}
	
	private String executePowerShellScript(String fileName) {
		
			String powershellFilePath  = System.getProperty("user.dir")+"\\"+fileName+".ps1";
		
		   PowerShell powerShell = null;
		   PowerShellResponse response = null;
		   try {
		       //Creates PowerShell session
		       powerShell = PowerShell.openSession();
		       //Increase timeout to give enough time to the script to finish
		       Map<String, String> config = new HashMap<String, String>();
		       config.put("maxWait", "80000");
		       
		       //Execute script
		       response = powerShell.configuration(config).executeScript(powershellFilePath);
		       
		       //Print results if the script
		       System.out.println("Script output:" + response.getCommandOutput());	
		       
		       powerShell.executeCommand("exit");
		       
		   } catch(PowerShellNotAvailableException ex) {
		       //Handle error when PowerShell is not available in the system
		       //Maybe try in another way?
		   } finally {
		       //Always close PowerShell session to free resources.
		       if (powerShell != null)
		         powerShell.close();
		   }
		   
		   return response.getCommandOutput();
		   
	}
	
	public static void main(String[] args) throws IOException {
		WindowsMonitor windowsMonitor = new WindowsMonitor();
		String fileName = "cpu_utilization";
		windowsMonitor.createPowershellFile(fileName);
		windowsMonitor.executePowerShellScript(fileName);
		windowsMonitor.deletePowershellFile(fileName);
	}

	
}
