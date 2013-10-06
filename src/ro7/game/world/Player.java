package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.engine.world.MovingEntity;
import ro7.game.sprites.PlayerSprite;
import cs195n.Vec2f;

public class Player extends MovingEntity implements Collidable {

	private static final float VELOCITY = 175f;
	private final float FAST_SHOOT_DELAY = 0.1f;
	private final float MEDIUM_SHOOT_DELAY = 0.15f;
	private final float SLOW_SHOOT_DELAY = 0.2f;
	private final Color FAST_BULLET_COLOR = Color.GREEN;
	private final Color MEDIUM_BULLET_COLOR = Color.RED;
	private final Color SLOW_BULLET_COLOR = Color.BLUE;

	private PlayerSprite sprite;
	private float lifepoints;

	private int bullet;
	private float elapsedShootTime;

	private Vec2f unnormDirection;

	public Player(GameWorld world, Vec2f position) {
		super(world, position, VELOCITY, false);
		this.direction = new Vec2f(0.0f, 0.0f);

		sprite = new PlayerSprite(position);
		bullet = 0;
		this.lifepoints = 100;

		this.unnormDirection = this.direction;
	}

	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

	public void move(Vec2f direction) {
		moving = true;
		if (!sameDirection(direction)) {
			this.unnormDirection = this.unnormDirection.plus(direction);
			if (this.unnormDirection.mag2() > 0) {
				this.direction = this.unnormDirection.normalized();
			} else {
				this.direction = this.unnormDirection;
			}
		}
	}

	private boolean sameDirection(Vec2f direction) {
		return (direction.x > 0 && this.direction.x > 0)
				|| (direction.x < 0 && this.direction.x < 0)
				|| (direction.y > 0 && this.direction.y > 0)
				|| (direction.y < 0 && this.direction.y < 0);
	}

	public void stop(Vec2f direction) {
		this.unnormDirection = this.unnormDirection.minus(direction);
		if (this.unnormDirection.mag2() > 0) {
			this.direction = this.unnormDirection.normalized();
		} else {
			this.direction = this.unnormDirection;
			moving = false;
		}
	}

	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		elapsedShootTime += nanoseconds / 1000000000.0f;
		sprite = new PlayerSprite(position);
	}

	public void changeWeapon() {
		bullet = (bullet+1)%3;
	}

	public Bullet shoot(Vec2f direction) {
		switch (bullet) {
		case 0:
			if (elapsedShootTime > FAST_SHOOT_DELAY) {
				elapsedShootTime = 0;
				return new FastBullet((TouWorld) world, position,
						FAST_BULLET_COLOR, direction);
			}
			break;
		case 1:
			if (elapsedShootTime > MEDIUM_SHOOT_DELAY) {
				elapsedShootTime = 0;
				return new MediumBullet((TouWorld) world, position,
						MEDIUM_BULLET_COLOR, direction);
			}
			break;
		case 2:
			if (elapsedShootTime > SLOW_SHOOT_DELAY) {
				elapsedShootTime = 0;
				return new SlowBullet((TouWorld) world, position,
						SLOW_BULLET_COLOR, direction);
			}
			break;
		}
		return null;
	}

	@Override
	public boolean collides(Collidable other) {
		return sprite.collides(other);
	}

	@Override
	public CollidingShape getShape() {
		return sprite.getShape();
	}

	public void shooted(Bullet bullet) {
		lifepoints -= bullet.getDamage();
	}

	public boolean isAlive() {
		return lifepoints > 0;
	}

	public void kill() {
		lifepoints = 0;
	}

	@Override
	public void insideWorld() {
		super.insideWorld();
		sprite = new PlayerSprite(position);
	}
	
	public Vec2f getPosition() {
		return position;
	}

	public float getLifepoints() {
		return lifepoints;
	}

}
