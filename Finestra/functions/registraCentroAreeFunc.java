package Finestra.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class registraCentroAreeFunc {

    public static boolean registraCentroAree(String[] datiInseriti, String[] userData) {

        boolean flag = false;
        String[] datiPuliti = {
                datiInseriti[0],
                datiInseriti[1],
                datiInseriti[2],
                datiInseriti[3],
                datiInseriti[4],
                datiInseriti[5]
        };
        String[] aree = datiInseriti[6].split(",");
        for (int k = 0; k < aree.length; k++) {
            aree[k] = aree[k].trim();
        }

        String dati;
        dati = String.join(",", datiPuliti);
        dati = dati + String.join(",", aree) + "\n";

        try {
            FileWriter fout = new FileWriter("CentroMonitoraggio.dati.csv", true);
            fout.write(dati);
            fout.close();

            JOptionPane.showMessageDialog(null, "Registrazione del Centro avvenuta con successo!");
            flag = true;

        } catch (Exception ex) {
            // TODO
            ex.printStackTrace();
        }

        try {
            FileReader fin = new FileReader("OperatoriRegistrati.dati.csv");
            BufferedReader fbuffer = new BufferedReader(fin);

            StringBuilder fileContent = new StringBuilder();
            String line;
            String userString = String.join(",", userData);

            while ((line = fbuffer.readLine()) != null) {
                if (line.equals(userString)) {
                    String newUseString = line.replace("null", datiInseriti[0]);
                    fileContent.append(newUseString).append("\n");
                    break;
                }
                fileContent.append(line).append("\n");
            }

            fin.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter("OperatoriRegistrati.dati.csv", false));
            writer.write(fileContent.toString());
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;

    }

    public static boolean checkInputRegister(String[] datiInseriti) {

        boolean flag = false;

        if (datiInseriti[0].equals("Centro di Monitoraggio")) {
            JOptionPane.showMessageDialog(null, "Inserisci il Nome del Centro");
        } else if (datiInseriti[1].equals("Via/Piazza")) {
            JOptionPane.showMessageDialog(null, "Inserisci la Via/Piazza");
        } else if (datiInseriti[2].equals("Numero Civico")) {
            JOptionPane.showMessageDialog(null, "Inserisci il Numero Civico");
        } else if (datiInseriti[3].equals("CAP")) {
            JOptionPane.showMessageDialog(null, " Inserisci CAP");
        } else if (datiInseriti[4].equals("Comune")) {
            JOptionPane.showMessageDialog(null, " Inserisci Comune ");
        } else if (datiInseriti[5].equals("Provincia")) {
            JOptionPane.showMessageDialog(null, " Inserisci Provincia");
        } else if (datiInseriti[6].equals("Area d'interesse")) {
            JOptionPane.showMessageDialog(null, " Inserisci le Aree di Interesse");
        } else {
            flag = true;
        }

        return flag;
    }

}
