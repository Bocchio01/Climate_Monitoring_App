package models.file;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import utils.Constants;

public class FileHandler {

    public FileHandler() {
        try {
            initFile(Constants.Path.Files.AREA,
                    new String[] {
                            "Area ID",
                            "Area Name",
                            "Street Name",
                            "Street Number",
                            "CAP",
                            "Town Name",
                            "District Name",
                            "City IDs" });

            initFile(Constants.Path.Files.WEATHER,
                    new String[] {
                            "Record ID",
                            "City ID",
                            "Area ID",
                            "Date",
                            "Wind",
                            "Humidity",
                            "Pressure",
                            "Temperature",
                            "Precipitation",
                            "Glacier elevation",
                            "Mass of glaciers" });

            initFile(Constants.Path.Files.CITY,
                    new String[] {
                            "City ID",
                            "Name",
                            "ASCII Name",
                            "Country Code",
                            "Country Name",
                            "Latitude",
                            "Longitude" });

            initFile(Constants.Path.Files.OPERATOR,
                    new String[] {
                            "Operator ID",
                            "Name Surname",
                            "Tax code",
                            "Email",
                            "Username",
                            "Password",
                            "Area ID" });

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    private static void initFile(String filePath, String[] fileHeaders) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();

            FileWriter writer = new FileWriter(file);
            writer.write(String.join(Constants.CSV_SEPARATOR, fileHeaders) + "\n");
            writer.close();
        }
    }

    public static List<String> readFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static void writeFile(String filePath, List<String> lines) throws IOException {
        Files.write(Paths.get(filePath), lines);
    }

    public static void appendToFile(String filePath, String newLine) throws IOException {
        FileWriter fout = new FileWriter(filePath, true);
        BufferedWriter wfbuffer = new BufferedWriter(fout);

        wfbuffer.write(newLine);
        wfbuffer.newLine();
        wfbuffer.close();
    }

}
