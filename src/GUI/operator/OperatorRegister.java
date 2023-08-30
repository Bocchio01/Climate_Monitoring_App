package src.GUI.operator;

import javax.swing.*;

import src.functions.OperatorFunctions;
import src.utils.ENV;
import src.utils.GUIHandler;
import src.utils.Widget;
import src.utils.templates.TwoColumns;


public class OperatorRegister extends TwoColumns implements GUIHandler.Panel {

    public static String ID = "OperatorRegister";
    public GUIHandler panelHandler;

    private JTextField textfieldName = new JTextField();
    private JTextField textfieldTaxCode = new JTextField();
    private JTextField textfieldEmail = new JTextField();
    private JTextField textfieldUserID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformRegistration = new Widget.Button("Registrati");

    public OperatorRegister() {
    }

    public void addActionEvent() {

        buttonPerformRegistration.addActionListener(e -> {

            OperatorFunctions.performLogout();

            String[] dataInserted = {
                    textfieldName.getText().trim(),
                    textfieldTaxCode.getText().trim(),
                    textfieldEmail.getText().trim(),
                    textfieldUserID.getText().trim(),
                    new String(textfieldPassword.getPassword()).trim(),
                    ENV.EMPTY_STRING
            };

            if (OperatorFunctions.isValidInput(dataInserted) &&
                    OperatorFunctions.performRegistration(dataInserted)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Profilo registrato con successo. Accedi.",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                panelHandler.goToPanel(OperatorLogin.ID, null);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Profilo non registrato.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    @Override
    public OperatorRegister createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel("Nome e Cognome", textfieldName));
        addRight(new Widget.FormPanel("Codice Fiscale", textfieldTaxCode));
        addRight(new Widget.FormPanel("Email", textfieldEmail));
        addRight(new Widget.FormPanel("User ID", textfieldUserID));
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
        OperatorFunctions.performLogout();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIHandler panelHandler = new GUIHandler();
            OperatorRegister operatorRegister = new OperatorRegister();

            panelHandler.addPanel(operatorRegister.createPanel(panelHandler));
            operatorRegister.onOpen(args);
        });
    }
}
