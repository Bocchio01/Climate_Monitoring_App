import java.io.Console;

import prog.io.*;

public class Operatore {

    public String nomeCognome;
    public String codFiscale;
    public String eMail;
    public static int userID = 1;
    private String password;
    public String centroMonitoraggio;

    public Operatore(String nomeCognome, String codFiscale, String eMail, int userID, String password,
            String centroMonitoraggio) {

        this.nomeCognome = nomeCognome;
        this.codFiscale = codFiscale;
        this.eMail = eMail;
        Operatore.userID = userID++;
        this.password = password;
        this.centroMonitoraggio = centroMonitoraggio;

    }

    private void registrazione() {
        ConsoleInputManager in = new ConsoleInputManager();
        System.out.println("Inserisci il tuo nome e cognome");
        String nomeCognome = in.readLine();
        System.out.println("Inserisci il tuo codice fiscale");
        String codFiscale = in.readLine();
        System.out.println("Inserisci la tua e-mail");
        String eMail = in.readLine();
        System.out.println("Inserisci la tua password lunga almeno 8 caratteri");
        String password = in.readLine();
        while (password.length() < 7) {
            System.out.println("Password troppo corta, reinseriscila");
            password = in.readLine();
        }
        System.out.println("Inserisci, se vuoi, il tuo centro di Monitoraggio di appartenenza");
        String centroMonitoraggio = in.readLine();
        int userID = codFiscale.charAt(0) + codFiscale.charAt(1) + codFiscale.charAt(2);
        System.out.println("Registrazione completata. Accedi all'App usando questo userID: " + Operatore.userID
                + " e la tua password");

    }
}
