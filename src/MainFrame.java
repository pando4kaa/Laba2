import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    //Ініціалізація складу
    Warehouse warehouse = new Warehouse();
    String choosedGroup = null;

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
    public MainFrame(){
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 500);
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
            //Кнопка додавання групи
        } else if (e.getSource() == removeGroup){
            //Кнопка видалення групи
        } else if (e.getSource() == editGroup){
            //Кнопка редагування групи
        } else if (e.getSource() == addGoods){
            //Кнопка додавання товарів
        } else if (e.getSource() == removeGoods){
            //Кнопка видалення товарів
        } else if (e.getSource() == editGoods){
            //Кнопка редагування товарів
        } else if (e.getSource() == increaseGoods){
            String groupName;
            try {
                groupName = (String) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            } catch (Exception exception){
                groupName = choosedGroup;
            }

            String productName = (String) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
            int oldQuanity = Integer.parseInt((String) goodsTable.getValueAt(goodsTable.getSelectedRow(), 3));

            warehouse.editProductQuantity(groupName, productName, oldQuanity+1);
            updateGoodsTable(groupName);
        } else if (e.getSource() == decreaseGoods){
            String groupName;
            try {
                groupName = (String) groupTable.getValueAt(groupTable.getSelectedRow(), 0);
            } catch (Exception exception){
                groupName = choosedGroup;
            }

            String productName = (String) goodsTable.getValueAt(goodsTable.getSelectedRow(), 0);
            int oldQuanity = Integer.parseInt((String) goodsTable.getValueAt(goodsTable.getSelectedRow(), 3));

            if(oldQuanity <= 0){
                JOptionPane.showMessageDialog(null, "Значення кількості товару не може бути менше за 0", "Помилка", JOptionPane.ERROR_MESSAGE);
            } else {
                warehouse.editProductQuantity(groupName, productName, oldQuanity-1);
                updateGoodsTable(groupName);
            }
        }
    }

    /**
     * Метод, який оновлює таблицю товарів.
     * @param groupTitle група, товари якої будуть зображені.
     */
    private void updateGoodsTable(String groupTitle) {
        goodsTablePanel.removeAll();
        goodsTablePanel.revalidate();
        goodsTablePanel.repaint();

        goodsTable = new JTable(GoodsParser.parseGroupGoods(warehouse.findGroupByTitle(groupTitle)), goodsColumnNames);
        goodsTable.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
        goodsTable.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        goodsTable.getColumnModel().getColumn(2).setCellRenderer(new MyCellRenderer());

        goodsTable.getColumnModel().getColumn(2).setMinWidth(100);
        goodsTable.getColumnModel().getColumn(2).setMinWidth(75);
        goodsTable.getColumnModel().getColumn(2).setMinWidth(200);
        goodsTable.getColumnModel().getColumn(3).setMaxWidth(50);
        goodsTable.getColumnModel().getColumn(4).setMaxWidth(50);

        goodsTable.setShowGrid(true);
        goodsTable.setGridColor(Color.BLACK);

        JScrollPane goodsTableScrollLambda = new JScrollPane(goodsTable);
        goodsTablePanel.add(goodsTableScrollLambda);;
    }

    /**
     * Метод, який ініціалізує вміст головної сторінки.
     */
    private void initMainPageContent() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.PAGE_AXIS));

        JLabel welcomeText = new JLabel("Вітаємо у нашій інтернет-книгарні \"ЯкаКнига\"");
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setFont(new Font("Helvetica", Font.PLAIN, 20));
        welcomeText.setBorder(new EmptyBorder(20,0,10,0));
        mainContentPanel.add(welcomeText);

        JLabel instructionsText = new JLabel("Для того щоб змінити сторінку натисність одну з кнопок зверху.");
        instructionsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(instructionsText);

        JLabel mainText = new JLabel("Головна - повертає вас на цю сторінку");
        mainText.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(mainText);

        JLabel goodsText = new JLabel("Товари - сторінка для керування групами та їх товарами");
        goodsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        goodsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(goodsText);

        JLabel statisticsText = new JLabel("Статистика - сторінка з статистичними даними");
        statisticsText.setAlignmentX(Component.CENTER_ALIGNMENT);
        statisticsText.setFont(new Font("Helvetica", Font.PLAIN, 16));
        mainContentPanel.add(statisticsText);

        JLabel aboutText = new JLabel("Про застосунок - сторінка про програму та розробників");
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

        JPanel groupTablePanel = initGroupTable(warehouse);

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
                    choosedGroup = groupTable.getValueAt(groupTable.getSelectedRow(), 0).toString();
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
     * @return контейнер з таблицею.
     */
    private JPanel initGroupTable(Warehouse warehouse) {
        //Створення контейнера з таблицею для груп товарів
        JPanel tablePanel = new JPanel(new GridLayout(1, 1));
        tablePanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        //Створення таблиці з групами товарів
        groupTable = new JTable(GoodsParser.parseGroups(warehouse), columnNames);
        groupTable.getColumnModel().getColumn(0).setCellRenderer(new MyCellRenderer());
        groupTable.getColumnModel().getColumn(1).setCellRenderer(new MyCellRenderer());
        groupTable.setShowGrid(true);
        groupTable.setGridColor(Color.BLACK);

        JScrollPane scrollPaneTable = new JScrollPane(groupTable);
        tablePanel.add(scrollPaneTable);
        return tablePanel;
    }
}
