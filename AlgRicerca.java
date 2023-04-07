
import java.io.*;
import java.util.*;
import prog.io.ConsoleInputManager;

class AlgRicerca {
    public static void main(String[] args) {
        ConsoleInputManager in = new ConsoleInputManager();
        PrintWriter print = new PrintWriter(System.out, true);
        String nomeFile = "CoordinateMonitoraggi.dati.csv";
        String ricerca = in.readLine("Inserisci il luogo che vuoi visualizzare" + "\n");

        List<String[]> data = new ArrayList<>();// lista per memorizzare i dati del file
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {

            String str;
            while ((str = br.readLine()) != null) {
                String[] campi = str.split(";");// divido la riga in base al punto e virgola
                data.add(campi);// aggiungo i dati alla lista

            }

        } catch (IOException e) {
            e.printStackTrace();
            return;// se il file non esiste o ci sono errori nella lettura, esco dal programma
        }

        for (String[] campi : data) {
            if (campi[1].toLowerCase().equals(ricerca.toLowerCase())) {
                print.write("ID: " + campi[0] + "\n" + "Nome: " + campi[1] + "\n" + "Nome ASCII: " + campi[2]
                        + "\n" + "Codice Paese: " + campi[3] + "\n" + "Nome Paese: " + campi[4] + "\n" + "Latitudine: "
                        + campi[5] + "\n" + "Longitudine: " + campi[6] + "\n");
                print.close();

            }
        }

    }
}