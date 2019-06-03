package view.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.AttributedString;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * The Class MenuPanel
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public class MenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6628867251040356742L;

	Image image;
	private int i = 0;

	/**
	 * Constructor of class menupanel
	 */
	public MenuPanel() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		URL menugif = classLoader.getResource("menu.gif");
		this.image = Toolkit.getDefaultToolkit().createImage(menugif);
	}

	/**
	 * Paint the panel menu
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// String for the text
		AttributedString as1 = new AttributedString("PRESS ENTER TO PLAY");
		// Give the attribute for the text
		as1.addAttribute(TextAttribute.SIZE, 30);
		g2d.setColor(Color.WHITE);

		// Draw the animated text
		g2d.drawString(as1.getIterator(), 45, 20);
		if (image != null) {
			g.drawImage(image, 0, 0, 960, 540, this);
			if (i < 6) {
				g2d.setColor(Color.WHITE);
				g2d.drawString(as1.getIterator(), 320, 470);
				i++;
			} else if (i < 12) {
				i++;
			} else {
				i = 0;
			}
		}
	}
}
