package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import cs195n.Vec2f;
import ro7.engine.sprites.shapes.AAB;
import ro7.engine.world.Entity;
import ro7.engine.world.GameWorld;

public class TouBackground extends Entity {
	
	private final Color BACKGROUND_COLOR = Color.BLACK;
	
	private AAB sprite;

	public TouBackground(GameWorld world, Vec2f position, Vec2f dimensions) {
		super(world, position);
		sprite = new AAB(position, BACKGROUND_COLOR, BACKGROUND_COLOR, dimensions);
	}

	@Override
	public void update(long nanoseconds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

}
