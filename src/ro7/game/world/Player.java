package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.Entity;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.PlayerSprite;
import cs195n.Vec2f;

public class Player extends Entity implements Collidable {

	private final float SHOOT_DELAY = 0.5f;
	private final float MOVE_FACTOR = 10.0f;
	private final Color FAST_BULLET_COLOR = Color.GREEN;
	private final Color SLOW_BULLET_COLOR = Color.BLUE;

	private PlayerSprite sprite;
	private float lifepoints;

	private boolean fastShoot;
	private float elapsedShootTime;

	public Player(GameWorld world, Vec2f position) {
		super(world, position);
		sprite = new PlayerSprite(position);
		fastShoot = true;
		this.lifepoints = 100;
	}

	public void draw(Graphics2D g) {
		sprite.draw(g);
	}

	public void move(Vec2f direction) {
		position = position.plus(direction.smult(MOVE_FACTOR));
		sprite = new PlayerSprite(position);
		insideWorld();
	}

	@Override
	public void update(long nanoseconds) {
		elapsedShootTime += nanoseconds/1000000000.0f;
	}
	
	public void changeWeapon() {
		fastShoot = !fastShoot;
	}

	public Bullet shoot(Vec2f direction) {
		if (elapsedShootTime < SHOOT_DELAY) {
			return null;
		}
		elapsedShootTime = 0;
		if (fastShoot) {
			return new FastBullet((TouWorld)world, position, FAST_BULLET_COLOR, direction);
		}
		return new SlowBullet((TouWorld)world, position, SLOW_BULLET_COLOR, direction);
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

}
