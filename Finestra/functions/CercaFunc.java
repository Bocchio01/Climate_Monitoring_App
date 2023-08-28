package Finestra.functions;

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
    public static boolean nameFind(String citta, String nomeFile, Integer posizioneLista) {
        try {
            String lineString, cityQuery = citta.toLowerCase().trim();
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

    // private static double calcolaDistanza(double lat1, double lon1, double lat2,
    // double lon2) {
    // int R = 6371; // raggio terrestre medio in km
    // double latDistanza = Math.toRadians(lat2 - lat1);
    // double lonDistanza = Math.toRadians(lon2 - lon1);
    // double a = Math.sin(latDistanza / 2) * Math.sin(latDistanza / 2)
    // + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
    // * Math.sin(lonDistanza / 2) * Math.sin(lonDistanza / 2);
    // double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    // double c2 = 2 * Math.asin(Math.sqrt(a));
    // double distanza = R * c;
    // double dist2 = R * c2;
    // System.out.println(distanza + "\n" + dist2);
    // return distanza;
    // }
}
