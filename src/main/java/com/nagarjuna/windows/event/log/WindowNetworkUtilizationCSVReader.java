package com.nagarjuna.windows.event.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Windows Network Utilization 
 *"Name"|"BytesTotalPersec"|"CurrentBandWidth"|"Network Utilization"
 * 
 *
 */

public class WindowNetworkUtilizationCSVReader {
	
	
	public static void main(String[] args) throws IOException {

        CSVReader reader = new com.opencsv.CSVReader(new FileReader("network_utilization.csv"),'|');
        List<String[]> windowsNetworkUtlizationEntries = reader.readAll();
        List<WindowsNetworkUtilization> windowsNetworkUtilizations = new ArrayList<WindowsNetworkUtilization>();
        
        windowsNetworkUtlizationEntries.remove(0);
        
        for(String[] nextFields : windowsNetworkUtlizationEntries) {

        	WindowsNetworkUtilization windowsNetworkUtilization = new WindowsNetworkUtilization(
        			nextFields[0],
        			nextFields[1],
        			nextFields[2],
        			nextFields[3]);
        	windowsNetworkUtilizations.add(windowsNetworkUtilization);

         }
        
        System.out.println("Windows Network Utilization Count ::" + windowsNetworkUtilizations.size());
        
        reader.close();
        
        for (Iterator<WindowsNetworkUtilization> iterator = windowsNetworkUtilizations.iterator(); iterator.hasNext();) {
        	WindowsNetworkUtilization windowsNetworkUtilization = iterator.next();
			System.out.println(windowsNetworkUtilization.toString());
			
		}
       
    }

}
