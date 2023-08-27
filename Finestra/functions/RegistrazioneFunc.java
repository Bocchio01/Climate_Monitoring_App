package Finestra.functions;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrazioneFunc {

    public static boolean registrazione(String[] dataInserted) {

        String dati = String.join(",", dataInserted) + "\n";

        try {
            FileWriter fileWriter = new FileWriter("OperatoriRegistrati.dati.csv", true);
            fileWriter.write(dati);
            fileWriter.close();
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean checkRegisterInputs(String[] dataInserted) {

        String title = "Dato inserito non corretto", message = "";

        // String nomeCognomeField,
        // String codiceFiscField,
        // String eMailField,
        // String userIdField,
        // String passwordField,
        // String centroMonField
        if (dataInserted[0].isEmpty()) {
            message = "Inserisci un nome e cognome";

        } else if (dataInserted[1].isEmpty()) {
            message = "Inserisci un codice fiscale";

        } else if (!isValidFromRegex(dataInserted[1], "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$")) {
            message = "Inserisci un codice fiscale valido";

        } else if (dataInserted[2].isEmpty()) {
            message = "Inserisci una email";

        } else if (!isValidFromRegex(dataInserted[2], "^[A-Za-z0-9+_.-]+@(.+)$")) {
            message = "Email non corretta";

        } else if (dataInserted[3].isEmpty()) {
            message = "Inserisci un id";

        } else if (dataInserted[4].isEmpty()) {
            message = "Inserisci una password";

        } else if (!LoginFunc.login(dataInserted[3], dataInserted[4]).equals("")) {
            message = "L'utente esiste già. Cambiare ID utente e/o password";

        } else if (!isValidFromRegex(dataInserted[4], "^(?=.*[A-Z])(?=.*[@#$%^&+=!])(.{8,})$")) {
            message = "La password non ha il formato corretto.";

            // } else {
            // if (dataInserted[0].isEmpty()) {
            // dataInserted[2] = null;
            // }

        }

        if (!message.equals("")) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean isValidFromRegex(String email, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // return matcher.matches();
        return true;
    }

    // public static boolean
}
