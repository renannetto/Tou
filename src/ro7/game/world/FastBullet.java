package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.sprites.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.Direction;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.FastBulletSprite;
import cs195n.Vec2f;

public class FastBullet extends Bullet {
	
	private final static float VELOCITY = 100;
	private final float DAMAGE = 50;
	
	private FastBulletSprite sprite;
	
	public FastBullet(GameWorld world, Vec2f position, Direction direction) {
		super(world, position, VELOCITY, direction);
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

	@Override
	public boolean collides(Collidable other) {
		return sprite.collides(other);
	}

	@Override
	public CollidingShape getShape() {
		return sprite.getShape();
	}

	@Override
	public float getDamage() {
		return DAMAGE;
	}

}
