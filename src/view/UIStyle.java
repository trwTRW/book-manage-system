package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

final class UIStyle {

    private static final Color BACKGROUND = new Color(246, 248, 251);
    private static final Color SURFACE = Color.WHITE;
    private static final Color TEXT = new Color(42, 49, 60);
    private static final Color MUTED = new Color(103, 113, 128);
    private static final Color BORDER = new Color(216, 222, 230);
    private static final Color ACCENT = new Color(65, 104, 178);
    private static final Color SELECTION = new Color(225, 235, 252);
    private static final String FONT_FAMILY = "Microsoft YaHei UI";

    private UIStyle() {
    }

    static void apply(JFrame frame) {
        frame.getContentPane().setBackground(BACKGROUND);
        styleComponent(frame.getContentPane());
        frame.setBackground(BACKGROUND);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private static void styleComponent(Component component) {
        if (component instanceof JScrollBar) {
            return;
        }

        if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            panel.setBackground(BACKGROUND);
        }

        if (component instanceof JLabel) {
            styleLabel((JLabel) component);
        } else if (component instanceof AbstractButton) {
            styleButton((AbstractButton) component);
        } else if (component instanceof JTextField) {
            styleTextField((JTextField) component);
        } else if (component instanceof JTable) {
            styleTable((JTable) component);
        } else if (component instanceof JComboBox) {
            styleComboBox((JComboBox<?>) component);
            return;
        } else if (component instanceof JScrollPane) {
            styleScrollPane((JScrollPane) component);
        }

        if (component instanceof Container) {
            Component[] children = ((Container) component).getComponents();
            for (Component child : children) {
                styleComponent(child);
            }
        }
    }

    private static void styleLabel(JLabel label) {
        int currentSize = label.getFont() == null ? 17 : label.getFont().getSize();
        int style = label.getFont() == null ? Font.PLAIN : label.getFont().getStyle();
        if (currentSize >= 40) {
            label.setFont(new Font(FONT_FAMILY, Font.BOLD, 34));
        } else if (currentSize >= 28) {
            label.setFont(new Font(FONT_FAMILY, style, 20));
        } else {
            label.setFont(new Font(FONT_FAMILY, style, 16));
        }
        label.setForeground(currentSize < 22 ? MUTED : TEXT);
    }

    private static void styleButton(AbstractButton button) {
        int currentSize = button.getFont() == null ? 17 : button.getFont().getSize();
        int fontSize = currentSize >= 30 ? 20 : 16;
        int fontStyle = currentSize >= 30 ? Font.BOLD : Font.PLAIN;
        button.setFont(new Font(FONT_FAMILY, fontStyle, fontSize));
        button.setForeground(ACCENT);
        button.setBackground(SURFACE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(8, 16, 8, 16));
        button.setBorder(compoundBorder(ACCENT, 1, 8, 14));
        button.setOpaque(true);
    }

    private static void styleTextField(JTextField field) {
        field.setFont(new Font(FONT_FAMILY, Font.PLAIN, 17));
        field.setForeground(TEXT);
        field.setBackground(SURFACE);
        field.setCaretColor(TEXT);
        field.setSelectionColor(SELECTION);
        field.setSelectedTextColor(TEXT);
        field.setBorder(compoundBorder(BORDER, 1, 7, 10));
    }

    private static void styleTable(JTable table) {
        table.setFont(new Font(FONT_FAMILY, Font.PLAIN, 16));
        table.setForeground(TEXT);
        table.setBackground(SURFACE);
        table.setSelectionBackground(SELECTION);
        table.setSelectionForeground(TEXT);
        table.setGridColor(BORDER);
        table.setRowHeight(32);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setIntercellSpacing(new java.awt.Dimension(0, 1));
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setFont(new Font(FONT_FAMILY, Font.BOLD, 16));
            header.setForeground(TEXT);
            header.setBackground(new Color(237, 241, 247));
            header.setPreferredSize(new java.awt.Dimension(header.getPreferredSize().width, 36));
        }
    }

    private static void styleComboBox(JComboBox<?> comboBox) {
        comboBox.setFont(new Font(FONT_FAMILY, Font.PLAIN, 16));
        comboBox.setForeground(TEXT);
        comboBox.setBackground(SURFACE);
        comboBox.setBorder(compoundBorder(BORDER, 1, 4, 8));
    }

    private static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        JViewport viewport = scrollPane.getViewport();
        if (viewport != null) {
            viewport.setBackground(BACKGROUND);
        }
    }

    private static Border compoundBorder(Color color, int thickness, int vertical, int horizontal) {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, thickness),
                BorderFactory.createEmptyBorder(vertical, horizontal, vertical, horizontal));
    }
}
