package ro7.game.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import cs195n.Vec2f;
import ro7.engine.Application;
import ro7.engine.Screen;
import ro7.engine.sprites.AAB;
import ro7.engine.sprites.Circle;
import ro7.engine.sprites.CollidingShape;
import ro7.engine.sprites.CompoundShape;

public class CollisionDebugScreen extends Screen {

	List<CollidingShape> shapes = new ArrayList<CollidingShape>();

	public CollisionDebugScreen(Application app) {
		super(app);
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		for (CollidingShape shapeA : shapes) {
			shapeA.changeFillColor(Color.GREEN);
			for (CollidingShape shapeB : shapes) {
				if (!shapeA.equals(shapeB) && shapeA.collides(shapeB)) {
					shapeA.changeFillColor(Color.RED);
					shapeB.changeFillColor(Color.RED);
				}
			}
		}
	}

	@Override
	public void onDraw(Graphics2D g) {
		for (CollidingShape shape : shapes) {
			shape.draw(g);
		}
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMousePressed(MouseEvent e) {
		int button = e.getButton();
		Point point = e.getPoint();

		CollidingShape shape;
		if (button == 1) {
			//shape = new Circle(new Vec2f(point.x, point.y),
				//	Color.GREEN, Color.GREEN, 30.0f);
			Vec2f pointVector = new Vec2f(point.x, point.y);
			shape = new CompoundShape(pointVector, new Circle(pointVector, Color.GREEN, Color.GREEN, 30.0f),
					new AAB(new Vec2f(point.x, point.y+30.0f), Color.GREEN, Color.GREEN, new Vec2f(50.0f, 100.0f)));
		} else {
			shape = new AAB(new Vec2f(point.x, point.y),
					Color.GREEN, Color.GREEN, new Vec2f(100.0f, 50.0f));
		}
		shapes.add(shape);
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

}
