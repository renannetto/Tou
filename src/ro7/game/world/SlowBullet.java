package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.world.Direction;
import ro7.game.sprites.SlowBulletSprite;
import cs195n.Vec2f;

public class SlowBullet extends Bullet {
	
	private final static float VELOCITY = 50;
	
	private SlowBulletSprite sprite;
	
	public SlowBullet(Vec2f position, Direction direction) {
		super(position, VELOCITY, direction);
		sprite = new SlowBulletSprite(position);
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds); 
		sprite = new SlowBulletSprite(position);
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

}
