package src.GUI.area;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import src.GUI.operator.OperatorLogin;
import src.functions.AreaFunctions;
import src.functions.OperatorFunctions;
import src.utils.GUIHandler;
import src.utils.Widget;
import src.utils.templates.TwoColumns;

public class AreaCreateNew extends TwoColumns implements GUIHandler.Panel {

    public static String ID = "AreaCreateNew";
    private GUIHandler panelHandler;

    private JTextField textfieldAreaName = new JTextField();
    private JTextField textfieldStreetName = new JTextField();
    private JTextField textfieldStreetNumber = new JTextField();
    private JTextField textfieldCAP = new JTextField();
    private JTextField textfieldTownName = new JTextField();
    private JTextField textfieldDistrictName = new JTextField();
    private JTextField textfieldCityNames = new JTextField();
    private JButton buttonPerformInit = new Widget.Button("Crea l'area");

    private JTextField[] formElements = {
            textfieldAreaName,
            textfieldStreetName,
            textfieldStreetNumber,
            textfieldCAP,
            textfieldTownName,
            textfieldDistrictName,
            textfieldCityNames
    };

    public AreaCreateNew() {
    }

    private void addActionEvent() {
        buttonPerformInit.addActionListener(e -> {

            String[] datiInseriti = new String[formElements.length];
            for (int i = 0; i < formElements.length; i++) {
                String fieldValue = formElements[i].getText().trim();
                datiInseriti[i] = !fieldValue.isEmpty() ? fieldValue : null;
            }

            if (datiInseriti[6] != null) {
                String[] aree = datiInseriti[6].split(",");
                for (int k = 0; k < aree.length; k++) {
                    aree[k] = aree[k].trim();
                }
                datiInseriti[6] = String.join(",", aree);
            }

            if (!AreaFunctions.checkInputRegister(datiInseriti)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Mancano uno o più dati da inserire.",
                        "Dati mancanti",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                if (AreaFunctions.isAreaExists(datiInseriti[0])) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Nome dell'area già preso. Scegliere un altro nome.",
                            "Dato errato",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (AreaFunctions.initNewArea(datiInseriti)) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Nuova area inserita correttamente.",
                                "Nuova area inserita",
                                JOptionPane.INFORMATION_MESSAGE);

                        panelHandler.goToPanel(AreaAddData.ID, null);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "C'è stato un problema nella creazione della nuova area. Riprovare.",
                                "Errore inserimento",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    @Override
    public AreaCreateNew createPanel(GUIHandler panelHandler) {
        this.panelHandler = panelHandler;

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel("Nome dell'area", textfieldAreaName));
        addRight(new Widget.FormPanel("Nome della via", textfieldStreetName));
        addRight(new Widget.FormPanel("Numero civico", textfieldStreetNumber));
        addRight(new Widget.FormPanel("CAP", textfieldCAP));
        addRight(new Widget.FormPanel("Nome del comune", textfieldTownName));
        addRight(new Widget.FormPanel("Nome della provincia", textfieldDistrictName));
        addRight(new Widget.FormPanel("Nomi delle città subordinate all'area (separate da virgola)",
                textfieldCityNames));
        addRight(buttonPerformInit);

        addActionEvent();

        return this;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void onOpen(Object[] args) {
        if (!OperatorFunctions.isUserLogged()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Per creare una nuova area devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);

            panelHandler.goToPanel(OperatorLogin.ID, null);

        } else if (OperatorFunctions.getCurrentUserArea() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hai già creato la tua area.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);

            panelHandler.goToPanel(AreaAddData.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIHandler panelHandler = new GUIHandler();
            AreaCreateNew areaCreateNew = new AreaCreateNew();

            panelHandler.addPanel(areaCreateNew.createPanel(panelHandler));
            areaCreateNew.onOpen(args);
        });
    }

}
