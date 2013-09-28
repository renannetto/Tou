package ro7.engine.world;

import java.awt.Graphics2D;

public interface Entity {
	
	public void update(long nanoseconds);
	
	public void draw(Graphics2D g);

}
