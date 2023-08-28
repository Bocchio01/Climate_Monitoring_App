package Finestra.frames;

import javax.swing.*;

import Finestra.frames.operator.OperatorHomeFrame;
import Finestra.utils.AppConstants;
import Finestra.utils.FrameHandler;
import Finestra.utils.Theme;

import java.awt.*;

public class HomeFrame extends JFrame {

    private String windowsTitle = "Home";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JButton buttonChangeTheme = new JButton();
    private JButton buttonToFind = new JButton();
    private JButton buttonToOperator = new JButton();

    public HomeFrame() {
        initializeComponents();
        createLayout();
        applyTheme();

        addActionEvent();
    }

    private void initializeComponents() {
        buttonChangeTheme.setIcon(new ImageIcon(AppConstants.PATH_IMG + "/tema.png"));

        labelLogoImage.setIcon(new ImageIcon(AppConstants.PATH_IMG + "/logo3.png"));

        buttonToFind.setText("Cerca e visualizza dati");
        buttonToFind.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToFind.setPreferredSize(AppConstants.WIDGET_DIMENSION);

        buttonToOperator.setText("Gestisci area operatore");
        buttonToOperator.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonToOperator.setPreferredSize(AppConstants.WIDGET_DIMENSION);
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
        panelMain.add(buttonToFind, gbc);
        panelMain.add(buttonToOperator, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }

    private void addActionEvent() {
        buttonChangeTheme.addActionListener(e -> {
            Theme.toggleTheme();
            applyTheme();
        });

        buttonToFind.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new FindFrame());
        });

        buttonToOperator.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new OperatorHomeFrame());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomeFrame homeFrame = new HomeFrame();
            homeFrame.setSize(1200, 800);
            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.setVisible(true);
            homeFrame.setLocationRelativeTo(null);
        });
    }
}
