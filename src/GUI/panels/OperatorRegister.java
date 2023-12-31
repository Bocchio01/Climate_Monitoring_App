package GUI.panels;

import javax.swing.*;

import GUI.GUI;
import GUI.Widget;
import GUI.layouts.TwoColumns;
import models.CurrentOperator;
import models.MainModel;
import utils.Interfaces;

public class OperatorRegister extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "OperatorRegister";
    private GUI gui;
    private MainModel mainModel;

    private JTextField textfieldName = new JTextField();
    private JTextField textfieldTaxCode = new JTextField();
    private JTextField textfieldEmail = new JTextField();
    private JTextField textfieldUsername = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformRegistration = new Widget.Button("Registrati");

    public OperatorRegister(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void addActionEvent() {

        buttonPerformRegistration.addActionListener(e -> {

            String nameSurname = textfieldName.getText().trim();
            String taxCode = textfieldTaxCode.getText().trim();
            String email = textfieldEmail.getText().trim();
            String username = textfieldUsername.getText().trim();
            String password = new String(textfieldPassword.getPassword()).trim();
            Integer areaID = null;

            try {
                mainModel.logicOperator.performRegistration(
                        nameSurname,
                        taxCode,
                        email,
                        username,
                        password,
                        areaID);
                JOptionPane.showMessageDialog(
                        this,
                        "Profilo registrato con successo. Accedi.",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                gui.goToPanel(OperatorLogin.ID, null);

            } catch (Exception exception) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage(),
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    @Override
    public OperatorRegister createPanel(GUI gui) {
        this.gui = gui;

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel(gui.appTheme, "Nome e Cognome", textfieldName));
        addRight(new Widget.FormPanel(gui.appTheme, "Codice Fiscale", textfieldTaxCode));
        addRight(new Widget.FormPanel(gui.appTheme, "Email", textfieldEmail));
        addRight(new Widget.FormPanel(gui.appTheme, "User ID", textfieldUsername));
        addRight(new Widget.FormPanel(gui.appTheme, "Password", textfieldPassword));
        addRight(buttonPerformRegistration);

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
        CurrentOperator currentOperator = CurrentOperator.getInstance();
        currentOperator.performLogout();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            OperatorRegister operatorRegister = new OperatorRegister(mainModel);

            gui.addPanel(operatorRegister.createPanel(gui));
            operatorRegister.onOpen(args);
        });
    }
}
