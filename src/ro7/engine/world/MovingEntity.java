package ro7.engine.world;

import cs195n.Vec2f;


public abstract class MovingEntity extends Entity {
	
	protected Vec2f position;
	protected float velocity;
	protected Vec2f direction;
	
	protected MovingEntity(GameWorld world, Vec2f position, float velocity, Vec2f direction) {
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
		
		//TODO: fix when direction < 1
		position = position.plus(direction.smult(velocity*seconds));
		
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
