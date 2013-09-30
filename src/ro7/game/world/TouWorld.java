package ro7.game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ro7.engine.world.GameWorld;
import ro7.engine.world.Viewport;
import ro7.game.sprites.TouBackground;
import cs195n.Vec2f;

public class TouWorld extends GameWorld {

	private final int ENEMY_CREATION_TIME = 5;
	
	private TouBackground background;

	private Player player;
	private Set<Bullet> playerBullets;
	private Set<Bullet> enemyBullets;
	private Set<Enemy> enemies;

	private List<Bullet> removeShoot;

	private float elapsedTime;

	public TouWorld(Vec2f dimensions) {
		super(dimensions);
		
		background = new TouBackground(this, dimensions.sdiv(2.0f), dimensions);
		entities.add(background);
		
		player = new Player(this, dimensions.sdiv(2));
		entities.add(player);

		enemies = new HashSet<Enemy>();

		playerBullets = new HashSet<Bullet>();
		enemyBullets = new HashSet<Bullet>();
	}

	@Override
	public void update(long nanoseconds) {
		removeShoot = new ArrayList<Bullet>();
		super.update(nanoseconds);

		for (Bullet bullet : playerBullets) {
			bullet.update(nanoseconds);
		}
		for (Bullet bullet : enemyBullets) {
			bullet.update(nanoseconds);
		}

		createEnemy(nanoseconds);
		checkCollisions();
		checkAlive();

		for (Bullet bullet : removeShoot) {
			playerBullets.remove(bullet);
			enemyBullets.remove(bullet);
		}
	}

	@Override
	public void draw(Graphics2D g, Vec2f min, Vec2f max, Viewport viewport) {
		super.draw(g, min, max, viewport);

		for (Bullet bullet : playerBullets) {
			bullet.draw(g);
		}
		for (Bullet bullet : enemyBullets) {
			bullet.draw(g);
		}
	}

	private void checkAlive() {
		List<Enemy> deadEnemies = new ArrayList<Enemy>();
		for (Enemy enemy : enemies) {
			if (!enemy.isAlive()) {
				deadEnemies.add(enemy);
			}
		}

		for (Enemy enemy : deadEnemies) {
			enemies.remove(enemy);
			entities.remove(entities.indexOf(enemy));
		}

		if (!player.isAlive() && entities.contains(player)) {
			entities.remove(entities.indexOf(player));
		}
	}

	private void checkCollisions() {
		for (Bullet bullet : playerBullets) {
			for (Enemy enemy : enemies) {
				if (bullet.collides(enemy)) {
					enemy.shooted(bullet);
					removeShoot(bullet);
				}
			}
		}

		if (player.isAlive()) {
			for (Bullet bullet : enemyBullets) {
				if (bullet.collides(player)) {
					player.shooted(bullet);
					removeShoot(bullet);
				}
			}
		}
	}

	private void createEnemy(long nanoseconds) {
		elapsedTime += nanoseconds / 1000000000.0f;
		if (elapsedTime > ENEMY_CREATION_TIME && enemies.size() < 10) {
			Vec2f position = new Vec2f((float) (Math.random() * dimensions.x),
					(float) (Math.random() * dimensions.y));

			Enemy enemy = new EnemyCircle(this, position);
			enemies.add(enemy);
			entities.add(enemy);

			position = new Vec2f((float) (Math.random() * dimensions.x),
					(float) (Math.random() * dimensions.y));

			enemy = new EnemySquare(this, position);
			enemies.add(enemy);
			entities.add(enemy);

			elapsedTime = 0;
		}
	}

	public void movePlayer(Vec2f direction) {
		if (player.isAlive()) {
			player.move(direction);
		}
	}

	public void shoot(Vec2f direction) {
		if (player.isAlive()) {
			Bullet bullet = player.shoot(direction);
			if (bullet != null) {
				playerBullets.add(bullet);
			}
		}
	}

	public void changeWeapon() {
		if (player.isAlive()) {
			player.changeWeapon();
		}
	}

	public void removeShoot(Bullet bullet) {
		removeShoot.add(bullet);
	}

	public void enemyShoot(Bullet bullet) {
		enemyBullets.add(bullet);
	}

	public boolean lost() {
		return !player.isAlive();
	}
	
	@Override
	public void resize(Vec2f newSize) {
		super.resize(newSize);
		entities.remove(entities.indexOf(background));
		background = new TouBackground(this, dimensions.sdiv(2.0f), dimensions);
		entities.add(0, background);
	}

}
