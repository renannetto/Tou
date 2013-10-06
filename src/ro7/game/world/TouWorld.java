package ro7.game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ro7.engine.world.GameWorld;
import ro7.engine.world.Viewport;
import ro7.game.sprites.TouBackground;
import ro7.game.sprites.TouUI;
import cs195n.Vec2f;

public class TouWorld extends GameWorld {

	private final int ENEMY_CIRCLE_TIME = 5;
	private final int ENEMY_SQUARE_TIME = 7;
	private final int ENEMY_TRIANGLE_TIME = 9;
	private final int ENEMY_LIMIT = 10;
	private final int ENEMIES_FOR_BOSS = 10;

	private TouBackground background;
	private TouUI ui;

	private Player player;
	private Boss boss;
	private Set<Bullet> playerBullets;
	private Set<Bullet> enemyBullets;
	private Set<Enemy> enemies;

	private List<Bullet> removeShoot;

	private float elapsedTimeCircle;
	private float elapsedTimeSquare;
	private float elapsedTimeTriangle;

	private int killedEnemies;

	public TouWorld(Vec2f dimensions) {
		super(dimensions);

		background = new TouBackground(this, dimensions.sdiv(2.0f), dimensions);
		entities.add(background);
		ui = new TouUI(this, new Vec2f(0.0f, dimensions.y-20.0f), dimensions, 0, 100.0f);
		entities.add(ui);

		player = new Player(this, dimensions.sdiv(2));
		entities.add(player);

		enemies = new HashSet<Enemy>();

		playerBullets = new HashSet<Bullet>();
		enemyBullets = new HashSet<Bullet>();

		elapsedTimeCircle = 0;
		killedEnemies = 0;
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
		if (killedEnemies >= ENEMIES_FOR_BOSS && boss == null) {
			createBoss();
		}

		for (Bullet bullet : removeShoot) {
			playerBullets.remove(bullet);
			enemyBullets.remove(bullet);
		}
	}

	private void createBoss() {
		boss = new Boss(this, dimensions.minus(dimensions.x / 2.0f,
				dimensions.y));
		entities.add(boss);
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
		boolean updateUI = false;
		for (Enemy enemy : enemies) {
			if (!enemy.isAlive()) {
				updateUI = true;
				killedEnemies++;
				deadEnemies.add(enemy);
			}
		}

		for (Enemy enemy : deadEnemies) {
			enemies.remove(enemy);
			entities.remove(entities.indexOf(enemy));
		}

		if (boss != null && !boss.isAlive() && entities.contains(boss)) {
			entities.remove(entities.indexOf(boss));
		}

		if (!player.isAlive() && entities.contains(player)) {
			entities.remove(entities.indexOf(player));
		}
		
		if (updateUI) {
			entities.remove(entities.indexOf(ui));
			ui = new TouUI(this, new Vec2f(0.0f, dimensions.y-20.0f), dimensions, killedEnemies, player.getLifepoints());
			entities.add(1, ui);
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

			if (boss != null && bullet.collides(boss)) {
				boss.shooted(bullet);
				removeShoot(bullet);
			}
		}

		if (player.isAlive()) {
			for (Bullet bullet : enemyBullets) {
				if (bullet.collides(player)) {
					player.shooted(bullet);
					removeShoot(bullet);
					entities.remove(entities.indexOf(ui));
					ui = new TouUI(this, new Vec2f(0.0f, dimensions.y-20.0f), dimensions, killedEnemies, player.getLifepoints());
					entities.add(1, ui);
				}
			}
		}
	}

	private void createEnemy(long nanoseconds) {
		elapsedTimeCircle += nanoseconds / 1000000000.0f;
		if (elapsedTimeCircle > ENEMY_CIRCLE_TIME
				&& enemies.size() < ENEMY_LIMIT) {
			Vec2f position = new Vec2f((float) (Math.random() * dimensions.x),
					(float) (Math.random() * dimensions.y));

			Enemy enemy = new EnemyCircle(this, position);
			enemies.add(enemy);
			entities.add(enemy);

			elapsedTimeCircle = 0;
		}

		elapsedTimeSquare += nanoseconds / 1000000000.0f;
		if (elapsedTimeSquare > ENEMY_SQUARE_TIME
				&& enemies.size() < ENEMY_LIMIT) {
			Vec2f position = new Vec2f((float) (Math.random() * dimensions.x),
					(float) (Math.random() * dimensions.y));

			Enemy enemy = new EnemySquare(this, position);
			enemies.add(enemy);
			entities.add(enemy);

			elapsedTimeSquare = 0;
		}

		elapsedTimeTriangle += nanoseconds / 1000000000.0f;
		if (elapsedTimeTriangle > ENEMY_TRIANGLE_TIME
				&& enemies.size() < ENEMY_LIMIT) {
			Vec2f position = new Vec2f((float) (Math.random() * dimensions.x),
					(float) (Math.random() * dimensions.y));
			Enemy enemy = new EnemyTriangle(this, position);
			enemies.add(enemy);
			entities.add(enemy);

			elapsedTimeTriangle = 0;
		}
	}

	public void movePlayer(Vec2f direction) {
		if (player.isAlive()) {
			player.move(direction);
		}
	}

	public void stopPlayer(Vec2f direction) {
		if (player.isAlive()) {
			player.stop(direction);
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

	public boolean won() {
		return !boss.isAlive();
	}

	@Override
	public void resize(Vec2f newSize) {
		super.resize(newSize);
		entities.remove(entities.indexOf(background));
		entities.remove(entities.indexOf(ui));
		background = new TouBackground(this, dimensions.sdiv(2.0f), dimensions);
		entities.add(0, background);
		ui = new TouUI(this, new Vec2f(0.0f, dimensions.y-20.0f), dimensions, killedEnemies, player.getLifepoints());
		entities.add(1, ui);
		player.insideWorld();
	}

	public Vec2f playerDirection(Vec2f position) {
		Vec2f playerPosition = player.getPosition();
		return playerPosition.minus(position);
	}

}
