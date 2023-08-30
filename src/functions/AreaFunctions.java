package src.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import src.utils.ENV;

public class AreaFunctions {

    public static boolean initNewArea(String[] datiInseriti) {

        if (!checkInputRegister(datiInseriti) || isAreaExists(datiInseriti[0])
                || OperatorFunctions.getCurrentUserArea() != null) {
            return false;
        }

        if (OperatorFunctions.updateUserData(ENV.Index.AREA, datiInseriti[0]) &&
                appendToFile(String.join(ENV.CSV_SEPARATOR, datiInseriti) + "\n",
                        ENV.Path.Files.AREA_DATA)) {

            return true;
        }

        return false;

    }

    public static boolean saveScoresToFile(
            Integer cityID,
            String nameArea,
            String date,
            Object[][] tableDatas) {

        try {
            FileWriter fout = new FileWriter(ENV.Path.Files.CITY_DATAS, true);
            BufferedWriter wfbuffer = new BufferedWriter(fout);

            wfbuffer.write(String.join(
                    ENV.CSV_SEPARATOR,
                    new String[] {
                            cityID.toString(),
                            nameArea,
                            date }));
            wfbuffer.write(ENV.CSV_SEPARATOR);

            for (Object[] row : tableDatas) {
                wfbuffer.write(String.join(
                        ",",
                        new String[] {
                                row[0] != null ? row[0].toString() : ENV.EMPTY_STRING,
                                row[1] != null ? (String) row[1] : ENV.EMPTY_STRING }));
                wfbuffer.write(ENV.CSV_SEPARATOR);
            }

            wfbuffer.newLine();
            wfbuffer.close();

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public static boolean appendToFile(String newLine, String fileName) {

        try {
            FileWriter fout = new FileWriter(fileName, true);

            fout.write(newLine);
            fout.close();

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }

    public static boolean checkInputRegister(String[] datiInseriti) {

        for (String data : datiInseriti) {
            if (data == null)
                return false;
        }

        return true;

    }

    public static boolean isAreaExists(String areaName) {

        try {
            FileReader fin = new FileReader(ENV.Path.Files.AREA_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                if (line.split(ENV.CSV_SEPARATOR)[0].equals(areaName)) {
                    rfbuffer.close();
                    return true;
                }
            }

            rfbuffer.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    public static Integer[] getCityIDInArea(String areaName) {

        ArrayList<Integer> cityIDs = new ArrayList<>();

        try {
            FileReader fin = new FileReader(ENV.Path.Files.AREA_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] lineSplitted = line.split(ENV.CSV_SEPARATOR);

                if (lineSplitted[0].equals(areaName)) {
                    rfbuffer.close();

                    for (int i = 5; i < lineSplitted.length; i++) {
                        try {
                            Integer cityID = Integer.parseInt(lineSplitted[i]);
                            cityIDs.add(cityID);
                        } catch (NumberFormatException e) {
                            // TODO: handle exception
                        }
                    }

                }
            }

            rfbuffer.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        return cityIDs.toArray(new Integer[cityIDs.size()]);

    }

    public static String getCityName(Integer cityID) {

        try {
            FileReader fin = new FileReader(ENV.Path.Files.CITY_COORDS);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] lineSplitted = line.split(ENV.CSV_SEPARATOR);

                if (lineSplitted[0].equals(cityID.toString())) {
                    rfbuffer.close();

                    return lineSplitted[1];
                }
            }

            rfbuffer.close();
            throw new Exception("ID errato");

        } catch (Exception e) {
            // TODO: handle exception
        }

        return "";
    }

    public static Integer getCityID(String cityName) {

        try {
            FileReader fin = new FileReader(ENV.Path.Files.CITY_COORDS);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                String[] lineSplitted = line.split(ENV.CSV_SEPARATOR);

                if (lineSplitted[1].equals(cityName)) {
                    rfbuffer.close();

                    return Integer.parseInt(lineSplitted[0]);
                }
            }

            rfbuffer.close();
            throw new Exception("Nome città errato");

        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    public static boolean isDateValid(String dateString, String datePattern) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
            LocalDate date = LocalDate.parse(dateString, dateFormatter);

            return !date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
