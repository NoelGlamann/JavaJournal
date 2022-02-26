package edu.neumont.csc150;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class JournalController {
    private JournalInterface ji = new JournalInterface();

    public boolean run() throws IOException {
        boolean running = true;
        ji.displayMainMenu();
        int selection = ji.getUserInputAsInt(1,4);
        switch (selection) {
            case 1 -> {
                //create new
                establishDate();
                break;
            }
            case 2 -> {
                //search by date
                searchForDate();
                break;
            }
            case 3 -> {
                //search by date range
                getDateRange();
                break;
            }
            case 4 -> {
                running = false;
            }
            default -> throw new IllegalArgumentException("unknown menu item\n");
        }
        return running;
    }

    private void establishDate() throws IOException {
        LocalDate d;
        ji.displayCurrentOrCustomDate();
        int choice = ji.getUserInputAsInt(1,3);
        switch(choice){
            case 1: {
                d = LocalDate.now();
                break;
            }
            case 2: {
                System.out.println("\nEnter date below --");
                d = ji.getCustomDate();
                break;
            }
            case 3: {
                return;
            }
            default: d = LocalDate.now();
        }
        System.out.println("got date");
        String dateString = d.toString();
        System.out.println(dateString);
        makeFile(dateString);
    }
    private void makeFile(String date) throws IOException {
        File folder = new File("Journal_Entries");
        if(!folder.exists()){
            folder.mkdir();
        }
        File entry = new File("Journal_Entries/"+date+".txt");
        writeToFile(entry);
    }
    private void writeToFile(File file) throws IOException {
        String text = ji.getEntryText();
        PrintStream out = new PrintStream(new FileOutputStream(file));
        try{
            out.println(text);
        }finally{
            out.close();
        }
    }
    private void searchForDate() throws IOException {
        System.out.println("\nEnter date below --");
        LocalDate dateToSearch = ji.getCustomDate();
        String[] files = getFileList();
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(dateToSearch.toString()+".txt")){
                readFromTextFile(files[i], dateToSearch);
                System.out.println("--------------------------------");
            }
        }
    }
    private String[] getFileList(){
        File journalEntries = new File("Journal_Entries");
        String[] list = journalEntries.list();
        return list;
    }
    private void readFromTextFile(String file, LocalDate date) throws IOException {
        InputStream fileIn = new FileInputStream("Journal_Entries/"+file);
        BufferedReader in = new BufferedReader(new InputStreamReader(fileIn));
        String line = "";
        try{
            while(in.ready()){
                line+=in.readLine()+"\r\n";
            }
        }finally{
            fileIn.close();
        }
        System.out.println("--------------------------------");
        System.out.println("Entry for: " + date.toString());
        System.out.println();
        System.out.println(line);
    }
    private void getDateRange() throws IOException {
        System.out.println("\nEnter start date below --");
        LocalDate startDate = ji.getCustomDate();
        System.out.println();
        System.out.println("\nEnter end date below --");
        LocalDate endDate = ji.getCustomDate();
        filesInRange(startDate, endDate);
    }
    private void filesInRange(LocalDate start, LocalDate end) throws IOException {
        String[] files = getFileList();
        for (int i = 0; i < files.length; i++) {
            LocalDate amIInRange = LocalDate.parse(files[i].substring(0, files[i].lastIndexOf('.')));
            if (amIInRange.equals(start) || amIInRange.equals(end)
            || (amIInRange.isBefore(end) && amIInRange.isAfter(start))){
                readFromTextFile(files[i], amIInRange);
                System.out.println("--------------------------------");
            }
        }
    }
}
