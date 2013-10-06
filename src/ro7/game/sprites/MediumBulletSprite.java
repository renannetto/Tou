package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.Sprite;
import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.sprites.shapes.Polygon;
import ro7.engine.world.Collidable;
import cs195n.Vec2f;

public class MediumBulletSprite extends Sprite implements Collidable {
	
	private final float EDGE_SIZE = 7.5f;
	
	private Polygon sprite;

	public MediumBulletSprite(Vec2f position, Color color) {
		super(position);
		sprite = new Polygon(position, color, position, position.plus(
				-EDGE_SIZE/2.0f, -EDGE_SIZE), position.plus(-EDGE_SIZE, 0.0f));
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
