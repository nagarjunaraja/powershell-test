package com.nagarjuna.windows.event.log;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * Windows WinEvent Definition
 * 
 * "Message"|"Id"|"Version"|"Qualifiers"|"Level"|"Task"|"Opcode"|"Keywords"|"RecordId"|"ProviderName"|"ProviderId"
 * |"LogName"|"ProcessId"|"ThreadId"|"MachineName"|"UserId"|"TimeCreated"|"ActivityId"|"RelatedActivityId"
 * |"ContainerLog"|"MatchedQueryIds"|"Bookmark"|"LevelDisplayName"|"OpcodeDisplayName"|"TaskDisplayName"
 * |"KeywordsDisplayNames"|"Properties"
 * 
 *
 */

public class WindowsWinEventCSVReader {
	
	public static void main(String[] args) throws IOException {

        CSVReader reader = new com.opencsv.CSVReader(new FileReader("winevent_setup.csv"),'|');
        List<String[]> winEventEntries = reader.readAll();
        List<WinEvent> winEvents = new ArrayList<WinEvent>();
        
        winEventEntries.remove(0);
        
        for(String[] nextFields : winEventEntries) {

        	WinEvent winEvent = new WinEvent(
        			nextFields[0],
        			nextFields[1],
        			nextFields[2],
        			nextFields[3],
        			nextFields[4],
        			nextFields[5], 
        			nextFields[6],
        			nextFields[7],
        			nextFields[8],
        			nextFields[9],
        			nextFields[10],
        			nextFields[11],
        			nextFields[12],
        			nextFields[13],
        			nextFields[14],
        			nextFields[15],
        			nextFields[16],
        			nextFields[17],
        			nextFields[18],
        			nextFields[19],
        			nextFields[20],
        			nextFields[21],
        			nextFields[22],
        			nextFields[23],
        			nextFields[24],
        			nextFields[25],
        			nextFields[26]);
        	
        	winEvents.add(winEvent);

         }
        
        System.out.println("Win Events Count ::" + winEvents.size());
        
        reader.close();
        
        for (Iterator<WinEvent> iterator = winEvents.iterator(); iterator.hasNext();) {
        	WinEvent winEvent = iterator.next();
			System.out.println(winEvent.toString());
			
		}
        
        
        
    }

}
