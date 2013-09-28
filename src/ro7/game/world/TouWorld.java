package ro7.game.world;

import ro7.engine.world.Direction;
import ro7.engine.world.GameWorld;
import cs195n.Vec2f;

public class TouWorld extends GameWorld {
	
	private Player player;

	public TouWorld(Vec2f dimensions) {
		super(dimensions);
		player = new Player(dimensions.sdiv(2));
		entities.add(player);
	}
	
	public void movePlayer(Direction direction) {
		player.move(direction);
	}

	public void shoot(Direction direction) {
		Bullet bullet = player.shoot(direction);
		entities.add(bullet);
	}

	public void changeWeapon() {
		player.changeWeapon();
	}

}
