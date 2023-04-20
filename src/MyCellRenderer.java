/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: MyCellRenderer.java
 *
 * Відповідає за відображення текстових даних у відповідному стовпці таблиці.
 * Якщо текст дуже довгий, то він автоматично переноситься на наступний рядок,
 * а висота рядка таблиці збільшується відповідно.
 */

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class MyCellRenderer extends JTextArea implements TableCellRenderer {

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
