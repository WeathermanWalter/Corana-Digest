package com.company;

import CSVReader.FileRead;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static ArrayList<String> dateStamps = new ArrayList<String>();
    static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        File dir = new File("RAW data\\COVID-19\\csse_covid_19_data\\csse_covid_19_daily_reports_us");

        File readMe = new File(dir.getCanonicalFile() + "\\README.MD");
        if(readMe.delete()) System.out.println("README.MD deleted successfully");

        //Read each file, and fill up the map and dateStamps
        for(File f : dir.listFiles()) {
            System.out.println(f.getName());
            String out = FileRead.readFile(f);
            String date = trimExtension(f.getName());
            map.put(date, out);
            dateStamps.add(date);
        }
    }

    private static String trimExtension(String input) {
        return input.substring(0, input.lastIndexOf('.'));
    }

}
