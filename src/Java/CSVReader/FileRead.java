package Java.CSVReader;

import java.io.*;

public class FileRead {

    public static String readFile(File f) throws NullPointerException {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(f));
            StringBuilder out = new StringBuilder();

            String temp;
            while((temp = br.readLine()) != null) {
                out.append(temp);
                out.append("\n");
            }
            return out.toString();
        } catch(FileNotFoundException fnfe) {
            System.out.println("File was not found");
        } catch (IOException ioe) {
            System.out.println("File could not be read");
        }
        //it should not get to here, but if it does, it will throw a NPE
        throw new NullPointerException();
    }
}
