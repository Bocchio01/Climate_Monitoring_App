package src.GUI.operator;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import src.models.MainModel;
import src.utils.GUI;
import src.utils.Interfaces;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class OperatorHome extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "OperatorHome";
    public GUI gui;

    private JButton buttonToRegistration = new Widget.Button("Registrati");
    private JButton buttonToLogin = new Widget.Button("Accedi");

    public OperatorHome() {
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
            OperatorHome operatorHome = new OperatorHome();

            gui.addPanel(operatorHome.createPanel(gui));
            operatorHome.onOpen(args);
        });
    }
}
