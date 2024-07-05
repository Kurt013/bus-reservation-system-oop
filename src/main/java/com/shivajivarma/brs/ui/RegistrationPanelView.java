package com.shivajivarma.brs.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.shivajivarma.brs.utility.ValidationUtil;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.Messages;
import com.shivajivarma.brs.utility.constants.ResourcePaths;


@SuppressWarnings("serial")
public class RegistrationPanelView extends BaseView implements View{
	
	private JTextField username, fullname, mobile, email;
	private JPasswordField password, reEnterPassword;
	private JButton submitButton, cancelButton;
	private int x=35, y=40;
	private Image backgroundImage;
	
	public RegistrationPanelView(){
		        super(); // Call superclass constructor

        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.REGISTER));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }
		this.initializeLabels();
		this.initializeComponents();
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
	private void initializeLabels(){
		Color jacliner = new Color(246, 209, 0);
		this.add(ViewComponentFactory.createJLabelHeader(Labels.REGISTER2, new int[]{x+30,y+0,300,28}, jacliner));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.USERNAME2, new int[]{x+100,y+60,150,20}, Color.white));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.FULL_NAME, new int[]{x+100,y+120,150,20}, Color.white));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.PASSWORD2, new int[]{x+105,y+180,200,20}, Color.white));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.RE_ENTER_PASSWORD, new int[]{x+22,y+240,200,20}, Color.white));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.MOBILE, new int[]{x+2,y+300,200,20},Color.white));
		this.add(ViewComponentFactory.createJLabelNormal(Labels.EMAIL, new int[]{x+142,y+360,200,20}, Color.white));
	}
	
	private void initializeComponents(){
		Insets insets = new Insets(5, 10, 3, 10);
		Color jaclinery = new Color(248, 188, 8);
		username = ViewComponentFactory.createJTextFieldNormal(new int[]{x+220,y+60,250,30});
		fullname = ViewComponentFactory.createJTextFieldNormal(new int[]{x+220,y+120,250,30});
		password = ViewComponentFactory.createJPasswordFieldNormal(new int[]{x+220,y+180,250,30});
		reEnterPassword = ViewComponentFactory.createJPasswordFieldNormal(new int[]{x+220,y+240,250,30});
		mobile = ViewComponentFactory.createJTextFieldNormal(new int[]{x+220,y+300,250,30});
		email = ViewComponentFactory.createJTextFieldNormal(new int[]{x+220,y+360,250,30});
		submitButton = ViewComponentFactory.createJButtonNormal(Labels.SUBMIT, new int[]{x+265,y+420,110,40}, Color.white, jaclinery);
		cancelButton = ViewComponentFactory.createJButtonNormal(Labels.CANCEL, new int[]{x+140,y+420,110,40}, Color.white, jaclinery);
		
		submitButton.setBorderPainted(false);
        cancelButton.setBorderPainted(false);

		username.setName(Labels.USERNAME);
		username.setMargin(insets);
		fullname.setName(Labels.FULL_NAME);
		fullname.setMargin(insets);
		password.setName(Labels.PASSWORD);
		password.setMargin(insets);
		password.setEchoChar('•');
		reEnterPassword.setName(Labels.RE_ENTER_PASSWORD);
		reEnterPassword.setMargin(insets);
		reEnterPassword.setEchoChar('•');
		mobile.setName(Labels.MOBILE);
		mobile.setMargin(insets);
		email.setName(Labels.EMAIL);
		email.setMargin(insets);
		
		inputFields.add(username);
		inputFields.add(fullname);
		inputFields.add(password);
		inputFields.add(reEnterPassword);
		inputFields.add(mobile);
		inputFields.add(email);
		
		mobile.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent arg) {
                int keyCode = arg.getKeyCode();
                if ((keyCode > 47 && keyCode < 58) || keyCode == 45) {
                	arg.consume();
                }
            }

        });
		
		this.add(username);
		this.add(fullname);
		this.add(password);
		this.add(reEnterPassword);
		this.add(mobile);
		this.add(email);
		this.add(submitButton);
		this.add(cancelButton);
	}
	
	public boolean validateFields(){
		
		ArrayList<String> errors = new ArrayList<String>();
		String message;
		
		message = ValidationUtil.validateField(username, new String[]{"required","username"});
		errors.add(message);
		
		message = ValidationUtil.validateField(fullname, new String[]{"required","alphabetsOnly"});
		errors.add(message);
		
		message = ValidationUtil.validateField(password, new String[]{"required", "noSpaces"});
		errors.add(message);
		
		message = ValidationUtil.validateField(reEnterPassword, new String[]{"required", "noSpaces"});
		errors.add(message);
		
		message = ValidationUtil.validateField(mobile, new String[]{"mobile"});
		errors.add(message);
		
		message = ValidationUtil.validateField(email, new String[]{"email"});
		errors.add(message);
		
		errors.removeAll(Collections.singleton(null));
		if(!errors.isEmpty()){
			message = "";
			for (String error : errors) {message = message + error + "\n";}
			Alert.errorMessage(message);
			return false;
		}
		
		else if(!getPassword().equals(getReEnterPassword())){
			Alert.errorMessage(Messages.ERROR_PASSWORD_MISMATCH);
			password.setText(null);
			reEnterPassword.setText(null);
			return false;
		}
		
		return true;
	}

	public String getUsername() {
		return username.getText().toLowerCase();
	}
	
	public String getFullname() {
		return fullname.getText();
	}

	public String getPassword() {
		return new String(password.getPassword());
	}

	private String getReEnterPassword() {
		return new String(reEnterPassword.getPassword());
	}

	public int getMobile() {
		return Integer.parseInt(mobile.getText());
	}
	
	public String getEmail() {
		return email.getText();
	}
	
	public JButton getSubmitButton() {
		return submitButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}
	
}