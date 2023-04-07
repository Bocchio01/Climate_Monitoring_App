import java.io.*;
import java.text.Normalizer;
import prog.io.ConsoleInputManager;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class trova_stringa {
    public static void main(String[] args)
    throws IOException{

        ConsoleInputManager in = new ConsoleInputManager();

        System.setIn(new java.io.BufferedInputStream(System.in));
        System.setOut(new java.io.PrintStream(System.out, true, StandardCharsets.ISO_8859_1));

        Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.ISO_8859_1));

        FileReader f;
        f = new FileReader("./CoordinateMonitoraggi.dati.csv");// "./Dati_abbreviati.csv"

        BufferedReader b;
        b = new BufferedReader(f);

        String s, ricerca, subs="";
        System.out.print("Inserisci una parola con caratteri accentati: ");
        ricerca = scanner.nextLine();
        ricerca = Normalizer.normalize( ricerca, Normalizer.Form.NFC );
        System.out.println(ricerca);

        char c;
        int q=0;

        for (int i = 0; i < ricerca.length(); i++) {
            c = ricerca.charAt(i);
            if(!Character.isLetter(c)){
                subs=ricerca.substring(q, i);
                ricerca=ricerca.substring(i);
                ricerca=subs+ricerca;
            }
        }
        ricerca = ricerca.toLowerCase();
        System.out.println(ricerca);
        

        String[] riga = new String[7];
        int j = 0, m = 0, i = 0, l = 0;

        s = b.readLine() + "\t";

        while (true) {
            s = b.readLine() + "\t";
            l = s.length();
            for (j = 0; j < l; j++) {
                if (s.charAt(j) == '\t') {
                    riga[i] = s.substring(m, j).toLowerCase();
                    m = j + 1;
                    i++;
                }
            }
            i = 0;
            m = 0;
            for (int n = 1; n < 5; n++) {
                if (riga[n].equals(ricerca)) {
                    System.out.println(s);
                    break;
                }
            }
            if (b.readLine() == null)
                break;
        }
    }
}
