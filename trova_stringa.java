import java.io.*;
import java.text.Normalizer;

public class trova_stringa {
    public static void main(String[] args)
            throws IOException {
        FileReader f;
        f = new FileReader("./CoordinateMonitoraggi.dati.csv");// "./Dati_abbreviati.csv"

        BufferedReader b;
        b = new BufferedReader(f);

        String s, ricerca = "sakcagoz", subs="";
        ricerca = Normalizer.normalize( ricerca, Normalizer.Form.NFD );

        char c;
        int q=0;

        for (int i = 0; i < ricerca.length(); i++) {
            c = ricerca.charAt(i);
            if(!Character.isLetter(c)){
                subs=ricerca.substring(q, i);
                ricerca=ricerca.substring(i+1);
                ricerca=subs+ricerca;
            }
        }
        //ricerca = ricerca.replaceAll("\\p{M}", "");
        ricerca = ricerca.toLowerCase();
        System.out.println(ricerca);
        

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
