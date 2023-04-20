/**
 * Академічно доброчесно взято з форуму oracle.com
 */
/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: MyCellRenderer.java
 * Class, that sets cell render for a table cells.
 */
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyCellRenderer extends JTextArea implements TableCellRenderer {

    int maxHeight = 0;
    public MyCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setLayout(new FlowLayout());
    }

    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column) {
        try{
            setText((String) value);
        } catch (Exception e){
            setText(value.toString());
        }

        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        setSize(table.getColumnModel().getColumn(column).getWidth(),
                getPreferredSize().height);

        if (table.getRowHeight(row) < getPreferredSize().height) {
            table.setRowHeight(row, getPreferredSize().height);
        }

        return this;
    }
}
