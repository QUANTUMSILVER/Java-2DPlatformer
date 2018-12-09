package game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage playerLeft, playerRight	;
	public static BufferedImage glow;
	
	public static BufferedImage[] grass;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/players.png"));
		SpriteSheet grasses = new SpriteSheet(ImageLoader.loadImage("/textures/grasses.png"));
	
		grass = new BufferedImage[9];
		
		grass[0] = grasses.crop(0, 0*118, 88, 118);
		grass[1] = grasses.crop(0, 1*118, 88, 118);
		grass[2] = grasses.crop(0, 2*118, 88, 118);
		grass[3] = grasses.crop(0, 3*118, 88, 118);
		grass[4] = grasses.crop(0, 4*118, 88, 118);
		grass[5] = grasses.crop(0, 5*118, 88, 118);
		grass[6] = grasses.crop(0, 6*118, 88, 118);
		grass[7] = grasses.crop(0, 7*118, 88, 118);
		grass[8] = grasses.crop(0, 8*118, 88, 118);
		
		playerRight = sheet.crop(0, 0, 64, 64);
		playerLeft = sheet.crop(0, 64, 64, 64);
		
		glow = ImageLoader.loadImage("/textures/glow.png");
		
	}
	
}
