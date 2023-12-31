package GUI.panels;

import java.awt.*;

import javax.swing.*;

import GUI.GUI;
import GUI.Widget;
import GUI.layouts.TwoRows;
import models.MainModel;
import utils.Constants;
import utils.Interfaces;

public class Loading extends TwoRows implements Interfaces.UIPanel {

    public static String ID = "Loading";
    private GUI gui;
    private MainModel mainModel;

    private JLabel labelAppName = new JLabel();
    private Timer timer;

    public Loading(MainModel mainModel) {
        this.mainModel = mainModel;
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

            labelAppName.setText(Constants.APP_TITLE + ".".repeat((currentStep % 4)));

            if (currentStep == animationSteps - 1) {
                timer.stop();
                gui.goToPanel(Home.ID, null);
            }
        });

        labelAppName.setText(Constants.APP_TITLE);
        labelAppName.setFont(new Font("Ink Free", Font.CENTER_BASELINE, 35));

        addTop(new Widget.LogoLabel(2));
        addBottom(labelAppName);

        gui.appTheme.registerPanel(topPanel);
        gui.appTheme.registerPanel(bottomPanel);
        gui.appTheme.registerLabel(labelAppName);

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
            Loading loading = new Loading(mainModel);

            gui.addPanel(loading.createPanel(gui));
            loading.onOpen(args);
        });
    }
}
