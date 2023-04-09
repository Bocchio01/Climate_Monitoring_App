
import java.io.*;
import java.util.*;
import prog.io.ConsoleInputManager;
import java.text.Normalizer;
import java.nio.charset.StandardCharsets;

class AlgRicerca {
    public static void main(String[] args) {

        ConsoleInputManager in = new ConsoleInputManager();
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.ISO_8859_1));
        Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

        String nomeFile = "CoordinateMonitoraggi.dati.csv";
        System.out.println("Inserisci il luogo che vuoi visualizzare" + "\n");
        String ricerca = scanner.nextLine();
        ricerca = Normalizer.normalize(ricerca, Normalizer.Form.NFC);

        char c;
        int q = 0;
        String subs;

        for (int i = 0; i < ricerca.length(); i++) {
            c = ricerca.charAt(i);
            if (!Character.isLetter(c)) {
                subs = ricerca.substring(q, i);
                ricerca = ricerca.substring(i);
                ricerca = subs + ricerca;
            }
        }

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
            if (campi[1].toLowerCase().equals(ricerca.toLowerCase())
                    || campi[2].toLowerCase().equals(ricerca.toLowerCase())) {

                System.out.println("ID: " + campi[0] + "\n" + "Nome: " + campi[1] + "\n" + "Nome ASCII: " + campi[2]
                        + "\n" + "Codice Paese: " + campi[3] + "\n" + "Nome Paese: " + campi[4] + "\n" + "Latitudine: "
                        + campi[5] + "\n" + "Longitudine: " + campi[6] + "\n");

            }
        }

    }
}