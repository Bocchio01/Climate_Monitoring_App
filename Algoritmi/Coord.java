package Algoritmi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import prog.io.ConsoleInputManager;

public class Coord {
    public static void main(String[] args) {
        ConsoleInputManager in = new ConsoleInputManager();

        String nomeFile = "CoordinateMonitoraggio.dati.csv";

        double inputLatitudine = in.readDouble("Inserisci la latitudine del luogo che vuoi trovare\n"); // latitudine di
                                                                                                        // input
        double inputLongitudine = in.readDouble("Inserisci la longitudine del luogo che vuoi trovare\n"); // longitudine
                                                                                                          // di input

        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {

            String str;
            String closestPaese = "";
            double closestDistanza = Double.POSITIVE_INFINITY;

            while ((str = br.readLine()) != null) {

                String[] paeseData = str.split(";");
                String countryName = paeseData[1];
                double latitudine = Double.parseDouble(paeseData[5]);
                double longitudine = Double.parseDouble(paeseData[6]);

                double distance = calcolaDistanza(inputLatitudine, inputLongitudine, latitudine, longitudine);

                if (distance < closestDistanza) {
                    closestPaese = countryName;
                    closestDistanza = distance;
                }
            }

            System.out.println("Il paese più vicino a (" + inputLatitudine + ", " + inputLongitudine
                    + ") è " + closestPaese + " a una distanza di " + closestDistanza + " km.");// stampa dei parametri
                                                                                                // del paese più vicino

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // calcolo della distanza ortodromica usando l'emisenoverso
    private static double calcolaDistanza(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // raggio terrestre medio in km
        double latDistanza = Math.toRadians(lat2 - lat1);
        double lonDistanza = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistanza / 2) * Math.sin(latDistanza / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistanza / 2) * Math.sin(lonDistanza / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double c2 = 2 * Math.asin(Math.sqrt(a));
        double distanza = R * c;
        double dist2 = R * c2;
        System.out.println(distanza + "\n" + dist2);
        return distanza;
    }

}
