package com.shivajivarma.brs.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;

public class SeatSelectionView extends BaseView implements View {

    private List<JCheckBox> seats = new ArrayList<>();
    private List<Boolean> seatTypes = new ArrayList<>();
    private List<JLabel> icons = new ArrayList<JLabel>();
    private BufferedImage backgroundImage;

    private static ImageIcon disabledSeat = ViewComponentFactory.createImageIcon(ResourcePaths.REDSEAT),
            availableSeat = ViewComponentFactory.createImageIcon(ResourcePaths.GREENSEAT),
            pwdSeat = ViewComponentFactory.createImageIcon(ResourcePaths.PWDSEAT); // Add pwdSeat icon
    private JButton bookButton, backButton;
    private JCheckBox discountCheckbox;
    public boolean isPWDseat = false, discountToggled = false;


    public SeatSelectionView() {
        super(); // Call superclass constructor

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.RESERVE6));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }

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

    private void initializeComponents() {
        Color jaclinery = new Color(248, 188, 8);
        bookButton = new RoundButton(Labels.BOOK, new int[]{43, 248, 100, 30}, Color.white, jaclinery);
        backButton = new RoundButton(Labels.BACK, new int[]{43, 298, 100, 30}, Color.white, jaclinery);

        bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(false);
        bookButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        addHoverEffect(bookButton, jaclinery, Color.white, Color.white, jaclinery);
        addHoverEffect(backButton, jaclinery, Color.white, Color.white, jaclinery);

        // Initialize discount checkbox
        discountCheckbox = new JCheckBox("Discount");
        discountCheckbox.setBounds(700, 20, 100, 20); // Adjust position as needed
        this.add(discountCheckbox);

        discountCheckbox.addActionListener(e -> {
            discountToggled = discountCheckbox.isSelected();
            isPWDseat = discountCheckbox.isSelected();
            updateSeatIcons(); // Method to update seat icons based on checkbox state
        });

        int totalSeats = 49;
        int seatsPerRow = 4;
        int lastRowSeats = 5;
        int rows = (totalSeats - lastRowSeats) / seatsPerRow + 1;

        for (int row = 0; row < rows; row++) {
            int seatsInRow = (row == rows - 1) ? lastRowSeats : seatsPerRow;
            for (int col = 0; col < seatsInRow; col++) {
                addSeat(row, col, seatsInRow);
                seatTypes.add(false);
            }
        }

        for (JCheckBox seat : seats) {
            seat.addActionListener(e -> {
                seatTypes.set(seats.indexOf(seat), seat.isSelected() && discountToggled);
            });
        }


        this.add(bookButton);
        this.add(backButton);
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    private void addHoverEffect(JButton button, Color originalColor, Color hoverColor, Color originalTextColor, Color hoverTextColor) {
        button.setBackground(originalColor);
        button.setForeground(originalTextColor);
        button.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(hoverTextColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
                button.setForeground(originalTextColor);
            }
        });
    }

    private void addSeat(int row, int col, int seatsInRow) {
        JCheckBox seat = new JCheckBox();
        JLabel icon = new JLabel(availableSeat);

        int seatIndex = row * 4 + col + 1;

        int xOffset = 20 + (col * 120) + ((4 - seatsInRow) * 80 / 2); // Increase spacing between seats
        if (col >= 2 && seatsInRow != 5) {
            xOffset += 120; // Add space after the first two seats
        }
        if (seatsInRow == 4) {
            xOffset += 380; 
        }
        if (seatsInRow == 5) {
            xOffset += 420; 
        }

        seat.setEnabled(true);
        seat.setBounds(xOffset, 90 + (row * 50), 20, 20);
        seat.setActionCommand(Integer.toString(seatIndex));
        seats.add(seat);


        icon.setBounds(xOffset + 22, 70 + (row * 50), 65, 65);
        icons.add(icon);

        this.add(seat);
        this.add(icon);
        JLabel seatNumberLabel = ViewComponentFactory.createJLabelNormal(Integer.toString(seatIndex), new int[]{xOffset + 68, 77 + (row * 50), 30, 20}, Color.black);
        this.add(seatNumberLabel);
        seatNumberLabel.setOpaque(true);
        seatNumberLabel.setBackground(new Color(255, 255, 255, 0)); // Transparent background for seat number label
        seatNumberLabel.setForeground(Color.black); // Ensure the text color is set to black
        seatNumberLabel.setVisible(true);
        setComponentZOrder(seatNumberLabel, 0);
    }

    public void disableSeat(int seatNumber) {
        seats.get(seatNumber - 1).setEnabled(false);
        icons.get(seatNumber - 1).setIcon(disabledSeat);
    }

    public List<JCheckBox> getSeats() {
        return seats;
    }

    public List<Boolean> getDisc() {
        return seatTypes;
    }

     private void updateSeatIcons() {
        for (int i = 0; i < seats.size(); i++) {
            JLabel icon = icons.get(i);
            ImageIcon currentIcon = (ImageIcon) icon.getIcon();

            /* Shows the PWD seats */
            if (!seats.get(i).isSelected() && i < 8) {
                if (isPWDseat && currentIcon == availableSeat) {
                    icon.setIcon(pwdSeat);
                } else if (!isPWDseat && currentIcon == pwdSeat) {
                    icon.setIcon(availableSeat);
                }
            }
        }
    }

}
