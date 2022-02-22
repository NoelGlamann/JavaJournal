package edu.neumont.csc150;

import java.io.IOException;

public class Journal {
    public static void main(String[] args) throws IOException {
        JournalController jc = new JournalController();
        jc.run();
        System.out.println("Journal Saved");
    }
}

//FUNCTION USES IN JOURNAL HELPER CODE

//how to get and format date = localDate()
//how to find if in date range or equal to date = compareLocalDate()
//helper class to control files = fileClass()
    //use to make journals folder to contain files and create files
//getting all the files in a directory = fileList()
    //if file names use - not _ you shouldn't need .replace at end of String dateOnly=
//getting first 45 chars summary = fortyFiveCharacters()
//taking input of multiple lines = getLifeStoryFromUser()
