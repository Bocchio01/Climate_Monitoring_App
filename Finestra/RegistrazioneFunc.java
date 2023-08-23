package Finestra;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrazioneFunc {

    public static void registrazione(JTextField nomeCognomeField, JTextField codiceFiscField, JTextField eMailField,
            JTextField userIdField, JPasswordField passwordField, JTextField centroMonField) {
        // Ottenere i valori inseriti dall'utente
        String nomeCognome = nomeCognomeField.getText();
        String codiceFisc = codiceFiscField.getText();
        String eMail = eMailField.getText();
        String userId = userIdField.getText();
        String password = new String(passwordField.getPassword());
        String centroMon = centroMonField.getText();

        if (nomeCognome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "inserisci un nome e cognome");
        } else if (codiceFisc.isEmpty()) {
            JOptionPane.showMessageDialog(null, "inserisci un codice fiscale");
        } else if (codiceFisc.length() <= 15) {
            JOptionPane.showMessageDialog(null, "inserisci un codice fiscale valido");
        } else if (eMail.isEmpty()) {
            JOptionPane.showMessageDialog(null, "inserisci una email");
        } else if (!eMail.contains("@")) {
            JOptionPane.showMessageDialog(null, "inserisci una email valida");
        } else if (userId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "inserisci un id");
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "inserisci una password");
        } else if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "la password deve essere lunga almeno 8 caratteri");
        } else if (!password.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(null, "la password deve contenere almeno una lettera maiuscola");
        } else {
            if (centroMon.isEmpty()) {
                centroMon = null;
            }

            // Creare una stringa con i dati inseriti
            String dati = nomeCognome + "," + codiceFisc.toUpperCase() + "," + eMail
                    + "," + userId + "," + password + "," + centroMon + "\n";

            // Salvare i dati su file txt
            try {
                FileWriter fileWriter = new FileWriter("OperatoriRegistrati.dati.txt", true);
                fileWriter.write(dati);
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Profilo registrato con successo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
