package ro7.game.world;

import java.awt.Color;
import java.awt.Graphics2D;

import ro7.engine.sprites.shapes.CollidingShape;
import ro7.engine.world.Collidable;
import ro7.engine.world.GameWorld;
import ro7.game.sprites.EnemyCircleSprite;
import cs195n.Vec2f;

public class EnemyCircle extends Enemy {
	
	private final Color BULLET_COLOR = Color.MAGENTA;
	
	private EnemyCircleSprite sprite;

	public EnemyCircle(GameWorld world, Vec2f position) {
		super(world, position);
		
		this.sprite = new EnemyCircleSprite(position);
	}
	
	@Override
	public void update(long nanoseconds) {
		super.update(nanoseconds);
		sprite = new EnemyCircleSprite(position);
	}
	
	@Override
	public void draw(Graphics2D g) {
		sprite.draw(g);
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
	protected void shoot() {
		Bullet bullet = new FastBullet(world, position, BULLET_COLOR, direction);
		((TouWorld)world).enemyShoot(bullet);
	}
	
	@Override
	public void insideWorld() {
		super.insideWorld();
		sprite = new EnemyCircleSprite(position);
	}

}
