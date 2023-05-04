package Finestra;

import javax.swing.*;
import java.awt.*;

public class Tabella extends JFrame {
        
        // Dati della tabella
    
        Object[][] data = {
            {"John", "Doe", 25},
            {"Jane", "Doe", 32},
            {"Bob", "Smith", 47},
            {"Alice", "Johnson", 18}
        };
       

        JPanel container = new JPanel();

        // Nomi delle colonne della tabella
        String[] columnNames = {"Categoria climatica", "Spiegazione", "Punteggio", "Commento"};

        // Crea la tabella e aggiungi i dati
        JTable table = new JTable(data, columnNames);

        Tabella(){

            setLayoutManager();
            setLocationAndSize();
            addComponentsToContainer();

        }

        public void setLayoutManager() {

            // Set info Container
    
            container.setBackground(new Color(153, 255, 255));
            container.setLayout(null);
        }

        public void setLocationAndSize() {

            // Set posizioni degli oggetti nel frame

            table.setBounds(290, 50, 100, 100);
        
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
            e.setResizable(false);
        }

        
    }

 

