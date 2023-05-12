package Finestra;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

class Tabella extends JFrame {

    // Dati della tabella

    JScrollPane container = new JScrollPane();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    Object[][] data = {
            { "Vento", "Velocità del vento (km/h), suddivisa in fasce", 1, "commento" },
            { "Umidità", "% di Umidità, suddivisa in fasce", 1, "commento" },
            { "Pressione", "In hPa, suddivisa in fasce", 1, "commento" },
            { "Temperatura", "In C°, suddivisa in fasce", 1, "commento" },
            { "Precipitazioni", "In mm di pioggia, suddivisa in fasce", 1, "commento" },
            { "Altitudine dei ghiacciai", "In m, suddivisa in piogge", 1, "commento" },
            { "Massa dei ghiacciai", "in kg, suddivisa in fasce", 1, "commento" }
    };

    // Nomi delle colonne della tabella
    String[] columnNames = { "Categoria climatica", "Spiegazione", "Punteggio", "Commento" };

    // Crea la tabella e aggiungi i dati
    JTable table = new JTable(data, columnNames);

    Tabella() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setVisible(true);
        // Allineamento delle celle al centro
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        getContentPane().setLayout(null); // Imposta il layout a null
        container = new JScrollPane(table);
        container.setBounds(10, 10, 380, 280); // Imposta le dimensioni e la posizione della tabella
        getContentPane().add(container);

        setVisible(true);
    }

    public void setLayoutManager() {

        // Set info Container

        container.setLayout(null);
    }

    public void setLocationAndSize() {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.doLayout();
        container.setBounds(0, 0, 800, 600);
        table.setBounds(700, 700, 700, 700);

        // Imposta la proprietà di resizable a false per impedire di modificare la
        // larghezza delle colonne
        table.getTableHeader().setResizingAllowed(false);

        // Imposta la proprietà di reordering a false per impedire di spostare le
        // colonne
        table.getTableHeader().setReorderingAllowed(false);

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(table);
        add(container);

    }

    private void setFrame(Tabella e) {

        e.setTitle("Tabella");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(true);
    }

}
