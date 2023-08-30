package src.GUI.area;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import src.GUI.operator.OperatorLogin;
import src.functions.AreaFunctions;
import src.functions.OperatorFunctions;
import src.utils.AppConstants;
import src.utils.PanelHandler;
import src.utils.Theme;
import src.utils.Widget;

public class AreaCreateNew extends JFrame {

    private static String windowsTitle = "Crea una nuova area";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = Widget.createLogoLabel();
    private JButton buttonPerformInit = Widget.createButton("Crea l'area");
    private JTextField textfieldAreaName = new JTextField();
    private JTextField textfieldStreetName = new JTextField();
    private JTextField textfieldStreetNumber = new JTextField();
    private JTextField textfieldCAP = new JTextField();
    private JTextField textfieldTownName = new JTextField();
    private JTextField textfieldDistrictName = new JTextField();
    private JTextField textfieldCityNames = new JTextField();

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
        initializeComponents();
        createLayout();
        applyTheme();
        addActionEvent();

        if (!OperatorFunctions.isUserLogged()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Per creare una nuova area devi prima essere loggato.",
                    "Utente non loggato",
                    JOptionPane.ERROR_MESSAGE);

            dispose();
            // PanelHandler.setFrame(new OperatorLogin());

        } else if (OperatorFunctions.getCurrentUserArea() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hai già creato la tua area.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);

            dispose();
            // PanelHandler.setFrame(new AreaAddData());
        }

    }

    private void initializeComponents() {
    }

    private void createLayout() {
        setTitle(windowsTitle);

        panelMain.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1;
        gbc.weighty = 1;
        // gbc.gridheight = textfields.length + 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(labelLogoImage, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;

        String[] labels = {
                "Nome dell'area",
                "Nome della via",
                "Numero civico",
                "CAP",
                "Nome della città",
                "Nome della provincia",
                "Nomi delle città subordinate all'area (separate da virgola)"
        };

        for (int i = 0; i < labels.length; i++) {
            panelMain.add(Widget.createFormPanel(labels[i], formElements[i]), gbc);
        }
        panelMain.add(buttonPerformInit, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
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

                        dispose();
                        // PanelHandler.setFrame(new AreaAddData());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorFunctions.performLogin(AppConstants.DefaultData.ID, AppConstants.DefaultData.PWD);

            AreaCreateNew initNewAreaFrame = new AreaCreateNew();
            initNewAreaFrame.setSize(1200, 800);
            initNewAreaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initNewAreaFrame.setVisible(true);
            initNewAreaFrame.setLocationRelativeTo(null);

            for (JTextField formElement : initNewAreaFrame.formElements) {
                formElement.setText("Completamento automatico");
            }
        });
    }

}
