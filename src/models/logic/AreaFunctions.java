package src.models.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import src.models.record.OperatorRecord;
import src.utils.ENV;

public class AreaFunctions {

    public static boolean initNewArea(String[] datiInseriti) {

        // OperatorRecord operator = LogicOperator.;
        

        if (!checkInputRegister(datiInseriti) || isAreaExists(datiInseriti[0])
                || OperatorFunctions.getCurrentUserArea() != null) {
            return false;
        }

        if (OperatorFunctions.updateUserData(ENV.Index.AREA, datiInseriti[0])) {
            ArrayList<Integer> cityIDs = new ArrayList<>();

            for (String cityName : datiInseriti[6].split(",")) {
                try {
                    Integer cityID = getCityID(cityName.trim());
                    cityIDs.add(cityID);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            if (cityIDs.size() > 0) {

                String[] cityIDStrings = new String[cityIDs.size()];

                for (int i = 0; i < cityIDs.size(); i++) {
                    cityIDStrings[i] = String.valueOf(cityIDs.get(i));
                }

                return appendToFile(
                        String.join(
                                ENV.CSV_SEPARATOR,
                                new String[] {
                                        datiInseriti[0],
                                        datiInseriti[1],
                                        datiInseriti[2],
                                        datiInseriti[3],
                                        datiInseriti[4],
                                        datiInseriti[5] })
                                + ENV.CSV_SEPARATOR
                                + String.join(
                                        ",",
                                        cityIDStrings)
                                + ENV.CSV_SEPARATOR
                                + "\n",
                        ENV.Path.Files.AREA);
            }
        }

        return false;

    }

    public static boolean saveScoresToFile(
            Integer cityID,
            String nameArea,
            String date,
            Object[][] tableDatas) {

        try {
            FileWriter fout = new FileWriter(ENV.Path.Files.WEATHER, true);
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
