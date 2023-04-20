import java.io.*;
import prog.io.ConsoleInputManager;
public class trova_stringa {

    public static void main(String[] args) throws IOException {

        ConsoleInputManager in = new ConsoleInputManager();

        FileReader f;
        f = new FileReader("./geonames-and-coordinates.csv");// "./Dati_abbreviati.csv"

        BufferedReader b;
        b = new BufferedReader(f);

        b=new BufferedReader(f);

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }
        String ricerca = console.readLine("Enter ricerca: %n");
        System.out.format("ricerca: %s%n", ricerca);
        if (ricerca.equals("Č")) {
            System.out.println("Test was successful!");
        }
    
        String s;
        //ricerca = in.readLine("Che città vuoi cercare? ");
        //Reader input = new InputStreamReader(, "UTF-8");
        ricerca = ricerca.toLowerCase();
        String[] riga = new String[7];
        int j=0, m=0, i=0, l=0;

        //byte[] originalBytes = ricerca.getBytes(StandardCharsets.ISO_8859_1);

        //ricerca = new String(originalBytes, StandardCharsets.UTF_8);

        System.out.println(ricerca);

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
            i=0;
            m=0;
            for (int n = 0; n < 7; n++) {
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
