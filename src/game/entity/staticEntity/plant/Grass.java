package game.entity.staticEntity.plant;

import java.awt.Graphics;

import game.Handler;
import game.entity.staticEntity.StaticEntity;
import game.gfx.Animation;
import game.gfx.Assets;
import lib.Vector;

public class Grass extends StaticEntity{
	
	private Animation anim;
	
	public Grass(Handler handler, Vector pos) {
		super(handler, pos);
		anim = new Animation(120, (int) (Math.random()*9), Assets.grass);
	}

	@Override
	public void update() {
		anim.update();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(anim.getCurretnFrame(), (int) (pos.x - handler.getCamera().getXoff()), (int) (pos.y - handler.getCamera().getYoff()), null);
	}
	
}
