package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.AAB;
import ro7.engine.sprites.CollidingShape;
import ro7.engine.sprites.Sprite;
import ro7.engine.world.Collidable;
import cs195n.Vec2f;

public class EnemySquareSprite extends Sprite implements Collidable {

	private final Vec2f DIMENSIONS = new Vec2f(20.0f, 20.0f);
	private final Color COLOR = Color.RED;
	
	private AAB sprite;

	public EnemySquareSprite(Vec2f position) {
		super(position);
		sprite = new AAB(position, COLOR, COLOR, DIMENSIONS);
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
