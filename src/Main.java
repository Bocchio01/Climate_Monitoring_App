package src;

import src.GUI.Loading;
import src.models.MainModel;
import src.utils.GUI;

class Main {

    public GUI gui;
    public MainModel mainModel;

    public Main() {
        mainModel = new MainModel();
    }

    public void lauchGUI() {
        gui = new GUI(mainModel);
        gui.addPanels();
        gui.goToPanel(Loading.ID, null);
    }

    public void lauchCMD(String[] args) {
    }

    public static void main(String[] args) {
        Main mainIstance = new Main();
        mainIstance.lauchGUI();

        // if (args.length == 0) {
        // mainIstance.lauchGUI();
        // } else {
        // // TODO: Add command line arguments
        // mainIstance.lauchCMD(args);
        // }
    }

}
