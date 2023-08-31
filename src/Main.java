package src;

import src.GUI.Loading;
import src.utils.GUIHandler;

class Main {

    public GUIHandler panelHandler;

    public Main() {
    }

    public void lauchGUI() {
        panelHandler = new GUIHandler();
        panelHandler.addPanels();
        panelHandler.goToPanel(Loading.ID, null);
    }

    // public void lauchCMD(String[] args) {
    // }

    public static void main(String[] args) {
        Main mainIstance = new Main();

        if (args.length == 0) {
            mainIstance.lauchGUI();
        } else {
            // TODO: Add command line arguments
            // mainIstance.lauchCMD(args);
        }
    }

}
