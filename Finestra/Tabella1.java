package Finestra;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tabella1 extends JFrame implements ActionListener {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private JButton indietroButton, homeButton;
    private ImageIcon icona;
    private Cursor cursoreInd, cursoreHome;

    public Tabella1() {

        indietroButton = new JButton("Indietro");
        icona = new ImageIcon("Immagini/icona_home.png");
        homeButton = new JButton(icona);
        cursoreInd = indietroButton.getCursor();
        cursoreHome = homeButton.getCursor();

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

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        // Creazione del pannello contenitore e dello scroll pane per la tabella
        JPanel container = new JPanel();

        if (Theme.tema()) {
            container.setBackground(new Color(153, 255, 255));
        } else {
            container.setBackground(new Color(49, 51, 56));
        }

        //container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
        scrollPane = new JScrollPane(table);

        // Modifica x,y per spostare la tabella
        scrollPane.setBounds(50, 200, 700, 135);

        homeButton.setBounds(635, 500, 30, 30);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        homeButton.addActionListener(this);
        indietroButton.addActionListener(this);

        // Aggiunta della tabella allo scroll pane e dello scroll pane al pannello
        container.add(scrollPane);
        container.add(homeButton);
        container.add(indietroButton);
        add(container);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            dispose();
            setFrame(new HomePage());
        }

        if (e.getSource() == indietroButton) {
            dispose();
            setFrame(new Cerca());
        }
    }

    private void setFrame(HomePage e) {

        e.setTitle("HomePage");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

    private void setFrame(Cerca e) {

        e.setTitle("Cerca");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

}
