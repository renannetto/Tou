package ro7.game.world;

import java.awt.Color;

import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.engine.world.MovingEntity;
import cs195n.Vec2f;

public abstract class Bullet extends MovingEntity implements Collidable {
	
	protected Color color;

	protected Bullet(GameWorld world, Vec2f position, Color color, float velocity, Vec2f direction) {
		super(world, position, velocity, direction);
		this.color = color;
	}
	
	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		
		Vec2f min = new Vec2f(0.0f, 0.0f);
		Vec2f max = world.getDimensions();
		if (position.x == min.x || position.x == max.x || position.y == min.y || position.y == max.y) {
			((TouWorld)world).removeShoot(this);
		}
	}
	
	public abstract float getDamage();

}
