package src.GUI;

import javax.swing.*;

import src.GUI.city.CityQuery;
import src.GUI.operator.OperatorHome;
import src.utils.AppConstants;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

import java.awt.*;

public class Home extends JPanel implements PanelHandler.PanelCreator {

    public static String windowsTitle = "Home";
    public static String ID = "Home";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JButton buttonChangeTheme = new JButton();
    private JButton buttonToFind = Widget.createButton("Cerca e visualizza dati");
    private JButton buttonToOperator = Widget.createButton("Gestisci area operatore");

    public Home() {
    }

    private void initializeComponents() {
        buttonChangeTheme.setIcon(new ImageIcon(AppConstants.Path.Assets.THEME));
    }

    private void createLayout() {

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
            PanelHandler.changePanel(CityQuery.ID);
        });
        
        buttonToOperator.addActionListener(e -> {
            // PanelHandler.changePanel(CityQuery.ID);
        });
    }

    @Override
    public JPanel createPanel() {
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        return this;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setSize(1200, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            PanelHandler.PanelCreator panel = new Home();
            frame.add(panel.createPanel());

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        
        });
    }
}
