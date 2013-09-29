package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.AAB;
import ro7.engine.sprites.CollidingShape;
import ro7.engine.sprites.Sprite;
import ro7.engine.world.Collidable;

public class FastBulletSprite extends Sprite implements Collidable {
	
	private final Vec2f SIZE = new Vec2f(10.0f, 5.0f);
	private final Color COLOR = Color.GREEN;
	
	private AAB sprite;

	public FastBulletSprite(Vec2f position) {
		super(position);
		sprite = new AAB(position, COLOR, COLOR, SIZE);
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

	@Override
	public boolean collides(Collidable other) {
		return sprite.collides(other.getShape());
	}

	@Override
	public CollidingShape getShape() {
		return sprite;
	}

}
