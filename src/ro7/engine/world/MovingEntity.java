package ro7.engine.world;

import cs195n.Vec2f;


public abstract class MovingEntity extends Entity {
	
	protected Vec2f position;
	protected float velocity;
	protected Direction direction;
	
	protected MovingEntity(GameWorld world, Vec2f position, float velocity, Direction direction) {
		super(world);
		this.position = position;
		this.velocity = velocity;
		this.direction = direction;
	}

	@Override
	public void update(long nanoseconds) {
		Vec2f min = new Vec2f(0.0f, 0.0f);
		Vec2f max = world.getDimensions();
		float seconds = nanoseconds/1000000000.0f;
		switch (direction) {
		case LEFT:
			position = position.plus(-velocity*seconds, 0.0f);
			break;
		case UP:
			position = position.plus(0.0f, -velocity*seconds);
			break;
		case RIGHT:
			position = position.plus(velocity*seconds, 0.0f);
			break;
		case DOWN:
			position = position.plus(0.0f, velocity*seconds);
			break;
		}
		
		if (position.x < min.x) {
			position = new Vec2f(min.x, position.y);
		} else if (position.x > max.x) {
			position = new Vec2f(max.x, position.y);
		}
		
		if (position.y < min.y) {
			position = new Vec2f(position.x, min.y);
		} else if (position.y > max.y) {
			position = new Vec2f(position.x, max.y);
		}
	}

}
