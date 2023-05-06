package Finestra;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Data extends JFrame implements ActionListener {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton salvaButton;

    public Data() {

        Object[][] data = {
                { "Vento", "Velocità del vento (km/h), suddivisa in fasce", 1, "commento" },
                { "Umidita'", "% di Umidità, suddivisa in fasce", 1, "commento" },
                { "Pressione", "In hPa, suddivisa in fasce", 1, "commento" },
                { "Temperatura", "In C°, suddivisa in fasce", 1, "commento" },
                { "Precipitazioni", "In mm di pioggia, suddivisa in fasce", 1, "commento" },
                { "Altitudine dei ghiacciai", "In m, suddivisa in piogge", 1, "commento" },
                { "Massa dei ghiacciai", "in kg, suddivisa in fasce", 1, "commento" }
        };

        String[] columnNames = { "Categoria climatica", "Spiegazione", "Punteggio", "Commento" };

        // Creazione del modello della tabella con 7 righe e 4 colonne
        model = new DefaultTableModel(data, columnNames);

        // Creazione della tabella con il modello creato
        table = new JTable(model);
        salvaButton = new JButton("Salva");

        // Impostazione del renderer delle celle per centrare il testo
        TableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        ((DefaultTableCellRenderer) centerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Impostazione delle dimensioni delle colonne per impedire all'utente di
        // modificarle
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        // Impostazione della larghezza massima delle celle della colonna a 256
        table.getColumnModel().getColumn(3).setMaxWidth(256);

        // table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // for (int i = 0; i < columnNames.length; i++) {
        // table.getColumnModel().getColumn(i).setPreferredWidth(150);
        // }
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        // Creazione del pannello contenitore e dello scroll pane per la tabella
        JPanel container = new JPanel();
        container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
        scrollPane = new JScrollPane(table);

        // Modifica x,y per spostare gli oggetti
        scrollPane.setBounds(50, 200, 700, 135);
        salvaButton.setBounds(340, 370, 100, 30);

        // Azione bottone Salva
        salvaButton.addActionListener(this);

        // Aggiunta della tabella allo scroll pane e dello scroll pane al pannello
        container.add(scrollPane);
        container.add(salvaButton);
        add(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        salvaFile();

    }

    private void salvaFile() {
        // Creazione del file e scrittura nel file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tabella.txt", true));
            for (int i = 0; i < model.getRowCount(); i++) {
                String row = model.getValueAt(i, 0) + "," + model.getValueAt(i, 2) + "," + model.getValueAt(i, 3);
                writer.write(row);
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Dati salvati con successo!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore nella scrittura dei dati!");
        }

    }

}
