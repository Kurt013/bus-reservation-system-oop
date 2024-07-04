package com.shivajivarma.brs.ui;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import com.shivajivarma.brs.utility.ViewComponentFactory;
import com.shivajivarma.brs.utility.constants.ResourcePaths;


/**
 * Author: <a href="http://shivajivarma.com" target="_blank">Shivaji Varma</a>
 */
@SuppressWarnings("serial")
public class BannerViewPanel extends BaseView implements View {
    private JLabel bannerLabel;
    private Image originalImage;

    public BannerViewPanel() {
        this.setLayout(new BorderLayout());

        // Create the JLabel using ViewComponentFactory
        bannerLabel = ViewComponentFactory.createJLabelImage(ResourcePaths.BANNER);
        originalImage = ((ImageIcon) bannerLabel.getIcon()).getImage();

        // Add the JLabel to the panel
        this.add(bannerLabel, BorderLayout.CENTER);

        // Add a component listener to resize the image when the panel size changes
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                resizeBanner();
            }
        });

        // Initial resize
        resizeBanner();
    }

    private void resizeBanner() {
        int width = BannerViewPanel.this.getWidth();

        if (width > 0) {
            // Scale the image to fit the width of the panel while maintaining the aspect ratio
            Image scaledImage = originalImage.getScaledInstance(width, -1, Image.SCALE_SMOOTH);
            bannerLabel.setIcon(new ImageIcon(scaledImage));
        }
    }
}
