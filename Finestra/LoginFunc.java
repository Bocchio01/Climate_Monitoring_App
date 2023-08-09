package Finestra;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LoginFunc {
    public static void login(JTextField idField, JTextField passwordField) throws IOException {
        String id = idField.getText();
        String password = passwordField.getText();
        find2(id, password);

    }

    public static void find2(String id, String password)
            throws IOException {

        FileReader f;
        f = new FileReader("./OperatoriRegistrati.dati.txt", StandardCharsets.UTF_8);

        BufferedReader b;
        b = new BufferedReader(f);

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }

        String s;

        String[] riga = new String[6];
        int j = 0, m = 0, i = 0, l = 0;

        boolean find = false;

        s = b.readLine();

        while (find == false) {
            s = b.readLine();
            if (s == null)
                break;
            l = s.length();
            for (j = 0; j < l; j++) {
                if (s.charAt(j) == ',') {
                    riga[i] = s.substring(m, j);
                    m = j + 1;
                    i++;
                }
            }
            i = 0;
            m = 0;
            if (riga[3] == id && riga[4] == password)

                SetFrameFunc.setFrame(new Data());

        }
        f.close();

    }
}
