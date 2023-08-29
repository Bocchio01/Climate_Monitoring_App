package src.GUI.area;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
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
import src.utils.FrameHandler;
import src.utils.Theme;

public class AreaCreateNew extends JFrame {

    private String windowsTitle = "Crea una nuova area";

    private JPanel panelMain = new JPanel();
    private JLabel labelLogoImage = new JLabel();
    private JButton buttonPerformInit = new JButton();
    private JTextField textfieldAreaName = new JTextField();
    private JTextField textfieldStreetName = new JTextField();
    private JTextField textfieldStreetNumber = new JTextField();
    private JTextField textfieldCAP = new JTextField();
    private JTextField textfieldTownName = new JTextField();
    private JTextField textfieldDistrictName = new JTextField();
    private JTextField textfieldCityNames = new JTextField();

    private JTextField[] textfields = new JTextField[] {
            textfieldAreaName,
            textfieldStreetName,
            textfieldStreetNumber,
            textfieldCAP,
            textfieldTownName,
            textfieldDistrictName,
            textfieldCityNames
    };

    private String[] placeholderTexts = {
            "Nome area di monitoraggio",
            "Via / Piazza",
            "Numero Civico",
            "CAP",
            "Comune",
            "Provincia",
            "Città incluse nell'area (separate da ',')"
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
            FrameHandler.setFrame(new OperatorLogin());

        } else if (OperatorFunctions.getCurrentUserArea() != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Hai già creato la tua area.",
                    "Errore",
                    JOptionPane.ERROR_MESSAGE);

            dispose();
            FrameHandler.setFrame(new AreaAddData());
        }

    }

    private void initializeComponents() {
        labelLogoImage.setIcon(new ImageIcon(AppConstants.Path.IMG + "/logoDefault.png"));

        for (int i = 0; i < placeholderTexts.length; i++) {
            textfields[i].setText(placeholderTexts[i]);
            textfields[i].setForeground(Color.GRAY);
            textfields[i].setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);
        }

        buttonPerformInit.setText("Crea l'area");
        buttonPerformInit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonPerformInit.setPreferredSize(AppConstants.GUI.WIDGET_DIMENSION);
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
        gbc.gridheight = textfields.length + 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panelMain.add(labelLogoImage, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;
        for (JTextField textField : textfields) {
            panelMain.add(textField, gbc);
        }
        panelMain.add(buttonPerformInit, gbc);

        add(panelMain);
    }

    private void applyTheme() {
        Theme.applyTheme(new Object[] { panelMain });
    }

    private void addActionEvent() {
        buttonPerformInit.addActionListener(e -> {

            String[] datiInseriti = new String[textfields.length];
            for (int i = 0; i < textfields.length; i++) {
                datiInseriti[i] = !textfields[i].getText().equals(placeholderTexts[i]) ? textfields[i].getText().trim()
                        : null;
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
                        FrameHandler.setFrame(new AreaAddData());
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

        for (int i = 0; i < textfields.length; i++) {
            final int iFinal = i;

            textfields[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (textfields[iFinal].getText().equals(placeholderTexts[iFinal])) {
                        textfields[iFinal].setText("");
                        textfields[iFinal].setForeground(Color.BLACK);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (textfields[iFinal].getText().isEmpty()) {
                        textfields[iFinal].setText(placeholderTexts[iFinal]);
                        textfields[iFinal].setForeground(Color.GRAY);
                    }
                }
            });
        }

        // inserisciButton.addActionListener(e -> {
        // Object selectedItem = ricercaBox.getSelectedItem();
        // if (selectedItem != null) {
        // dispose();
        // FrameHandler.setFrame(new Data(selectedItem.toString(),
        // textFields[0].getText()));
        // } else {
        // JOptionPane.showMessageDialog(null, "Selezionare un'area");
        // }
        // });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OperatorFunctions.performLogin(AppConstants.DefaultData.ID, AppConstants.DefaultData.PWD);

            AreaCreateNew initNewAreaFrame = new AreaCreateNew();
            initNewAreaFrame.setSize(1200, 800);
            initNewAreaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            initNewAreaFrame.setVisible(true);
            initNewAreaFrame.setLocationRelativeTo(null);

            for (JTextField textField : initNewAreaFrame.textfields) {
                textField.setText("Comlatemnto");
            }
        });
    }

}
