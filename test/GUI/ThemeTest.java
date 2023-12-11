package GUI;

import static org.junit.Assert.*;
import org.junit.*;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThemeTest {

    private Theme theme;
    private JLabel testLabel;
    private JPanel testPanel;
    private final static Color WHITE = new Color(255, 250, 250);
    private final static Color DARK_GRAY = new Color(49, 51, 56);

    @Before
    public void setUp() {
        theme = new Theme();
        testLabel = new JLabel();
        testPanel = new JPanel();
    }

    @Test
    public void testToggleTheme() {
        assertTrue(theme.isDarkTheme());
        theme.toggleTheme();
        assertFalse(theme.isDarkTheme());
    }

    @Test
    public void testRegisterLabel() {
        theme.registerLabel(testLabel);
        assertTrue(theme.getLabels().contains(testLabel));
    }

    @Test
    public void testRegisterPanel() {
        theme.registerPanel(testPanel);
        assertTrue(theme.getPanels().contains(testPanel));
    }

    @Test
    public void testApplyTheme() {
        theme.registerLabel(testLabel);
        theme.registerPanel(testPanel);

        theme.applyTheme();

        if (theme.isDarkTheme()) {
            assertEquals(Color.WHITE, testLabel.getForeground());
        } else {
            assertEquals(Color.BLACK, testLabel.getForeground());
        }

        if (theme.isDarkTheme()) {
            assertEquals(DARK_GRAY, testPanel.getBackground());
        } else {
            assertEquals(WHITE, testPanel.getBackground());
        }
    }
}
