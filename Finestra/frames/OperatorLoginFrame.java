package Finestra.frames;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import Finestra.functions.LoginFunc;
import Finestra.utils.AppConstants;
import Finestra.utils.FrameHandler;
import Finestra.utils.Theme;


public class OperatorLoginFrame extends JFrame {

    private String windowsTitle = "Accedi all'area riservata";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JLabel userIDLabel = new JLabel("User ID");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField userIDField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton buttonPerformLogin = new JButton();

    public OperatorLoginFrame() {

        initializeComponents();
        createLayout();
        applyTheme();

        addActionEvent();
    }


    private void initializeComponents() {
        labelLogoImage.setIcon(new ImageIcon(AppConstants.PATH_IMG + "/logo3.png"));

        buttonPerformLogin.setText("Accedi");
        buttonPerformLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPerformLogin.setPreferredSize(AppConstants.WIDGET_DIMENSION);

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
        panelMain.add(buttonPerformLogin, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }


    public void addActionEvent() {

        buttonPerformLogin.addActionListener(e -> {
            try {

                String userString = LoginFunc.login(userIDField.getText(), new String(passwordField.getPassword()));

                if (!userString.equals("")) {
                    // User exists
                    dispose();
                    FrameHandler.setFrame(new CreaCentro(userString));
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
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorLoginFrame operatorLoginFrame = new OperatorLoginFrame();
            operatorLoginFrame.setSize(1200, 800);
            operatorLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            operatorLoginFrame.setVisible(true);
            operatorLoginFrame.setLocationRelativeTo(null);
        });
    }
}
