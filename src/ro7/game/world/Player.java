package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.sprites.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.Direction;
import ro7.engine.world.Entity;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.PlayerSprite;
import cs195n.Vec2f;

public class Player extends Entity implements Collidable {

	private final float SHOOT_DELAY = 0.5f;
	private final float MOVE_FACTOR = 10.0f;

	private Vec2f position;
	private PlayerSprite sprite;
	private float lifepoints;

	private boolean fastShoot;
	private float elapsedShootTime;

	public Player(GameWorld world, Vec2f position) {
		super(world);
		this.position = position;
		sprite = new PlayerSprite(position);
		fastShoot = true;
		this.lifepoints = 100;
	}

	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

	public void move(Direction direction) {
		Vec2f translate = new Vec2f(0.0f, 0.0f);
		switch (direction) {
		case LEFT:
			translate = new Vec2f(-MOVE_FACTOR, 0.0f);
			break;
		case UP:
			translate = new Vec2f(0.0f, -MOVE_FACTOR);
			break;
		case RIGHT:
			translate = new Vec2f(MOVE_FACTOR, 0.0f);
			break;
		case DOWN:
			translate = new Vec2f(0.0f, MOVE_FACTOR);
			break;
		}
		position = position.plus(translate);
		sprite = new PlayerSprite(position);
	}

	@Override
	public void update(long nanoseconds) {
		elapsedShootTime += nanoseconds/1000000000.0f;
	}
	
	public void changeWeapon() {
		fastShoot = !fastShoot;
	}

	public Bullet shoot(Direction direction) {
		if (elapsedShootTime < SHOOT_DELAY) {
			return null;
		}
		elapsedShootTime = 0;
		if (fastShoot) {
			return new FastBullet((TouWorld)world, position, direction);
		}
		return new SlowBullet((TouWorld)world, position, direction);
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

}
