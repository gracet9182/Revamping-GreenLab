package com.example.green.ui.store;

import com.example.green.Store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileStore {
    private static ArrayList<String> name = new ArrayList<String>();
    private static ArrayList<String> location = new ArrayList<String>();
    private static ArrayList<String> category = new ArrayList<String>();
    private static ArrayList<String> contacts = new ArrayList<String>();

    public static void main(String[] args) {
        //Input file which needs to be parsed
        String fileToParse = "/Users/parinitaedke/Desktop/SheHacks/app/src/main/java/com/example/green/ui/store/stores.csv";
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
                location.add(tokens[1]);
                category.add(tokens[2]);
                contacts.add(tokens[3]);

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

    public ArrayList<Store> createStores() {
        ArrayList<Store> stores = new ArrayList<Store>();
        for (int i = 0; i < this.name.size(); i++ ){
            Store newStore = new Store(this.name.get(i), this.location.get(i), this.category.get(i), this.contacts.get(i));
            stores.add(newStore);
        }
        return stores;

    }
}
