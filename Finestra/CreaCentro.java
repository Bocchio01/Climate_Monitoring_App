package Finestra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreaCentro extends JFrame implements ActionListener {

    JPanel container = new JPanel();
    // Da fare il focus listener
    private JComboBox<String> ricercaBox;
    private JButton creaButton = new JButton("Crea");
    private JButton inserisciButton = new JButton("Inserisci dati");
    private JTextField nomeArea = new JTextField("Area d'interesse");
    private JTextField nomeCentro = new JTextField("Centro di monitoraggio");
    private JTextField piaField = new JTextField("Via/Piazza");
    private JTextField numerocivField = new JTextField("Numero civico");
    private JTextField capField = new JTextField("CAP");
    private JTextField comuneField = new JTextField("Comune");
    private JTextField provField = new JTextField("Provincia");
    private int i;

    CreaCentro(int i) throws IOException {

        this.i = i;

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

        String s = new String(ricercaCentro());
        System.out.println(s);
        if (!(s.equals("null"))) {
            nomeCentro.setText(s);
            nomeCentro.setEditable(false);

            try (BufferedReader br = new BufferedReader(new FileReader("./CentroMonitoraggio.dati.txt"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] parole = linea.split(",");
                    if (parole[0].equals(s)) {
                        for (int h = 7; h < parole.length; h++) {
                            ricercaBox.addItem(parole[h]);
                        }
                    }

                }

            }

        }

        ricercaBox.setBounds(380, 100, 150, 30);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public String ricercaCentro() throws IOException {

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

                return parole[5];
            }

            return "null";

        }
    }

}
