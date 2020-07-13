package Java.company;

import Java.CSVReader.FileRead;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> dateStamps = new ArrayList<String>();
    static ArrayList<Place> current = new ArrayList<>(50);
    static Map<String, String> map = new HashMap<String, String>(); //assigns each csv to its time stamp

    //String = date
    static HashMap<String, ArrayList<Place>> history = new HashMap<String, ArrayList<Place>>();

    public static void main(String[] args) throws IOException {
        //updateRepo();
        readFile();
        assignPlaces();


        //Methods returning a string will always have a newline on it
        String dates = getDates();
        StringBuilder data = new StringBuilder();

        for (Place i : current) {
            data.append(getHistory(i.getProvince_State()));
        }
        writeToFile("CountryWideCases.csv", dates + data);

        /*
        String data = getHistory("California");
        data += getHistory("Florida");
        data += getHistory("New York");
        data += getHistory("Nevada");
        writeToFile("StateHistory.csv", dates + data);

         */


    }

    static void updateRepo() {
        //TODO properly update the Repo
        /*
        File f = new File("RAW data");
        if(f.isDirectory()) {
            File test = new File
        }
        */
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
        File dir = new File("RAW data/csse_covid_19_data/csse_covid_19_daily_reports_us");

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
        for (int i = dateStamps.size() - 1; i >= 0; i--) {
            String date = dateStamps.get(i);
            String buffer = map.get(date);
            //System.out.println(date);


            CSVReader reader = new CSVReader(buffer);

            //first line will be the entry names
            for (int a = 0; a < 18; a++) {
                String foo = reader.getNextValue();
                //System.out.print(foo + ",");
            }

            String entry;
            ArrayList<Place> areas = new ArrayList<Place>(58);
            while (true) {
                entry = reader.getNextValue();
                if (entry.equals("\n"))
                    break; //if our reader returns null, then the reader closed its stream and nothing left is to be read

                //following code is a bit messy, but I dont know any better way to go at this
                Place p = new Place();
                p.setFile(date);
                p.setProvince_State(entry); //our first word is already here, we needed to test if it was the end of the file
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

                areas.add(p);
            }

            history.put(date, areas);
            areas = new ArrayList<Place>();
        }

        //assigning current
        String d = dateStamps.get(dateStamps.size() - 1);
        current= history.get(d);

        /*
        //checking if history is correct
        for (int i = 0; i < history.size(); i++) {
            //System.out.println(i);
            ArrayList<Place> list = history.get(dateStamps.get(i));
            for (Place r : list) {
                System.out.println(r.getProvince_State());
            }
        }
         */
    }

    static Place[] retrieveHistoricData(String state) {
        Place[] stateHistory = new Place[dateStamps.size()];
        for (int i = 0; i < dateStamps.size(); i++) {
            ArrayList<Place> list = history.get(dateStamps.get(i));
            for (int s = 0; s < list.size(); s++) {
                Place p = list.get(s);
                if (state.equals(p.getProvince_State())) {
                    stateHistory[i] = p;
                }
            }
        }
        return stateHistory;
    }

    static String constructCSV(Place... data) {
        StringBuilder secondLine = new StringBuilder();
        secondLine.append(data[0].getConfirmed());
        for (int i = 1; i < data.length; i++) {
            secondLine.append("," + data[i].getConfirmed());
        }
        return secondLine.toString() + "\n";
    }

    static String getHistory(String state) {
        Place[] historicData = retrieveHistoricData(state);
        return state + "," + constructCSV(historicData);
    }

    static String getDates() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dateStamps.size(); i++) {
            sb.append("," + dateStamps.get(i));
        }
        return sb.toString() + "\n";
    }

    static void writeToFile(String title, String data) {
        File f = new File("CSVout");
        f.mkdir();
        File nf = new File("CSVout\\" + title);
        try {
            FileWriter fw = new FileWriter(nf);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
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
                    if (c == ',' || c == '\n') {
                        break;
                    }
                    if(value == -1) {
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
