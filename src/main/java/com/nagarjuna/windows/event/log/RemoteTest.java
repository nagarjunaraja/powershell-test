package com.nagarjuna.windows.event.log;

import java.io.IOException;

public class RemoteTest {
	
	public static void main(String[] args) {
		
		 Process p;

		    try {

		        p = new ProcessBuilder()
		        .inheritIO()
		        .command("powershell", "$user='nraja'; $pass='osius@2016'; $passwd=ConvertTo-SecureString -AsPlainText $pass -Force; $cred=New-Object System.Management.Automation.PSCredential -ArgumentList $user, $passwd; Invoke-command -ComputerName osi-l-0329 -ScriptBlock {Get-ChildItem C:\\} -credential $cred").start();
		        p.waitFor();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    } catch (InterruptedException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		    
		   

		
	}
	
	
}
