package Finestra;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    Cerca() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {

        // Set info Container

        container.setBackground(new Color(153, 255, 255));
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
        add(container);
    }

    public void addActionEvent() {
        cercaButton.addActionListener(this);
        indietroButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cercaButton) {
            dispose();
            setFrame(new Tabella1());

            // Controllo dati nel file

        }


        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            this.setVisible(false);
            setFrame(new HomePage());

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

}
