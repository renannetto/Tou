package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.AAB;
import ro7.engine.sprites.Circle;
import ro7.engine.sprites.CollidingShape;
import ro7.engine.sprites.CompoundShape;
import ro7.engine.sprites.Sprite;
import ro7.engine.world.Collidable;

public class PlayerSprite extends Sprite implements Collidable {

	private final float CENTER_RADIUS = 10.0f;
	private final Vec2f WING_DIMENSIONS = new Vec2f(20.0f, 20.0f);
	private final Color PLAYER_COLOR = Color.WHITE;

	private CompoundShape sprite;

	public PlayerSprite(Vec2f position) {
		super(position);
		Circle center = new Circle(position, PLAYER_COLOR, PLAYER_COLOR,
				CENTER_RADIUS);
		AAB wing1 = new AAB(new Vec2f(position.x - CENTER_RADIUS
				- (WING_DIMENSIONS.x / 2.0f), position.y), PLAYER_COLOR,
				PLAYER_COLOR, WING_DIMENSIONS);
		AAB wing2 = new AAB(new Vec2f(position.x + CENTER_RADIUS
				+ (WING_DIMENSIONS.x / 2.0f), position.y), PLAYER_COLOR,
				PLAYER_COLOR, WING_DIMENSIONS);
		sprite = new CompoundShape(position, center, wing1, wing2);
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
