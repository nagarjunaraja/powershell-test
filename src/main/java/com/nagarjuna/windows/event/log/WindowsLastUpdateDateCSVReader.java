package com.nagarjuna.windows.event.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Windows Last Update Date
 * 
 *"date"|"Update"
 * 
 *
 */

public class WindowsLastUpdateDateCSVReader {
	
	
	public static void main(String[] args) throws IOException {

        CSVReader reader = new com.opencsv.CSVReader(new FileReader("OSI-L-0329windows_last_update.csv"),'|');
        List<String[]> windowsLastUpdateEntries = reader.readAll();
        
        windowsLastUpdateEntries.remove(0);
        
        String[] nextFields = windowsLastUpdateEntries.get(0);
        
        System.out.println("Windows Last Update Date ::  " + nextFields[0]); 
       
    }

}
