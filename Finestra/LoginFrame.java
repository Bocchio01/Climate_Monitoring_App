package Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

class LoginFrame extends JFrame implements ActionListener {

    // Oggetti da inserire nel frame

    Container container = getContentPane();
    JButton accediButton = new JButton("Accedi");
    JButton registratiButton = new JButton("Registrati");
    JButton ospiteButton = new JButton("Continua come ospite");
    JLabel logo = new JLabel(new ImageIcon("log3.png"));

    LoginFrame() {

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

        accediButton.setBounds(350, 270, 100, 30);
        registratiButton.setBounds(350, 320, 100, 30);
        ospiteButton.setBounds(300, 370, 200, 30);
        logo.setBounds(290, 50, 200, 186);

    }

    public void addComponentsToContainer() {

        // Aggiunta dei componenti al container

        container.add(accediButton);
        container.add(registratiButton);
        container.add(ospiteButton);
        container.add(logo);

    }

    public void addActionEvent() {

        // Aggiunta ActionListener ai bottoni

        accediButton.addActionListener(this);
        registratiButton.addActionListener(this);
        ospiteButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Bottone Accedi
        if (e.getSource() == accediButton) {
            // Apre la finestra per l'accesso
            JOptionPane.showMessageDialog(this, "Finstra accedi");
        }

        // Bottone Registrati
        if (e.getSource() == registratiButton) {
            // Apre la finestra per la regitrazione
            JOptionPane.showMessageDialog(this, "Finstra registrazione");

        }

        // Bottone Ospite
        if (e.getSource() == ospiteButton) {
            // Apre la finestra per la ricerca della zona
            JOptionPane.showMessageDialog(this, "Finstra cerca/viasualizza area");

        }

    }

}

class Login {
    public static void main(String[] args) {

        // Creazione frame con i suoi parametri

        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("logo_png.png");
        frame.setIconImage(image.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
    }

}
