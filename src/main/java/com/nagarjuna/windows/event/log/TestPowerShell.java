package com.nagarjuna.windows.event.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestPowerShell {
	
	public static void main(String[] args) throws IOException 
    {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec("powershell C:\\Users\\nraja\\Desktop\\avg.ps1");
        InputStream is = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String line;
        StringBuilder sbf = new StringBuilder();
        
       // System.out.println("Count :: " + reader.lines().count());
        
        while ((line = reader.readLine()) != null)
        {
            System.out.println(line);
            sbf.append(line);
        }
        
        System.out.println("I am outside");
        reader.close();
        proc.getOutputStream().close();
        System.out.println("I am here");
        System.out.println("Here it is :: " + sbf.toString().trim());
    }

}
