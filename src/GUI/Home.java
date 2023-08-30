package src.GUI;

import javax.swing.*;

import src.GUI.city.CityQuery;
import src.GUI.operator.OperatorHome;
import src.utils.AppConstants;
import src.utils.FrameHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;

public class Home extends JFrame {

    private static String windowsTitle = "Home";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JButton buttonChangeTheme = new JButton();
    private JButton buttonToFind = Widget.createButton("Cerca e visualizza dati");
    private JButton buttonToOperator = Widget.createButton("Gestisci area operatore");

    public Home() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();
    }

    private void initializeComponents() {
        buttonChangeTheme.setIcon(new ImageIcon(AppConstants.Path.Assets.THEME));
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
        panelMain.add(buttonChangeTheme, gbc);

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
            FrameHandler.setFrame(new CityQuery());
        });

        buttonToOperator.addActionListener(e -> {
            dispose();
            FrameHandler.setFrame(new OperatorHome());
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home homeFrame = new Home();
            homeFrame.setSize(1200, 800);
            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.setVisible(true);
            homeFrame.setLocationRelativeTo(null);
        });
    }
}
