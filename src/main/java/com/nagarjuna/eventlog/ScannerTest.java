package com.nagarjuna.eventlog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerTest {
	
	public static void main(String[] args) throws IOException {
		// open file input stream
		BufferedReader reader = new BufferedReader(new FileReader(
				"eventlog_csv.csv"));

		// read file line by line
		String line = null;
		java.util.Scanner scanner = null;
		int index = 0;
		List<EventLogData> eventLogDataList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			EventLogData eventLogData = new EventLogData();
			scanner = new Scanner(line);
			scanner.useDelimiter("|");
			while (scanner.hasNext()) {
				String data = scanner.next();
				System.out.println(data);
				/*if (index == 0)
					System.out.println(data);
				else if (index == 1)
					System.out.println(data);
				else if (index == 2)
					System.out.println(data);
				else if (index == 3)
					System.out.println(data);
				else
					System.out.println("invalid data::" + data);
				index++;*/
			}
			index = 0;
		}
		
		//close reader
		reader.close();
	}
}
