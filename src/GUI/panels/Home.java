package GUI.panels;

import javax.swing.*;

import GUI.GUI;
import GUI.Widget;
import GUI.layouts.TwoRows;
import models.MainModel;
import utils.Interfaces;

public class Home extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "Home";
    private GUI gui;
    private MainModel mainModel;

    private JButton buttonToFind = new Widget.Button("Cerca e visualizza dati");
    private JButton buttonToOperator = new Widget.Button("Gestisci area operatore");

    public Home(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private void addActionEvent() {
        buttonToFind.addActionListener(e -> {
            gui.goToPanel(CityQuery.ID, null);
        });

        buttonToOperator.addActionListener(e -> {
            gui.goToPanel(OperatorHome.ID, null);
        });
    }

    @Override
    public Home createPanel(GUI gui) {
        this.gui = gui;

        addTop(new Widget.LogoLabel());
        addBottom(buttonToFind);
        addBottom(buttonToOperator);

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
            Home home = new Home(mainModel);

            gui.addPanel(home.createPanel(gui));
            home.onOpen(args);
        });
    }
}
