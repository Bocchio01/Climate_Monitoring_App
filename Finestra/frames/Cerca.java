package Finestra.frames;

import javax.swing.*;

import Finestra.functions.CercaFunc;
import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

import java.awt.*;
import java.awt.event.*;

public class Cerca extends JFrame implements ActionListener {

    JPanel container = new JPanel();

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    JLabel ricercaLabel = new JLabel("Tipo ricerca");
    JTextField cittaField = new JTextField(10);
    JLabel cittaLabel = new JLabel("Città");
    JComboBox<String> ricercaBox = new JComboBox<String>(
            new String[] { "Cerca per nome", "Cerca per coordinate" });
    JLabel latLabel = new JLabel("Latitudine");
    JTextField latField = new JTextField(10);
    JLabel longLabel = new JLabel("Longitudine");
    JTextField longField = new JTextField(10);
    JButton cercaButton = new JButton("Cerca");
    Cursor cursoreReg = cercaButton.getCursor();
    JButton indietroButton = new JButton("Indietro");

    public Cerca() {

        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);
        Theme.applyThemeToLabel(cittaLabel);
        Theme.applyThemeToLabel(ricercaLabel);
        Theme.applyThemeToLabel(latLabel);
        Theme.applyThemeToLabel(longLabel);

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

        cittaField.setEnabled(true);
        latField.setEnabled(false);
        longField.setEnabled(false);

        cercaButton.setBounds(340, 400, 100, 30);
        cercaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        indietroButton.setBounds(670, 500, 80, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

        add(container);
    }

    public void addActionEvent() {
        cercaButton.addActionListener(this);
        indietroButton.addActionListener(this);
        ricercaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cittaField.setEnabled(false);
                latField.setEnabled(false);
                longField.setEnabled(false);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cercaButton) {

            String città = "";

            switch (ricercaBox.getSelectedIndex()) {
                case 0:
                    città = cittaField.getText();
                    if (CercaFunc.nameFind(città, "./geonames-and-coordinates.csv", 1)) {
                        dispose();
                        SetFrameFunc.setFrame(new Tabella(città));
                    } else {
                        JOptionPane.showMessageDialog(this, "Città non trovata.");
                    }
                    break;

                case 1:
                    città = CercaFunc.coordFind(latField.getText(), longField.getText(),
                            "./geonames-and-coordinates.csv", 1);
                    dispose();
                    SetFrameFunc.setFrame(new Tabella(città));
                    break;

                default:
                    break;
            }

            // try {
            // if (cittaField.getText().isEmpty())
            // s = CercaFunc.coordFind(latField, longField);
            // else
            // s = CercaFunc.nameFind(cittaField);
            // } catch (IOException e1) {
            // // Auto-generated catch block
            // e1.printStackTrace();
            // }
            // if (s != null) {

            // dispose();
            // SetFrameFunc.setFrame(new Tabella(cittaField.getText()));
            // }

        }

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            SetFrameFunc.setFrame(new HomePage());

        }
    }

}