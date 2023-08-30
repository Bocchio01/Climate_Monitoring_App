package src.GUI;

import java.awt.*;

import javax.swing.*;

import src.utils.ENV;
import src.utils.GUIHandler;
import src.utils.Theme;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class Loading extends TwoRows implements GUIHandler.Panel {

    public static String ID = "Loading";
    public GUIHandler panelHandler;

    private JLabel labelAppName = new JLabel();

    private Timer timer;

    public Loading() {
    }

    public void runAnimation() {
        timer.start();
    }

    @Override
    public Loading createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        timer = new Timer(700, e -> {
            int animationSteps = 5;
            int currentStep = (int) (e.getWhen() / 700 % animationSteps);

            labelAppName.setText(ENV.APP_TITLE + ".".repeat((currentStep % 4)));

            if (currentStep == animationSteps - 1) {
                timer.stop();
                panelHandler.goToPanel(Home.ID, null);
            }
        });

        labelAppName.setText(ENV.APP_TITLE);
        labelAppName.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));

        addTop(new Widget.LogoLabel(2));
        addBottom(labelAppName);

        Theme.registerLabel(labelAppName);

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        runAnimation();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIHandler panelHandler = new GUIHandler();
            Loading loading = new Loading();

            panelHandler.addPanel(loading.createPanel(panelHandler));
            loading.onOpen(args);
        });
    }
}
