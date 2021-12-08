package com.nagarjuna.eventlog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class CSVReader {
	
	 public static void main(String[] args) {

	        String csvFile = "eventlog_csv.csv";
	        String line = "";
	        String cvsSplitBy = "|";

	        //System.out.println(cvsSplitBy);
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {
	            	
	            	StringTokenizer stringTokenizer = new StringTokenizer(line, "|");

	     		   while (stringTokenizer.hasMoreElements()) {

  //"EventID"|"MachineName"|"Data"|"Index"|"Category"|"CategoryNumber"|"EntryType"|"Message"|"Source"|"ReplacementStrings"|"InstanceId"|"TimeGenerated"|"TimeWritten"|"UserName"|"Site"|"Container"

	     			  String eventId = stringTokenizer.nextElement().toString();
	     			  System.out.println(eventId);
	     			  String machineName = stringTokenizer.nextElement().toString();
	     			  System.out.println(machineName);
	     			  String data = stringTokenizer.nextElement().toString();
	     			 System.out.println(data);
//	     			  String index = stringTokenizer.nextElement().toString();
//	     			  String category = stringTokenizer.nextElement().toString();
//	     			  String category_number = stringTokenizer.nextElement().toString();
//	     			  String entry_type = stringTokenizer.nextElement().toString();
	     			  String message = stringTokenizer.nextElement().toString();
	     			  System.out.println(message);
//	     			  String source = stringTokenizer.nextElement().toString();
//	     			  String replacementString = stringTokenizer.nextElement().toString();
//	     			  String instanceId = stringTokenizer.nextElement().toString();
//	     			  String timegenerated = stringTokenizer.nextElement().toString();
//	     			  String timewritten = stringTokenizer.nextElement().toString();
//	     			  String username = stringTokenizer.nextElement().toString();
//	     			  String site = "";
//	     			  String container ="";
//	     			  //System.out.println(eventId + " " + machineName + " " + data + " " +index + " " + category + " " + category_number + " " + entry_type + " " + message + " " + source + " "+ replacementString + " " + instanceId + " " + timegenerated + " " +timewritten + " " + username + " " + site + " " +container);
	     			  
	     			  System.out.println("........");
	     		   }

	                // use comma as separator
	               // String[] eventlogs = line.split(Pattern.quote("|"));
	                //System.out.println(line);
	                //System.out.println("==> " + eventlogs[0]);

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }


}
