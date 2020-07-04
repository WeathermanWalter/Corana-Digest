package com.company;

import CSVReader.FileRead;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static ArrayList<String> dateStamps = new ArrayList<String>();
    static ArrayList<Place> areas = new ArrayList<Place>(58);
    static Map<String, String> map = new HashMap<String, String>(); //assigns each csv to its time stamp

    public static void main(String[] args) throws IOException {
        //updateRepo();
        readFile();
        assignPlaces();
    }

    static void updateRepo() {
        File f = new File("RAW data");
            try {
                Git git = Git.cloneRepository()
                        .setURI("https://github.com/CSSEGISandData/COVID-19.git") //this is the covid repo, updated daily
                        .setDirectory(new File("RAW data")) //perhaps change it to f?
                        .call();
            } catch (GitAPIException e) {
                e.printStackTrace();
                System.out.println("Could not update from online, please check your internet connection");
                System.out.println("exiting");
                System.exit(1);
            }

    }

    static boolean checkContents(File f) {
        boolean bool = f.mkdir();
        if (bool) return false; //if it did make a dir, nothings in it, and needs to be filled
        try {
            FileRepositoryBuilder builder = new FileRepositoryBuilder();
            Repository repo = builder.setGitDir(f)
                    .readEnvironment()
                    .findGitDir()
                    .build();

            // setting remote server so wa can "pull" from github
            Git git = new Git(repo);
            PullCommand cmd = git.pull();
            cmd.setRemote("https://github.com/CSSEGISandData/COVID-19.git");

            System.out.println(cmd.getRemote());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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

        //first line will be the entry names
        for (int i = 0; i < 18; i++) {
            System.out.print(reader.getNextValue() + ",");
        }

        String entry;
        while (true) {
            entry = reader.getNextValue();
            if(entry.equals("\n")) break; //if our reader returns null, then the reader closed its stream and nothing left is to be read

            //following code is a bit messy, but I dont know any better way to go at this
            Place p = new Place();
            p.setProvince_State(entry); //our first word is alread here, we needed to test if it was the end of the file
            p.setCountry_Region(reader.getNextValue());
            p.setLast_Update(reader.getNextValue());
            p.setLat(reader.getNextValue());
            p.setLong_(reader.getNextValue());
            p.setConfirmed(reader.getNextValue());
            p.setDeaths(reader.getNextValue());
            p.setRecovered(reader.getNextValue());
            p.setActive(reader.getNextValue());
            p.setFIPS(reader.getNextValue());
            p.setIncident_Rate(reader.getNextValue());
            p.setPeople_Tested(reader.getNextValue());
            p.setPeople_Hospitalized(reader.getNextValue());
            p.setMortality_Rate(reader.getNextValue());
            p.setUID(reader.getNextValue());
            p.setISO3(reader.getNextValue());
            p.setTesting_Rate(reader.getNextValue());
            p.setHospitalization_Rate(reader.getNextValue());
            //System.out.println(p.toString());

            areas.add(p);
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
                    System.out.println(sb.toString());
                    if (c == ',' || c == '\n') {
                        break;
                    }
                    if(value == -1) {
                        System.out.println("End of stream");
                        br.close();
                        return "\n";
                    }
                    sb. append(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

    }

}
