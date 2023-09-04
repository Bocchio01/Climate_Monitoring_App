package src.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.GUI.panels.AreaAddData;
import src.GUI.panels.AreaCreateNew;
import src.GUI.panels.CityQuery;
import src.GUI.panels.CityVisualizer;
import src.GUI.panels.Home;
import src.GUI.panels.Loading;
import src.GUI.panels.OperatorHome;
import src.GUI.panels.OperatorLogin;
import src.GUI.panels.OperatorRegister;
import src.GUI.templates.MainFrame;
import src.GUI.templates.MainWindows;
import src.GUI.templates.MenuBar;
import src.models.MainModel;
import src.utils.Interfaces;
import src.utils.Interfaces.UIPanel;
import src.utils.Interfaces.UIWindows;

public class GUI {

    private MainModel mainModel;
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
    // private AreaAddData areaAddDataPanel = new AreaAddData();

    public GUI(MainModel mainModel) {

        this.mainModel = mainModel;

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
        // addPanel(areaAddDataPanel.createPanel(this));
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
