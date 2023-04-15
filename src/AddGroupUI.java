import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Клас AddGroupUI є графічним інтерфейсом для додавання нової групи товарів
 */
public class AddGroupUI extends JFrame {
    //елементи вікна
    private JPanel panel = new JPanel();
    private JLabel groupNameLabel, groupDescriptionLabel;
    private JTextArea groupNameField, groupDescriptionField;
    private JButton cancelButton,addGroupButton;

    /**
     * Конструктор класу AddGroupUI. Встановлює параметри вікна і додає графічний інтерфейс
     */
    public AddGroupUI() {
        super("Додати нову групу товарів");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        addGroupUI();//додати елементи на панель
        getContentPane().add(panel); //додати панель на вікно
    }

    /**
     * створення графічного інтерфейсу для додавання нової групи товарів
     */
    private void addGroupUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //додавання назви групи товарів
        groupNameLabel = new JLabel("Назва групи товару:");
        groupNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        groupNameLabel.setForeground(new Color(42, 48, 119));
        panel.add(groupNameLabel);

        //додавання поля вводу назви групи товарів
        groupNameField = new JTextArea(2,20);
        groupNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, groupNameField.getPreferredSize().height));
        groupNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(groupNameField);

        //додавання опису групи товарів
        groupDescriptionLabel = new JLabel("Опис групи товару:");
        groupDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupDescriptionLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        groupDescriptionLabel.setForeground(new Color(42, 48, 119));
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

        //колір фону полів вводу
        groupNameField.setBackground(new Color(196, 212, 236));
        groupDescriptionField.setBackground(new Color(196, 212, 236));

        //додавання кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        addButton(buttonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        cancelButton(buttonPanel);
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
    }

    /**
     * створення кнопки "Додати" і встановлення її відповідного функціоналу
     * @param buttonPanel - панель для розміщення кнопки
     */
    private void addButton(JPanel buttonPanel) {
        addGroupButton = new JButton("Додати");
        addGroupButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        addGroupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductGroup();
                dispose();
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
    private void addProductGroup() {
        String groupName = groupNameField.getText();
        String groupDescription = groupDescriptionField.getText();
        ProductsGroup group = new ProductsGroup(groupName, groupDescription, new ArrayList<>());
        Warehouse.addProductGroup(group);
    }
}