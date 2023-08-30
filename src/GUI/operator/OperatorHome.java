package src.GUI.operator;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import src.GUI.Home;
import src.utils.GUIHandler;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class OperatorHome extends TwoRows implements GUIHandler.Panel {

    public static String ID = "OperatorHome";
    public GUIHandler panelHandler;

    private JButton buttonToRegistration = new Widget.Button("Registrati");
    private JButton buttonToLogin = new Widget.Button("Accedi");

    public OperatorHome() {
    }

    public void addActionEvent() {
        buttonToRegistration.addActionListener(e -> {
            panelHandler.goToPanel(OperatorRegister.ID, null);
        });
        
        buttonToLogin.addActionListener(e -> {
            panelHandler.goToPanel(OperatorLogin.ID, null);
        });
    }

    @Override
    public OperatorHome createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

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
            GUIHandler panelHandler = new GUIHandler();
            OperatorHome operatorHome = new OperatorHome();

            panelHandler.addPanel(operatorHome.createPanel(panelHandler));
            operatorHome.onOpen(args);
        });
    }
}
