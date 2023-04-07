
import java.io.*;

public class trova_stringa {
    public static void main(String[] args)
            throws IOException {
        FileReader f;
        f = new FileReader("./CoordinateMonitoraggi.dati.csv");// "./Dati_abbreviati.csv"

        BufferedReader b;
        b = new BufferedReader(f);

        String s, ricerca = "Parigi";
        ricerca = ricerca.toLowerCase();
        String[] riga = new String[7];
        int j = 0, m = 0, i = 0, l = 0;

        s = b.readLine() + ";";

        while (true) {
            s = b.readLine() + ";";
            l = s.length();
            for (j = 0; j < l; j++) {
                if (s.charAt(j) == ';') {
                    riga[i] = s.substring(m, j).toLowerCase();
                    m = j + 1;
                    i++;
                }
            }
            i = 0;
            m = 0;
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
