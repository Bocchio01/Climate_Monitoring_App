import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors

public class CreaFile {
    public static void main(String[] args) {
        try {

            File file00 = new File("OperatoriRegistrati.dati.txt");
            File file01 = new File("CentroMonitoraggio.dati.txt");
            File file02 = new File("ParametriClimatici.dati.txt");

            if (file00.createNewFile()) {
                System.out.println("Il file creato e': " + file00.getName());
            } else {
                System.out.println("Il file esiste gia'.");
            }

            if (file01.createNewFile()) {
                System.out.println("Il file creato e': " + file01.getName());
            } else {
                System.out.println("Il file esiste gia'.");
            }

            if (file02.createNewFile()) {
                System.out.println("Il file creato e': " + file02.getName());
            } else {
                System.out.println("Il file esiste gia'.");
            }
        } catch (IOException e) {
            System.out.println("Errore.");
            e.printStackTrace();
        }
    }
}
