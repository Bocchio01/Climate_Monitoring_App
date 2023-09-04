package src.GUI.panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import src.GUI.GUI;
import src.GUI.templates.TwoColumns;
import src.GUI.templates.Widget;
import src.models.MainModel;
import src.models.record.OperatorRecord;
import src.utils.Interfaces;

public class OperatorLogin extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "OperatorLogin";
    private GUI gui;
    private MainModel mainModel;

    private JTextField textfieldUsedID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformLogin = new Widget.Button("Accedi");

    public OperatorLogin(MainModel mainModel) {
        this.mainModel = mainModel;
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
            String userID = textfieldUsedID.getText();
            String userPassword = new String(textfieldPassword.getPassword());

            try {
                mainModel.logicOperator.performLogin(userID, userPassword);

                OperatorRecord currentOperator = mainModel.logicOperator.getCurrentOperator();

                if (currentOperator.areaID() == null) {
                    Integer response = JOptionPane.showConfirmDialog(
                            null,
                            "Ancora non hai creato la tua area. Vuoi crearla ora?",
                            "Area non creata",
                            JOptionPane.OK_CANCEL_OPTION);
                    if (response == JOptionPane.OK_OPTION) {
                        gui.goToPanel(AreaCreateNew.ID, null);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "Stai per essere reindirizzato alla home page.",
                                "Area mancante",
                                JOptionPane.INFORMATION_MESSAGE);
                        gui.goToPanel(Home.ID, null);
                    }

                } else {
                    gui.goToPanel(AreaAddData.ID, null);
                }

            } catch (Exception exception) {
                // TODO: handle exception
                // System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage(),
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
    public OperatorLogin createPanel(GUI gui) {
        this.gui = gui;

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel(gui.appTheme, "ID Utente", textfieldUsedID));
        addRight(new Widget.FormPanel(gui.appTheme, "Password", textfieldPassword));
        addRight(buttonPerformLogin);

        gui.appTheme.registerPanel(leftPanel);
        gui.appTheme.registerPanel(rightPanel);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        if(mainModel.logicOperator.isUserLogged()) {
            Integer response = JOptionPane.showConfirmDialog(
                    null,
                    "Risulti già loggato con UserName: " + mainModel.logicOperator.getCurrentOperator().username() + "\n"
                            + "Proseguire?",
                    "Utente già loggato",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                textfieldUsedID.setText(mainModel.logicOperator.getCurrentOperator().username());
                textfieldPassword.setText(mainModel.logicOperator.getCurrentOperator().password());
                buttonPerformLogin.doClick();
            } else {
                mainModel.logicOperator.performLogout();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            Home home = new Home(mainModel);

            gui.addPanel(home.createPanel(gui));
            home.onOpen(args);
        });
    }
}
