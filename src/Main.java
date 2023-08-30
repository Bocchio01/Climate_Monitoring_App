package src;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import src.GUI.Home;
import src.GUI.Loading;
import src.GUI.city.CityQuery;
import src.GUI.city.CityVisualizer;
import src.utils.AppConstants;
import src.utils.PanelHandler;

class Main {

    public static void setWindow() {
        JFrame mainFrame = new JFrame("Monitoraggio Climatico");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon iconImage = new ImageIcon(AppConstants.Path.Assets.LOGO);
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        PanelHandler panelHandler = new PanelHandler(cardLayout, mainPanel);

        mainPanel.add(panelHandler.getPanelCreator(Loading.ID).createPanel(), Loading.ID);
        mainPanel.add(panelHandler.getPanelCreator(Home.ID).createPanel(), Home.ID);
        mainPanel.add(panelHandler.getPanelCreator(CityQuery.ID).createPanel(), CityQuery.ID);
        mainPanel.add(panelHandler.getPanelCreator(CityVisualizer.ID).createPanel(), CityVisualizer.ID);

        JMenuBar menuBar = createMenuBar(mainPanel, cardLayout);

        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        mainFrame.setIconImage(iconImage.getImage());
        mainFrame.setSize(1200, 800);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static JMenuBar createMenuBar(JPanel mainPanel, CardLayout cardLayout) {
        JMenuBar menuBar = new JMenuBar();

        JMenuItem itemHome = new JMenuItem("Home");
        JMenuItem itemCityQuery = new JMenuItem("Area Cittadino");

        JMenu submenuOperator = new JMenu("Area Operatore");
        JMenuItem itemOperatorLogin = new JMenuItem("Login");
        JMenuItem itemOperatorRegistration = new JMenuItem("Registrazione");
        JMenuItem itemAreaAddData = new JMenuItem("Gestisci area");

        itemHome.addActionListener(e -> {
            cardLayout.show(mainPanel, Home.ID);
        });

        itemCityQuery.addActionListener(e -> {
            cardLayout.show(mainPanel, CityQuery.ID);
        });

        menuBar.add(itemHome);
        menuBar.add(itemCityQuery);

        menuBar.add(submenuOperator);
        submenuOperator.add(itemOperatorLogin);
        submenuOperator.add(itemOperatorRegistration);
        submenuOperator.add(itemAreaAddData);

        return menuBar;
    }

    public static void launchLoading() {

        Loading.configureTimer();

    }

    public static void main(String[] args) {

        setWindow();
        launchLoading();

    }

}
