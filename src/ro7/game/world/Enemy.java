package ro7.game.world;

import static ro7.engine.world.Direction.DOWN;
import static ro7.engine.world.Direction.LEFT;
import static ro7.engine.world.Direction.RIGHT;
import static ro7.engine.world.Direction.UP;
import ro7.engine.world.Collidable;
import ro7.engine.world.Direction;
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

	public Enemy(GameWorld world, Vec2f position, Direction direction) {
		super(world, position, VELOCITY, direction);
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
	
	private void shoot() {
		Bullet bullet = new FastBullet(world, position, direction);
		((TouWorld)world).enemyShoot(bullet);
	}

	public void changeDirection() {
		int newDirection = (int) (Math.random() * 4);
		switch (newDirection) {
		case 0:
			direction = LEFT;
			break;
		case 1:
			direction = UP;
			break;
		case 2:
			direction = RIGHT;
			break;
		case 3:
			direction = DOWN;
			break;
		}
	}

	public void shooted(Bullet bullet) {
		lifepoints -= bullet.getDamage();
	}
	
	public boolean isAlive() {
		return lifepoints > 0;
	}

}
