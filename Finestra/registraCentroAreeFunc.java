package Finestra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class registraCentroAreeFunc {

    public static int registraCentroAree(JTextField nomeCentroField, JTextField piaField, JTextField numerocivField,
            JTextField capField, JTextField comunTextField, JTextField provField, JTextField nomeAreaField, int i) {
        int flag = 0;// flag che serve per capire se tutti i campi sono riempiti altrimenti il
                     // dispose funziona sempre
        String nomeCentro = nomeCentroField.getText();
        String pia = piaField.getText();
        String numeroCiv = numerocivField.getText();
        String cap = capField.getText();
        String comune = comunTextField.getText();
        String prov = provField.getText();
        String text = nomeAreaField.getText();
        String[] aree = text.split(",");
        for (int k = 0; k < aree.length; k++) {
            aree[k] = aree[k].trim();
        }

        if (nomeCentro.equals("Centro di Monitoraggio")) {
            JOptionPane.showMessageDialog(null, "Inserisci il Nome del Centro");
        } else if (pia.equals("Via/Piazza")) {
            JOptionPane.showMessageDialog(null, "Inserisci la Via/Piazza");
        } else if (numeroCiv.equals("Numero Civico")) {
            JOptionPane.showMessageDialog(null, "Inserisci il Numero Civico");
        } else if (cap.equals("CAP")) {
            JOptionPane.showMessageDialog(null, " Inserisci CAP");
        } else if (comune.equals("Comune")) {
            JOptionPane.showMessageDialog(null, " Inserisci Comune ");
        } else if (prov.equals("Provincia")) {
            JOptionPane.showMessageDialog(null, " Inserisci Provincia");
        } else if (text.equals("Area d'interesse")) {
            JOptionPane.showMessageDialog(null, " Inserisci le Aree di Interesse");
        } else {

            String dati = nomeCentro + "," + pia + "," + numeroCiv + "," + cap + "," + comune + "," + prov;
            dati = dati + "," + String.join(",", aree);
            // salva i dati del centro
            try {
                FileWriter fileWriter = new FileWriter("CentroMonitoraggio.dati.txt", true);
                fileWriter.write(dati + "\n");
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Registrazione del Centro avvenuta con successo!");
                flag = 1;
            } catch (IOException ex) {
                ex.printStackTrace();

            }
            // sovrascrive il nome del centro appena creato
            try {
                BufferedReader reader = new BufferedReader(new FileReader("OperatoriRegistrati.dati.txt"));
                StringBuilder fileContent = new StringBuilder();
                String line;
                int currentLine = 0;

                while ((line = reader.readLine()) != null) {
                    if (currentLine == i) {

                        System.out.println(nomeCentro);
                        line = line.replace("null", nomeCentro);
                        System.out.println(line);
                    }
                    fileContent.append(line).append("\n");
                    currentLine++;
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("OperatoriRegistrati.dati.txt"));
                writer.write(fileContent.toString());

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;

    }

}
