package src.utils;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import src.GUI.Home;
import src.GUI.Loading;
import src.GUI.area.AreaAddData;
import src.GUI.area.AreaCreateNew;
import src.GUI.city.CityQuery;
import src.GUI.city.CityVisualizer;
import src.GUI.operator.OperatorHome;
import src.GUI.operator.OperatorLogin;
import src.GUI.operator.OperatorRegister;
import src.utils.templates.MainFrame;
import src.utils.templates.MainWindows;
import src.utils.templates.MenuBar;

public class GUIHandler {

    public interface Panel {
        JPanel createPanel(GUIHandler panelHandler);

        void onOpen(Object[] args);

        String getID();
    }

    public interface WindowsArea {
        JPanel getMainPanel();

        JScrollPane getScrollPane();

        JPanel getContentPanel();

        void setAppInfo(String text);
    }

    public CardLayout cardLayout;
    public JFrame mainFrame;
    public WindowsArea mainWindowsArea;
    private Map<String, Panel> Panels = new HashMap<>();
    private String currentID;

    private Loading loadingPanel = new Loading();
    private Home homePanel = new Home();
    private CityQuery cityQueryPanel = new CityQuery();
    private CityVisualizer cityVisualizerPanel = new CityVisualizer();
    private OperatorHome operatorHomePanel = new OperatorHome();
    private OperatorLogin operatorLoginPanel = new OperatorLogin();
    private OperatorRegister operatorRegisterPanel = new OperatorRegister();
    private AreaCreateNew areaCreateNewPanel = new AreaCreateNew();
    private AreaAddData areaAddDataPanel = new AreaAddData();

    public GUIHandler() {
        this.cardLayout = new CardLayout();
        this.mainFrame = new MainFrame();
        this.mainWindowsArea = new MainWindows(cardLayout);

        mainFrame.setJMenuBar(new MenuBar(this));
        mainFrame.add(mainWindowsArea.getMainPanel(), BorderLayout.CENTER);

        mainWindowsArea.getMainPanel().revalidate();
        mainWindowsArea.getMainPanel().repaint();

    }

    public void addPanels() {
        addPanel(loadingPanel.createPanel(this));
        addPanel(homePanel.createPanel(this));
        addPanel(cityQueryPanel.createPanel(this));
        addPanel(cityVisualizerPanel.createPanel(this));
        addPanel(operatorHomePanel.createPanel(this));
        addPanel(operatorLoginPanel.createPanel(this));
        addPanel(operatorRegisterPanel.createPanel(this));
        addPanel(areaCreateNewPanel.createPanel(this));
        addPanel(areaAddDataPanel.createPanel(this));
    }

    public void addPanel(Panel Panel) {
        Panels.put(Panel.getID(), Panel);
        mainWindowsArea.getContentPanel().add((Component) Panel, Panel.getID());

        Theme.registerPanel((JPanel) Panel);
        Theme.applyTheme();
    }

    public Panel getPanel(String ID) {
        return Panels.get(ID);
    }

    public WindowsArea getMainWindowsArea() {
        return mainWindowsArea;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public String getCurrentID() {
        return currentID;
    }

    public void goToPanel(String ID, Object[] args) {
        try {
            cardLayout.show(mainWindowsArea.getContentPanel(), ID);
            getPanel(ID).onOpen(args);
            currentID = ID;
            // mainWindowsArea.setAppInfo("ID Pagina corrente: " + ID);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
