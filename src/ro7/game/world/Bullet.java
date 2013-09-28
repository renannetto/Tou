package ro7.game.world;

import ro7.engine.world.Direction;
import ro7.engine.world.MovingEntity;
import cs195n.Vec2f;

public abstract class Bullet extends MovingEntity {

	protected Bullet(Vec2f position, float velocity, Direction direction) {
		super(position, velocity, direction);
	}

}
