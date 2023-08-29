package src.GUI.operator;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.GUI.Home;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;

public class OperatorHome extends JFrame {

    private String windowsTitle = "Area operatore";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JButton buttonToRegistration = new JButton();
    private JButton buttonToLogin = new JButton();
    private JButton buttonToBack = new JButton();

    public OperatorHome() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

    }

    private void initializeComponents() {

        labelLogoImage.setIcon(new ImageIcon(AppConstants.Path.IMG + "/logoDefault.png"));

        buttonToRegistration.setText("Registrati");
        buttonToRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToRegistration.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonToLogin.setText("Accedi");
        buttonToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToLogin.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);

        buttonToBack.setText("Indietro");
        buttonToBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToBack.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);
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
        panelMain.add(buttonToRegistration, gbc);
        panelMain.add(buttonToLogin, gbc);
        panelMain.add(buttonToBack, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }

    public void addActionEvent() {
        buttonToRegistration.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new OperatorRegister());
        });

        buttonToLogin.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new OperatorLogin());
        });

        buttonToBack.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new Home());
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorHome operatorHomeFrame = new OperatorHome();
            operatorHomeFrame.setSize(1200, 800);
            operatorHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            operatorHomeFrame.setVisible(true);
            operatorHomeFrame.setLocationRelativeTo(null);
        });
    }
}
