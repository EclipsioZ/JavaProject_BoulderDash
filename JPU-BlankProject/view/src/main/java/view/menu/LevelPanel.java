package view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

import model.Texture;
import model.bdd.BDDGetData;
import model.elements.Element;

public class LevelPanel extends JPanel{

	Image image;
	int level = 1;	
	int[][] coordlevel;
	int i;
	private String levelName;
	
	public LevelPanel() {
		this.CoordLevel();
		this.image = Toolkit.getDefaultToolkit()
				.createImage(getClass().getClassLoader().getResource("MapLevel.png").getFile());
		this.levelName = "";
	}

	private void CoordLevel() {
	coordlevel = new int[13][2];
	//Level 1
	coordlevel[0][0] = 830;	coordlevel[0][1] = 308;
	//Level 2
	coordlevel[1][0] = 830;	coordlevel[1][1] = 222;
	//Level 3
	coordlevel[2][0] = 750;	coordlevel[2][1] = 222;
	//Level 4
	coordlevel[3][0] = 750;	coordlevel[3][1] = 136;
	//Level 5
	coordlevel[4][0] = 83;	coordlevel[4][1] = 222;
	//Level 6
	coordlevel[5][0] = 83;	coordlevel[5][1] = 305;
	//Level 7
	coordlevel[6][0] = 160;	coordlevel[6][1] = 305;
	//Level 8
	coordlevel[7][0] = 160;	coordlevel[7][1] = 389;
	//Level 9
	coordlevel[8][0] = 280;	coordlevel[8][1] = 559;
	//Level 10
	coordlevel[9][0] = 397;	coordlevel[9][1] = 559;
	//Level 11
	coordlevel[10][0] = 514; coordlevel[10][1] = 559;
	//Level 12
	coordlevel[11][0] = 631; coordlevel[11][1] = 559;
	//Level 13
	coordlevel[12][0] = 748; coordlevel[12][1] = 559;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, 12*80 - 18, 12*80 - 20, this);
		
		drawLevel(g, level);
		
		drawLevelName(g);
	}
	
	public void drawLevelName(Graphics g) {
		Font font = new Font("Arial", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString(this.getLevelName(), 10, 30);
	}
	
	public void drawLevel(Graphics g, int level) {
			g.drawImage(Texture.playerlevelselector[1], getCoordlevel(level-1, 0) -25,getCoordlevel(level-1, 1)-25, 70, 70, null);

	}
	
	public int getCoordlevel(int a, int b) {
		return coordlevel[a][b];
	}
	public void setCoordlevel(int[][] coordlevel) {
		this.coordlevel = coordlevel;
	}
    
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

}
