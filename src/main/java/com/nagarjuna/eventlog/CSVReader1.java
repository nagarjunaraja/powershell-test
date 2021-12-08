package com.nagarjuna.eventlog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader1 {
	
	 public static void main(String[] args) throws IOException {

	        String csvFile = "eventlog_csv.csv";
	        String line = "";
	        String cvsSplitBy = "|";

	        //System.out.println(cvsSplitBy);
	        /*try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
	        {

	            while ((line = br.readLine()) != null) {
	            	
	                // use comma as separator
	                String[] eventlogs = line.split(Pattern.quote("|"));
	                //System.out.println(line);
	                System.out.println("==> " + eventlogs[0]);

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         */
	        /*com.opencsv.CSVReader reader = new com.opencsv.CSVReader(new FileReader(csvFile));
	        String [] nextLine;
	        while ((nextLine = reader.readNext()) != null) {
	           // nextLine[] is an array of values from the line
	           System.out.println(nextLine[0]);
	        }*/
	        
	        com.opencsv.CSVReader reader1 = new com.opencsv.CSVReader(new FileReader(csvFile),'|');
	        List<String[]> myEntries = reader1.readAll();
	        System.out.println("Entries Size :: " +  myEntries.size());
	        int increment = 1;
	       
	        for(String[] nextLine : myEntries) {
	            // nextLine[] is an array of values from the line
	            //System.out.println(nextLine[0] + " "  + nextLine[1] + " " + nextLine[2] + " " + nextLine[3] );
	            System.out.println(nextLine[0]  );
	            System.out.println(nextLine[1]  );
	            System.out.println(nextLine[2] );
	            System.out.println(nextLine[3]  );
	            System.out.println("Increment .. " + increment);
	            increment++;
	         }
	        
	    }


}
