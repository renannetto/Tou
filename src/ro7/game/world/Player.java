package ro7.game.world;

import java.awt.Graphics2D;

import ro7.engine.world.Direction;
import ro7.engine.world.Entity;
import ro7.game.sprites.PlayerSprite;
import cs195n.Vec2f;

public class Player implements Entity {

	private final float MOVE_FACTOR = 10.0f;

	private Vec2f position;
	private PlayerSprite sprite;

	private boolean fastShoot;

	public Player(Vec2f position) {
		this.position = position;
		sprite = new PlayerSprite(position);
		fastShoot = true;
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
		// TODO Auto-generated method stub

	}
	
	public void changeWeapon() {
		fastShoot = !fastShoot;
	}

	public Bullet shoot(Direction direction) {
		if (fastShoot) {
			return new FastBullet(position, direction);
		}
		return new SlowBullet(position, direction);
	}

}
