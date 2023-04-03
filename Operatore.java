import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import prog.io.*;

public class Operatore {

    public String nomeCognome;
    public String codFiscale;
    public String eMail;
    public static int userID = 1;
    private String password;
    public String centroMonitoraggio;

    public Operatore() {

    }

    private static void registrazione(Operatore g) throws IOException {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        ConsoleInputManager in = new ConsoleInputManager();

        try {
            fw = new FileWriter("OperatoriRegistrati.dati.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);

            System.out.println("Inserisci il tuo nome e cognome");
            String nomeCognome = in.readLine();
            out.print(nomeCognome + "\t");
            System.out.println("Inserisci il tuo codice fiscale");
            String codFiscale = in.readLine();
            out.print(codFiscale + "\t");
            System.out.println("Inserisci la tua e-mail");
            String eMail = in.readLine();
            out.print(eMail + "\t");
            System.out.println("Inserisci la tua password lunga almeno 8 caratteri");
            String password = in.readLine();
            while (password.length() < 8) {
                System.out.println("Password troppo corta, reinseriscila");
                password = in.readLine();
            }
            out.print(password + "\t");

            System.out.println("Inserisci, se vuoi, il tuo centro di Monitoraggio di appartenenza");
            String centroMonitoraggio = in.readLine();
            if (centroMonitoraggio != null)
                out.print(centroMonitoraggio + "\t");

            int userID = codFiscale.charAt(0) + codFiscale.charAt(1) + codFiscale.charAt(2);
            System.out.println("Registrazione completata. Accedi all'App usando questo userID: " + userID
                    + " e la tua password");
            out.print(userID);
            out.println();

            out.close();

        } catch (IOException e) {

        } finally {
            if (out != null)
                out.close();

        }

    }

    public static void main(String[] args) throws IOException {

        Operatore g1 = new Operatore();
        registrazione(g1);
    }
}
