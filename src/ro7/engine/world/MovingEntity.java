package ro7.engine.world;

import cs195n.Vec2f;


public abstract class MovingEntity implements Entity {
	
	protected Vec2f position;
	protected float velocity;
	protected Direction direction;
	
	protected MovingEntity(Vec2f position, float velocity, Direction direction) {
		this.position = position;
		this.velocity = velocity;
		this.direction = direction;
	}

	@Override
	public void update(long nanoseconds) {
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
				
	}

}
