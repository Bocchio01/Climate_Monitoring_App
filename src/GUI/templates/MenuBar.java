package src.GUI.templates;

import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import src.GUI.GUI;
import src.GUI.panels.AreaAddData;
import src.GUI.panels.CityQuery;
import src.GUI.panels.Home;
import src.GUI.panels.OperatorHome;
import src.GUI.panels.OperatorLogin;
import src.GUI.panels.OperatorRegister;

public class MenuBar extends JMenuBar {

    public MenuBar(GUI gui) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JMenuItem itemHome = new JMenuItem("Home");
        JMenuItem itemCityQuery = new JMenuItem("Area Cittadino");

        JMenu submenuOperator = new JMenu("Area Operatore");
        JMenuItem itemOperatorLogin = new JMenuItem("Login");
        JMenuItem itemOperatorRegistration = new JMenuItem("Registrazione");
        JMenuItem itemAreaAddData = new JMenuItem("Gestisci area");

        JCheckBoxMenuItem itemToggleTheme = new JCheckBoxMenuItem("Tema scuro");
        itemToggleTheme.setSelected(gui.appTheme.isDarkTheme());

        JMenuItem[] jMenuItems = new JMenuItem[] {
                itemHome,
                itemCityQuery,
                submenuOperator,
                itemOperatorLogin,
                itemOperatorRegistration,
                itemAreaAddData,
                itemToggleTheme };

        itemHome.addActionListener(e -> {
            gui.goToPanel(Home.ID, null);
        });

        itemCityQuery.addActionListener(e -> {
            gui.goToPanel(CityQuery.ID, null);
        });

        submenuOperator.addActionListener(e -> {
            gui.goToPanel(OperatorHome.ID, null);
        });

        itemOperatorLogin.addActionListener(e -> {
            gui.goToPanel(OperatorLogin.ID, null);
        });

        itemOperatorRegistration.addActionListener(e -> {
            gui.goToPanel(OperatorRegister.ID, null);
        });

        itemAreaAddData.addActionListener(e -> {
            gui.goToPanel(AreaAddData.ID, null);
        });

        itemToggleTheme.addActionListener(e -> {
            gui.appTheme.toggleTheme();
        });

        add(itemHome);
        add(itemCityQuery);

        add(submenuOperator);
        submenuOperator.add(itemOperatorLogin);
        submenuOperator.add(itemOperatorRegistration);
        submenuOperator.add(itemAreaAddData);
        add(itemToggleTheme);

        for (JMenuItem jMenuItem : jMenuItems) {
            jMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
}