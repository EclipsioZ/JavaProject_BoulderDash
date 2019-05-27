package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private static int height = 16;
	private static int width = 16;
	
	//Creating block objects for different animations
	public BufferedImage[] background, air, wall, dirt = new BufferedImage[0]; //ID = 9
// BufferedImage[] air = new BufferedImage[0]; // ID = 0
//	BufferedImage[] wall = new BufferedImage[0]; // ID = 2
//	BufferedImage[] dirt = new BufferedImage[0]; // ID = 3
	public BufferedImage[] rock, diamond, mob1, mob2, endblock, explode = new BufferedImage[3]; // ID = 4
//	BufferedImage[] diamond = new BufferedImage[3]; // ID = 5
//	BufferedImage[] mob1 = new BufferedImage[3]; // ID = 6
//	BufferedImage[] mob2 = new BufferedImage[3]; // ID = 7
//	BufferedImage[] endblock = new BufferedImage[3]; // ID = 8
//	BufferedImage[] explode = new BufferedImage[3]; // ID = 10
	
	//Creating player objects for different animations
	public BufferedImage[] playerlowersrest, playeruprest, playerrightrest, playerleftrest = new BufferedImage[0];
	public BufferedImage[] playerlowers, playerup = new BufferedImage[3];
	public BufferedImage[] playerrest, playerwinlevel, playerdierock  = new BufferedImage[1];
	public BufferedImage[] playerright, playerleft = new BufferedImage[2];
	public BufferedImage[] playerdietime = new BufferedImage[5];
	
	
	
	
	private File playerpng = null ;
	private File blockpng = null;
	Spritesheet ps, bs;
	private BufferedImage player_sheet = null;
	private BufferedImage block_sheet = null;
	
	
	public Texture() {
		playerpng = new File("/Player.png");
		blockpng = new File("/Blocks.png");
		try {
			player_sheet = ImageIO.read(playerpng);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			block_sheet = ImageIO.read(blockpng);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ps = new Spritesheet(player_sheet);
		bs = new Spritesheet(block_sheet);
	}
	
	// Classe élément pour créer l'instance
	// Texture tex;
	// tex = new Texture();
	// public static Texture getInstance(){
	// return tex;
	// }
	
	private void getTexture() {
		
		//Texture for Blocks
		background[0] = bs.grabImage(3, 1, width, height);
		air[0] = null;
		wall[0] = bs.grabImage(1, 1, width, height);
		dirt[0] = bs.grabImage(2, 1, width, height);
		
		for(int x = 0; x < 4; x++) {
			rock[x] = bs.grabImage(4, x+1, width, height);
			diamond[x] = bs.grabImage(5, x+1, width, height);
			mob1[x] = bs.grabImage(10, x+1, width, height);
			mob2[x] = bs.grabImage(11, x+1, width, height);
			endblock[x] = bs.grabImage(7, x+1, width, height);
			explode[x] = bs.grabImage(12, x+1, width, height);
			
		}
		//Texture for player
		playerlowersrest[0] = ps.grabImage(1, 5, width, height);
		playeruprest[0] = ps.grabImage(1, 3, width, height);
		playerrightrest[0] = ps.grabImage(2, 6, width, height);
		playerleftrest[0] = ps.grabImage(4, 6, width, height);
		
		//Animation with 2 frames
		for(int x = 0; x < 2; x++) {
		playerrest[x] = ps.grabImage(x+1, 1, width, height);
		playerwinlevel[x] = ps.grabImage(x+3, 1, width, height);
		playerdierock[x] = ps.grabImage(x+5, 1, width, height);
		}
		
		//Animation with 3 frames
		for(int x = 0; x < 3; x++) {
		playerright[x] = ps.grabImage(x+1, 4, width, height);
		playerleft[x] = ps.grabImage(x+1, 2, width, height);
		}
		
		//Animation with 4 frames
		for(int x = 0; x < 4; x++) {
		playerlowers[x] = ps.grabImage(x+1, 5, width, height);
		playerup[x] = ps.grabImage(x+1, 3, width, height);
		}
		
		//Animation with 4 frames
		for(int x = 0; x < 5; x++) {
		playerdietime[x] = ps.grabImage(x+1, 6, width, height);
		}
	}
}
