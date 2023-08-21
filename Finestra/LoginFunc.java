package Finestra;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFunc {
    public static boolean login(JTextField idField, JTextField passwordField) throws IOException {
        String id = idField.getText();
        String password = passwordField.getText();
        boolean trovato = find2(id, password);
        return trovato;

    }

    public static boolean find2(String id, String password)
            throws IOException {
        boolean trovato = false;
        try (BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.dati.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] parole = linea.split(",");
                if (parole[3].trim().equals(id) && parole[4].trim().equals(password)) {

                    trovato = true;

                }

            }

        }
        return trovato;
    }

}
