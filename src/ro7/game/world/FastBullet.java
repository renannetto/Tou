package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.world.Direction;
import ro7.game.sprites.FastBulletSprite;
import cs195n.Vec2f;

public class FastBullet extends Bullet {
	
	private final static float VELOCITY = 100;
	
	private FastBulletSprite sprite;
	
	public FastBullet(Vec2f position, Direction direction) {
		super(position, VELOCITY, direction);
		sprite = new FastBulletSprite(position);
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds); 
		sprite = new FastBulletSprite(position);
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

}
