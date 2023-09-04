package src.GUI.panels;

import javax.swing.*;

import src.GUI.GUI;
import src.GUI.templates.TwoColumns;
import src.GUI.templates.Widget;
import src.models.MainModel;
import src.utils.Interfaces;

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
        addRight(new Widget.FormPanel("Nome e Cognome", textfieldName));
        addRight(new Widget.FormPanel("Codice Fiscale", textfieldTaxCode));
        addRight(new Widget.FormPanel("Email", textfieldEmail));
        addRight(new Widget.FormPanel("User ID", textfieldUsername));
        addRight(new Widget.FormPanel("Password", textfieldPassword));
        addRight(buttonPerformRegistration);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        mainModel.logicOperator.performLogout();
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
