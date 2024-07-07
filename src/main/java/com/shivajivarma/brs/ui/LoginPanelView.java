package com.shivajivarma.brs.ui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import com.shivajivarma.brs.utility.ValidationUtil;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.Labels;
import com.shivajivarma.brs.utility.constants.ResourcePaths;


@SuppressWarnings("serial")
public class LoginPanelView extends BaseView implements View {


    // UI Components
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton, registerButton, togglePasswordButton;
    private int x = 250, y = 125;
    private Image backgroundImage;
    private boolean passwordVisible = false;
    private ImageIcon showIcon, hideIcon;
     // Yellow color


    /**
     * Constructor to initialize UI components and set up the view.
     */
    public LoginPanelView() {
        super(); // Call superclass constructor


        try {
            // Load the background image using ResourcePaths.BANNER
            backgroundImage = ImageIO.read(getClass().getResource(ResourcePaths.LOGIN));


            // Load eye icons for password toggle
            showIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_OPEN)));
            hideIcon = new ImageIcon(ImageIO.read(getClass().getResource(ResourcePaths.EYE_CLOSED)));
        } catch (IOException e) {
            e.printStackTrace(); // Handle loading error here
        }


        initializeLabels();
        initializeComponents();
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
        add(ViewComponentFactory.createJLabelHeader(Labels.LOGIN, new int[] { x + 375, y - 40, 120, 28 }, jacliner));
        add(ViewComponentFactory.createJLabelNormal(Labels.USERNAME, new int[] { x + 305, y + 25, 150, 20 }, Color.white));
        add(ViewComponentFactory.createJLabelNormal(Labels.PASSWORD, new int[] { x + 305, y + 105, 150, 20 }, Color.white));
    }


    private void initializeComponents() {
        Color jaclinery = new Color(248, 188, 8);
        Insets insets = new Insets(5, 10, 3, 10);


        username = ViewComponentFactory.createJTextFieldNormal(new int[] { x + 305, y + 55, 365, 30 });
        username.setMargin(insets);


        password = ViewComponentFactory.createJPasswordFieldNormal(new int[] { x + 305, y + 135, 310, 30 });
        password.setMargin(insets);
        password.setEchoChar('•');


        // Toggle button to show/hide password using eye icons
        togglePasswordButton = new JButton(hideIcon);
        togglePasswordButton.setBounds(x + 625, y + 135, 35, 30);
        togglePasswordButton.setOpaque(true); 
        togglePasswordButton.setBackground(Color.YELLOW); 
        togglePasswordButton.setFocusPainted(false);    
        togglePasswordButton.setBorderPainted(false); 
        togglePasswordButton.addActionListener(e -> togglePasswordVisibility());


        loginButton = ViewComponentFactory.createJButtonNormal(Labels.LOGINB, new int[] { x + 375, y + 210, 90, 40 }, Color.white, jaclinery);
        registerButton = ViewComponentFactory.createJButtonNormal(Labels.REGISTER, new int[] { x + 480, y + 210, 120, 40 }, Color.white, jaclinery);


        loginButton.setBorderPainted(false);
        registerButton.setBorderPainted(false);


        username.setName(Labels.USERNAME);
        password.setName(Labels.PASSWORD);


        inputFields.add(username);
        inputFields.add(password);


        add(username);
        add(password);
        add(loginButton);
        add(registerButton);
        add(togglePasswordButton); // Add toggle button to the panel
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


    public boolean validateFields() {
        ArrayList<String> errors = new ArrayList<>();
        String message;


        message = ValidationUtil.validateField(username, new String[] { "required", "username" });
        errors.add(message);


        message = ValidationUtil.validateField(password, new String[] { "required", "noSpaces" });
        errors.add(message);


        errors.removeAll(Collections.singleton(null));
        if (!errors.isEmpty()) {
            message = "";
            for (String error : errors) {
                message = message + error + "\n";
            }
            // Alert.errorMessage(message); // You may implement your own error message handling
            return false;
        }
        return true;
    }


    public String getUsername() {
        return username.getText().toLowerCase();
    }


    public String getPassword() {
        return new String(password.getPassword());
    }


    public JButton getLoginButton() {
        return loginButton;
    }


    public JButton getRegisterButton() {
        return registerButton;
    }
}



