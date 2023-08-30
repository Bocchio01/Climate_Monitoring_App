package src.GUI.operator;

import javax.swing.*;

import src.GUI.Home;
import src.functions.OperatorFunctions;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;

public class OperatorRegister extends JFrame {

    private static String windowsTitle = "Registrazione nuovo utente";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JLabel labelName = new JLabel();
    private JLabel labelTaxCode = new JLabel();
    private JLabel labelEmail = new JLabel();
    private JLabel labelUserID = new JLabel();
    private JLabel labelPassword = new JLabel();
    private JTextField textfieldName = new JTextField();
    private JTextField textfieldTaxCode = new JTextField();
    private JTextField textfieldEmail = new JTextField();
    private JTextField textfieldUserID = new JTextField();
    private JPasswordField textfieldPassword = new JPasswordField();
    private JButton buttonPerformRegistration = new JButton();
    private JButton buttonToHome = new JButton();

    public OperatorRegister() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

    }

    private void initializeComponents() {

        labelName.setText("Nome e Cognome");
        labelName.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldName.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelTaxCode.setText("Codice Fiscale");
        labelTaxCode.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldTaxCode.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelEmail.setText("Email");
        labelEmail.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldEmail.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelUserID.setText("User ID");
        labelUserID.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldUserID.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        labelPassword.setText("Password");
        labelPassword.setPreferredSize(AppConstants.GUI.LABEL_DIMENSION);
        textfieldPassword.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonPerformRegistration.setText("Registrati");
        buttonPerformRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPerformRegistration.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonToHome.setText("Home");
        buttonToHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToHome.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);
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

        panelMain.add(labelName, gbc);
        panelMain.add(textfieldName, gbc);
        panelMain.add(labelTaxCode, gbc);
        panelMain.add(textfieldTaxCode, gbc);
        panelMain.add(labelEmail, gbc);
        panelMain.add(textfieldEmail, gbc);
        panelMain.add(labelUserID, gbc);
        panelMain.add(textfieldUserID, gbc);
        panelMain.add(labelPassword, gbc);
        panelMain.add(textfieldPassword, gbc);

        panelMain.add(buttonPerformRegistration, gbc);
        panelMain.add(buttonToHome, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] {
                panelMain,
                labelName,
                labelTaxCode,
                labelEmail,
                labelUserID,
                labelPassword
        });
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
                FrameHandler.setFrame(new OperatorLogin());

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
            FrameHandler.setFrame(new Home());
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
