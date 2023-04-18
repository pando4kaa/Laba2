import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddProductToGroupUI extends JFrame {
    // елементи вікна
    private JPanel panel = new JPanel();
    private JLabel groupNameLabel, productNameLabel, productDescriptionLabel, productAuthorLabel, productPublisherLabel, productQuantityLabel, productPriceLabel;
    private JTextArea groupNameField, productNameField, productDescriptionField, productAuthorField, productPublisherField, productQuantityField, productPriceField;
    private JButton cancelButton, addProductButton;
    public AddProductToGroupUI() {
        super("Додати товар до групи");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 700);
        setLocationRelativeTo(null);

        addProductToGroupUI(); // додати елементи на панель
        getContentPane().add(panel); // додати панель на вікно
    }

    private void addProductToGroupUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addGroupName();
        addProductName();
        addProductDescription();
        addProductAuthor();
        addProductPublisher();
        addProductQuantity();
        addProductPrice();

        // колір фону полів вводу
        JTextArea[] fields = {groupNameField, productNameField, productDescriptionField,
                productAuthorField, productPublisherField, productQuantityField,
                productPriceField};

        for (JTextArea field : fields) {
            field.setBackground(new Color(196, 212, 236));
        }


        // додавання кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        addProductButton(buttonPanel);
        buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        cancelButton(buttonPanel);
        buttonPanel.add(Box.createHorizontalGlue());
        panel.add(buttonPanel);
    }


    private void addGroupName() {
        // додавання назви групи товарів
        groupNameLabel = new JLabel("Назва групи товарів:");
        groupNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        groupNameLabel.setForeground(new Color(42, 48, 119));
        panel.add(groupNameLabel);

        // додавання поля вводу назви групи товарів
        groupNameField = new JTextArea(2, 20);
        groupNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, groupNameField.getPreferredSize().height));
        groupNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupNameField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(groupNameField);
    }
    private void addProductName() {
        // додавання назви товару
        productNameLabel = new JLabel("Назва товару:");
        productNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNameLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productNameLabel.setForeground(new Color(42, 48, 119));
        panel.add(productNameLabel);

        // додавання поля вводу назви товару
        productNameField = new JTextArea(2, 20);
        productNameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productNameField.getPreferredSize().height));
        productNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productNameField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(productNameField);
    }

    private void addProductDescription() {
        //додавання опису товару
        productDescriptionLabel = new JLabel("Опис товару:");
        productDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productDescriptionLabel.setForeground(new Color(42, 48, 119));
        panel.add(productDescriptionLabel);

        //додавання поля вводу опису товару з можливістю прокрутки
        productDescriptionField = new JTextArea(7, 20);
        productDescriptionField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productDescriptionField.getPreferredSize().height));
        productDescriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productDescriptionField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        JScrollPane descriptionScrollPane = new JScrollPane(productDescriptionField);
        descriptionScrollPane.setPreferredSize(new Dimension(500, 200));
        descriptionScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(descriptionScrollPane);
    }
    private void addProductAuthor() {
        //додавання автора книги
        productAuthorLabel = new JLabel("Автор книги:");
        productAuthorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productAuthorLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productAuthorLabel.setForeground(new Color(42, 48, 119));
        panel.add(productAuthorLabel);

        //додавання поля для вводу автора книги
        productAuthorField = new JTextArea(2, 20);
        productAuthorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productNameField.getPreferredSize().height));
        productAuthorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productAuthorField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(productAuthorField);
    }

    private void addProductPublisher() {
        //додавання книжкового видавництва
        productPublisherLabel = new JLabel("Видавництво:");
        productPublisherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPublisherLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productPublisherLabel.setForeground(new Color(42, 48, 119));
        panel.add(productPublisherLabel);

        //додавання поля вводу видавництва
        productPublisherField = new JTextArea(2, 20);
        productPublisherField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productNameField.getPreferredSize().height));
        productPublisherField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPublisherField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(productPublisherField);
    }
    private void addProductQuantity() {
        //додавання кількості книжок
        productQuantityLabel = new JLabel("Кількість на складі:");
        productQuantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productQuantityLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productQuantityLabel.setForeground(new Color(42, 48, 119));
        panel.add(productQuantityLabel);

        //додавання поля вводу кількості книжок
        productQuantityField = new JTextArea(2, 20);
        productQuantityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productNameField.getPreferredSize().height));
        productQuantityField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productQuantityField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(productQuantityField);
    }
    private void addProductPrice() {
        //додавання ціни за екземпляр
        productPriceLabel = new JLabel("Ціна за екземпляр:");
        productPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPriceLabel.setFont(new Font("Helvetica", Font.BOLD, 16));
        productPriceLabel.setForeground(new Color(42, 48, 119));
        panel.add(productPriceLabel);

        //додавання поля вводу ціни за екземпляр
        productPriceField = new JTextArea(2, 20);
        productPriceField.setMaximumSize(new Dimension(Integer.MAX_VALUE, productNameField.getPreferredSize().height));
        productPriceField.setAlignmentX(Component.CENTER_ALIGNMENT);
        productPriceField.setFont(new Font("Helvetica", Font.ITALIC, 16));
        panel.add(productPriceField);
    }

    private void addProductButton(JPanel buttonPanel) {
        addProductButton = new JButton("Додати");
        addProductButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductToGroup();
                dispose();
            }
        });
        buttonPanel.add(addProductButton);
    }

    private void cancelButton(JPanel buttonPanel) {
        cancelButton = new JButton("Скасувати");
        cancelButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // дія при натисканні кнопки "Скасувати"
                dispose(); // закрити вікно
            }
        });
        buttonPanel.add(cancelButton);
    }
    private void addProductToGroup() {
        String groupName = groupNameField.getText();
        String productName = productNameField.getText();
        String productDescription = productDescriptionField.getText();
        String productAuthor = productAuthorField.getText();
        String productPublisher = productPublisherField.getText();
        int productQuantity = Integer.parseInt(productQuantityField.getText());
        double productPrice = Double.parseDouble(productPriceField.getText());

        // створення нового продукту і додавання його до групи товарів
        Product product = new Product(productName, productDescription, productAuthor, productPublisher, productQuantity, productPrice);
        Warehouse.addProductToGroup(groupName, product);
    }
}