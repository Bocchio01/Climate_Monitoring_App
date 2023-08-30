package src;

import src.GUI.Loading;
import src.utils.GUIHandler;

class Main {
    
    public GUIHandler panelHandler = new GUIHandler();

    public Main() {
        panelHandler.addPanels();
    }

    public static void main(String[] args) {

        Main mainInstance = new Main();
        mainInstance.panelHandler.goToPanel(Loading.ID, null);

    }

}
