package ro7.game.screens;

import static ro7.engine.world.Direction.DOWN;
import static ro7.engine.world.Direction.LEFT;
import static ro7.engine.world.Direction.RIGHT;
import static ro7.engine.world.Direction.UP;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;
import java.util.Set;

import ro7.engine.Application;
import ro7.engine.Screen;
import ro7.engine.world.Viewport;
import ro7.game.world.TouWorld;
import cs195n.Vec2f;
import cs195n.Vec2i;

public class GameScreen extends Screen {

	private Viewport viewport;
	private TouWorld world;

	private Set<Integer> pressedKeys;

	public GameScreen(Application app) {
		super(app);
		pressedKeys = new HashSet<Integer>();
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		try {
			world.update(nanosSincePreviousTick);
			if (world.lost()) {
				app.popScreen();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDraw(Graphics2D g) {
		viewport.draw(g);
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		pressedKeys.add(e.getKeyCode());

		for (int keyCode : pressedKeys) {
			switch (keyCode) {
			case 32:
				world.changeWeapon();
				break;
			case 73:
				world.shoot(UP);
				break;
			case 74:
				world.shoot(LEFT);
				break;
			case 75:
				world.shoot(DOWN);
				break;
			case 76:
				world.shoot(RIGHT);
				break;
			case 65:
				world.movePlayer(LEFT);
				break;
			case 87:
				world.movePlayer(UP);
				break;
			case 68:
				world.movePlayer(RIGHT);
				break;
			case 83:
				world.movePlayer(DOWN);
				break;
			}
		}
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		pressedKeys.remove(e.getKeyCode());
	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResize(Vec2i newSize) {
		super.onResize(newSize);

		try {
			world = new TouWorld(new Vec2f(windowSize.x, windowSize.y));

			if (viewport != null) {
				Vec2f gamePosition = viewport.getGamePosition();
				Vec2f scale = viewport.getScale();
				viewport = new Viewport(new Vec2f(0.0f, 0.0f), new Vec2f(
						windowSize.x, windowSize.y), world, scale, gamePosition);
			} else {
				viewport = new Viewport(new Vec2f(0.0f, 0.0f), new Vec2f(
						windowSize.x, windowSize.y), world, new Vec2f(1.0f,
						1.0f), new Vec2f(0.0f, 0.0f));
			}
		} catch (NullPointerException e) {
			System.out.println("No window size defined");
		}
	}

}
