package Finestra.frames;

import javax.swing.*;

import Finestra.functions.LoginFunc;
import Finestra.utils.SetFrameFunc;
import Finestra.utils.Theme;

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
    Cursor cursoreReg = loginButton.getCursor();

    public Login() {

        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        Theme.applyThemeToContainer(container);
        Theme.applyThemeToLabel(userIDLabel);
        Theme.applyThemeToLabel(passwordLabel);
    }

    public void setLocationAndSize() {

        logo.setBounds(290, 50, 200, 186);
        userIDLabel.setBounds(300, 270, 200, 30);
        userIDField.setBounds(300, 305, 200, 30);
        passwordLabel.setBounds(300, 340, 200, 30);
        passwordField.setBounds(300, 375, 200, 30);
        loginButton.setBounds(350, 450, 100, 30);

        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    public void addComponentsToContainer() {

        container.add(logo);
        container.add(userIDField);
        container.add(userIDLabel);
        container.add(passwordField);
        container.add(passwordLabel);
        container.add(loginButton);

        add(container);
    }

    public void addActionEvent() {

        loginButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try {

                String userString = LoginFunc.login(userIDField.getText(), new String(passwordField.getPassword()));

                if (!userString.equals("")) {
                    // User exists
                    dispose();
                    SetFrameFunc.setFrame(new CreaCentro(userString));
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Password o ID errati",
                            "Errore di login",
                            JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

    }

}
