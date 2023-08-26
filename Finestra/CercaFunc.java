package Finestra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

public class CercaFunc {

    // Ricerca per coordinate
    public static String coordFind(String latitude, String longitude, String nomeFile, Integer posizioneLista) {
        try {

            String lineString, cittaSelected = null;

            double latInsert = Double.parseDouble(latitude.replace(',', '.')),
                    lonInsert = Double.parseDouble(longitude.replace(',', '.')),
                    actualMaxDistance = Integer.MAX_VALUE;
            Integer maxDistance = 400;

            FileReader fin = new FileReader(nomeFile, StandardCharsets.UTF_8);
            BufferedReader fbuffer = new BufferedReader(fin);

            while ((lineString = fbuffer.readLine()) != null) {
                String[] lineArray = lineString.split("[;]");

                try {
                    double latFile = Double.parseDouble(lineArray[5].replace(',', '.'));
                    double lonFile = Double.parseDouble(lineArray[6].replace(',', '.'));

                    double temp = Math.sqrt(Math.pow(latFile - latInsert, 2) + Math.pow(lonFile - lonInsert, 2));
                    if (temp < maxDistance && temp < actualMaxDistance) {
                        actualMaxDistance = temp;
                        cittaSelected = lineString;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

            fin.close();
            return cittaSelected.split("[;]")[posizioneLista];
        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }
    }

    // Ricerca per nome
    public static boolean nameFind(String città, String nomeFile, Integer posizioneLista) {
        try {
            String lineString, cityQuery = città.toLowerCase().trim();
            boolean findFlag = false;

            FileReader fin = new FileReader(nomeFile, StandardCharsets.UTF_8);
            BufferedReader fbuffer = new BufferedReader(fin);

            while (!findFlag && (lineString = fbuffer.readLine()) != null) {
                String[] lineArray = lineString.split("[,;]");
                findFlag = lineArray.length > posizioneLista
                        && lineArray[posizioneLista].toLowerCase().equals(cityQuery);
            }

            fin.close();
            return findFlag;

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
