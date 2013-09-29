package ro7.engine.world;

import java.awt.Graphics2D;

public abstract class Entity {
	
	protected GameWorld world;
	
	protected Entity(GameWorld world) {
		this.world = world;
	}
	
	public abstract void update(long nanoseconds);
	
	public abstract void draw(Graphics2D g);

}
