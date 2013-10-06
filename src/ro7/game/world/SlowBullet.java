package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.SlowBulletSprite;
import cs195n.Vec2f;

public class SlowBullet extends Bullet {
	
	private final static float VELOCITY = 200;
	private final float DAMAGE = 30;
	
	private SlowBulletSprite sprite;
	
	public SlowBullet(GameWorld world, Vec2f position, Color color, Vec2f direction) {
		super(world, position, color, VELOCITY, direction);
		sprite = new SlowBulletSprite(position, color);
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds); 
		sprite = new SlowBulletSprite(position, color);
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
