package ui;

import java.awt.Graphics;
//import java.awt.Image;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
//import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image;

	public ImagePanel() {
		setOpaque(false);
		setLayout(null);
	}

	public ImagePanel(ImageIcon image) {
		setOpaque(false);
		setLayout(null);
		this.image = image;
	}

	public void setImageIcon(ImageIcon image) {
		this.image = image;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
		setOpaque(false);
	}
}
