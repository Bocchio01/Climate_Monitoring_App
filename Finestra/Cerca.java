package Finestra;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Cerca extends JFrame implements ActionListener {

    JPanel container = new JPanel();

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    JLabel ricercaLabel = new JLabel("Tipo ricerca");
    JTextField cittaField = new JTextField(10);
    JLabel cittaLabel = new JLabel("Città");
    JComboBox<String> ricercaBox = new JComboBox<String>(new String[] { "Cerca per nome", "Cerca per coordinate" });
    JLabel latLabel = new JLabel("Latitudine");
    JTextField latField = new JTextField(10);
    JLabel longLabel = new JLabel("Longitudine");
    JTextField longField = new JTextField(10);
    JButton cercaButton = new JButton("Cerca");
    Cursor cursoreReg = cercaButton.getCursor();
    JButton indietroButton = new JButton("Indietro");

    ImageIcon sun_moon = new ImageIcon("Immagini/tema.png");
    JButton theme = new JButton(sun_moon);

    Cerca() {

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {

        // Set info Container

        if (Theme.tema()) {
            container.setBackground(new Color(153, 255, 255));
            cittaLabel.setForeground(Color.BLACK);
            ricercaLabel.setForeground(Color.BLACK);
            latLabel.setForeground(Color.BLACK);
            longLabel.setForeground(Color.BLACK);
        } else {
            container.setBackground(new Color(49, 51, 56));
            cittaLabel.setForeground(Color.WHITE);
            ricercaLabel.setForeground(Color.WHITE);
            latLabel.setForeground(Color.WHITE);
            longLabel.setForeground(Color.WHITE);
        }

        container.setLayout(null);
    }

    public void setLocationAndSize() {

        logo.setBounds(280, 50, 200, 186);

        ricercaLabel.setBounds(130, 270, 100, 30);
        ricercaBox.setBounds(130, 305, 150, 30);
        cittaField.setBounds(300, 305, 100, 30);
        cittaLabel.setBounds(300, 270, 100, 30);
        latLabel.setBounds(430, 270, 100, 30);
        latField.setBounds(430, 305, 100, 30);
        longLabel.setBounds(550, 270, 100, 30);
        longField.setBounds(550, 305, 100, 30);

        cercaButton.setBounds(340, 400, 100, 30);
        cercaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        theme.setBounds(20, 20, 30, 30);
        theme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        theme.setBounds(20, 20, 30, 30);
        theme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(logo);
        container.add(cittaLabel);
        container.add(cittaField);
        container.add(ricercaLabel);
        container.add(ricercaBox);
        container.add(latLabel);
        container.add(latField);
        container.add(longLabel);
        container.add(longField);
        container.add(cercaButton);
        container.add(indietroButton);

        container.add(theme);

        add(container);
    }

    public void addActionEvent() {
        cercaButton.addActionListener(this);
        indietroButton.addActionListener(this);
        ricercaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedSearchType = (String) combo.getSelectedItem();
                if (selectedSearchType.equals("Cerca per nome")) {
                    cittaField.setEnabled(true);
                    latField.setEnabled(false);
                    longField.setEnabled(false);
                } else {
                    cittaField.setEnabled(false);
                    latField.setEnabled(true);
                    longField.setEnabled(true);
                }
            }

        });

        cittaField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    cercaButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // not used
            }
        });

        theme.addActionListener(this);

        theme.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cercaButton) {

            String s = null;

            try {
                s = Find_string.find(cittaField);
            } catch (IOException e1) {
                // Auto-generated catch block
                e1.printStackTrace();
            }
            if (s != null) {
                dispose();
                setFrame(new Tabella1());
            }

        }

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            setFrame(new HomePage());

        }

        // Bottone tema
        if (e.getSource() == theme) {
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

}
