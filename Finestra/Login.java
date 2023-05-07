package Finestra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JPanel container = new JPanel();

    JLabel logo = new JLabel(new ImageIcon("Immagini/logo3.png"));
    private JTextField userIDField = new JTextField(10);
    private JLabel userIDLabel = new JLabel("User ID");
    private JPasswordField passwordField = new JPasswordField(10);
    private JLabel passwordLabel = new JLabel("Password");
    private JButton loginButton = new JButton("Accedi");
    JButton indietroButton = new JButton("Indietro");
    Cursor cursoreReg = loginButton.getCursor();
    ImageIcon icona = new ImageIcon("Immagini/icona_home.png");
    JButton homeButton = new JButton(icona);

    Login() {

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

        logo.setBounds(290, 50, 200, 186);
        userIDLabel.setBounds(300, 270, 200, 30);
        userIDField.setBounds(300, 305, 200, 30);
        passwordLabel.setBounds(300, 340, 200, 30);
        passwordField.setBounds(300, 375, 200, 30);
        loginButton.setBounds(350, 450, 100, 30);
        indietroButton.setBounds(670, 500, 80, 30);
        homeButton.setBounds(635, 500, 30, 30);

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        indietroButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        container.add(logo);
        container.add(userIDField);
        container.add(userIDLabel);
        container.add(passwordField);
        container.add(passwordLabel);
        container.add(loginButton);
        container.add(indietroButton);
        container.add(homeButton);
        add(container);
    }

    public void addActionEvent() {

        loginButton.addActionListener(this);
        indietroButton.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {

            // Controllo dati nel file
            JOptionPane.showMessageDialog(this, "Login avvenuto con successo!");
            dispose();
            setFrame(new Data());

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

    private void setFrame(Data e) {

        e.setTitle("Cerca");
        e.setVisible(true);
        e.setBounds(10, 10, 800, 600);
        e.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon("Immagini/logo_png.png");
        e.setIconImage(image.getImage());
        e.setLocationRelativeTo(null);
        e.setResizable(false);
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
