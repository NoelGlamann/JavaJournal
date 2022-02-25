package edu.neumont.csc150;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class JournalController {
    private JournalInterface ji = new JournalInterface();

    public void run() throws IOException {
        while(true){
            ji.displayMainMenu();
            int selection = ji.getUserInputAsInt(1,4);
            switch (selection) {
                case 1 -> {
                    //create new
                    establishDate();
                }
                case 2 -> {
                    //search by date
                }
                case 3 -> {
                    //search by date range
                }
                case 4 -> {
                    return;
                }
                default -> throw new IllegalArgumentException("unknown menu item\n");
            }
        }
    }

    public void establishDate() throws IOException {
        LocalDate d;
        ji.displayCurrentOrCustomDate();
        int choice = ji.getUserInputAsInt(1,3);
        switch(choice){
            case 1: {
                d = LocalDate.now();
            }
            case 2: {
                d = ji.getCustomDate();
            }
            case 3: {
                return;
            }
            default: {
                d = LocalDate.now();
            }
        }
        SimpleDateFormat fmt = new SimpleDateFormat("MM-dd-yyyy");
        String dateString = fmt.format(d);

    }
}
