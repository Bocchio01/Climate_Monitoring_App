package Finestra.frames.operator;

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

import Finestra.frames.HomeFrame;
import Finestra.frames.OperatorLoginFrame;
import Finestra.frames.RegisterFrame;
import Finestra.utils.AppConstants;
import Finestra.utils.FrameHandler;
import Finestra.utils.Theme;

public class OperatorHomeFrame extends JFrame {

    private String windowsTitle = "Area operatore";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JButton buttonToRegistration = new JButton();
    private JButton buttonToLogin = new JButton();
    private JButton buttonToBack = new JButton();

    public OperatorHomeFrame() {
        initializeComponents();
        createLayout();
        applyTheme();

        addActionEvent();
    }

    private void initializeComponents() {

        labelLogoImage.setIcon(new ImageIcon(AppConstants.PATH_IMG + "/logo3.png"));

        buttonToRegistration.setText("Registrati");
        buttonToRegistration.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToRegistration.setPreferredSize(AppConstants.WIDGET_DIMENSION);

        buttonToLogin.setText("Accedi");
        buttonToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToLogin.setPreferredSize(AppConstants.WIDGET_DIMENSION);

        buttonToBack.setText("Indietro");
        buttonToBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToBack.setPreferredSize(AppConstants.WIDGET_DIMENSION);
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
            FrameHandler.setFrame(new RegisterFrame());
        });

        buttonToLogin.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new OperatorLoginFrame());
        });

        buttonToBack.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new HomeFrame());
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorHomeFrame operatorHomeFrame = new OperatorHomeFrame();
            operatorHomeFrame.setSize(1200, 800);
            operatorHomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            operatorHomeFrame.setVisible(true);
            operatorHomeFrame.setLocationRelativeTo(null);
        });
    }
}
