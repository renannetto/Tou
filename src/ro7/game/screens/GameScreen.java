package ro7.game.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashSet;
import java.util.Set;

import ro7.engine.Application;
import ro7.engine.Screen;
import ro7.engine.sprites.Message;
import ro7.engine.world.Viewport;
import ro7.game.world.TouWorld;
import cs195n.Vec2f;
import cs195n.Vec2i;

public class GameScreen extends Screen {

	private Viewport viewport;
	private TouWorld world;
	private Message endMessage;

	private Set<Integer> pressedKeys;

	public GameScreen(Application app) {
		super(app);
		pressedKeys = new HashSet<Integer>();
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		try {
			world.update(nanosSincePreviousTick);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDraw(Graphics2D g) {
		viewport.draw(g);
		if (world.lost()) {
			endMessage.draw(g);
		}
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
			case 27:
				app.popScreen();
			case 32:
				world.changeWeapon();
				break;
			case 73:
				world.shoot(new Vec2f(0.0f, -1.0f));
				break;
			case 74:
				world.shoot(new Vec2f(-1.0f, 0.0f));
				break;
			case 75:
				world.shoot(new Vec2f(0.0f, 1.0f));
				break;
			case 76:
				world.shoot(new Vec2f(1.0f, 0.0f));
				break;
			case 65:
				world.movePlayer(new Vec2f(-1.0f, 0.0f));
				break;
			case 87:
				world.movePlayer(new Vec2f(0.0f, -1.0f));
				break;
			case 68:
				world.movePlayer(new Vec2f(1.0f, 0.0f));
				break;
			case 83:
				world.movePlayer(new Vec2f(0.0f, 1.0f));
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
			if (world == null) {
				world = new TouWorld(new Vec2f(windowSize.x, windowSize.y));
			} else {
				world.resize(new Vec2f(newSize.x, newSize.y));
			}

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

			float endX = windowSize.x / 10.0f;
			float endY = windowSize.y / 2.0f;
			int fontSize = windowSize.x / 24;
			endMessage = new Message(
					"You lost! Press Esc to go back to title screen.",
					fontSize, Color.WHITE, new Vec2f(endX, endY));
		} catch (NullPointerException e) {
			System.out.println("No window size defined");
		}
	}

}
