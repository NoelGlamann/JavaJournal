package edu.neumont.csc150;

import java.io.*;
import java.time.LocalDate;

public class JournalController {
    private JournalInterface ji = new JournalInterface();

    public void run() throws IOException {
        while(true){
            ji.displayMainMenu();
            int selection = ji.getUserInputAsInt(1,4);
            switch (selection) {
                case 1 -> {
                    createNew();
                }
                case 2 -> {
                    searchForEnteredDate();
                }
                case 3 -> {
                    //search by date
                }
                case 4 -> {
                    return;
                }
                default -> throw new IllegalArgumentException("unknown menu item\n");
            }
        }
    }
    public void createNew() throws IOException {
        boolean useTodayDate = ji.establishDate();
        LocalDate date = LocalDate.now();
        String title = ji.getTitle();
        String entryText = ji.getEntry();
        writeToTextFile(title, date, entryText);
    }
    public void searchForEnteredDate() throws IOException {
        String dateToSearch = ji.getDateToSearch();
    }

    public void writeToTextFile(String title, LocalDate d, String text) throws IOException {
        PrintStream out = new PrintStream( new FileOutputStream(title+=".txt"));
        try {
            out.println(d+"\n");
            out.println(text);
        }finally{
            //fileOut.flush();
            out.close();
        }
    }
    public static void readFromTextFile(String title) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(title+=".txt")));
        String lines = "";
        try {
            while(in.ready()){
                lines += in.readLine()+"\r\n";

            }
        }finally {
            in.close();
        }

        System.out.println(lines);
    }

}
