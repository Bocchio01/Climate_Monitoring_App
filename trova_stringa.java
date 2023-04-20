import java.io.*;
import java.nio.charset.StandardCharsets;

public class trova_stringa {
    public static void main(String[] args) 
    throws IOException {

        FileReader f;
        f=new FileReader("./geonames-and-coordinates.csv", StandardCharsets.UTF_8);
    
        BufferedReader b;
        b=new BufferedReader(f);

        //!in terminale chcp 850
        //? forse funge anche senza

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }
        String command = console.readLine("Che citta' vuoi cercare? %n");
        //System.out.format("command: %s%n", command);

        String s;

        command = command.toLowerCase();
        String[] riga = new String[7];
        int j=0, m=0, i=0, l=0;

        System.out.println(command);

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
                if (riga[n].equals(command)) {
                    System.out.println(s);
                }
            }
        }
    }
}
