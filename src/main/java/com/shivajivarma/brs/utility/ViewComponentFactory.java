package com.shivajivarma.brs.utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ViewComponentFactory {
	
	public static final Font FONT_HEADER;
	public static final Font FONT_NORMAL;
	public static final Font FONT_SMALL;
	public static final Font FONT_LARGE;
	public static final Font FONT_FIELD;

	static {
		FONT_HEADER = loadFont("/fonts/Montserrat-ExtraBoldItalic.ttf", Font.BOLD, 24);
		FONT_NORMAL = loadFont("/fonts/Montserrat-Bold.ttf", Font.PLAIN, 17);
		FONT_FIELD = loadFont("/fonts/Inter-SemiBold.ttf", Font.PLAIN, 17);
		FONT_SMALL = loadFont("/fonts/CustomFont.ttf", Font.PLAIN, 18);
		FONT_LARGE = loadFont("/fonts/CustomFont.ttf", Font.BOLD, 40);
	}

	private static Font loadFont(String path, int style, int size) {
		try (InputStream is = ViewComponentFactory.class.getResourceAsStream(path)) {
			if (is == null) {
				throw new IOException("Font resource not found: " + path);
			}
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			return font.deriveFont(style, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			// Fallback to a default font in case of an error
			return new Font("Serif", style, size);
		}
	}

	public static JLabel createJLabelLarge(String name, Color color) {
		JLabel label = new JLabel(name);
		label.setFont(FONT_LARGE);
		label.setForeground(color);
		return label;
	}

	public static JLabel createJLabelLarge(String name, int[] coordinates, Color color) {
		JLabel label = createJLabelLarge(name, color);
		Dimension size = label.getPreferredSize();
		label.setBounds(coordinates[0], coordinates[1], size.width, size.height);
		return label;
	}

	public static JLabel createJLabelHeader(String name, Color color) {
		JLabel label = new JLabel(name);
		label.setFont(FONT_HEADER);
		label.setForeground(color);
		return label;
	}

	public static JLabel createJLabelHeader(String name, int[] coordinates, Color color) {
		JLabel label = createJLabelHeader(name, color);
		Dimension size = label.getPreferredSize();
		label.setBounds(coordinates[0], coordinates[1], size.width, size.height);
		return label;
	}

	public static JLabel createJLabelNormal(String name, Color color) {
		JLabel label = new JLabel(name);
		label.setFont(FONT_NORMAL);
		label.setForeground(color);
		return label;
	}

	public static JLabel createJLabelNormal(String name, int[] coordinates, Color color) {
		JLabel label = createJLabelNormal(name, color);
		Dimension size = label.getPreferredSize();
		label.setBounds(coordinates[0], coordinates[1], size.width, size.height);
		return label;
	}

	public static JLabel createJLabelSmall(String name, Color color) {
		JLabel label = new JLabel(name);
		label.setFont(FONT_SMALL);
		label.setForeground(color);
		return label;
	}

	public static JLabel createJLabelSmall(String name, int[] coordinates, Color color) {
		JLabel label = createJLabelSmall(name, color);
		label.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		return label;
	}

	public static JLabel createJLabelImage(String path) {
		return new JLabel(new ImageIcon(ViewComponentFactory.class.getResource(path)));
	}

	public static ImageIcon createImageIcon(String path) {
		return new ImageIcon(ViewComponentFactory.class.getResource(path));
	}

	/*
	 * Buttons
	 */
	public static JButton createJButtonNormal(String name) {
		JButton button = new JButton(name);
		button.setFont(FONT_NORMAL);
		return button;
	}

	public static JButton createJButtonNormal(String name, int[] coordinates) {
		JButton button = createJButtonNormal(name);
		button.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		return button;
	}

	/*
	 * Text fields
	 */
	public static JTextField createJTextFieldNormal() {
		JTextField textField = new JTextField();
		textField.setFont(FONT_FIELD);
		return textField;
	}

	public static JTextField createJTextFieldNormal(int[] coordinates) {
		JTextField textField = createJTextFieldNormal();
		textField.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		return textField;
	}

	public static JPasswordField createJPasswordFieldNormal() {
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(FONT_FIELD);
		return passwordField;
	}

	public static JPasswordField createJPasswordFieldNormal(int[] coordinates) {
		JPasswordField passwordField = createJPasswordFieldNormal();
		passwordField.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		return passwordField;
	}

	/*
	 * Combo boxes
	 */
	public static <T> JComboBox<T> createJComboBoxNormal(Class<T> type) {
		JComboBox<T> comboBox = new JComboBox<>();
		comboBox.setFont(FONT_NORMAL);
		return comboBox;
	}

	public static <T> JComboBox<T> createJComboBoxNormal(int[] coordinates, Class<T> type) {
		JComboBox<T> comboBox = createJComboBoxNormal(type);
		comboBox.setBounds(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
		return comboBox;
	}
}
