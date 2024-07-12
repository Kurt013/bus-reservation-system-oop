package com.shivajivarma.brs.ui;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;




import com.shivajivarma.brs.utility.ValidationUtil;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.Messages;
import com.shivajivarma.brs.utility.constants.ResourcePaths;


public class RegistrationPanelView extends BaseView implements View {


    private JTextField username, fullname, mobile, email;
    private JPasswordField password, reEnterPassword;
    private JButton submitButton, cancelButton, togglePasswordButton, toggleReEnterPasswordButton;
    private int x = 35, y = 40;
    private Image backgroundImage;
    private boolean passwordVisible = false;
    private boolean repasswordVisible = false;
    private ImageIcon showIcon, hideIcon;
    private Color yellowColor = new Color(255, 255, 0); 


    public RegistrationPanelView() {
        super(); 


        try {
            // Load the background image using ResourcePaths.REGISTER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.REGISTER));


            // Load eye icons for password toggle
            showIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_OPEN)));
            hideIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_CLOSED)));
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


    private void initializeLabels() {
        Color jacliner = new Color(246, 209, 0);
        this.add(ViewComponentFactory.createJLabelHeader(Labels.REGISTER2, new int[] { x + 30, y + 0, 300, 28 }, jacliner));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.USERNAME2, new int[] { x + 100, y + 60, 150, 20 }, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.FULL_NAME, new int[] { x + 100, y + 120, 150, 20 }, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.PASSWORD2, new int[] { x + 105, y + 180, 200, 20 }, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.RE_ENTER_PASSWORD, new int[] { x + 22, y + 240, 200, 20 }, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.MOBILE, new int[] { x + 2, y + 300, 200, 20 }, Color.white));
        this.add(ViewComponentFactory.createJLabelNormal(Labels.EMAIL, new int[] { x + 142, y + 360, 200, 20 }, Color.white));
    }


    private void initializeComponents() {
        Insets insets = new Insets(5, 10, 3, 10);
        Color jaclinery = new Color(248, 188, 8);


        username = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 220, y + 60, 250, 30 });
        fullname = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 220, y + 120, 250, 30 });
        password = ViewComponentFactory.createJPasswordFieldNormal(new int[]{x+220,y+180,215,30});
        reEnterPassword = ViewComponentFactory.createJPasswordFieldNormal(new int[]{x+220,y+240,215,30});
        mobile = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 220, y + 300, 250, 30 });
        email = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 220, y + 360, 250, 30 });
        submitButton =new RoundButton(Labels.SUBMIT, new int[] { x + 265, y + 420, 110, 35 }, Color.white, jaclinery);
        cancelButton = new RoundButton(Labels.CANCEL, new int[] { x + 140, y + 420, 110, 35 }, Color.white, jaclinery);


        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorderPainted(false);
        cancelButton.setBorder(new EmptyBorder(0, 0, 0, 0));

        addHoverEffect(submitButton, jaclinery, Color.white, Color.white, jaclinery);
        addHoverEffect(cancelButton, jaclinery, Color.white, Color.white, jaclinery);


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
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Allow only digits
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        

        // Toggle button to show/hide password using eye icons
        togglePasswordButton = new JButton(hideIcon);
        togglePasswordButton.setBounds(x + 440, y + 180, 30, 30); 
        togglePasswordButton.setOpaque(true); 
        togglePasswordButton.setBackground(Color.YELLOW); 
   
        togglePasswordButton.setFocusPainted(false);        
        togglePasswordButton.setBorderPainted(false); 
        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
        add(togglePasswordButton); // Add toggle button near the password field


        toggleReEnterPasswordButton = new JButton(hideIcon);
        toggleReEnterPasswordButton.setBounds(x + 440, y + 240, 30, 30); 
        toggleReEnterPasswordButton.setOpaque(true); 
        toggleReEnterPasswordButton.setBackground(yellowColor); 
        toggleReEnterPasswordButton.setFocusPainted(false);        
        toggleReEnterPasswordButton.setBorderPainted(false);
        toggleReEnterPasswordButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            toggleReEnterPasswordVisibility();
        }
    });
    add(toggleReEnterPasswordButton);


        this.add(username);
        this.add(fullname);
        this.add(password);
        this.add(reEnterPassword);
        this.add(mobile);
        this.add(email);
        this.add(submitButton);
        this.add(cancelButton);
    }


    private void togglePasswordVisibility() {
        if (passwordVisible) {
            // Hide password
            password.setEchoChar('•');
            togglePasswordButton.setIcon(hideIcon);
        } else {
            // Show password
            password.setEchoChar((char) 0);
            togglePasswordButton.setIcon(showIcon);
        }
        passwordVisible = !passwordVisible;
    }
    private void toggleReEnterPasswordVisibility() {
        if (repasswordVisible) {
            // Hide re-enter password
            reEnterPassword.setEchoChar('•');
            toggleReEnterPasswordButton.setIcon(hideIcon);
        } else {
            // Show re-enter password
            reEnterPassword.setEchoChar((char) 0);
            toggleReEnterPasswordButton.setIcon(showIcon);
        }
        repasswordVisible = !repasswordVisible;
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
    public boolean validateFields() {


        ArrayList<String> errors = new ArrayList<String>();
        String message;


        message = ValidationUtil.validateField(username, new String[] { "required", "username" });
        errors.add(message);


        message = ValidationUtil.validateField(fullname, new String[] { "required", "alphabetsOnly" });
        errors.add(message);


        message = ValidationUtil.validateField(password, new String[]{"required", "password"});
        errors.add(message);

		message = ValidationUtil.validateField(reEnterPassword, new String[]{"required", "repassword"});
        errors.add(message);

        message = ValidationUtil.validateField(mobile, new String[] { "mobile" });
        errors.add(message);


        message = ValidationUtil.validateField(email, new String[] { "email" });
        errors.add(message);


        errors.removeAll(Collections.singleton(null));
        if (!errors.isEmpty()) {
            message = "";
            for (String error : errors) {
                message = message + error + "\n";
            }
            Alert.errorMessage(message);
            return false;
        } else if (!getPassword().equals(getReEnterPassword())) {
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


    public String getMobile() {
        return mobile.getText();
    }


    public String getEmail() {
        return email.getText();
    }


    public JButton getSubmitButton(){
        return submitButton;
    }


    public JButton getCancelButton() {
        return cancelButton;
    }
   
}