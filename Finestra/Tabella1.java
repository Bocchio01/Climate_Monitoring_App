package Finestra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;

public class Tabella1 extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;

    public Tabella1() {

        Object[][] data = {
                { "Vento", "Velocità del vento (km/h), suddivisa in fasce", 1, "commento" },
                { "Umidità", "% di Umidità, suddivisa in fasce", 1, "commento" },
                { "Pressione", "In hPa, suddivisa in fasce", 1, "commento" },
                { "Temperatura", "In C°, suddivisa in fasce", 1, "commento" },
                { "Precipitazioni", "In mm di pioggia, suddivisa in fasce", 1, "commento" },
                { "Altitudine dei ghiacciai", "In m, suddivisa in piogge", 1, "commento" },
                { "Massa dei ghiacciai", "in kg, suddivisa in fasce", 1, "commento" }
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

        // Modifica x,y per spostare la tabella
        scrollPane.setBounds(50, 200, 700, 135);

        // Aggiunta della tabella allo scroll pane e dello scroll pane al pannello
        container.add(scrollPane);
        add(container);

    }

}
