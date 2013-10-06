package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.MediumBulletSprite;
import cs195n.Vec2f;

public class MediumBullet extends Bullet {
	
	private final static float VELOCITY = 300;
	private final float DAMAGE = 20;
	
	private MediumBulletSprite sprite;
	
	public MediumBullet(GameWorld world, Vec2f position, Color color, Vec2f direction) {
		super(world, position, color, VELOCITY, direction);
		sprite = new MediumBulletSprite(position, color);
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds); 
		sprite = new MediumBulletSprite(position, color);
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
