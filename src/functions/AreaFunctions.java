package src.functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import src.utils.AppConstants;

public class AreaFunctions {

    public static boolean initNewArea(String[] datiInseriti) {

        if (!checkInputRegister(datiInseriti) || isAreaExists(datiInseriti[0]) || OperatorFunctions.getCurrentUserArea() != null) {
            return false;
        }

        if (OperatorFunctions.updateUserData(AppConstants.Index.AREA, datiInseriti[0]) &&
                appendToFile(String.join(",", datiInseriti) + "\n", AppConstants.Path.Files.AREA_DATA)) {

            return true;
        }

        return false;

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
            FileReader fin = new FileReader(AppConstants.Path.Files.AREA_DATA);
            BufferedReader rfbuffer = new BufferedReader(fin);
            String line;

            while ((line = rfbuffer.readLine()) != null) {
                if (line.split(",")[0].equals(areaName)) {
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

}
