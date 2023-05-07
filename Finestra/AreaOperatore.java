package Finestra;

import java.awt.Color;

import java.awt.Cursor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AreaOperatore extends JFrame implements ActionListener {
    // Oggetti da inserire nel frame

    JPanel container = new JPanel();

    JButton registratiButton = new JButton("Registrati");
    JButton accediButton = new JButton("Accedi");
    JButton indietroButton = new JButton("Indietro");
    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    Cursor cursoreReg = registratiButton.getCursor();
    Cursor cursoreAcc = accediButton.getCursor();
    Cursor cursoreInd = indietroButton.getCursor();

    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    AreaOperatore() {

        // Formazione del frame+componenti

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

        registratiButton.setBounds(300, 270, 200, 30);
        logo.setBounds(290, 50, 200, 186);
        accediButton.setBounds(300, 320, 200, 30);
        indietroButton.setBounds(670, 500, 80, 30);
        homeButton.setBounds(635, 500, 30, 30);

        // homeButton.setContentAreaFilled(false); TRASPARENTE O NO???

        registratiButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accediButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(registratiButton);
        container.add(logo);
        container.add(accediButton);
        container.add(indietroButton);
        container.add(homeButton);
        add(container);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        registratiButton.addActionListener(this);
        accediButton.addActionListener(this);
        indietroButton.addActionListener(this);
        homeButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone area operatore
        if (e.getSource() == registratiButton) {
            // Apre la finestra per l'accesso
            dispose();
            setFrame(new RegisterFrame());

        }

        // Bottone Ospite
        if (e.getSource() == accediButton)

        {
            // Apre la finestra per la ricerca della zona
            dispose();
            setFrame(new Login());

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

    }

    private void setFrame(RegisterFrame e) {

        e.setTitle("Registrazione");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
    }

    private void setFrame(Login e) {

        e.setTitle("Login");
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
