import java.io.*;
import java.nio.charset.*;
import prog.io.*;

public class trova_stringa {
    public static void main(String[] args) 
    throws IOException {

        ConsoleInputManager in = new ConsoleInputManager();

        FileReader f;
        f=new FileReader("./CoordinateMonitoraggi.dati.csv");//"./Dati_abbreviati.csv"
    
        BufferedReader b;
        b=new BufferedReader(f);

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }
        String command = console.readLine("Enter command: %n");
        System.out.format("command: %s%n", command);
        if (command.equals("Č")) {
            System.out.println("Test was successful!");
        }
    
        String s, ricerca="milan";
        //ricerca = in.readLine("Che città vuoi cercare? ");
        //Reader input = new InputStreamReader(, "UTF-8");
        command = command.toLowerCase();
        String[] riga = new String[7];
        int j=0, m=0, i=0, l=0;

        //byte[] originalBytes = ricerca.getBytes(StandardCharsets.ISO_8859_1);

        //ricerca = new String(originalBytes, StandardCharsets.UTF_8);

        System.out.println(command);

        boolean find=false;

        s=b.readLine()+";";
        
        while (find==false) {
            s=b.readLine()+";";
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
            for (int n = 0; n < 7; n++) {
                if (riga[n].equals(command)) {
                    System.out.println(s);
                    find=true;
                    break;
                }
            }
            if(b.readLine()==null) break;
        }
      }
}
