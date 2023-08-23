package Finestra;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class registraCentroAreeFunc {

    public static void registraCentroAree(JTextField nomeCentroField, JTextField piaField, JTextField numerocivField,
            JTextField capField, JTextField comunTextField, JTextField provField, JTextField nomeAreaField) {

        String nomeCentro = nomeCentroField.getText();
        String pia = piaField.getText();
        String numeroCiv = numerocivField.getText();
        String cap = capField.getText();
        String comune = comunTextField.getText();
        String prov = provField.getText();
        String text = nomeAreaField.getText();
        String[] aree = text.split(",");
        for (int i = 0; i < aree.length; i++) {
            aree[i] = aree[i].trim();
        }

        if (nomeCentro.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci il Nome del Centro");
        } else if (pia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci la Via/Piazza");
        } else if (numeroCiv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inserisci il Numero Civico");
        } else if (cap.isEmpty()) {
            JOptionPane.showMessageDialog(null, " Inserisci CAP");
        } else if (comune.isEmpty()) {
            JOptionPane.showMessageDialog(null, " Inserisci Comune ");
        } else if (prov.isEmpty()) {
            JOptionPane.showMessageDialog(null, " Inserisci Provincia");
        } else if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, " Inserisci le Aree di Interesse");
        }

        String dati = nomeCentro + "," + pia + "," + numeroCiv + "," + cap + "," + comune + "," + prov;
        dati = dati + "," + String.join(",", aree) + "\n";

        try {
            FileWriter fileWriter = new FileWriter("CentroMonitoraggio.dati.txt", true);
            fileWriter.write(dati);
            fileWriter.close();
            JOptionPane.showMessageDialog(null, "Registrazione del Centro avvenuta con successo!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
