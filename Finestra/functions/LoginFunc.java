package Finestra.functions;

import java.io.BufferedReader;
import java.io.FileReader;

public class LoginFunc {
    public static String login(String id, String password) {

        String line;

        try {
            FileReader fin = new FileReader("./OperatoriRegistrati.dati.csv");
            BufferedReader fbuffer = new BufferedReader(fin);

            while ((line = fbuffer.readLine()) != null) {
                String[] parole = line.split("[,;]");

                if (parole[3].trim().equals(id) && parole[4].trim().equals(password)) {
                    fbuffer.close();
                    return line;
                }

            }

            fbuffer.close();
            return "";

        } catch (Exception e) {
            // TODO: handle exception
            return "";
        }

    }

}