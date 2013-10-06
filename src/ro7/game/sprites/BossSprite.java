package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.Sprite;
import ro7.engine.sprites.shapes.Circle;
import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.sprites.shapes.CompoundShape;
import ro7.engine.sprites.shapes.Polygon;
import ro7.engine.world.Collidable;

public class BossSprite extends Sprite implements Collidable {

	private final float RADIUS = 50.0f;
	private final float EDGE_SIZE = 100.0f;
	private final Color COLOR = Color.GRAY;

	private CompoundShape sprite;

	public BossSprite(Vec2f position) {
		super(position);
		Circle circle = new Circle(position, COLOR, COLOR, RADIUS);
		Vec2f trianglePos = position.plus(RADIUS, RADIUS/4.0f);
		Polygon triangle = new Polygon(trianglePos, COLOR, trianglePos, trianglePos.plus(-EDGE_SIZE, 0.0f), trianglePos.plus(
				-EDGE_SIZE/2.0f, EDGE_SIZE));
		sprite = new CompoundShape(position, circle, triangle);
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
