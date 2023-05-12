package Finestra;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Cerca extends JFrame implements ActionListener {

    JPanel container = new JPanel();

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    JLabel cittàLabel = new JLabel("Città");
    JTextField cittàField = new JTextField(10);
    JLabel areaLabel = new JLabel("Area d'interesse");
    JComboBox areaBox = new JComboBox<String>();
    JLabel latLabel = new JLabel("Latitudine");
    JTextField latField = new JTextField(10);
    JLabel longLabel = new JLabel("Longitudine");
    JTextField longField = new JTextField(10);
    JButton cercaButton = new JButton("Cerca");
    Cursor cursoreReg = cercaButton.getCursor();
    JButton indietroButton = new JButton("Indietro");
    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    ImageIcon sun_moon = new ImageIcon("Immagini/tema.png");
    JButton theme = new JButton(sun_moon);
    boolean dark_theme=false;

    Cerca() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {

        // Set info Container

        if(dark_theme==false) {
            container.setBackground(new Color(153, 255, 255));
            cittàLabel.setForeground(Color.BLACK);
            areaLabel.setForeground(Color.BLACK);
            latLabel.setForeground(Color.BLACK);
            longLabel.setForeground(Color.BLACK);
        }
        else {
            container.setBackground(new Color(49, 51, 56));
            cittàLabel.setForeground(Color.WHITE);
            areaLabel.setForeground(Color.WHITE);
            latLabel.setForeground(Color.WHITE);
            longLabel.setForeground(Color.WHITE);
        }
        
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        logo.setBounds(280, 50, 200, 186);
        cittàLabel.setBounds(185, 270, 100, 30);
        cittàField.setBounds(150, 305, 100, 30);
        areaLabel.setBounds(280, 270, 100, 30);
        areaBox.setBounds(280, 305, 100, 30);
        latLabel.setBounds(400, 270, 100, 30);
        latField.setBounds(400, 305, 100, 30);
        longLabel.setBounds(520, 270, 100, 30);
        longField.setBounds(520, 305, 100, 30);
        cercaButton.setBounds(340, 400, 100, 30);
        cercaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setBounds(635, 500, 30, 30);
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        theme.setBounds(20, 20, 30, 30);
        theme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(logo);
        container.add(cittàLabel);
        container.add(cittàField);
        container.add(areaLabel);
        container.add(areaBox);
        container.add(latLabel);
        container.add(latField);
        container.add(longLabel);
        container.add(longField);
        container.add(cercaButton);
        container.add(indietroButton);
        container.add(homeButton);

        container.add(theme);

        add(container);
    }

    public void addActionEvent() {
        cercaButton.addActionListener(this);
        indietroButton.addActionListener(this);
        homeButton.addActionListener(this);

        theme.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == cercaButton) {

            String s=null;

            try {
                s=null;

                // TODO mettere nell'if la condizione del menù a tendina
                if(false)s = Find_coord.closest_coord(latField, longField);//cerca coordinate più vicine nel file e apre la finestra della tabella

                else s = Find_string.find(cittàField); // Cerca città nel file e se lo trova apre la finestra della tabella

                if(s!=null){
                    dispose();
                    setFrame(new Tabella1());
                }
                else s="";
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            setFrame(new HomePage());

        }

        // Bottone Home
        if (e.getSource() == homeButton) {

            dispose();
            setFrame(new SchermataIniziale());

        }

        if(e.getSource()==theme){
            dark_theme = !dark_theme;
            setLayoutManager();
        }
    }

    private void setFrame(Tabella1 e) {

        e.setTitle("Tabella");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
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

    private void setFrame(SchermataIniziale e) {

        e.setTitle("SchermataIniziale");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

}
