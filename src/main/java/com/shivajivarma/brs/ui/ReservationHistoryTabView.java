package com.shivajivarma.brs.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;

/**
 * Author: Shivaji Varma (contact@shivajivarma.com)
 */
@SuppressWarnings("serial")
public class ReservationHistoryTabView extends BaseView implements View {

    private DefaultTableModel tableModel;
    private JButton printButton;
    private JTable table;
    private int hoveredRow = -1;
	private BufferedImage backgroundImage;

    public ReservationHistoryTabView() {
  
        super(); // Call superclass constructor

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE4));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

        this.initializeLabels();
        this.initializeComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image scaled to fit the component size
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initializeLabels() {
		this.add(ViewComponentFactory.createJLabelNormal(Labels.REMINDER3, new int[]{97,289, 240, 30}, Color.white));
    }

    private void initializeComponents() {
        printButton = ViewComponentFactory.createJButtonNormal(Labels.PRINT, new int[]{910, 400, 80, 40}, Color.black, Color.gray);

        table = new JTable(null, columns()) {
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };

        // Set the font of the table
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30); // Optional: Set a consistent row height
		

        TableColumn tc;
        for (int i = 0; i < table.getColumnCount(); i++) {
            tc = table.getColumnModel().getColumn(i);
            tc.setMaxWidth(400);
        }

        tableModel = (DefaultTableModel) table.getModel();

        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);

        // Remove grid lines by setting the grid color to the background color
        table.setShowGrid(false);

        // Set the renderer for hover effect, font, and alternating row colors
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set font
                c.setFont(new Font("Arial", Font.PLAIN, 14));
				c.setForeground(new Color(182, 4, 11));
				((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
				
                // Set alternating row colors
                if (row % 2 == 0) {
                    c.setBackground(new Color(245, 245, 245)); // Light gray for even rows
                } else {
                    c.setBackground(Color.WHITE); // White for odd rows
                }

                // Set hover effect
                if (row == hoveredRow) {
                    c.setBackground(new Color(220, 220, 220)); // Light gray for hover
                }

                // Set selection effect
                if (isSelected) {
                    c.setBackground(new Color(184, 207, 229)); // Light blue for selected row
                }

                return c;
            }
        };

        // Apply the custom renderer to each column
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Add mouse motion listener for hover effect
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    table.repaint();
                }
            }
        });

        // Add mouse listener to clear hover effect when mouse exits table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                table.repaint();
            }
        });

        // Custom header renderer for the table header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setFont(new Font("Arial", Font.BOLD, 14));
                label.setBackground(new Color(182, 4, 11)); 
				label.setForeground(Color.white);// red for header background
                label.setHorizontalAlignment(JLabel.CENTER); // Center align header text
                return label;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(72, 85, 850, 200); // Adjust the position and size here
        scrollPane.getViewport().setBackground(Color.YELLOW); // Set the background color for empty space
        scrollPane.setBorder(null); // Remove border from JScrollPane
        table.setBorder(null); // Remove border from JTable

        this.add(scrollPane);
        this.add(printButton);
    }

    private static Vector<String> columns() {
        Vector<String> columns = new Vector<String>();
        /*
         * Populating history table column titles
         */
        columns.addElement("Ticket ID");
        columns.addElement("BID");
        columns.addElement("Journey Date");
        columns.addElement("Origin");
        columns.addElement("Destination");
        columns.addElement("Departure");
        columns.addElement("Arrival");
        columns.addElement("Seat");
        return columns;
    }

    public JButton getPrintButton() {
        return printButton;
    }

    public void clearTable() {
        int rowCount = tableModel.getRowCount();
        // Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    public void addTableRow(String id, String bid, String dt, String origin, String destination, String depttime, String arrtime, String seat) {
        tableModel.addRow(new Object[]{id, bid, dt, origin, destination, depttime, arrtime, seat});
    }

    public boolean isRowSelected(int i) {
        return table.isRowSelected(i);
    }
}
