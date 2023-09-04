package src.GUI.panels;

import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import src.GUI.GUI;
import src.GUI.templates.TwoColumns;
import src.GUI.templates.Widget;
import src.models.MainModel;
import src.models.data.DataStorage;
import src.models.logic.AreaFunctions;
import src.utils.ENV;
import src.utils.Interfaces;

public class AreaCreateNew extends TwoColumns implements Interfaces.UIPanel {

    public static String ID = "AreaCreateNew";
    private GUI gui;
    private MainModel mainModel;

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

    public AreaCreateNew(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    private void addActionEvent() {
        buttonPerformInit.addActionListener(e -> {

            // String[] datiInseriti = new String[formElements.length];
            // for (int i = 0; i < formElements.length; i++) {
            //     String fieldValue = formElements[i].getText().trim();
            //     datiInseriti[i] = !fieldValue.isEmpty() ? fieldValue : null;
            // }

            // if (datiInseriti[6] != null) {
            //     String[] aree = datiInseriti[6].split(Pattern.quote(ENV.CSV_SUB_SEPARATOR));
            //     for (int k = 0; k < aree.length; k++) {
            //         aree[k] = aree[k].trim();
            //     }
            //     datiInseriti[6] = String.join(ENV.CSV_SUB_SEPARATOR, aree);
            // }

            // if (!AreaFunctions.checkInputRegister(datiInseriti)) {
            //     JOptionPane.showMessageDialog(
            //             this,
            //             "Mancano uno o più dati da inserire.",
            //             "Dati mancanti",
            //             JOptionPane.WARNING_MESSAGE);
            // } else {
            //     if (AreaFunctions.isAreaExists(datiInseriti[0])) {
            //         JOptionPane.showMessageDialog(
            //                 this,
            //                 "Nome dell'area già preso. Scegliere un altro nome.",
            //                 "Dato errato",
            //                 JOptionPane.ERROR_MESSAGE);
            //     } else {
            //         if (AreaFunctions.initNewArea(datiInseriti)) {
            //             JOptionPane.showMessageDialog(
            //                     this,
            //                     "Nuova area inserita correttamente.",
            //                     "Nuova area inserita",
            //                     JOptionPane.INFORMATION_MESSAGE);

            //             gui.goToPanel(AreaAddData.ID, null);
            //         } else {
            //             JOptionPane.showMessageDialog(
            //                     this,
            //                     "C'è stato un problema nella creazione della nuova area. Riprovare.",
            //                     "Errore inserimento",
            //                     JOptionPane.ERROR_MESSAGE);
            //         }
            //     }
            // }
        });
    }

    @Override
    public AreaCreateNew createPanel(GUI gui) {
        this.gui = gui;

        addLeft(new Widget.LogoLabel());
        addRight(new Widget.FormPanel("Nome dell'area", textfieldAreaName));
        addRight(new Widget.FormPanel("Nome della via", textfieldStreetName));
        addRight(new Widget.FormPanel("Numero civico", textfieldStreetNumber));
        addRight(new Widget.FormPanel("CAP", textfieldCAP));
        addRight(new Widget.FormPanel("Nome del comune", textfieldTownName));
        addRight(new Widget.FormPanel("Sigla della provincia", textfieldDistrictName));
        addRight(new Widget.FormPanel("Nomi delle città nell'area (separate da '|')",
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
        if (!mainModel.logicOperator.isUserLogged()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Per creare una nuova area devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);

            gui.goToPanel(OperatorLogin.ID, null);

        } else if (mainModel.logicOperator.getCurrentOperator().areaID() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hai già creato la tua area.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);

            gui.goToPanel(AreaAddData.ID, null);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // OperatorFunctions.performLogin("ID_Tetta", "PWD_Tetta");

            MainModel mainModel = new MainModel();
            GUI gui = new GUI(mainModel);
            AreaCreateNew areaCreateNew = new AreaCreateNew(mainModel);

            gui.addPanel(areaCreateNew.createPanel(gui));
            areaCreateNew.onOpen(args);

            areaCreateNew.textfieldAreaName.setText("Area di Tetta");
            areaCreateNew.textfieldStreetName.setText("Via Lucini");
            areaCreateNew.textfieldStreetNumber.setText("10");
            areaCreateNew.textfieldCAP.setText("22040");
            areaCreateNew.textfieldTownName.setText("San Fermo della Battaglia");
            areaCreateNew.textfieldDistrictName.setText("CO");
            areaCreateNew.textfieldCityNames.setText("New York City,Milano");

        });
    }

}
