package edu.neumont.csc150;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class JournalInterface {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void displayMainMenu(){
        System.out.println("\n\nWhat would you like to do?\r\n" +
                "\t1 - Create New Entry\r\n" +
                "\t2 - View Entry By Date\r\n" +
                "\t3 - Search for Entries\r\n" +
                "\t4 - Exit\r\n");
    }

    public void displayCurrentOrCustomDate(){
        System.out.println("\n\nWhat date would you like to establish?\r\n" +
                "\t1 - Today\r\n" +
                "\t2 - Custom\r\n" +
                "\t3 - Back\r\n");
    }

    public LocalDate getCustomDate() throws IOException {
        System.out.println("\nEnter custom date below");
        System.out.println("Month: ");
        int month = getUserInputAsInt(1,12);
        System.out.println("Day: ");
        int day = 0;
        if (month == 2){
            day = getUserInputAsInt(1, 29);
        }
        else if (month==4 || month==6 || month==9 || month==11){
            day = getUserInputAsInt(1,30);
        }else{
            day = getUserInputAsInt(1, 31);
        }
        System.out.println("Year: ");
        int year = getUserInputAsInt(2000, 3000);

        return LocalDate.of(year, month, day);
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
