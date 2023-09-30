package GUI.panels;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import GUI.GUI;
import GUI.Widget;
import GUI.layouts.TwoRows;
import models.MainModel;
import utils.Interfaces;

public class OperatorHome extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "OperatorHome";
    private GUI gui;
    private MainModel mainModel;

    private JButton buttonToRegistration = new Widget.Button("Registrati");
    private JButton buttonToLogin = new Widget.Button("Accedi");

    public OperatorHome(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public void addActionEvent() {
        buttonToRegistration.addActionListener(e -> {
            gui.goToPanel(OperatorRegister.ID, null);
        });
        
        buttonToLogin.addActionListener(e -> {
            gui.goToPanel(OperatorLogin.ID, null);
        });
    }

    @Override
    public OperatorHome createPanel(GUI gui) {
        this.gui = gui;

        addTop(new Widget.LogoLabel());
        addBottom(buttonToRegistration);
        addBottom(buttonToLogin);

        gui.appTheme.registerPanel(topPanel);
        gui.appTheme.registerPanel(bottomPanel);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            OperatorHome operatorHome = new OperatorHome(mainModel);

            gui.addPanel(operatorHome.createPanel(gui));
            operatorHome.onOpen(args);
        });
    }
}
