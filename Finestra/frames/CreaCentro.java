package Finestra.frames;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Finestra.functions.LoginFunc;
import Finestra.functions.registraCentroAreeFunc;
import Finestra.utils.FrameHandler;
import Finestra.utils.Theme;

public class CreaCentro extends JFrame implements ActionListener {

    JPanel container = new JPanel();
    private JComboBox<String> ricercaBox;
    private JButton creaButton = new JButton("Crea");
    private JButton inserisciButton = new JButton("Inserisci dati");
    private JTextField nomeArea = new JTextField();
    private JTextField nomeCentro = new JTextField();
    private JTextField piaField = new JTextField();
    private JTextField numerocivField = new JTextField();
    private JTextField capField = new JTextField();
    private JTextField comuneField = new JTextField();
    private JTextField provField = new JTextField();
    String[] userData;

    JTextField[] textFields;

    public CreaCentro(String userString) {

        // ricercaCentro();

        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);

        userData = userString.split("[,;]");

        if (userData.length == 6 && !(userData[5].equals("null"))) {
            nomeCentro.setText(userData[5]);
            nomeCentro.setEnabled(false);

            try {
                FileReader fin = new FileReader("./CentroMonitoraggio.dati.csv");
                BufferedReader fbuffer = new BufferedReader(fin);
                String line;

                while ((line = fbuffer.readLine()) != null) {
                    String[] parole = line.split("[,;]");

                    if (parole[0].equals(userData[5])) {
                        for (int h = 6; h < parole.length; h++) {
                            ricercaBox.addItem(parole[h]);
                        }
                        break;
                    }

                }

                fbuffer.close();

            } catch (Exception e) {
                // TODO jsnck

            }
        } else {
            nomeCentro.setEnabled(true);
        }

    }

    public void setLocationAndSize() {
        nomeCentro.setBounds(50, 100, 125, 30);
        piaField.setBounds(50, 140, 125, 30);
        numerocivField.setBounds(50, 180, 125, 30);
        capField.setBounds(50, 220, 125, 30);
        comuneField.setBounds(50, 260, 125, 30);
        provField.setBounds(50, 300, 125, 30);
        nomeArea.setBounds(50, 340, 125, 30);
        creaButton.setBounds(50, 380, 125, 30);
        inserisciButton.setBounds(550, 100, 125, 30);
        ricercaBox = new JComboBox<String>();

        ricercaBox.setBounds(380, 100, 150, 30);

        textFields = new JTextField[7];
        textFields[0] = nomeCentro;
        textFields[1] = piaField;
        textFields[2] = numerocivField;
        textFields[3] = capField;
        textFields[4] = comuneField;
        textFields[5] = provField;
        textFields[6] = nomeArea;

        String[] placeHolderTexts = {
                "Centro di Monitoraggio",
                "Via/Piazza",
                "Numero Civico",
                "CAP",
                "Comune",
                "Provincia",
                "Area d'interesse"
        };

        for (int k = 0; k < textFields.length; k++) {

            textFields[k].setForeground(Color.GRAY);
            textFields[k].setText(placeHolderTexts[k]);

            int index = k;
            textFields[k].addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                    if (textFields[index].getText().equals(placeHolderTexts[index])) {
                        textFields[index].setText("");
                        textFields[index].setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textFields[index].getText().isEmpty()) {
                        textFields[index].setText(placeHolderTexts[index]);
                        textFields[index].setForeground(Color.GRAY);
                    }
                }
            });

        }

    }

    public void addComponentsToContainer() {

        container.add(nomeCentro);
        container.add(piaField);
        container.add(numerocivField);
        container.add(capField);
        container.add(comuneField);
        container.add(provField);
        container.add(creaButton);
        container.add(ricercaBox);
        container.add(nomeArea);
        container.add(inserisciButton);

        add(container);
    }

    public void addActionEvent() {
        creaButton.addActionListener(this);
        inserisciButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == creaButton) {

            String[] datiInseriti = new String[textFields.length];
            for (int i = 0; i < textFields.length; i++) {
                datiInseriti[i] = textFields[i].getText();
            }

            if (registraCentroAreeFunc.checkInputRegister(datiInseriti)) {
                registraCentroAreeFunc.registraCentroAree(datiInseriti, userData);
                String userString = LoginFunc.login(userData[3], userData[4]);
                dispose();
                FrameHandler.setFrame(new CreaCentro(userString));
            }

        }

        if (e.getSource() == inserisciButton) {
            Object selectedItem = ricercaBox.getSelectedItem();
            if (selectedItem != null) {
                dispose();
                FrameHandler.setFrame(new Data(selectedItem.toString(), textFields[0].getText()));
            } else {
                JOptionPane.showMessageDialog(null, "Selezionare un'area");
            }

        }

    }

}
