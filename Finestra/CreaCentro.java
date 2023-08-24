package Finestra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    private int i;
    private String s = "null";

    public CreaCentro(int i) throws IOException {

        this.i = i;

        ricercaCentro();

        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        if (!s.equals("null")) {
            nomeCentro.setText(s);
            nomeCentro.setEnabled(false);
        }
    }

    public void setLayoutManager() {

        // Set info Container

        container.setBackground(new Color(153, 255, 255));
        container.setLayout(null);
    }

    public void setLocationAndSize() throws IOException {
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

        System.out.println(s);
        if (!(s.equals("null"))) {
            nomeCentro.setText(s);
            nomeCentro.setEnabled(false);

            try (BufferedReader br = new BufferedReader(new FileReader("./CentroMonitoraggio.dati.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] parole = linea.split(",");
                    if (parole[0].equals(s)) {
                        for (int h = 6; h < parole.length; h++) {
                            System.out.println(parole[h]);
                            ricercaBox.addItem(parole[h]);
                        }
                    }

                }

            }

        }

        ricercaBox.setBounds(380, 100, 150, 30);

        JTextField[] textFields = new JTextField[7];
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == creaButton) {
            int flag = registraCentroAreeFunc.registraCentroAree(nomeCentro, piaField, numerocivField, capField,
                    capField,
                    provField, nomeArea, i);
            if (flag == 1) {
                dispose();
                try {

                    SetFrameFunc.setFrame(new CreaCentro(i));
                } catch (IOException e1) {

                    e1.printStackTrace();
                }
            }
        }
        if (e.getSource() == inserisciButton) {
            Object selectedItem = ricercaBox.getSelectedItem();
            if (selectedItem != null) {
                String focusedWord = selectedItem.toString();
                dispose();
                SetFrameFunc.setFrame(new Data(focusedWord, s));
            } else {
                JOptionPane.showMessageDialog(null, "Selezionare un'area");
            }

        }

    }

    public void ricercaCentro() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("./OperatoriRegistrati.dati.txt"))) {
            String linea;
            int count = 0;
            String[] parole = new String[6];
            while ((linea = br.readLine()) != null) {
                if (count == i) {
                    parole = linea.split(",");
                    break;
                }
                count++;

            }
            if (!(parole[5].equals("null"))) {

                s = parole[5];
            } else
                s = "null";

        }
    }

}
