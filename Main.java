import prog.io.*;

public class Main {
    public static void main(String[] args) {
        ConsoleInputManager in = new ConsoleInputManager();
        ConsoleOutputManager out = new ConsoleOutputManager();

        String zona;

        out.println("Inserisci 1 se sei un Utente o 2 se sei un Operatore");
        int scelta = in.readInt();
        if (scelta == 1) {
            out.println("Inserisci la zona che vuoi trovare");
            cercaAreaGeografica(zona);// da definire
            visulazziAreaGeografica(zona);// da definire

        } else if (scelta == 2) {
            out.println("Sei un nuovo operatore?");
            String azione = in.readLine();
            if (azione == "signup") {
                registrazione();// da definire
            } else if (azione == "login") {
                autenticazione();// da definire
            }

            out.println("Cosa vuole fare?");
            String azione2 = in.readLine();
            if (azione == "cercaarea") {
                out.println("Inserisci la zona che vuoi trovare");
                cercaAreaGeografica(zona);// da definire
                out.println("Vuoi inserire dei nuovi dati o modificarli?");
                String scelta2 = in.readLine();
                if (scelta2 == "si") {
                    inserisciParametriCLimatici(zona);
                } else if (scelta2 == "no") {
                    visulazziAreaGeografica(zona);// da definire

                }

            }

        }

    }

}
