package src.utils.templates;

import java.awt.Cursor;
import java.awt.FlowLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import src.GUI.Home;
import src.GUI.area.AreaAddData;
import src.GUI.city.CityQuery;
import src.GUI.operator.OperatorHome;
import src.GUI.operator.OperatorLogin;
import src.GUI.operator.OperatorRegister;
import src.utils.GUIHandler;
import src.utils.Theme;

public class MenuBar extends JMenuBar {

        public MenuBar(GUIHandler panelHandler) {
            setLayout(new FlowLayout(FlowLayout.LEFT));

            JMenuItem itemHome = new JMenuItem("Home");
            JMenuItem itemCityQuery = new JMenuItem("Area Cittadino");

            JMenu submenuOperator = new JMenu("Area Operatore");
            JMenuItem itemOperatorLogin = new JMenuItem("Login");
            JMenuItem itemOperatorRegistration = new JMenuItem("Registrazione");
            JMenuItem itemAreaAddData = new JMenuItem("Gestisci area");

            JMenuItem itemToggleTheme = new JMenuItem("Cambia tema");

            JMenuItem[] jMenuItems = new JMenuItem[] {
                    itemHome,
                    itemCityQuery,
                    submenuOperator,
                    itemOperatorLogin,
                    itemOperatorRegistration,
                    itemAreaAddData,
                    itemToggleTheme };

            itemHome.addActionListener(e -> {
                panelHandler.goToPanel(Home.ID, null);
            });

            itemCityQuery.addActionListener(e -> {
                panelHandler.goToPanel(CityQuery.ID, null);
            });

            submenuOperator.addActionListener(e -> {
                panelHandler.goToPanel(OperatorHome.ID, null);
            });

            itemOperatorLogin.addActionListener(e -> {
                panelHandler.goToPanel(OperatorLogin.ID, null);
            });

            itemOperatorRegistration.addActionListener(e -> {
                panelHandler.goToPanel(OperatorRegister.ID, null);
            });

            itemAreaAddData.addActionListener(e -> {
                panelHandler.goToPanel(AreaAddData.ID, null);
            });

            itemToggleTheme.addActionListener(e -> {
                Theme.toggleTheme();
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