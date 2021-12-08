package com.nagarjuna.windows.event.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUTCConversion {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEEE, MMM dd, yyyy HH:mm:ss a");
		String dateInString = "7/10/2017 4:40:43 AM";
		//7/10/2017 4:40:43 AM

		/*Date date = formatter.parse(dateInString);
		String s = formatter.format(date);
		SimpleDateFormat formatter1 = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss'Z'");
		formatter1.setTimeZone(TimeZone.getTimeZone("UTC"));
		formatter1.format(date);
		System.out.println("output " + formatter1.format(date));*/
		
		String lv_dateFormateInUTC=""; //Will hold the final converted date      
		SimpleDateFormat lv_formatter = new SimpleDateFormat(); 
		lv_formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
		lv_dateFormateInUTC = lv_formatter.format(dateInString); 
		
		System.out.println(lv_dateFormateInUTC);
	}

}
