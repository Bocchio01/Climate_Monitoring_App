package src.GUI.operator;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import src.GUI.Home;
import src.utils.FrameHandler;
import src.utils.Theme;
import src.utils.Widget;

public class OperatorHome extends JFrame {

    private static String windowsTitle = "Area operatore";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JButton buttonToRegistration = Widget.createButton("Registrati");
    private JButton buttonToLogin = Widget.createButton("Accedi");
    private JButton buttonToBack = Widget.createButton("Indietro");

    public OperatorHome() {
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
