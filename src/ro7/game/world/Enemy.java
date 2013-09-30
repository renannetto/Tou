package ro7.game.world;

import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.engine.world.MovingEntity;
import cs195n.Vec2f;

public abstract class Enemy extends MovingEntity implements Collidable {

	private final int CHANGE_DIRECTION_TIME = 3;
	private final int SHOOT_TIME = 2;
	private final static float VELOCITY = 50.0f;

	private float lifepoints;
	
	private float elapsedMovingTime;
	private float elapsedShootingTime;

	public Enemy(GameWorld world, Vec2f position) {
		super(world, position, VELOCITY);
		this.lifepoints = 100;
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		elapsedMovingTime += nanoseconds/1000000000.0f;
		elapsedShootingTime += nanoseconds/1000000000.0f;
		if (elapsedMovingTime>CHANGE_DIRECTION_TIME) {
			changeDirection();
			elapsedMovingTime = 0;
		}
		if (elapsedShootingTime>SHOOT_TIME) {
			shoot();
			elapsedShootingTime = 0;
		}
	}
	
	protected abstract void shoot();

	public void changeDirection() {
		direction = newDirection();
	}

	public void shooted(Bullet bullet) {
		lifepoints -= bullet.getDamage();
	}
	
	public boolean isAlive() {
		return lifepoints > 0;
	}
	
	

}
