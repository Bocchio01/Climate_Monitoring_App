package Finestra;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Find_string {

    public static String find(JTextField città) 
    throws IOException {

        FileReader f;
        f=new FileReader("./geonames-and-coordinates.csv", StandardCharsets.UTF_8);
    
        BufferedReader b;
        b=new BufferedReader(f);

        //!in terminale chcp 850
        //? forse funge anche senza     dipende dalla lingua ma i bytecode funzionana quindi va bene

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }
        //String ricerca = console.readLine("Che citta' vuoi cercare? %n");
        //System.out.format("ricerca: %s%n", ricerca);

        String s, ricerca;

        ricerca = città.getText();
        ricerca = ricerca.toLowerCase();
        String[] riga = new String[7];
        int j=0, m=0, i=0, l=0;

        System.out.println(ricerca);

        boolean find=false;

        s=b.readLine();
        
        while (find==false) {
            s=b.readLine();
            if(s==null) break;
            l=s.length();
            for (j = 0; j < l; j++) {
                if(s.charAt(j)==';'){
                    riga[i] = s.substring(m,j).toLowerCase();
                    m=j+1;
                    i++;
                }
            }
            i=0;
            m=0;
            //System.out.println(riga[0]);
            for (int n = 0; n < 7; n++) {
                if (riga[n].equals(ricerca)) {
                    System.out.println(s);
                    f.close();
                    return s;
                }
            }
        }
        f.close();
        return s;
    }
}
