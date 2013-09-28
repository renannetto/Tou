package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.Circle;
import ro7.engine.sprites.Sprite;

public class SlowBulletSprite extends Sprite {
	
	private final float RADIUS = 5.0f;
	private final Color COLOR = Color.BLUE;
	
	private Circle sprite;

	public SlowBulletSprite(Vec2f position) {
		super(position);
		sprite = new Circle(position, COLOR, COLOR, RADIUS);
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

}
