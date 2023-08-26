package Finestra;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

public class Tabella1 extends JFrame implements ActionListener {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton indietroButton, homeButton;
    private ImageIcon icona;
    private Cursor cursoreInd, cursoreHome;
    private JTextArea datiArea;

    public Tabella1() {

        String testo = "";
        try {
            FileReader f;
            f = new FileReader("../Ricerca.csv");

            BufferedReader b;
            b = new BufferedReader(f);

            // Dati dell'area
            testo = b.readLine();
            datiArea = new JTextArea(testo);
            datiArea.setEditable(false);
            b.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        int offset = testo.length() * 7 / 2;

        Object[][] data = {
                { "Vento", "Velocità del vento (km/h)", 1, "Commento" },
                { "Umidità", "% di Umidità", 1, "commento" },
                { "Pressione", "In hPa", 1, "commento" },
                { "Temperatura", "In C°", 1, "commento" },
                { "Precipitazioni", "In mm di pioggia", 1, "commento" },
                { "Altitudine dei ghiacciai", "In m", 1, "commento" },
                { "Massa dei ghiacciai", "in kg", 1, "commento" }
        };

        String[] columnNames = { "Categoria climatica", "Spiegazione", "Punteggio", "Commento" };

        // Creazione del modello della tabella con 7 righe e 4 colonne
        model = new DefaultTableModel(data, columnNames) {

            // Override del metodo isCellEditable per impedire all'utente di modificare i
            // campi
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Creazione della tabella con il modello creato
        table = new JTable(model);

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

        table.setRowHeight(50);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        // Creazione del pannello contenitore e dello scroll pane per la tabella
        JPanel container = new JPanel();

        if (Theme.theme()) {
            container.setBackground(new Color(153, 255, 255));
            datiArea.setBackground(new Color(153, 255, 255));
            datiArea.setForeground(Color.BLACK);
        } else {
            container.setBackground(new Color(49, 51, 56));
            datiArea.setBackground(new Color(49, 51, 56));
            datiArea.setForeground(Color.WHITE);
        }

        // container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
        scrollPane = new JScrollPane(table);

        // Modifica x,y per spostare la tabella
        scrollPane.setBounds(50, 180, 700, 250);

        homeButton.setBounds(635, 500, 30, 30);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        datiArea.setBounds(400 - offset, 100, 500, 50);

        homeButton.addActionListener(this);
        indietroButton.addActionListener(this);

        // Aggiunta della tabella allo scroll pane e dello scroll pane al pannello
        container.add(scrollPane);
        container.add(homeButton);
        container.add(indietroButton);
        container.add(datiArea);
        add(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            dispose();
            SetFrameFunc.setFrame(new HomePage());
        }

        if (e.getSource() == indietroButton) {
            dispose();
            SetFrameFunc.setFrame(new Cerca());
        }
    }

}