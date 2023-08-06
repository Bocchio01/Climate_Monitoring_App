package Finestra;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.JTextField;

public class CercaFunc {

    // Ricerca per coordinate
    public static String coordFind(JTextField la, JTextField lo)
            throws IOException {

        FileReader f;
        f = new FileReader("./geonames-and-coordinates.csv", StandardCharsets.UTF_8);

        BufferedReader b;
        b = new BufferedReader(f);

        String s, string_latitude = la.getText(), string_longitude = lo.getText(), closest = "",
                nomeFileCSV = "../Ricerca.csv";
        String[] line_elements = new String[7];
        int j = 0, m = 0, i = 0, l = 0;
        double double_latitude = Double.parseDouble(string_latitude),
                double_longitude = Double.parseDouble(string_longitude);
        double data_latitude, data_longitude, distance = 400, temp_distance;

        s = b.readLine();

        while (true) {
            s = b.readLine();
            if (s == null)
                break;
            l = s.length();
            for (j = 0; j < l; j++) {
                if (s.charAt(j) == ';') {
                    line_elements[i] = s.substring(m, j).toLowerCase();
                    m = j + 1;
                    i++;
                }
            }
            i = 0;
            m = 0;
            line_elements[5] = line_elements[5].replace(',', '.');
            line_elements[6] = line_elements[6].replace(',', '.');
            data_latitude = Double.parseDouble(line_elements[5]);
            data_longitude = Double.parseDouble(line_elements[6]);
            temp_distance = Math.sqrt(
                    Math.pow(data_latitude - double_latitude, 2) + Math.pow(data_longitude - double_longitude, 2));
            if (distance > temp_distance) {
                distance = temp_distance;
                closest = s;
            }
            if (b.readLine() == null)
                break;
        }
        System.out.println(closest);
        l = closest.length();
        for (j = 0; j < l; j++) {
            if (closest.charAt(j) == ';') {
                line_elements[i] = closest.substring(m, j);
                m = j + 1;
                i++;
            }
        }
        Save_string.saveStringToCSV("Paese cercato: " + line_elements[2] + "\tStato: " + line_elements[4]
                + "\tCoordinate: " + line_elements[5] + ", " + line_elements[6], nomeFileCSV);
        f.close();
        return closest;
    }

    // Ricerca per nome
    public static String nameFind(JTextField città)
            throws IOException {

        FileReader f;
        f = new FileReader("./geonames-and-coordinates.csv", StandardCharsets.UTF_8);

        BufferedReader b;
        b = new BufferedReader(f);

        // !in terminale chcp 850
        // ? forse funge anche senza dipende dalla lingua ma i bytecode funzionana
        // quindi va bene

        Console console = System.console();
        if (console == null) {
            System.err.println("No console");
            System.exit(1);
        }
        // String ricerca = console.readLine("Che citta' vuoi cercare? %n");
        // System.out.format("ricerca: %s%n", ricerca);

        String s, ricerca, nomeFileCSV = "../Ricerca.csv";

        ricerca = città.getText();
        ricerca = ricerca.toLowerCase();
        String[] riga = new String[7];
        int j = 0, m = 0, i = 0, l = 0;

        System.out.println(ricerca);

        boolean find = false;

        s = b.readLine();

        while (find == false) {
            s = b.readLine();
            if (s == null)
                break;
            l = s.length();
            for (j = 0; j < l; j++) {
                if (s.charAt(j) == ';') {
                    riga[i] = s.substring(m, j).toLowerCase();
                    m = j + 1;
                    i++;
                }
            }
            i = 0;
            m = 0;
            // System.out.println(riga[0]);
            for (int n = 0; n < 7; n++) {
                if (riga[n].equals(ricerca)) {
                    Save_string.saveStringToCSV("Paese cercato: " + riga[2] + "\tStato: " + riga[4] + "\tCoordinate: "
                            + riga[5] + ", " + riga[6], nomeFileCSV);
                    System.out.println(s);
                    f.close();
                    return s;
                }
            }
        }
        f.close();
        return s;
    }

}
