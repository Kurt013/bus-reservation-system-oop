package com.shivajivarma.brs.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;

@SuppressWarnings("serial")
public class HomeTabsPanelView extends BaseView implements View {

    private JTabbedPane tabs;
    private JLabel welcome, date;
    private JButton logoutButton;
    private BufferedImage backgroundImage;
    private Timer timer;

    public HomeTabsPanelView() {
        super(); // Call superclass constructor

        try {
            // Load the background image using ResourcePaths.REGISTER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

        Color jaclinery = new Color(246, 209, 0);

        this.initializeLabels();
        this.initializeComponents();
        this.setupTabChangeListener();
        this.setBackground(jaclinery);
        this.startTimer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void setDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
        String formattedDate = formatter.format(currentDate);
        this.date.setText(formattedDate);
    }

    private void startTimer() {
        timer = new Timer(1000, e -> setDate()); // Update every second (1000 milliseconds)
        timer.start();
    }

    private void initializeLabels() {
        welcome = ViewComponentFactory.createJLabelNormal("This is where the welcome message will show up", new int[]{47, 6, 300, 30}, Color.black);
        date = ViewComponentFactory.createJLabelNormal("This is where the date will show up", new int[]{683, 50, 300, 30}, Color.black);
        welcome.setFont(ViewComponentFactory.FONT_HOME);
        welcome.setForeground(new Color(182, 4, 11));
        date.setForeground(new Color(182, 4, 11));
        this.add(welcome);
        this.add(date);
    }

    private void initializeComponents() {
        tabs = new JTabbedPane();
        tabs.setBounds(0, 40, 1024, 500);
        tabs.setFont(ViewComponentFactory.FONT_TABS);
    
        Color jaclinerr = new Color(220, 2, 11);
        logoutButton = ViewComponentFactory.createJButtonNormal(Labels.LOGOUT, new int[]{900, 10, 100, 32}, Color.WHITE, jaclinerr);

        // Remove focus border from logout button
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        tabs.setFocusable(false);
        tabs.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.add(tabs);
        this.add(logoutButton);

        tabs.setUI(new CustomTabbedPaneUI(jaclinerr, Color.white, Color.white, jaclinerr));
    }

    public void insertTab(View tab, String tabName) {
        tabs.add((JPanel) tab, tabName);
        this.revalidate();
        this.repaint();
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }

    public void setWelcome(String username) {
        this.welcome.setText("Welcome, " + username);
    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    private void setupTabChangeListener() {
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                for (int i = 0; i < tabs.getTabCount(); i++) {
                    tabs.setBackgroundAt(i, Color.blue);
                    tabs.setForegroundAt(i, Color.blue);
                }
            }
        });
    }

    private static class CustomTabbedPaneUI extends BasicTabbedPaneUI {
        private Color selectedColor;
        private Color unselectedColor;
        private Color selectedTextColor;
        private Color unselectedTextColor;

        public CustomTabbedPaneUI(Color selectedColor, Color unselectedColor, Color selectedTextColor, Color unselectedTextColor) {
            this.selectedColor = selectedColor;
            this.unselectedColor = unselectedColor;
            this.selectedTextColor = selectedTextColor;
            this.unselectedTextColor = unselectedTextColor;
        }

        @Override
        protected void installDefaults() {
            super.installDefaults();
            tabInsets = new Insets(5, 20, 5, 20); // Set insets for spacing
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            g.setColor(isSelected ? selectedColor : unselectedColor);
            int arcWidth = 20;
            int arcHeight = 20;

            g.fillRoundRect(x, y, w, h, arcWidth, arcHeight);
            g.fillRect(x, y + arcHeight / 2, w, h - arcHeight / 2);
        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setFont(font);
            g2d.setColor(isSelected ? selectedTextColor : unselectedTextColor);
            int mnemonicIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);

            // Adjust the text rectangle to consider insets
            textRect.y = textRect.y + metrics.getAscent() + (tabInsets.bottom / 2);

            g2d.drawString(title, textRect.x, textRect.y);
            if (mnemonicIndex >= 0 && mnemonicIndex < title.length()) {
                int underlineX = textRect.x + metrics.stringWidth(title.substring(0, mnemonicIndex));
                int underlineY = textRect.y + 1;
                int underlineWidth = metrics.charWidth(title.charAt(mnemonicIndex));
                int underlineHeight = 1;
                g2d.fillRect(underlineX, underlineY, underlineWidth, underlineHeight);
            }
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            // Do nothing to remove the tab border
        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            // Do nothing to remove the content border
        }
    }
}
