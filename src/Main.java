package src;

import src.GUI.GUI;
import src.GUI.panels.Loading;
import src.models.MainModel;

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
        // cmd = new CMD(mainModel);
        // cmd.run(args);
        // RecordCity city = mainModel.dataHandler.dataQuery.getCityBy(3178229);
        // System.out.println(city);
    }

    public static void main(String[] args) {
        Main mainIstance = new Main();

        if (args.length == 0) {
            mainIstance.lauchGUI();
        } else {
            mainIstance.lauchCMD(args);
            // TODO: Add command line arguments
        }
    }

}
