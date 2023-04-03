package AppFrames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    JFrame mainFrame = new JFrame();
    JPanel contentPanel = new JPanel();

    JButton mainPage;
    JButton goodsPage;
    JButton statisticsPage;
    JButton aboutPage;

    public MainFrame(){
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 700);
        mainFrame.setLayout(new BorderLayout());

        initMenuBar();
        initContentPanel();
        initMainPageContent();

        mainFrame.add(contentPanel, BorderLayout.CENTER);

        mainFrame.setVisible(true);
    }

    private void initContentPanel() {
        //Settings of the contentPanel
    }

    private void initMainPageContent() {
        clearContentPanel();
        JLabel welcomeText = new JLabel("Вітаємо у нашій інтернет-книгарні \"ЯкаКнига\"");
        welcomeText.setFont(new Font("Helvetica", Font.PLAIN, 20));
        welcomeText.setBorder(new EmptyBorder(20,0,10,0));
        contentPanel.add(welcomeText);
    }

    private void clearContentPanel() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

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
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mainPage){
            initMainPageContent();
        } else if(e.getSource() == goodsPage){
            initGoodsPage();
        } else if (e.getSource() == statisticsPage) {
            //load statistics page

        } else if (e.getSource() == aboutPage) {
            //load about page

        }
    }

    private void initGoodsPage() {
        clearContentPanel();
        JPanel goodsContentPanel = new JPanel();
        goodsContentPanel.setLayout(new BorderLayout());
    }
}
