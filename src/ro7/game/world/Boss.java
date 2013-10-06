package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.engine.world.MovingEntity;
import ro7.game.sprites.BossSprite;
import cs195n.Vec2f;

public class Boss extends MovingEntity implements Collidable {
	
	private final Color BULLET_COLOR = Color.YELLOW;
	private final static float VELOCITY = 10.0f;
	private final float FAST_SHOOT_DELAY = 1f;
	private final float MEDIUM_SHOOT_DELAY = 2f;
	private final float SLOW_SHOOT_DELAY = 3f;
	
	private BossSprite sprite;
	
	private float lifepoints;
	private float elapsedTimeFast;
	private float elapsedTimeMedium;
	private float elapsedTimeSlow;

	public Boss(GameWorld world, Vec2f position) {
		super(world, position, VELOCITY, true);
		this.lifepoints = 500.0f;
		this.direction = ((TouWorld)world).playerDirection(position).normalized();
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		this.direction = ((TouWorld)world).playerDirection(position).normalized();
		
		elapsedTimeFast += nanoseconds/1000000000.0f;
		if (elapsedTimeFast > FAST_SHOOT_DELAY) {
			shootFast();
			elapsedTimeFast = 0;
		}
		
		elapsedTimeMedium += nanoseconds/1000000000.0f;
		if (elapsedTimeMedium > MEDIUM_SHOOT_DELAY) {
			shootMedium();
			elapsedTimeMedium = 0;
		}
		
		elapsedTimeSlow += nanoseconds/1000000000.0f;
		if (elapsedTimeSlow > SLOW_SHOOT_DELAY) {
			shootSlow();
			elapsedTimeSlow = 0;
		}
	}

	@Override
	public boolean collides(Collidable other) {
		return sprite.collides(other);
	}

	@Override
	public CollidingShape getShape() {
		return sprite.getShape();
	}

	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
	}
	
	@Override
	public void insideWorld() {
		super.insideWorld();
		sprite = new BossSprite(position);
	}
	
	protected void shootFast() {
		Bullet bullet = new FastBullet(world, position.minus(30.0f, 0.0f), BULLET_COLOR, direction);
		((TouWorld)world).enemyShoot(bullet);
	}
	
	protected void shootMedium() {
		Bullet bullet = new MediumBullet(world, position, BULLET_COLOR, direction);
		((TouWorld)world).enemyShoot(bullet);
	}
	
	protected void shootSlow() {
		Bullet bullet = new SlowBullet(world, position.plus(30.0f, 0.0f), BULLET_COLOR, direction);
		((TouWorld)world).enemyShoot(bullet);
	}
	
	public void shooted(Bullet bullet) {
		lifepoints -= bullet.getDamage();
	}
	
	public boolean isAlive() {
		return lifepoints > 0;
	}

}
