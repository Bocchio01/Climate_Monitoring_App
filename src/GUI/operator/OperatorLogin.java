package src.GUI.operator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.security.auth.login.LoginException;
import javax.swing.*;

import src.GUI.Home;
import src.GUI.area.AreaAddData;
import src.GUI.area.AreaCreateNew;
import src.functions.OperatorFunctions;
import src.utils.GUIHandler;
import src.utils.Widget;
import src.utils.templates.TwoColumns;

public class OperatorLogin extends TwoColumns implements GUIHandler.Panel {

    public static String ID = "OperatorLogin";
    public GUIHandler panelHandler;

    private JTextField textfieldUsedID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformLogin = new Widget.Button("Accedi");

    public OperatorLogin() {
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
                                panelHandler.goToPanel(AreaCreateNew.ID, null);
                            } else {
                                JOptionPane.showMessageDialog(
                                        this,
                                        "Stai per essere reindirizzato alla home page.",
                                        "Area mancante",
                                        JOptionPane.INFORMATION_MESSAGE);
                                panelHandler.goToPanel(Home.ID, null);
                            }

                        } else {
                            panelHandler.goToPanel(AreaAddData.ID, null);
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

    @Override
    public OperatorLogin createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        
        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel("ID Utente", textfieldUsedID));
        addRight(new Widget.FormPanel("Password", textfieldPassword));
        addRight(buttonPerformLogin);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIHandler panelHandler = new GUIHandler();
            Home home = new Home();

            panelHandler.addPanel(home.createPanel(panelHandler));
            home.onOpen(args);
        });
    }
}
