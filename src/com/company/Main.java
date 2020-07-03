package com.company;

import CSVReader.FileRead;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File dir = new File("RAW data\\COVID-19\\csse_covid_19_data\\csse_covid_19_daily_reports_us");

        File readMe = new File(dir.getCanonicalFile() + "\\README.MD");
        if(readMe.delete()) System.out.println("README.MD deleted successfully");

        for(File f : dir.listFiles()) {
            System.out.println(f.getName());
            String test = FileRead.readFile(f);
        }
    }
}
