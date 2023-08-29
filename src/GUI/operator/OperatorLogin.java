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

public class OperatorLogin extends JFrame {

    private String windowsTitle = "Accedi all'area riservata";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JLabel labelUserID = new JLabel();
    private JLabel labelPassword = new JLabel();
    private JTextField textfieldUsedID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformLogin = new JButton();

    public OperatorLogin() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        if (OperatorFunctions.isUserLogged()) {
            int response = JOptionPane.showConfirmDialog(
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
        labelLogoImage.setIcon(new ImageIcon(AppConstants.Path.IMG + "/logoDefault.png"));

        labelUserID.setText("ID Utente");
        labelUserID.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldUsedID.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelPassword.setText("Password");
        labelPassword.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldPassword.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonPerformLogin.setText("Accedi");
        buttonPerformLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPerformLogin.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

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

        gbc.weighty = 0;
        panelMain.add(labelUserID, gbc);
        gbc.weighty = 1;
        panelMain.add(textfieldUsedID, gbc);

        gbc.weighty = 0;
        panelMain.add(labelPassword, gbc);
        gbc.weighty = 1;
        panelMain.add(textfieldPassword, gbc);

        panelMain.add(buttonPerformLogin, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] {
                panelMain,
                labelUserID,
                labelPassword
        });
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

                            int response = JOptionPane.showConfirmDialog(
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
