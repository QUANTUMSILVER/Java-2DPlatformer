package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage playerLeft, playerRight	;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/players.png"));
		System.out.println(sheet);
		playerRight = sheet.crop(0, 0, 64, 64);
		playerLeft = sheet.crop(0, 64, 64, 64);
		
	}
	
}
