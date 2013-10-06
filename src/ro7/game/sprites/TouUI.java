package ro7.game.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.Message;
import ro7.engine.world.Entity;
import ro7.game.world.TouWorld;
import cs195n.Vec2f;

public class TouUI extends Entity {
	
	private Message playerMessage;
	private Message enemiesMessage; 

	public TouUI(TouWorld world, Vec2f position, Vec2f windowDimensions, int enemiesKilled, float playerLifepoints) {
		super(world, position);
		this.playerMessage = new Message("Player lifepoints: " + playerLifepoints, 28, Color.WHITE, position);
		this.enemiesMessage = new Message("Enemies killed: " + enemiesKilled, 28, Color.WHITE, new Vec2f(windowDimensions.x/2.0f, position.y));
	}

	@Override
	public void draw(Graphics2D g) {
		playerMessage.draw(g);		
		enemiesMessage.draw(g);
	}

	@Override
	public void update(long nanoseconds) {
		// TODO Auto-generated method stub
	}

}
