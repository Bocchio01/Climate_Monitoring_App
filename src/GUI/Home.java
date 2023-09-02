package src.GUI;

import javax.swing.*;

import src.GUI.city.CityQuery;
import src.GUI.operator.OperatorHome;
import src.models.MainModel;
import src.utils.GUI;
import src.utils.Interfaces;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class Home extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "Home";
    private GUI gui;

    private JButton buttonToFind = new Widget.Button("Cerca e visualizza dati");
    private JButton buttonToOperator = new Widget.Button("Gestisci area operatore");

    public Home() {
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
            Home home = new Home();

            gui.addPanel(home.createPanel(gui));
            home.onOpen(args);
        });
    }
}
