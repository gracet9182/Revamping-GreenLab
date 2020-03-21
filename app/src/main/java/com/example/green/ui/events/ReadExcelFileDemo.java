package com.example.green.ui.events;

import com.example.green.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadExcelFileDemo {
    private static ArrayList<String> date = new ArrayList<String>();
    private static ArrayList<String> time = new ArrayList<String>();
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> location = new ArrayList<String>();

    public static void main(String[] args) {
        //Input file which needs to be parsed
        String fileToParse = "/Users/kobychoy/StudioProjects/SheHacks/app/src/main/java/com/example/green/ui/events/events.csv";
        BufferedReader fileReader = null;

        //Delimiter used in CSV file
        final String DELIMITER = ";";
        try {
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileToParse));

            //Read the file line by line
            //Can make this store in any format we need. For now it simply prints all the items one after the other.
            //We can store it in Array Lists as needed.
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(DELIMITER);
                name.add(tokens[0]);
                date.add(tokens[1]);
                time.add(tokens[2]);
                location.add(tokens[3]);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Event> createEvents() {
        ArrayList<Event> events = new ArrayList<Event>();
        for (int i = 0; i < this.name.size(); i++ ){
            Event newEvent = new Event(this.date.get(i), this.time.get(i), this.name.get(i), this.location.get(i));
            events.add(newEvent);
        }
        return events;

    }
}