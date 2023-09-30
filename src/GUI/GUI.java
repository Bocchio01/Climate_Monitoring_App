package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.mainElements.MainFrame;
import GUI.mainElements.MainWindows;
import GUI.mainElements.MenuBar;
import GUI.panels.AreaAddData;
import GUI.panels.AreaCreateNew;
import GUI.panels.CityQuery;
import GUI.panels.CityVisualizer;
import GUI.panels.Home;
import GUI.panels.Loading;
import GUI.panels.OperatorHome;
import GUI.panels.OperatorLogin;
import GUI.panels.OperatorRegister;
import models.MainModel;
import utils.Interfaces;

public class GUI {

    public Theme appTheme = new Theme();

    private CardLayout cardLayout = new CardLayout();
    private JFrame mainFrame = new MainFrame();
    private Interfaces.UIWindows mainWindowsArea = new MainWindows(cardLayout);
    private Map<String, Interfaces.UIPanel> Panels = new HashMap<>();
    private String currentID;

    private Loading loadingPanel;
    private Home homePanel;
    private CityQuery cityQueryPanel;
    private CityVisualizer cityVisualizerPanel;
    private OperatorHome operatorHomePanel;
    private OperatorLogin operatorLoginPanel;
    private OperatorRegister operatorRegisterPanel;
    private AreaCreateNew areaCreateNewPanel;
    private AreaAddData areaAddDataPanel;

    public GUI(MainModel mainModel) {

        mainFrame.setJMenuBar(new MenuBar(this));
        mainFrame.add(mainWindowsArea.getMainPanel(), BorderLayout.CENTER);

        mainWindowsArea.getMainPanel().revalidate();
        mainWindowsArea.getMainPanel().repaint();

        loadingPanel = new Loading(mainModel);
        homePanel = new Home(mainModel);
        cityQueryPanel = new CityQuery(mainModel);
        cityVisualizerPanel = new CityVisualizer(mainModel);
        operatorHomePanel = new OperatorHome(mainModel);
        operatorLoginPanel = new OperatorLogin(mainModel);
        operatorRegisterPanel = new OperatorRegister(mainModel);
        areaCreateNewPanel = new AreaCreateNew(mainModel);
        areaAddDataPanel = new AreaAddData(mainModel);

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

    public void addPanel(Interfaces.UIPanel Panel) {
        Panels.put(Panel.getID(), Panel);
        mainWindowsArea.getContentPanel().add((Component) Panel, Panel.getID());

        appTheme.registerPanel((JPanel) Panel);
        appTheme.applyTheme();
    }

    public Interfaces.UIPanel getUIPanel(String ID) {
        return Panels.get(ID);
    }

    public Interfaces.UIWindows getMainWindowsArea() {
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
            getUIPanel(ID).onOpen(args);
            currentID = ID;
            // mainWindowsArea.setAppInfo("ID Pagina corrente: " + ID);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Errore: Panel non trovato.");
        }
    }

}
