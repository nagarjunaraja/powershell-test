package com.nagarjuna.eventlog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.exec.ExecuteWatchdog;

public class TestPowerShell {

	public static void main(String[] args) throws IOException 
    {
		try {
            String command = "powershell.exe \"E:\\Grid_Director\\EclipseWorkspace\\EventLog\\powershell.ps1\"";
            ExecuteWatchdog watchdog = new ExecuteWatchdog(20000);
            Process powerShellProcess = Runtime.getRuntime().exec(command);
            if (watchdog != null) {
                watchdog.start(powerShellProcess);
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(powerShellProcess.getInputStream()));
            String line;
            System.out.println("Output :");
            while ((line = stdInput.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
