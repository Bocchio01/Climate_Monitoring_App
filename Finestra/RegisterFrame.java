package Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;

class RegisterFrame extends JFrame implements ActionListener {

    // Oggetti da inserire nel frame

    JPanel container = new JPanel();

    JLabel nomeCognomeLabel = new JLabel("Nome e Cognome*");
    JTextField nomeCognomeField = new JTextField();
    JLabel codiceFiscLabel = new JLabel("Codice Fiscale*");
    JTextField codiceFiscField = new JTextField();
    JLabel eMailLabel = new JLabel("E-Mail*");
    JTextField eMailField = new JTextField();
    JLabel userIdLabel = new JLabel("User ID*");
    JTextField userIdField = new JTextField();
    JLabel passwordLabel = new JLabel("Password*");
    JPasswordField passwordField = new JPasswordField();
    JLabel centroMonLabel = new JLabel("Centro di Monitoraggio");
    JTextField centroMonField = new JTextField();
    JLabel campiObblabel = new JLabel("*Campi obbligatori");

    JButton registrati = new JButton("Registrati");
    JButton indietroButton = new JButton("Indietro");
    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));

    RegisterFrame() {
        // Costruttore: formazione del frame+componenti

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

        // Set posizioni degli oggetti nel frame

        nomeCognomeLabel.setBounds(330, 25, 200, 30);
        nomeCognomeField.setBounds(330, 60, 200, 30);

        codiceFiscLabel.setBounds(330, 95, 200, 30);
        codiceFiscField.setBounds(330, 130, 200, 30);

        eMailLabel.setBounds(330, 165, 200, 30);
        eMailField.setBounds(330, 200, 200, 30);

        userIdLabel.setBounds(330, 235, 200, 30);
        userIdField.setBounds(330, 270, 200, 30);

        passwordLabel.setBounds(330, 305, 200, 30);
        passwordField.setBounds(330, 340, 200, 30);

        centroMonLabel.setBounds(330, 375, 200, 30);
        centroMonField.setBounds(330, 410, 200, 30);

        registrati.setBounds(370, 465, 120, 30);
        registrati.addActionListener(this);
        registrati.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        campiObblabel.setBounds(640, 510, 200, 30);

        logo.setBounds(0, 0, 200, 186);

        campiObblabel.setForeground(Color.RED);// colore testo

        indietroButton.setBounds(670, 470, 80, 30);
        homeButton.setBounds(635, 470, 30, 30);
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(nomeCognomeLabel);
        container.add(nomeCognomeField);
        container.add(codiceFiscLabel);
        container.add(codiceFiscField);
        container.add(eMailLabel);
        container.add(eMailField);
        container.add(userIdLabel);
        container.add(userIdField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(centroMonLabel);
        container.add(centroMonField);
        container.add(campiObblabel);
        container.add(registrati);
        container.add(logo);
        container.add(indietroButton);
        container.add(homeButton);
        add(container);

    }

    public void addActionEvent() {

        indietroButton.addActionListener((this));
        homeButton.addActionListener(this);
        // Aggiunta ActionListener ai bottoni

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ottenere i valori inseriti dall'utente
        if (e.getSource() == registrati) {
            String nomeCognome = nomeCognomeField.getText();
            String codiceFisc = codiceFiscField.getText();
            String eMail = eMailField.getText();
            String userId = userIdField.getText();
            String password = new String(passwordField.getPassword());
            String centroMon = centroMonField.getText();

            if (nomeCognome.isEmpty() == true) {
                if (nomeCognome.isEmpty() == true) {
                    JOptionPane.showMessageDialog(null, "inserisci un nome e cognome");
                } else {
                    if (codiceFisc.isEmpty() == true) {
                    } else {
                        if (codiceFisc.isEmpty() == true) {
                            JOptionPane.showMessageDialog(null, "inserisci un codice fiscale");
                        } else {

                            /*
                             * if (codiceFisc.length() <= 15) {
                             * JOptionPane.showMessageDialog(null, "inserisci un codice fiscale valido");
                             * } else
                             */ {
                                if (eMail.isEmpty() == true) {
                                    JOptionPane.showMessageDialog(null, "inserisci una email");
                                } else {
                                    if (eMail.contains("@") == false) {
                                        JOptionPane.showMessageDialog(null, "inserisci una email valida");
                                    } else {
                                        if (userId.isEmpty() == true) {
                                            JOptionPane.showMessageDialog(null, "inserisci un id");
                                        } else {
                                            if (password.isEmpty() == true) {
                                                JOptionPane.showMessageDialog(null, "inserisci una password");
                                            } else {
                                                if (password.length() < 8) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "la password deve essere lunga almeno 8 cartteri");
                                                } else if (!password.matches(".*[A-Z].*")) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "la password deve contenere almeno una lettera maiuscola");
                                                } else {
                                                    if (centroMon.isEmpty() == true) {
                                                        centroMon = null;
                                                    }

                                                    // Creare una stringa con i dati inseriti
                                                    String dati = nomeCognome + "," + codiceFisc.toUpperCase() + ","
                                                            + eMail
                                                            + "," + userId + "," + password + "," + centroMon + "\n";

                                                    // Salvare i dati su file txt
                                                    try {
                                                        FileWriter fileWriter = new FileWriter("dati_utente.txt", true);
                                                        fileWriter.write(dati);
                                                        fileWriter.close();
                                                        JOptionPane.showMessageDialog(null,
                                                                "Profilo registrato con successo");
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottone Indietro
        if (e.getSource() == indietroButton) {

            dispose();
            setFrame(new AreaOperatore());

        }

        // Bottone Home
        if (e.getSource() == homeButton) {

            dispose();
            setFrame(new SchermataIniziale());

        }
    }

    private void setFrame(AreaOperatore e) {

        e.setTitle("Area Operatore");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

    private void setFrame(SchermataIniziale e) {

        e.setTitle("Schermata Iniziale");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

}

class Register {
    public static void main(String[] args) {

        // Creazione frame con i suoi parametri

        RegisterFrame frame = new RegisterFrame();
        frame.setTitle("Register Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

}
