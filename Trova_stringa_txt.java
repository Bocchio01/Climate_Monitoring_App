import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.FileInputStream;

public class Trova_stringa_txt {

    public static void main(String[] args) {

        // Apri il file txt in lettura
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./CoordinateMonitoraggi.dati.txt"), "UTF-8"));
        ) {

            // Leggi la parola inserita dall'utente
            Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            System.out.println("Inserisci la località da cercare:");
            String parola = scanner.nextLine();
            scanner.close();

            // Leggi il file riga per riga e cerca la parola in ogni riga
            String riga;
            while ((riga = bufferedReader.readLine()) != null) {
                String[] campi = riga.split(";");
                //System.out.println(riga);
                for (int i = 1; i < campi.length-2; i++) {
                    //System.out.println(campi[i]);
                    //System.out.println(parola.length());
                    if(parola.toLowerCase().contentEquals(campi[i].toLowerCase())){
                        System.out.println(riga);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
