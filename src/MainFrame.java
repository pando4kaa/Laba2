import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    //Ініціалізація складу
    Warehouse warehouse = new Warehouse();
    ProductsGroup choosedGroup = null;
    Product choosedFoundProduct;

    JFrame mainFrame = new JFrame();
    JPanel contentPanel = new JPanel(new GridLayout(1, 1));

    JButton mainPage;
    JButton goodsPage;
    JButton statisticsPage;
    JButton aboutPage;
    JButton findPage;

    JTable goodsTable = new JTable();
    JTable groupTable = new JTable();
    JTable foundProductTable = new JTable();

    String[] columnNames = {"Назва", "Опис"};
    String[] goodsColumnNames = {"Назва", "Автор", "Опис", "Видавництво", "Кількість", "Ціна"};

    JPanel goodsTablePanel;
    JPanel findProductTablePanel;
    JPanel groupTablePanel;

    JPanel productInfoPanel;

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
        menuBar.setBorderPainted(false);
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Creating menu buttons
        mainPage = new JButton("Головна");

        goodsPage = new JButton("Товари");
        
        findPage = new JButton("Пошук");

        statisticsPage = new JButton("Статистика");

        aboutPage = new JButton("Про застосунок");

        menuBar.add(mainPage);
        menuBar.add(goodsPage);
        menuBar.add(findPage);
        menuBar.add(statisticsPage);
        menuBar.add(aboutPage);
        
        //Adding listeners
        mainPage.addActionListener(this);
        goodsPage.addActionListener(this);
        statisticsPage.addActionListener(this);
        aboutPage.addActionListener(this);
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
        } else if (e.getSource() == aboutPage) {
            clearContentPanel();
            initAboutPage();
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
        findPageContent.setLayout(new GridLayout(1, 3));

        //Ліва частина
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));

        JPanel fieldsForFinding = new JPanel();

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
        findProducts.addActionListener(this);

        leftPanel.add(fieldsForFinding);
        leftPanel.add(findProducts);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        findProductTablePanel = new JPanel();
        findProductTablePanel.setLayout(new GridLayout(1, 1));
        findProductTablePanel.setBorder(new EtchedBorder());

        initFoundProductTable(new Object[][]{});
        centerPanel.add(findProductTablePanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.RED);
        rightPanel.setLayout(new GridLayout(1, 1));

        productInfoPanel = new JPanel();
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
            foundProductName.setHorizontalTextPosition(SwingConstants.LEFT);
            foundProductAuthor.setText("Автор: "+product.getAuthor());
            foundProductDescription = new JTextArea(product.getDescription());
            foundProductPublisher.setText("Видавництво: "+product.getPublisher());
            foundProductQuanity.setText("Кількість на складі: "+product.getQuantity());
            foundProductPrice.setText("Ціна за одиницю: "+ product.getPrice());

            foundProductDescription.setEditable(false);
            foundProductDescription.setLineWrap(true);

            JScrollPane scrollPane = new JScrollPane(foundProductDescription);

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

        JScrollPane scrollPane = new JScrollPane(foundProductTable);
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
        goodsTablePanel.add(goodsTableScrollLambda);
    }

    /**
     * Метод, який ініціалізує вміст головної сторінки.
     */
    private void initMainPageContent() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.PAGE_AXIS));
        mainContentPanel.setBackground(new Color(206, 236, 246));

        JLabel welcomeText = new JLabel("Вітаємо у нашій інтернет-книгарні \"ЯкаКнига\"");
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeText.setBorder(new EmptyBorder(20,0,10,0));
        mainContentPanel.add(welcomeText);

        JLabel instructionsText = new JLabel("Для того щоб змінити сторінку натисність одну з кнопок зверху.");
        instructionsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsText.setFont(new Font("Helvetica", Font.ITALIC, 16));
        mainContentPanel.add(instructionsText);

        JLabel mainText = new JLabel("<html><b>Головна</b> - повертає вас на цю сторінку</html>");
        //<div style='text-align: center; width:950'>
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(mainText);

        JLabel goodsText = new JLabel("<html><b>Товари</b> - сторінка для керування групами та їх товарами</html>");
        goodsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        goodsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(goodsText);

        JLabel statisticsText = new JLabel("<html><b>Статистика</b> - сторінка з статистичними даними</html>");
        statisticsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(statisticsText);

        JLabel aboutText = new JLabel("<html><b>Про застосунок</b> - сторінка про програму та розробників</html>");
        aboutText.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(aboutText);

        contentPanel.add(mainContentPanel);
    }

    /**
     * Метод, який ініціалізує вміст сторінки "Про програму".
     */
    private void initAboutPage() {
        JPanel aboutContentPanel = new JPanel();
        aboutContentPanel.setBackground(Color.blue);
        aboutContentPanel.setLayout(new FlowLayout());

        contentPanel.add(aboutContentPanel);
    }

    /**
     * Метод, який ініціалізує вміст сторінки "Статистика".
     */
    private void initStatisticsPage() {
        JPanel statiscticsContentPanel = new JPanel();
        statiscticsContentPanel.setLayout(new BorderLayout());
    }

    /**
     * Метод, який ініціалізує вміст сторінки "Товари".
     */
    private void initGoodsPage() {

        //Головне найбільше вікно, яке тримає усі інші обʼєкти в собі
        JPanel goodsContentPanel = new JPanel();
        goodsContentPanel.setLayout(new BorderLayout());

        //ЛІВА ЧАСТИНА ВІКНА

        //Створення лівого вікна для групи товарів та кнопок
        JPanel groupPanel = new JPanel();
        groupPanel.setPreferredSize(new Dimension(400, 0));
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));

        groupTablePanel = new JPanel(new GridLayout(1, 1));
        groupTablePanel.setBorder(new EmptyBorder(5,5,5,5));

        initGroupTable(warehouse);

        JPanel groupButtonsPanel = new JPanel();
        groupButtonsPanel.setLayout(new BoxLayout(groupButtonsPanel, BoxLayout.X_AXIS));

        addGroup = new JButton("Додати");
        removeGroup = new JButton("Видалити");
        editGroup = new JButton("Редагувати");

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
        goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));

        goodsTablePanel = new JPanel(new GridLayout(1, 1));
        goodsTablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        if(choosedGroup == null){
            goodsTable = new JTable();
            JScrollPane goodsTableScroll = new JScrollPane(goodsTable);
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
        decreaseGoods = new JButton("-");

        addGoods = new JButton("Додати");
        removeGoods = new JButton("Видалити");
        editGoods = new JButton("Редагувати");

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

        JScrollPane scrollPaneTable = new JScrollPane(groupTable);
        groupTablePanel.add(scrollPaneTable);
    }
}
