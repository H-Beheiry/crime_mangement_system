package GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class ThemeManager {

    // --- MODERN FLAT PALETTE ---
    public static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Modern Blue
    public static final Color TEXT_COLOR = new Color(44, 62, 80);      // Dark Grey
    public static final Color BG_COLOR = new Color(245, 247, 250);     // Light Cloud Grey
    public static final Color WHITE = Color.WHITE;

        public static void applyTheme() {
            try {
                UIManager.put("control", BG_COLOR);
                UIManager.put("info", BG_COLOR);
                UIManager.put("nimbusBase", PRIMARY_COLOR);

                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // --- GLOBAL FONT & INPUTS ---
            Font boldFont = new Font("Segoe UI", Font.BOLD, 14);
            Font mainFont = new Font("Segoe UI", Font.PLAIN, 14);

            UIManager.put("defaultFont", mainFont);
            UIManager.put("Label.font", mainFont);
            UIManager.put("TextField.font", mainFont);
            UIManager.put("TextField.background", WHITE);
            UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    new EmptyBorder(8, 10, 8, 10)
            ));

            // --- BUTTON PAINTERS (THE FIX) ---

            // 1. Define the painters ONCE
            Painter<JComponent> normalPainter = (g, c, w, h) -> {
                g.setColor(PRIMARY_COLOR);
                g.fillRoundRect(0, 0, w, h, 10, 10);
            };

            Painter<JComponent> hoverPainter = (g, c, w, h) -> {
                g.setColor(PRIMARY_COLOR.darker());
                g.fillRoundRect(0, 0, w, h, 10, 10);
            };

            // 2. Apply Normal State
            UIManager.getLookAndFeelDefaults().put("Button.contentMargins", new Insets(10, 20, 10, 20));
            UIManager.getLookAndFeelDefaults().put("Button[Enabled].backgroundPainter", normalPainter);
            UIManager.getLookAndFeelDefaults().put("Button[Default].backgroundPainter", normalPainter);
            UIManager.getLookAndFeelDefaults().put("Button[Focused].backgroundPainter", normalPainter); // Fixes the "Ring" on first button

            // 3. Apply Hover State (COVER ALL STATES)
            // Standard Hover
            UIManager.getLookAndFeelDefaults().put("Button[MouseOver].backgroundPainter", hoverPainter);
            // Focused Hover (The one you were missing!)
            UIManager.getLookAndFeelDefaults().put("Button[Focused+MouseOver].backgroundPainter", hoverPainter);
            // Default Button Hover (If it's the 'Enter' key button)
            UIManager.getLookAndFeelDefaults().put("Button[Default+MouseOver].backgroundPainter", hoverPainter);
            UIManager.getLookAndFeelDefaults().put("Button[Default+Focused+MouseOver].backgroundPainter", hoverPainter);

            // 4. Fix Text Colors
            UIManager.getLookAndFeelDefaults().put("Button.textForeground", WHITE);
            UIManager.getLookAndFeelDefaults().put("Button[MouseOver].textForeground", WHITE);
            UIManager.getLookAndFeelDefaults().put("Button[Focused].textForeground", WHITE);
            UIManager.getLookAndFeelDefaults().put("Button[Default].textForeground", WHITE);
        }
}
