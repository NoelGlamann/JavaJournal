package edu.neumont.csc150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JournalInterface {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void displayMainMenu(){
        System.out.println("\n\nHow would you like to play?\r\n" +
                "\t1 - Create New Entry\r\n" +
                "\t2 - View Entry By Date\r\n" +
                "\t3 - Search for Entries\r\n" +
                "\t4 - Exit\r\n");
    }

    public boolean establishDate(){
        //establish if they would like to make an entry for today or a custom date
        return false;
    }

    public String getTitle() throws IOException {
        System.out.println("Name the file (no spaces): \n");
        return in.readLine();
    }
    public String getDateToSearch() throws IOException {
        System.out.println("Enter date to search for in specified format: (yyyy-mm-dd).");
        return in.readLine();
    }



    public String getEntry() throws IOException {
        System.out.println("You may begin your entry below. \n");
        return in.readLine();
    }

    public int getUserInputAsInt(int min, int max) throws IOException {
        float value = getUserInputAsFloat(min, max);

        return (int) value;
    }
    public float getUserInputAsFloat(float min, float max) throws IOException {
        float input = 0;
        do {
            String line = in.readLine();
            try {
                input = Float.parseFloat(line);
                if (input < min || input > max) {
                    throw new NumberFormatException("out of range");
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Input invalid. " +
                        "You must enter a number between " + min + " and " + max);
            }
        } while (true);
        return input;
    }
}
