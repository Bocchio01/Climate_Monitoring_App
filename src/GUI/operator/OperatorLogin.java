package src.GUI.operator;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.security.auth.login.LoginException;
import javax.swing.*;

import src.GUI.Home;
import src.GUI.area.AreaCreateNew;
import src.GUI.area.AreaAddData;
import src.functions.OperatorFunctions;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;
import src.utils.Widget;

public class OperatorLogin extends JFrame {

    private static String windowsTitle = "Accedi all'area riservata";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JTextField textfieldUsedID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformLogin = Widget.createButton("Accedi");

    public OperatorLogin() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        if (OperatorFunctions.isUserLogged()) {
            Integer response = JOptionPane.showConfirmDialog(
                    null,
                    "Risulti già loggato con ID_Utente: " + OperatorFunctions.getCurrentUserID() + "\n"
                            + "Proseguire?",
                    "Utente già loggato",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                textfieldUsedID.setText(OperatorFunctions.getCurrentUserID());
                textfieldPassword.setText(OperatorFunctions.getCurrentUserPassword());
                buttonPerformLogin.doClick();

            } else {
                OperatorFunctions.performLogout();
            }
        }
    }

    private void initializeComponents() {
    }

    private void createLayout() {
        setTitle(windowsTitle);

        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(labelLogoImage, gbc);

        gbc.weighty = 1;
        panelMain.add(Widget.createFormPanel("ID Utente", textfieldUsedID), gbc);
        panelMain.add(Widget.createFormPanel("Password", textfieldUsedID), gbc);

        panelMain.add(buttonPerformLogin, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }

    public void addActionEvent() {

        KeyListener enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    String userID = textfieldUsedID.getText();
                    String userPassword = new String(textfieldPassword.getPassword());

                    if (!userID.isEmpty() && !userPassword.isEmpty()) {
                        buttonPerformLogin.doClick();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Completa prima tutti i campi.",
                                "Dato mancante",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        };

        buttonPerformLogin.addActionListener(e -> {
            try {

                String userID = textfieldUsedID.getText();
                String userPassword = new String(textfieldPassword.getPassword());

                if (OperatorFunctions.isUserExist(userID, userPassword)) {
                    if (OperatorFunctions.performLogin(userID, userPassword)) {
                        if (OperatorFunctions.getCurrentUserArea() == null) {

                            Integer response = JOptionPane.showConfirmDialog(
                                    null,
                                    "Ancora non hai creato la tua area. Vuoi crearla ora?",
                                    "Area non creata",
                                    JOptionPane.OK_CANCEL_OPTION);

                            if (response == JOptionPane.OK_OPTION) {
                                dispose();
                                FrameHandler.setFrame(new AreaCreateNew());
                            } else {
                                JOptionPane.showMessageDialog(
                                        this,
                                        "Stai per essere reindirizzato alla home page.",
                                        "Area mancante",
                                        JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                FrameHandler.setFrame(new Home());
                            }

                        } else {
                            dispose();
                            FrameHandler.setFrame(new AreaAddData());
                        }
                    } else {
                        throw new LoginException("Errore imprevisto in fase di login. Contattare l'assistenza.");
                    }
                } else {
                    throw new LoginException("Password o ID errati");
                }

            } catch (LoginException loginException) {
                JOptionPane.showMessageDialog(
                        this,
                        loginException.getMessage(),
                        "Errore di login",
                        JOptionPane.ERROR_MESSAGE);

            } finally {
                cleanTextFiels();
            }
        });

        textfieldUsedID.addKeyListener(enterKeyListener);
        textfieldPassword.addKeyListener(enterKeyListener);

    }

    public void cleanTextFiels() {
        textfieldUsedID.setText("");
        textfieldPassword.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorFunctions.performLogin(AppConstants.DefaultData.ID, AppConstants.DefaultData.PWD);

            OperatorLogin operatorLoginFrame = new OperatorLogin();
            operatorLoginFrame.setSize(1200, 800);
            operatorLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            operatorLoginFrame.setVisible(true);
            operatorLoginFrame.setLocationRelativeTo(null);

            // operatorLoginFrame.textfieldUsedID.setText(AppConstants.DefaultData.ID);
            // operatorLoginFrame.textfieldPassword.setText(AppConstants.DefaultData.PWD);
        });
    }
}
