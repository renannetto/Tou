package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.Sprite;
import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.sprites.shapes.Polygon;
import ro7.engine.world.Collidable;
import cs195n.Vec2f;

public class EnemyTriangleSprite extends Sprite implements Collidable {

	private final float EDGE_SIZE = 20.0f;
	private final Color COLOR = Color.RED;

	private Polygon sprite;

	public EnemyTriangleSprite(Vec2f position) {
		super(position);
		sprite = new Polygon(position, COLOR, position, position.plus(-EDGE_SIZE, 0.0f), position.plus(
				-EDGE_SIZE/2.0f, -EDGE_SIZE));
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
