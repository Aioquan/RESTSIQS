package Beans;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by devouty on 2015/11/1.
 */
public class EditButtonRenderer extends JButton implements TableCellRenderer {

    public EditButtonRenderer() {
        setOpaque(true);
    }
    public EditButtonRenderer(String name) {

        this.setText(name);
    }


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
//            System.out.println("row:" + row + "        column:" + column);
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("UIManager"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
