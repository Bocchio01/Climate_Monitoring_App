package src.GUI.operator;

import javax.swing.*;

import src.GUI.Home;
import src.functions.OperatorFunctions;
import src.utils.AppConstants;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;

public class OperatorRegister extends JFrame {

    private static String windowsTitle = "Registrazione nuovo utente";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JTextField textfieldName = new JTextField();
    private JTextField textfieldTaxCode = new JTextField();
    private JTextField textfieldEmail = new JTextField();
    private JTextField textfieldUserID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformRegistration = Widget.createButton("Registrati");
    private JButton buttonToHome = Widget.createButton("Home");

    private JTextField[] formElements = {
            textfieldName,
            textfieldTaxCode,
            textfieldEmail,
            textfieldUserID,
            textfieldPassword
    };

    public OperatorRegister() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

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
        gbc.weighty = 1;
        gbc.gridheight = 10;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(labelLogoImage, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;

        String[] labels = {
                "Nome e Cognome",
                "Codice Fiscale",
                "Email",
                "User ID",
                "Password"
        };
        for (int i = 0; i < labels.length; i++) {
            panelMain.add(Widget.createFormPanel(labels[i], formElements[i]), gbc);
        }

        panelMain.add(buttonPerformRegistration, gbc);
        panelMain.add(buttonToHome, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
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
                    AppConstants.EMPTY_STRING
            };

            if (OperatorFunctions.isValidInput(dataInserted) &&
                    OperatorFunctions.performRegistration(dataInserted)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Profilo registrato con successo. Accedi.",
                        "Successo",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                // PanelHandler.setFrame(new OperatorLogin());

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Profilo non registrato.",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonToHome.addActionListener(e -> {
            dispose();
            // PanelHandler.setFrame(new Home());
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorRegister operatorRegisterFrame = new OperatorRegister();
            operatorRegisterFrame.setSize(1200, 800);
            operatorRegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            operatorRegisterFrame.setVisible(true);
            operatorRegisterFrame.setLocationRelativeTo(null);

            operatorRegisterFrame.textfieldName.setText(AppConstants.DefaultData.NAME);
            operatorRegisterFrame.textfieldTaxCode.setText(AppConstants.DefaultData.TAXCODE);
            operatorRegisterFrame.textfieldEmail.setText(AppConstants.DefaultData.EMAIL);
            operatorRegisterFrame.textfieldUserID.setText(AppConstants.DefaultData.ID);
            operatorRegisterFrame.textfieldPassword.setText(AppConstants.DefaultData.PWD);
        });
    }
}
