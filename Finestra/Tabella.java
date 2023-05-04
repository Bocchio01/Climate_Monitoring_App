package Finestra;

import javax.swing.*;
import java.awt.*;

class Tabella extends JFrame {

    // Dati della tabella

    JScrollPane container = new JScrollPane();

    Object[][] data = {
            { "John", "Doe", 25, 4 },
            { "Jane", "Doe", 32, 4 },
            { "Bob", "Smith", 47, 4 },
            { "Alice", "Johnson", 18, 4 }
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

        getContentPane().setLayout(null); // Imposta il layout a null
        container = new JScrollPane(table);
        container.setBounds(10, 10, 380, 280); // Imposta le dimensioni e la posizione della tabella
        getContentPane().add(container);

        setVisible(true);
    }

    public void setLayoutManager() {

        // Set info Container

       // container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        container.setBounds(0, 0, 800, 600);
        table.setBounds(60, 60, 200, 200);

        // Set posizioni degli oggetti nel frame

        // table.setBounds(290, 50, 100, 100);

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(table);
        add(container);

        // container.add(table);
        // add(container);

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
