package src.GUI;

import java.awt.*;

import javax.swing.*;

import src.models.MainModel;
import src.utils.ENV;
import src.utils.GUI;
import src.utils.Interfaces;
import src.utils.Theme;
import src.utils.Widget;
import src.utils.templates.TwoRows;

public class Loading extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "Loading";
    private GUI gui;

    private JLabel labelAppName = new JLabel();
    private Timer timer;

    public Loading() {
    }

    public void runAnimation() {
        timer.start();
    }

    @Override
    public Loading createPanel(GUI gui) {
        this.gui = gui;

        timer = new Timer(700, e -> {
            int animationSteps = 5;
            int currentStep = (int) (e.getWhen() / 700 % animationSteps);

            labelAppName.setText(ENV.APP_TITLE + ".".repeat((currentStep % 4)));

            if (currentStep == animationSteps - 1) {
                timer.stop();
                gui.goToPanel(Home.ID, null);
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
            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            Loading loading = new Loading();

            gui.addPanel(loading.createPanel(gui));
            loading.onOpen(args);
        });
    }
}
