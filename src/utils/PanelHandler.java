package src.utils;

import java.awt.CardLayout;
import java.awt.Panel;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.GUI.Home;
import src.GUI.Loading;
import src.GUI.city.CityQuery;
import src.GUI.city.CityVisualizer;

public class PanelHandler {

    public interface PanelCreator {
        JPanel createPanel();
    }

    public static CardLayout cardLayout;
    public static JPanel mainPanel;
    private static Map<String, PanelCreator> panelCreators = new HashMap<>();
    
    static {
        panelCreators.put(Loading.ID, new Loading());
        panelCreators.put(Home.ID, new Home());
        panelCreators.put(CityQuery.ID, new CityQuery());
        panelCreators.put(CityVisualizer.ID, new CityVisualizer());
    }

    public PanelHandler(CardLayout cardLayout, JPanel mainPanel) {
        PanelHandler.cardLayout = cardLayout;
        PanelHandler.mainPanel = mainPanel;
    }

    public PanelCreator getPanelCreator(String name) {
        return panelCreators.get(name);
    }

    public static void changePanel(String ID) {
        cardLayout.show(mainPanel, ID);
    }


    public static void setFrame(JFrame mainFrame) {
        ImageIcon iconImage = new ImageIcon(AppConstants.Path.Assets.LOGO);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setIconImage(iconImage.getImage());
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

}
