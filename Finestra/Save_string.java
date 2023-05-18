package Finestra;
import java.io.FileWriter;
import java.io.IOException;

public class Save_string {
    public static void saveStringToCSV(String stringa, String nomeFile) {
        try {
            FileWriter writer = new FileWriter(nomeFile);
            writer.append(stringa);
            writer.flush();
            writer.close();
            System.out.println("Stringa salvata correttamente nel file CSV.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante il salvataggio della stringa nel file CSV.");
            e.printStackTrace();
        }
    }
    
}
