package Finestra;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginFunc {
    public static int[] login(JTextField idField, JTextField passwordField) throws IOException {
        String id = idField.getText();
        String password = passwordField.getText();
        int[] trovato = find2(id, password);
        return trovato;

    }

    public static int[] find2(String id, String password)
            throws IOException {
        int trovato = 0;
        int[] valori;
        try (BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.dati.txt"))) {

            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                String[] parole = linea.split(",");
                if (parole[3].trim().equals(id) && parole[4].trim().equals(password)) {

                    trovato = 1;
                    break;
                }
                count++;

            }
            valori = new int[2];
            valori[0] = trovato;
            valori[1] = count;

        }
        return valori;

    }

}