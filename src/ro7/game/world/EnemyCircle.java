package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.sprites.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.Direction;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.EnemyCircleSprite;
import cs195n.Vec2f;

public class EnemyCircle extends Enemy {
	
	private EnemyCircleSprite sprite;

	public EnemyCircle(GameWorld world, Vec2f position, Direction direction) {
		super(world, position, direction);
		
		this.sprite = new EnemyCircleSprite(position);
	}
	
	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		sprite = new EnemyCircleSprite(position);
	}
	
	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

	@Override
	public boolean collides(Collidable other) {
		return sprite.collides(other);
	}

	@Override
	public CollidingShape getShape() {
		return sprite.getShape();
	}

}