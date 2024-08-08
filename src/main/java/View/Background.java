package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Custom JPanel class to help add background images to the GUI.
 */
public class Background extends JPanel {
    /** The image to be used as the background. */
    private Image backgroundImage;

    /**
     * Constructor for the Background.
     * @param imagePath String file path to the image file.
     */
    public Background(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Overridden method to ensure that the background image scales to fit the entire panel.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
