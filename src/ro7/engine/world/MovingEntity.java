package ro7.engine.world;

import cs195n.Vec2f;


public abstract class MovingEntity extends Entity {
	
	protected float velocity;
	protected Vec2f direction;
	
	protected MovingEntity(GameWorld world, Vec2f position, float velocity, Vec2f direction) {
		super(world, position);
		this.velocity = velocity;
		this.direction = direction;
	}
	
	protected MovingEntity(GameWorld world, Vec2f position, float velocity) {
		super(world, position);
		this.velocity = velocity;
		this.direction = newDirection();
	}

	@Override
	public void update(long nanoseconds) {
		float seconds = nanoseconds/1000000000.0f;		
		position = position.plus(direction.smult(velocity*seconds));
		insideWorld();
	}
	
	protected Vec2f newDirection() {
		float dirX = -1 + (float) (Math.random() * 2);
		float dirY = (float) Math.sqrt(1-(dirX*dirX));
		int up = (int) (Math.random()*2);
		if (up == 0) {
			dirY = -dirY;
		}
		return new Vec2f(dirX, dirY);
	}

}
