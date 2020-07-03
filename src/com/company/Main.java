package com.company;

import CSVReader.FileRead;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static ArrayList<String> dateStamps = new ArrayList<String>();
    static ArrayList<Place> areas = new ArrayList<Place>();
    static Map<String, String> map = new HashMap<String, String>(); //assigns each csv to its time stamp

    public static void main(String[] args) throws IOException {
        //updateRepo();
        readFile();
        assignPlaces();
    }

    static void updateRepo() {
        try {
            Git git = Git.cloneRepository()
                    .setURI("https://github.com/CSSEGISandData/COVID-19.git")
                    .setDirectory(new File("C:\\Users\\david\\Desktop\\Corona_digest\\RAW data"))
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            System.out.println("Could not update from online, please check your internet connection");
            System.out.println("exiting");
            System.exit(1);
        }
    }

    private static void readFile() throws IOException {
        File dir = new File("RAW data\\csse_covid_19_data\\csse_covid_19_daily_reports_us");

        File readMe = new File(dir.getCanonicalFile() + "\\README.MD");
        if(readMe.delete()) System.out.println("README.MD deleted successfully");

        //Read each file, and fill up the map and dateStamps
        for(File f : dir.listFiles()) {
            //System.out.println(f.getName());
            String out = FileRead.readFile(f);
            String date = trimExtension(f.getName());
            map.put(date, out);
            dateStamps.add(date);
        }
    }

    private static void assignPlaces() {
        //loop through most recent csv file, and start assigning places
        String buffer = map.get(dateStamps.get(dateStamps.size() - 1));
        CSVReader reader = new CSVReader(buffer);
        for (int i = 0; i < 18; i++) {
            System.out.println(reader.getNextValue());
        }
    }

    private static String trimExtension(String input) {
        return input.substring(0, input.lastIndexOf('.'));
    }

    private static class CSVReader {
        int index;
        String data;
        BufferedReader br;

        CSVReader(String CSVData) {
            data = CSVData;
            index = 0;
            br = new BufferedReader(new StringReader(data));
        }

        String getNextValue() {
            StringBuilder sb = new StringBuilder("");
            int value;
            try {
                while (true) {
                    value = br.read();
                    char c = (char)value;
                    if (c == ',') {
                        break;
                    } else if (c == '\n') {
                        break;
                    }
                    sb.append(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

    }

}
