package com.nagarjuna.eventlog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

	public static void main(String[] args) throws ParseException {
	
	 //3/15/2013 12:06:00 pm
		
	  String dateS = "01/07/2017 01:37:56";
	  Date dNow = new Date( );
	  System.out.println(dNow);
      SimpleDateFormat ft = 
      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

      System.out.println("Current Date: " + ft.format(dNow));
      
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
      
      System.out.println("Parse " + sdf.parse(dateS));
      
      System.out.println("Current Date: " + sdf.format(dNow));
      
      //sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
      
      /*Date dateObj = sdf.parse("2013-10-22T01:37:56");
      
      System.out.println(dateObj.toString());*/
	}
}
