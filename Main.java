import prog.io.*;

public class Main extends Operatore {
    public static void main(String[] args) {
        ConsoleInputManager in = new ConsoleInputManager();
        ConsoleOutputManager out = new ConsoleOutputManager();

        String zona;

        out.println("Inserisci 1 se sei un Utente o 2 se sei un Operatore");
        char scelta = in.readChar();
        if (scelta == 1) {
            out.println("Inserisci la zona che vuoi trovare");
            cercaAreaGeografica(zona);// TODO definire il metodo
            visulazziAreaGeografica(zona);// TODO definire il metodo

        } else if (scelta == 2) {
            out.println("Sei un nuovo operatore?");
            String azione = in.readLine();
            if (azione == "si") {// signup
                registrazione();
            } else if (azione == "no") {// login
                autenticazione();// TODO implementare la ricerca di stringhe per le credenziali
            }

            out.println("Cosa vuole fare?");
            String azione2 = in.readLine();
            if (azione == cercaarea) {
                out.println("Inserisci la zona che vuoi trovare");
                cercaAreaGeografica(zona);// TODO metodo da definire
                out.println("Vuoi inserire dei nuovi dati o modificarli?");
                String scelta2 = in.readLine();
                if (scelta2 == "si") {
                    inserisciParametriCLimatici(zona);// TODO metodo da definire
                } else if (scelta2 == "no") {
                    visulazziAreaGeografica(zona);// TODO metodo da definire

                }

            }

        }

    }

}
