package Finestra;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class ScoreCellEditor extends AbstractCellEditor implements TableCellEditor {
    private JSpinner spinner;

    public ScoreCellEditor() {
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1)); // Imposta il range dei punteggi da 1 a 5
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Quando il valore cambia, notifica che l'editing è finito
                fireEditingStopped();
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue(); // Restituisci il valore del JSpinner
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value); // Imposta il valore del JSpinner
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        // Abilita la modifica solo quando l'evento è un doppio clic o un Enter
        return e instanceof MouseEvent || e instanceof KeyEvent;
    }
}
