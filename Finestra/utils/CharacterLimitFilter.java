package Finestra.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CharacterLimitFilter extends DocumentFilter {
    private int maxCharacters;

    public CharacterLimitFilter(int maxCharacters) {
        this.maxCharacters = maxCharacters;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        String text = fb.getDocument().getText(0, fb.getDocument().getLength());
        if ((text.length() + string.length()) <= maxCharacters) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength());
        newText = newText.substring(0, offset) + text + newText.substring(offset + length);
        if (newText.length() <= maxCharacters) {
            super.replace(fb, offset, length, text, attrs);
        }
    }
}
