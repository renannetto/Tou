package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.Sprite;
import ro7.engine.sprites.shapes.Circle;
import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;

public class EnemyCircleSprite extends Sprite implements Collidable {

	private final float RADIUS = 10.0f;
	private final Color COLOR = Color.RED;
	
	private Circle sprite;

	public EnemyCircleSprite(Vec2f position) {
		super(position);
		sprite = new Circle(position, COLOR, COLOR, RADIUS);
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
