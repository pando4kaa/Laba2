import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    //Ініціалізація складу
    Warehouse warehouse = new Warehouse();
    ProductsGroup choosedGroup = null;

    JFrame mainFrame = new JFrame();
    JPanel contentPanel = new JPanel(new GridLayout(1, 1));

    JButton mainPage;
    JButton goodsPage;
    JButton statisticsPage;
    JButton aboutPage;

    JTable goodsTable = new JTable();
    JTable groupTable = new JTable();

    String[] columnNames = {"Назва", "Опис"};
    String[] goodsColumnNames = {"Назва", "Автор", "Опис", "Кількість", "Ціна"};

    JPanel goodsTablePanel;
    JPanel groupTablePanel;

    //Goods page buttons
    JButton addGroup;
    JButton removeGroup;
    JButton editGroup;

    JButton addGoods;
    JButton removeGoods;
    JButton editGoods;

    JButton increaseGoods;
    JButton decreaseGoods;

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

        statisticsPage = new JButton("Статистика");

        aboutPage = new JButton("Про застосунок");

        menuBar.add(mainPage);
        menuBar.add(goodsPage);
        menuBar.add(statisticsPage);
        menuBar.add(aboutPage);
        
        //Adding listeners
        mainPage.addActionListener(this);
        goodsPage.addActionListener(this);
        statisticsPage.addActionListener(this);
        aboutPage.addActionListener(this);

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
        } else if(e.getSource() == goodsPage){
            clearContentPanel();
            initGoodsPage();
        } else if (e.getSource() == statisticsPage) {
            clearContentPanel();
            initStatisticsPage();
        } else if (e.getSource() == aboutPage) {
            clearContentPanel();
            initAboutPage();
        } else if (e.getSource() == addGroup){
            AddGroupUI addGroupDialog = new AddGroupUI();
            addGroupDialog.setVisible(true);
            updateGroupTable();
        } else if (e.getSource() == removeGroup){
            ProductsGroup choosedGroup = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            int choice = JOptionPane.showConfirmDialog(null, "Ви впевнені, що хочете видалити групу " + choosedGroup.getName() + "?", "Видалити групу", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                warehouse.deleteProductsGroup(choosedGroup);
                updateGroupTable();
                JOptionPane.showMessageDialog(null, "Групу " + choosedGroup.getName() + " успішно видалено");
            }
        } else if (e.getSource() == editGroup){
            //Кнопка редагування групи
        } else if (e.getSource() == addGoods){
            ProductsGroup group;
            try {
                group = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            } catch (Exception exception){
                group = choosedGroup;
            }

            AddProductToGroupUI addProductDialog = new AddProductToGroupUI(choosedGroup, warehouse, this);
            addProductDialog.setVisible(true);
        } else if (e.getSource() == removeGoods){
            //Кнопка видалення товарів
        } else if (e.getSource() == editGoods){
            //Кнопка редагування товарів
        } else if (e.getSource() == increaseGoods){
            ProductsGroup group;
            try {
                group = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            } catch (Exception exception){
                group = choosedGroup;
            }

            Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
            int oldQuanity = product.getQuantity();

            warehouse.editProductQuantity(product, oldQuanity+1);
            updateGoodsTable(group);
        } else if (e.getSource() == decreaseGoods){
            ProductsGroup group;
            try {
                group = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            } catch (Exception exception){
                group = choosedGroup;
            }

            Product product = (Product) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
            int oldQuanity = product.getQuantity();

            if(oldQuanity <= 0){
                JOptionPane.showMessageDialog(null, "Значення кількості товару не може бути менше за 0", "Помилка", JOptionPane.ERROR_MESSAGE);
            } else {
                warehouse.editProductQuantity(product, oldQuanity-1);
                updateGoodsTable(group);
            }
        }
    }

    /**

     Метод, який оновлює таблицю груп.
     */
    private void updateGroupTable() {
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
                    updateGoodsTable(choosedGroup);
                }
            }
        });
        goodsTablePanel.removeAll();
        goodsTablePanel.revalidate();
        goodsTablePanel.repaint();
    }


    /**
     * Метод, який оновлює таблицю товарів.
     * @param group група, товари якої будуть зображені.
     */
    protected void updateGoodsTable(ProductsGroup group) {
        goodsTablePanel.removeAll();
        goodsTablePanel.revalidate();
        goodsTablePanel.repaint();

        goodsTable.removeAll();
        goodsTable = new JTable(GoodsParser.parseGroupGoods(group), goodsColumnNames);
        MyCellRenderer cellRenderer = new MyCellRenderer();
        for(int i = 0; i < goodsTable.getColumnCount()-2; i++){
            goodsTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        goodsTable.getColumnModel().getColumn(2).setMinWidth(200);
        goodsTable.getColumnModel().getColumn(3).setWidth(50);
        goodsTable.getColumnModel().getColumn(4).setWidth(50);

        goodsTable.setShowGrid(true);
        goodsTable.setGridColor(Color.BLACK);

        JScrollPane goodsTableScrollLambda = new JScrollPane(goodsTable);
        goodsTablePanel.add(goodsTableScrollLambda);
        System.out.println("dsafsdfdsa");
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
            updateGoodsTable(choosedGroup);
        }

        //Створення слухача для реакції на вибір рядка в таблиці з групами
        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                if ( !e.getValueIsAdjusting() && !lsm.isSelectionEmpty()) {
                    choosedGroup = (ProductsGroup) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
                    updateGoodsTable(choosedGroup);
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
