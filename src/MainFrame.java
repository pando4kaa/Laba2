/**
 * Authors: Tuhai Anastasia, Rafikov Rinat
 * File: MainFrame.java
 * Class, that creates MainFrame.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    Color textColor = new Color(0x2F4052);
    Color mainColor = new Color(0xD8E1E9);
    Color secondColor = new Color(0xE7EBF1);
    Color thirdColor = new Color(0x759EB8);
    Color tableBackground = new Color(0xE7EAF3);

    //Ініціалізація складу
    Warehouse warehouse = new Warehouse();
    ProductsGroup choosedGroup = null;
    Product choosedFoundProduct;

    JFrame mainFrame = new JFrame();
    JPanel contentPanel = new JPanel(new GridLayout(1, 1));

    JButton mainPage;
    JButton goodsPage;
    JButton statisticsPage;
    JButton findPage;

    JTable goodsTable = new JTable();
    JTable groupTable = new JTable();
    JTable foundProductTable = new JTable();
    JTable groupPricesTable = new JTable();
    JTable allPoductsTable = new JTable();

    String[] columnNames = {"Назва", "Опис"};
    String[] goodsColumnNames = {"Назва", "Автор", "Опис", "Видавництво", "Кількість", "Ціна"};

    JPanel goodsTablePanel;
    JPanel findProductTablePanel;
    JPanel groupTablePanel;

    JPanel productInfoPanel;

    JPanel groupPricesTablePanel;
    JPanel allProductsTablePanel;

    //Goods page buttons
    JButton addGroup;
    JButton removeGroup;
    JButton editGroup;

    JButton addGoods;
    JButton removeGoods;
    JButton editGoods;

    JButton increaseGoods;
    JButton decreaseGoods;

    JButton findProducts;

    JLabel foundProductName = new JLabel();
    JLabel foundProductAuthor = new JLabel();
    JTextArea foundProductDescription = new JTextArea();
    JLabel foundProductPublisher = new JLabel();
    JLabel foundProductQuanity = new JLabel();
    JLabel foundProductPrice = new JLabel();

    JTextField nameField;
    JTextField authorField;

    /**
     * Конструктор класу, який створює вікно.
     */
    public MainFrame() throws UnsupportedLookAndFeelException {
        mainFrame.setTitle("єСклад");
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        UIManager.setLookAndFeel(new MetalLookAndFeel());
        mainFrame.setMinimumSize(new Dimension(1000, 500));
        mainFrame.setLayout(new BorderLayout());

        initMenuBar();
        initContentPanel();
        initMainPageContent();

        mainFrame.add(contentPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    /**
     * Ініціалізація контейнера з усім вмістом сторінки (без меню).
     */
    private void initContentPanel() {
        //Settings of the contentPanel
    }

    /**
     * Метод, який очищує вміст контейнера сторінки.
     */
    private void clearContentPanel() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Метод, який ініціалізує верхнє меню.
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(thirdColor);
        menuBar.setBorderPainted(false);
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Creating menu buttons
        mainPage = new JButton("Головна");
        mainPage.setFocusable(false);
        mainPage.setBackground(mainColor);
        mainPage.setForeground(textColor);

        goodsPage = new JButton("Товари");
        goodsPage.setFocusable(false);
        goodsPage.setBackground(mainColor);
        goodsPage.setForeground(textColor);
        
        findPage = new JButton("Пошук");
        findPage.setFocusable(false);
        findPage.setBackground(mainColor);
        findPage.setForeground(textColor);

        statisticsPage = new JButton("Статистика");
        statisticsPage.setFocusable(false);
        statisticsPage.setBackground(mainColor);
        statisticsPage.setForeground(textColor);

        menuBar.add(mainPage);
        menuBar.add(goodsPage);
        menuBar.add(findPage);
        menuBar.add(statisticsPage);
        
        //Adding listeners
        mainPage.addActionListener(this);
        goodsPage.addActionListener(this);
        statisticsPage.addActionListener(this);
        findPage.addActionListener(this);

        mainFrame.add(menuBar, BorderLayout.NORTH);
    }

    /**
     * Слухач для кнопок.
     * @param e the event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainPage){
            clearContentPanel();
            initMainPageContent();
        } else if(e.getSource() == goodsPage) {
            clearContentPanel();
            initGoodsPage();
        } else if (e.getSource() == findPage) {
            clearContentPanel();
            initFindPage();
        } else if (e.getSource() == statisticsPage) {
            clearContentPanel();
            initStatisticsPage();
        } else if (e.getSource() == addGroup){
            AddGroupUI addGroupDialog = new AddGroupUI(this, warehouse);
            addGroupDialog.setVisible(true);
        } else if (e.getSource() == removeGroup){
            try {
                ProductsGroup choosedGroup = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
                int choice = JOptionPane.showConfirmDialog(null, "Ви впевнені, що хочете видалити групу " + choosedGroup.getName() + "?", "Видалити групу", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    warehouse.deleteProductsGroup(choosedGroup);
                    updateGroupTable();
                    this.choosedGroup = null;
                    JOptionPane.showMessageDialog(null, "Групу " + choosedGroup.getName() + " успішно видалено");
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть групу!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == editGroup){
            try {
                ProductsGroup group = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
                AddGroupUI editGroupUI = new AddGroupUI(group,this, warehouse);
                editGroupUI.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть групу!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == addGoods){
            if (choosedGroup!=null){
                AddProductToGroupUI addProductDialog = new AddProductToGroupUI(choosedGroup, warehouse, this);
                addProductDialog.setVisible(true);
            } else{
                JOptionPane.showMessageDialog(null, "Спочатку оберіть групу!", "Помилка", JOptionPane.ERROR_MESSAGE);

            }

        } else if (e.getSource() == removeGoods){
            try {
                Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
                int choice = JOptionPane.showConfirmDialog(null, "Ви впевнені, що хочете видалити товар " + product.getName() + "?", "Видалити товар", JOptionPane.YES_NO_OPTION);
                if(choice == JOptionPane.YES_OPTION){
                    warehouse.deleteProduct(choosedGroup, product);
                    updateGoodsTable();
                    JOptionPane.showMessageDialog(null, "Групу " + choosedGroup.getName() + " успішно видалено");
                }

            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть товар!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == editGoods){
            try {
                Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
                AddProductToGroupUI addProductToGroupUI = new AddProductToGroupUI(product, choosedGroup,  warehouse, this);
                addProductToGroupUI.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть товар!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == increaseGoods){
            try {
                Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
                int oldQuanity = product.getQuantity();

                warehouse.editProductQuantity(product, oldQuanity+1);
                updateGoodsTable();
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть товар!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == decreaseGoods){
            try {
                Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
                int oldQuanity = product.getQuantity();

                if(oldQuanity <= 0){
                    JOptionPane.showMessageDialog(null, "Значення кількості товару не може бути менше за 0", "Помилка", JOptionPane.ERROR_MESSAGE);
                } else {
                    warehouse.editProductQuantity(product, oldQuanity-1);
                    updateGoodsTable();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Спочатку оберіть товар!", "Помилка", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == findProducts) {
            findProductTablePanel.removeAll();
            findProductTablePanel.revalidate();
            findProductTablePanel.repaint();
            initFoundProductTable(warehouse.findProdctsByNameAndAuthor(nameField.getText(), authorField.getText()));
            System.out.println("Назва:"+nameField.getText().isEmpty());
            System.out.println("Автор:"+authorField.getText().isEmpty());
        }
    }

    private void initFindPage() {
        JPanel findPageContent = new JPanel();
        findPageContent.setBackground(secondColor);
        findPageContent.setLayout(new GridLayout(1, 3));

        //Ліва частина
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(secondColor);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));

        JPanel fieldsForFinding = new JPanel();
        fieldsForFinding.setBackground(secondColor);

        GroupLayout groupLayoutLeft = new GroupLayout(fieldsForFinding);
        fieldsForFinding.setLayout(groupLayoutLeft);
        groupLayoutLeft.setAutoCreateGaps(true);
        groupLayoutLeft.setAutoCreateContainerGaps(true);

        JLabel nameLabel = new JLabel("Назва:");
        JLabel authorLabel = new JLabel("Автор:");

        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
        authorField = new JTextField();
        authorField.setMaximumSize(new Dimension(Integer.MAX_VALUE, authorField.getPreferredSize().height));

        groupLayoutLeft.setHorizontalGroup(groupLayoutLeft.createSequentialGroup()
                .addGroup(groupLayoutLeft.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameLabel)
                        .addComponent(authorLabel))
                .addGroup(groupLayoutLeft.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameField)
                        .addComponent(authorField)));

        groupLayoutLeft.setVerticalGroup(groupLayoutLeft.createSequentialGroup()
                .addGroup(groupLayoutLeft.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(nameLabel)
                        .addComponent(nameField))
                .addGroup(groupLayoutLeft.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(authorLabel)
                        .addComponent(authorField)));

        findProducts = new JButton("Знайти");
        findProducts.setFocusable(false);
        findProducts.setForeground(textColor);
        findProducts.setBackground(mainColor);
        findProducts.addActionListener(this);

        leftPanel.add(fieldsForFinding);
        leftPanel.add(findProducts);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(secondColor);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        findProductTablePanel = new JPanel();
        findProductTablePanel.setBackground(secondColor);
        findProductTablePanel.setLayout(new GridLayout(1, 1));
        findProductTablePanel.setBorder(new EtchedBorder());

        initFoundProductTable(new Object[][]{});
        centerPanel.add(findProductTablePanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.RED);
        rightPanel.setLayout(new GridLayout(1, 1));

        productInfoPanel = new JPanel();
        productInfoPanel.setBackground(secondColor);
        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.PAGE_AXIS));

        initProductInfoPanel(null);
        rightPanel.add(productInfoPanel);

        findPageContent.add(leftPanel);
        findPageContent.add(centerPanel);
        findPageContent.add(rightPanel);

        contentPanel.add(findPageContent);
    }

    private void initProductInfoPanel(Product product) {
        if(product != null){
            foundProductName.setText("Назва: "+product.getName());
            foundProductName.setForeground(textColor);
            foundProductName.setBackground(secondColor);
            foundProductAuthor.setText("Автор: "+product.getAuthor());
            foundProductAuthor.setForeground(textColor);
            foundProductAuthor.setBackground(secondColor);
            foundProductDescription = new JTextArea(product.getDescription());
            foundProductPublisher.setText("Видавництво: "+product.getPublisher());
            foundProductPublisher.setForeground(textColor);
            foundProductPublisher.setBackground(secondColor);
            foundProductQuanity.setText("Кількість на складі: "+product.getQuantity());
            foundProductQuanity.setForeground(textColor);
            foundProductQuanity.setBackground(secondColor);
            foundProductPrice.setText("Ціна за одиницю: "+ product.getPrice());
            foundProductPrice.setForeground(textColor);
            foundProductPrice.setBackground(secondColor);

            foundProductDescription.setEditable(false);
            foundProductDescription.setLineWrap(true);
            foundProductDescription.setOpaque(true);
            foundProductDescription.setForeground(textColor);
            foundProductDescription.setBackground(secondColor);

            JScrollPane scrollPane = new JScrollPane(foundProductDescription);
            scrollPane.getViewport().setBackground(mainColor);

            productInfoPanel.add(foundProductName);
            productInfoPanel.add(foundProductAuthor);

            productInfoPanel.add(foundProductPublisher);
            productInfoPanel.add(foundProductQuanity);
            productInfoPanel.add(foundProductPrice);
            JLabel desclabel = new JLabel("Опис");
            productInfoPanel.add(desclabel);
            productInfoPanel.add(scrollPane);
        }
    }

    private void initFoundProductTable(Object[][] array) {
        foundProductTable = new JTable(array, new String[]{"Продукт"});
        foundProductTable.setShowGrid(true);
        foundProductTable.setGridColor(Color.BLACK);
        foundProductTable.setBackground(tableBackground);
        foundProductTable.setOpaque(false);
        foundProductTable.getTableHeader().setBackground(mainColor);
        foundProductTable.getTableHeader().setForeground(textColor);

        foundProductTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if ( !e.getValueIsAdjusting() && !lsm.isSelectionEmpty()) {
                    choosedFoundProduct = (Product) foundProductTable.getValueAt(foundProductTable.getSelectedRow(), 0);
                    productInfoPanel.removeAll();
                    productInfoPanel.revalidate();
                    productInfoPanel.repaint();
                    initProductInfoPanel(choosedFoundProduct);
                }
            }
        });

        for(int i = 0; i < foundProductTable.getColumnCount(); i++){
            foundProductTable.getColumnModel().getColumn(i).setCellRenderer(new MyCellRenderer());
        }

        JScrollPane scrollPane = new JScrollPane(foundProductTable);
        scrollPane.getViewport().setBackground(tableBackground);
        findProductTablePanel.add(scrollPane);
    }

    /**
     * Метод, який оновлює таблицю груп.
     */
    protected void updateGroupTable() {
        groupTablePanel.removeAll();
        groupTablePanel.revalidate();
        groupTablePanel.repaint();

        initGroupTable(warehouse);
        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if ( !e.getValueIsAdjusting() && !lsm.isSelectionEmpty()) {
                    choosedGroup = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
                    updateGoodsTable();
                }
            }
        });
        goodsTablePanel.removeAll();
        goodsTablePanel.revalidate();
        goodsTablePanel.repaint();
    }


    /**
     * Метод, який оновлює таблицю товарів.
     */
    protected void updateGoodsTable() {
        ProductsGroup group;
        try {
            group = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
        } catch (Exception exception){
            group = choosedGroup;
        }

        goodsTablePanel.removeAll();
        goodsTablePanel.revalidate();
        goodsTablePanel.repaint();

        goodsTable.removeAll();
        goodsTable = new JTable(GoodsParser.parseGroupGoods(group), goodsColumnNames);
        goodsTable.setBackground(tableBackground);
        goodsTable.getTableHeader().setBackground(mainColor);
        goodsTable.getTableHeader().setForeground(textColor);
        MyCellRenderer cellRenderer = new MyCellRenderer();
        for(int i = 0; i < goodsTable.getColumnCount(); i++){
            goodsTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        goodsTable.getColumnModel().getColumn(2).setMinWidth(200);
        goodsTable.getColumnModel().getColumn(3).setWidth(50);
        goodsTable.getColumnModel().getColumn(4).setWidth(50);

        goodsTable.setShowGrid(true);
        goodsTable.setGridColor(Color.BLACK);

        JScrollPane goodsTableScrollLambda = new JScrollPane(goodsTable);
        goodsTableScrollLambda.getViewport().setBackground(tableBackground);
        goodsTablePanel.add(goodsTableScrollLambda);
    }

    /**
     * Метод, який ініціалізує вміст головної сторінки.
     */
    private void initMainPageContent() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.PAGE_AXIS));
        mainContentPanel.setBackground(secondColor);

        JLabel welcomeText = new JLabel("Вітаємо на нашому складі інтернет-книгарні \"ЯкаКнига\"");
        welcomeText.setBorder(new EmptyBorder(135, 0,10, 0));
        welcomeText.setForeground(textColor);
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setFont(new Font("Helvetica", Font.BOLD, 20));
        mainContentPanel.add(welcomeText);

        JLabel instructionsText = new JLabel("Для того щоб змінити сторінку натисність одну з кнопок зверху.");
        instructionsText.setForeground(textColor);
        instructionsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsText.setFont(new Font("Helvetica", Font.ITALIC, 16));
        mainContentPanel.add(instructionsText);

        JLabel mainText = new JLabel("Головна - повертає вас на цю сторінку");
        mainText.setForeground(textColor);
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(mainText);

        JLabel goodsText = new JLabel("Товари - сторінка для керування групами та їх товарами");
        goodsText.setForeground(textColor);
        goodsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        goodsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(goodsText);

        JLabel statisticsText = new JLabel("Статистика - сторінка з статистичними даними");
        statisticsText.setForeground(textColor);
        statisticsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(statisticsText);

        JLabel aboutText = new JLabel("Пошук - сторінка для пошуку товарів за автором і назвою");
        aboutText.setForeground(textColor);
        aboutText.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(aboutText);

        contentPanel.add(mainContentPanel);
    }

    /**
     * Метод, який ініціалізує вміст сторінки "Статистика".
     */
    private void initStatisticsPage() {
        JPanel statiscticsContentPanel = new JPanel();
        statiscticsContentPanel.setBackground(secondColor);
        statiscticsContentPanel.setLayout(new BorderLayout());

        JPanel statiscticsDataPanel = new JPanel();
        statiscticsDataPanel.setBackground(secondColor);

        JLabel totalValueLabel = new JLabel("Загальна вартість товару: "+
                Math.round(Statistics.calculateTotalValue((ArrayList<ProductsGroup>) warehouse.getGroups())*100000.0)/100000.0
                +" од.");

        totalValueLabel.setBackground(secondColor);
        totalValueLabel.setForeground(textColor);

        groupPricesTablePanel = new JPanel();
        groupPricesTablePanel.setBackground(secondColor);
        groupPricesTablePanel.setLayout(new GridLayout(1, 1));
        groupPricesTablePanel.setPreferredSize(new Dimension(250, Integer.MAX_VALUE));

        initGroupPricesTable();

        groupPricesTablePanel.setMaximumSize(new Dimension(100, Integer.MAX_VALUE));

        allProductsTablePanel = new JPanel();
        allProductsTablePanel.setBackground(secondColor);

        allProductsTablePanel.setLayout(new GridLayout(1, 1));

        initAllProductsTable();

        statiscticsDataPanel.add(totalValueLabel);
        statiscticsContentPanel.add(statiscticsDataPanel, BorderLayout.NORTH);
        statiscticsContentPanel.add(groupPricesTablePanel, BorderLayout.WEST);
        statiscticsContentPanel.add(allProductsTablePanel, BorderLayout.CENTER);
        contentPanel.add(statiscticsContentPanel);
    }

    private void initAllProductsTable() {
        allPoductsTable = new JTable(GoodsParser.parseAllGoods(warehouse), new String[]{"Група", "Назва", "Автор", "Опис", "Видавництво", "Кількість", "Ціна"});
        allPoductsTable.setBackground(tableBackground);
        allPoductsTable.setForeground(textColor);
        allPoductsTable.getTableHeader().setForeground(textColor);
        allPoductsTable.getTableHeader().setBackground(mainColor);

        for(int i = 0; i < allPoductsTable.getColumnCount(); i++) {
            allPoductsTable.getColumnModel().getColumn(i).setCellRenderer(new MyCellRenderer());
        }
        allPoductsTable.getColumnModel().getColumn(5).setMaxWidth(75);
        allPoductsTable.getColumnModel().getColumn(6).setMaxWidth(75);
        
        JScrollPane jScrollPane = new JScrollPane(allPoductsTable);
        jScrollPane.getViewport().setBackground(tableBackground);
        allProductsTablePanel.add(jScrollPane);
    }

    private void initGroupPricesTable() {
        groupPricesTable = new JTable(GoodsParser.getGroupPrices(warehouse), new String[]{"Група", "Ціна"});
        groupPricesTable.setBackground(tableBackground);
        groupPricesTable.setForeground(textColor);
        groupPricesTable.getTableHeader().setForeground(textColor);
        groupPricesTable.getTableHeader().setBackground(mainColor);

        for(int i = 0; i < groupPricesTable.getColumnCount(); i++){
            groupPricesTable.getColumnModel().getColumn(i).setCellRenderer(new MyCellRenderer());
        }
        JScrollPane jScrollPane = new JScrollPane(groupPricesTable);
        jScrollPane.getViewport().setBackground(tableBackground);
        groupPricesTablePanel.add(jScrollPane);
    }

    /**
     * Метод, який ініціалізує вміст сторінки "Товари".
     */
    private void initGoodsPage() {

        //Головне найбільше вікно, яке тримає усі інші обʼєкти в собі
        JPanel goodsContentPanel = new JPanel();
        goodsContentPanel.setBackground(secondColor);
        goodsContentPanel.setLayout(new BorderLayout());

        //ЛІВА ЧАСТИНА ВІКНА

        //Створення лівого вікна для групи товарів та кнопок
        JPanel groupPanel = new JPanel();
        groupPanel.setBackground(secondColor);
        groupPanel.setPreferredSize(new Dimension(400, 0));
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));

        groupTablePanel = new JPanel(new GridLayout(1, 1));
        groupTablePanel.setBackground(secondColor);
        groupTablePanel.setBorder(new EmptyBorder(5,5,5,5));

        initGroupTable(warehouse);

        JPanel groupButtonsPanel = new JPanel();
        groupButtonsPanel.setBackground(secondColor);
        groupButtonsPanel.setLayout(new BoxLayout(groupButtonsPanel, BoxLayout.X_AXIS));

        addGroup = new JButton("Додати");
        addGroup.setBackground(mainColor);
        addGroup.setFocusable(false);
        addGroup.setForeground(textColor);
        removeGroup = new JButton("Видалити");
        removeGroup.setBackground(mainColor);
        removeGroup.setFocusable(false);
        removeGroup.setForeground(textColor);
        editGroup = new JButton("Редагувати");
        editGroup.setBackground(mainColor);
        editGroup.setFocusable(false);
        editGroup.setForeground(textColor);

        addGroup.addActionListener(this);
        removeGroup.addActionListener(this);
        editGroup.addActionListener(this);

        groupButtonsPanel.add(addGroup);
        groupButtonsPanel.add(removeGroup);
        groupButtonsPanel.add(editGroup);

        groupPanel.add(groupTablePanel);
        groupPanel.add(groupButtonsPanel);

        //ЦЕНТРАЛЬНА ЧАСТИНА ВІКНА

        JPanel goodsPanel = new JPanel();
        goodsPanel.setBackground(secondColor);
        goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));

        goodsTablePanel = new JPanel(new GridLayout(1, 1));
        goodsPanel.setBackground(secondColor);
        goodsTablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        if(choosedGroup == null){
            goodsTable = new JTable();
            goodsTable.setBackground(tableBackground);
            goodsTable.getTableHeader().setBackground(mainColor);
            goodsTable.getTableHeader().setForeground(textColor);

            JScrollPane goodsTableScroll = new JScrollPane(goodsTable);
            goodsTableScroll.getViewport().setBackground(tableBackground);
            goodsTablePanel.add(goodsTableScroll);
        } else {
            updateGoodsTable();
        }

        //Створення слухача для реакції на вибір рядка в таблиці з групами
        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if ( !e.getValueIsAdjusting() && !lsm.isSelectionEmpty()) {
                    choosedGroup = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
                    updateGoodsTable();
                }
            }
        });

        JPanel goodsButtonsPanel = new JPanel();
        goodsButtonsPanel.setLayout(new BoxLayout(goodsButtonsPanel, BoxLayout.X_AXIS));

        increaseGoods = new JButton("+");
        increaseGoods.setBackground(mainColor);
        increaseGoods.setFocusable(false);
        increaseGoods.setForeground(textColor);
        decreaseGoods = new JButton("-");
        decreaseGoods.setBackground(mainColor);
        decreaseGoods.setFocusable(false);
        decreaseGoods.setForeground(textColor);

        addGoods = new JButton("Додати");
        addGoods.setBackground(mainColor);
        addGoods.setFocusable(false);
        addGoods.setForeground(textColor);
        removeGoods = new JButton("Видалити");
        removeGoods.setBackground(mainColor);
        removeGoods.setFocusable(false);
        removeGoods.setForeground(textColor);
        editGoods = new JButton("Редагувати");
        editGoods.setBackground(mainColor);
        editGoods.setFocusable(false);
        editGoods.setForeground(textColor);

        increaseGoods.addActionListener(this);
        decreaseGoods.addActionListener(this);
        addGoods.addActionListener(this);
        removeGoods.addActionListener(this);
        editGoods.addActionListener(this);

        goodsButtonsPanel.add(increaseGoods);
        goodsButtonsPanel.add(decreaseGoods);

        goodsButtonsPanel.add(addGoods);
        goodsButtonsPanel.add(removeGoods);
        goodsButtonsPanel.add(editGoods);

        goodsPanel.add(goodsTablePanel);
        goodsPanel.add(goodsButtonsPanel);

        goodsContentPanel.add(groupPanel, BorderLayout.WEST);
        goodsContentPanel.add(goodsPanel, BorderLayout.CENTER);

        contentPanel.add(goodsContentPanel);
    }

    /**
     * Метод, що ініціалізує контейнер з таблицею з групами товарів.
     * @param warehouse склад.
     */
    private void initGroupTable(Warehouse warehouse) {
        //Створення таблиці з групами товарів
        groupTable = new JTable(GoodsParser.parseGroups(warehouse), columnNames);
        groupTable.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
        groupTable.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        groupTable.setShowGrid(true);
        groupTable.setGridColor(Color.BLACK);
        groupTable.setForeground(textColor);
        groupTable.setBackground(tableBackground);
        groupTable.setOpaque(true);
        groupTable.getTableHeader().setForeground(textColor);
        groupTable.getTableHeader().setBackground(mainColor);

        JScrollPane scrollPaneTable = new JScrollPane(groupTable);
        scrollPaneTable.getViewport().setBackground(tableBackground);
        groupTablePanel.add(scrollPaneTable);
    }
}
