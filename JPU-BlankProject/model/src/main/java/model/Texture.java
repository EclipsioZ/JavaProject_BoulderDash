package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	
	private static int height = 16;
	private static int width = 16;
	public int idTexture;
	
	//Creating block objects for different animations
	public static BufferedImage[] background = new BufferedImage[4]; //ID = 9
	public static BufferedImage[] air = new BufferedImage[4]; // ID = 0
	public static BufferedImage[] wall = new BufferedImage[1]; // ID = 2
	public static BufferedImage[] dirt = new BufferedImage[4]; // ID = 3
	public static BufferedImage[] rock = new BufferedImage[4]; // ID = 4
	public static BufferedImage[] diamond = new BufferedImage[4]; // ID = 5
	public static BufferedImage[] mob1 = new BufferedImage[4]; // ID = 6
	public static BufferedImage[] mob2 = new BufferedImage[4]; // ID = 7
	public static BufferedImage[] endblock = new BufferedImage[4]; // ID = 8
	public static BufferedImage[] explode = new BufferedImage[4]; // ID = 10
	
	//Creating player objects for different animations
	public static BufferedImage[] playerlowersrest = new BufferedImage[1];
	public static BufferedImage[] playeruprest = new BufferedImage[1];
	public static BufferedImage[] playerrightrest = new BufferedImage[1];
	public static BufferedImage[] playerleftrest = new BufferedImage[1];
	public static BufferedImage[] playerrest  = new BufferedImage[2];
	public static BufferedImage[] playerwinlevel  = new BufferedImage[2];
	public static BufferedImage[] playerdierock  = new BufferedImage[2];
	public static BufferedImage[] playerright = new BufferedImage[3];
	public static BufferedImage[] playerleft = new BufferedImage[3];
	public static BufferedImage[] playerlowers = new BufferedImage[4];
	public static BufferedImage[] playerup = new BufferedImage[4];
	public static BufferedImage[] playerdietime = new BufferedImage[6];
	public static BufferedImage[] playerlevelselector = new BufferedImage[2];
	
	
	
	
	private File playerpng = null ;
	private File blockpng = null;
	Spritesheet ps, bs;
	private BufferedImage player_sheet = null;
	private BufferedImage block_sheet = null;
	
	
	public Texture() {
		playerpng = new File(getClass().getClassLoader().getResource("Player.png").getFile());
		blockpng = new File(getClass().getClassLoader().getResource("Blocks.png").getFile());
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
	
	public void getTexture(int world) {
		
		idTexture = world;
		
		int textureworld = 0;
		int dirtanimated = 0;
		int backgroundanimated = 0;
		int rockanimated = 0;
		
		switch (world) {
		case 1:
			textureworld = 0;
			rockanimated = 1;
			break;
		case 2:
			textureworld = 4;
			rockanimated = 1;
			break;
		case 3:
			textureworld = 8;
			rockanimated = 1;
			dirtanimated = 1;
			backgroundanimated = 1;
			break;
		case 4:
			textureworld = 12;
			rockanimated = 1;
			backgroundanimated = 1;
			break;
		case 5:
			textureworld = 16;
			dirtanimated = 1;
			backgroundanimated = 1;
			break;
		case 6:
			textureworld = 20;
			rockanimated = 1;
			dirtanimated = 1;
			backgroundanimated = 1;
			break;
		default:
			textureworld = 0;
			break;
		}
		
		
		//Texture for Blocks
		wall[0] = bs.grabImage(1, textureworld + 1, width, height);
		dirt[0] = bs.grabImage(2, textureworld + 1, width, height);
		
		//Animations with 4 frames
		for(int x = 0; x < 4; x++) {
			if(backgroundanimated == 1) {
				background[x] = bs.grabImage(3, textureworld + (x+1), width, height);
				air[x] = bs.grabImage(3, textureworld + (x+1), width, height);
			}
			else {
				background[x] = bs.grabImage(3, textureworld + 1, width, height);
				air[x] = bs.grabImage(3, textureworld + 1, width, height);
			}
			if(dirtanimated == 1) {
				dirt[x] = bs.grabImage(2, textureworld + (x+1), width, height);
			}
			else {
				dirt[x] = bs.grabImage(2, textureworld + 1, width, height);
			}
			if(rockanimated == 1) {
				rock[x] = bs.grabImage(4, textureworld + (x+1), width, height);
			}
			else {
				rock[x] = bs.grabImage(4, textureworld + 1, width, height);
			}
			diamond[x] = bs.grabImage(5, textureworld + (x+1), width, height);
			mob1[x] = bs.grabImage(10, textureworld + (x+1), width, height);
			mob2[x] = bs.grabImage(11, textureworld + (x+1), width, height);
			endblock[x] = bs.grabImage(7, textureworld + (x+1), width, height);
			explode[x] = bs.grabImage(12, textureworld + (x+1), width, height);
			
		}
		//Texture for player
		playerlowersrest[0] = ps.grabImage(1, 5, width, height);
		playeruprest[0] = ps.grabImage(1, 3, width, height);
		playerrightrest[0] = ps.grabImage(2, 6, width, height);
		playerleftrest[0] = ps.grabImage(4, 6, width, height);
		
		//Animations with 2 frames
		for(int x = 0; x < 2; x++) {
			playerrest[x] = ps.grabImage(x+1, 1, width, height);
			playerwinlevel[x] = ps.grabImage(x+3, 1, width, height);
			playerdierock[x] = ps.grabImage(x+5, 1, width, height);
			playerlevelselector[x] = ps.grabImage(x+5, 14, width, height);
		}
		
		//Animations with 3 frames
		for(int x = 0; x < 3; x++) {
			playerright[x] = ps.grabImage(x+1, 4, width, height);
			playerleft[x] = ps.grabImage(x+1, 2, width, height);
		}
		
		//Animations with 4 frames
		for(int x = 0; x < 4; x++) {
			playerlowers[x] = ps.grabImage(x+1, 5, width, height);
			playerup[x] = ps.grabImage(x+1, 3, width, height);
		}
		
		//Animations with 4 frames
		for(int x = 0; x < 5; x++) {
			playerdietime[x] = ps.grabImage(x+1, 6, width, height);
		}
	}
}
