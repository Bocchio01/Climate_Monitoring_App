package src.GUI;

import javax.swing.*;

import src.GUI.city.CityQuery;
import src.GUI.operator.OperatorHome;
import src.utils.GUIHandler;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class Home extends TwoRows implements GUIHandler.Panel {

    public static String ID = "Home";
    public GUIHandler panelHandler;

    private JButton buttonToFind = new Widget.Button("Cerca e visualizza dati");
    private JButton buttonToOperator = new Widget.Button("Gestisci area operatore");

    public Home() {
    }

    private void addActionEvent() {
        buttonToFind.addActionListener(e -> {
            panelHandler.goToPanel(CityQuery.ID, null);
        });

        buttonToOperator.addActionListener(e -> {
            panelHandler.goToPanel(OperatorHome.ID, null);
        });
    }

    @Override
    public Home createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

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
            GUIHandler panelHandler = new GUIHandler();
            Home home = new Home();

            panelHandler.addPanel(home.createPanel(panelHandler));
            home.onOpen(args);
        });
    }
}
