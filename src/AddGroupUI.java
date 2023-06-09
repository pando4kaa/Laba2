/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: AddGroupUI.java
 *
 * Клас AddGroupUI - це вікно для додавання або редагування групи товарів.
 * Вікно містить елементи, такі як поля введення назви та опису групи товарів,
 * а також кнопки «Додати» та «Скасувати». Залежно від того, як конструктор викликається,
 * вікно може бути призначене для додавання нової групи товарів або для редагування існуючої групи товарів.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Клас AddGroupUI є графічним інтерфейсом для додавання нової групи товарів
 */
public class AddGroupUI extends JFrame {

    //елементи вікна
    private JPanel panel = new JPanel();
    private JLabel groupNameLabel, groupDescriptionLabel;
    private JTextArea groupNameField, groupDescriptionField;
    private JButton cancelButton,addGroupButton;
    private MainFrame frame;
    private Warehouse warehouse;
    private ProductsGroup editGroup;

    Color textColor = new Color(0x2F4052);
    Color mainColor = new Color(0xD8E1E9);
    Color secondColor = new Color(0xE7EBF1);

    /**
     * Конструктор класу AddGroupUI. Встановлює параметри вікна і додає графічний інтерфейс
     */
    public AddGroupUI(MainFrame frame, Warehouse warehouse) {
        super("Додати нову групу товарів");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        this.warehouse = warehouse;
        this.frame = frame;
        this.editGroup = editGroup;

        addGroupUI();//додати елементи на панель
        getContentPane().add(panel); //додати панель на вікно
    }

    /**
     * Конструктор для редагування групи.
     * @param editGroup група яка буде редагована
     * @param frame  головне вікно
     * @param warehouse склад
     */
    public AddGroupUI(ProductsGroup editGroup, MainFrame frame, Warehouse warehouse) {
        super("Редагувати групу товарів");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        this.warehouse = warehouse;
        this.frame = frame;
        this.editGroup = editGroup;

        addGroupUI(editGroup);//додати елементи на панель
        getContentPane().add(panel); //додати панель на вікно
    }

    /**
     * створення графічного інтерфейсу для додавання нової групи товарів
     */
    private void addGroupUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(secondColor);

        addGroupName();
        addProductDescription();

        //колір фону полів вводу
        groupNameField.setBackground(mainColor);
        groupNameField.setForeground(textColor);
        groupDescriptionField.setBackground(mainColor);
        groupDescriptionField.setForeground(textColor);

        //додавання кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(secondColor);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        addButton(buttonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        cancelButton(buttonPanel);
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
    }
    /**
     * створення графічного інтерфейсу для редагування нової групи товарів
     */
    private void addGroupUI(ProductsGroup group) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(secondColor);

        addGroupName();
        addProductDescription();

        groupNameField.setText(group.getName());
        groupNameField.setBackground(mainColor);
        groupNameField.setForeground(textColor);
        groupDescriptionField.setText(group.getDescription());
        groupDescriptionField.setBackground(mainColor);
        groupDescriptionField.setForeground(textColor);

        //додавання кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(secondColor);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        addButton(buttonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        cancelButton(buttonPanel);
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
    }

    /**
     * Метод, який створює меню та додає різні контейнери.
     */
    private void addGroupName() {
        //додавання назви групи товарів
        groupNameLabel = new JLabel("Назва групи товару:");
        groupNameLabel.setBackground(secondColor);
        groupNameLabel.setForeground(textColor);
        groupNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        groupNameLabel.setBorder(new EmptyBorder(5, 0, 5,0));
        panel.add(groupNameLabel);

        //додавання поля вводу назви групи товарів
        groupNameField = new JTextArea(2,20);
        groupNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, groupNameField.getPreferredSize().height));
        groupNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(groupNameField);
    }

    /**
     * Метод, який додає контейнери для опису групи.
     */
    private void addProductDescription() {
        //додавання опису групи товарів
        groupDescriptionLabel = new JLabel("Опис групи товару:");
        groupDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupDescriptionLabel.setBackground(secondColor);
        groupDescriptionLabel.setForeground(textColor);
        groupDescriptionLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        groupDescriptionLabel.setBorder(new EmptyBorder(5, 0, 5,0));
        panel.add(groupDescriptionLabel);

        //додавання поля вводу опису групи товарів з можливістю прокрутки
        groupDescriptionField = new JTextArea(5, 20);
        groupDescriptionField.setLineWrap(true);
        groupDescriptionField.setWrapStyleWord(true);
        groupDescriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, groupDescriptionField.getPreferredSize().height));
        groupDescriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupDescriptionField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        JScrollPane scrollPane = new JScrollPane(groupDescriptionField);
        panel.add(scrollPane);
    }

    /**
     * створення кнопки "Додати" і встановлення її відповідного функціоналу
     * @param buttonPanel - панель для розміщення кнопки
     */
    private void addButton(JPanel buttonPanel) {
        addGroupButton = new JButton("Додати");
        addGroupButton.setBackground(mainColor);
        addGroupButton.setForeground(textColor);
        addGroupButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editGroup == null) {
                    try {
                        addProductGroup();
                        warehouse.writeToFile();
                        dispose();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    editGroup();
                    warehouse.writeToFile();
                }

            }
        });
        buttonPanel.add(addGroupButton);
    }

    /**
     * створення кнопки "Скасувати" і встановлення її відповідного функціоналу
     * @param buttonPanel - панель для розміщення кнопки
     */
    private void cancelButton(JPanel buttonPanel) {
        cancelButton = new JButton("Скасувати");
        cancelButton.setBackground(mainColor);
        cancelButton.setForeground(textColor);
        cancelButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);
    }

    /**
     * зчитує введені користувачем назву та опис групи товарів,
     * створює об'єкт класу ProductsGroup і додає цю групу товарів до складу (Warehouse)
     */
    private void addProductGroup() throws Exception {
        String groupName = groupNameField.getText();
        String groupDescription = groupDescriptionField.getText();
        ProductsGroup group = new ProductsGroup(groupName, groupDescription);
        warehouse.addProductGroup(group);
        frame.updateGroupTable();

    }

    /**
     * Метод, для редагування групи.
     */
    private void editGroup() {
        String groupName = groupNameField.getText();
        String groupDescription = groupDescriptionField.getText();

        if (groupName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Назва групи не може бути порожньою!", "Помилка", JOptionPane.ERROR_MESSAGE);
        } else {
            editGroup.setName(groupName);
            editGroup.setDescription(groupDescription);
            frame.updateGroupTable();
            dispose();
        }

    }
}