package Finestra.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Finestra.functions.RegistrazioneFunc;
import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

public class RegisterFrame extends JFrame implements ActionListener {

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
    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));

    public RegisterFrame() {
        // Costruttore: formazione del frame+componenti

        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);
        Theme.applyThemeToLabel(nomeCognomeLabel);
        Theme.applyThemeToLabel(codiceFiscLabel);
        Theme.applyThemeToLabel(eMailLabel);
        Theme.applyThemeToLabel(userIdLabel);
        Theme.applyThemeToLabel(passwordLabel);
        Theme.applyThemeToLabel(centroMonLabel);
        // Theme.applyThemeToLabel(campiObblabel);

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

        homeButton.setBounds(635, 470, 30, 30);
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
        container.add(homeButton);
        add(container);

    }

    public void addActionEvent() {

        homeButton.addActionListener(this);
        // Aggiunta ActionListener ai bottoni

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Ottenere i valori inseriti dall'utente
        if (e.getSource() == registrati) {
            String[] dataInserted = {
                    nomeCognomeField.getText(),
                    codiceFiscField.getText(),
                    eMailField.getText(),
                    userIdField.getText(),
                    new String(passwordField.getPassword()),
                    !centroMonField.getText().equals("") ? centroMonField.getText() : "null"
            };
            if (RegistrazioneFunc.checkRegisterInputs(dataInserted) &&
                    RegistrazioneFunc.registrazione(dataInserted)) {
                JOptionPane.showMessageDialog(null, "Profilo registrato con successo");
                dispose();
                SetFrameFunc.setFrame(new AreaOperatore());
            } else
                JOptionPane.showMessageDialog(null, "Profilo non registrato");

        }

        // Bottone Home
        if (e.getSource() == homeButton) {

            dispose();
            SetFrameFunc.setFrame(new HomePage());

        }
    }

}
