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


@SuppressWarnings("serial")
public class SeatSelectionView extends BaseView implements View{

	private List<JCheckBox> seats = new ArrayList<JCheckBox>();
	private List<JLabel> icons = new ArrayList<JLabel>();
	private BufferedImage backgroundImage;
	
	private static ImageIcon disabledSeat = ViewComponentFactory.createImageIcon(ResourcePaths.REDSEAT),
			availableSeat = ViewComponentFactory.createImageIcon(ResourcePaths.GREENSEAT);
	private JButton bookButton, backButton;
	private int i=0,j=1;
	
	public SeatSelectionView(){
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
	
	
	private void initializeComponents(){
		Color jaclinery = new Color(248, 188, 8);
		bookButton = new RoundButton (Labels.BOOK, new int[]{43,248,100,30}, Color.white, jaclinery);
		backButton = new RoundButton(Labels.BACK, new int[]{43,298,100,30}, Color.white, jaclinery);
		
		bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(false);
        bookButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setBorder(new EmptyBorder(0, 0, 0, 0));

		addHoverEffect(bookButton, jaclinery, Color.white, Color.white, jaclinery);
		addHoverEffect(backButton, jaclinery, Color.white, Color.white, jaclinery);
		


		
		for (int i =1 ; i <=40; i++) {
			this.addSeat();
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
	
	public void addSeat(){
		JCheckBox seat = new JCheckBox();
		JLabel icon = new JLabel(availableSeat);
		
		seat.setEnabled(true);
		seat.setBounds(20+(j*200),90+(i*50),20,20);
		seat.setActionCommand(Integer.toString((i*4)+j));
		seats.add(seat);
		
		icon.setBounds(42+(j*200),70+(i*50),70,70);
		icons.add(icon);
	
		this.add(seat);
		this.add(icon);
		this.add(ViewComponentFactory.createJLabelNormal(Integer.toString((i*4)+j), new int[]{90+(j*200),77+(i*50),30,20}, Color.black));
		
		if(j==4){
			j=1;
			i++;
		} else{
			j++;
		}
	}
	
	public void disableSeat(int seatNumber){
		seats.get(seatNumber-1).setEnabled(false);
		icons.get(seatNumber-1).setIcon(disabledSeat);
	}

	public List<JCheckBox> getSeats() {
		return seats;
	}
	
}