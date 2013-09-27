package ro7.engine.sprites;

import java.awt.Graphics2D;

import cs195n.Vec2f;

public class CompoundShape extends Shape {

	public CompoundShape(Vec2f position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collides(Shape shape) {
		return shape.collidesCompoundShape(this);
	}

	@Override
	public boolean collidesCircle(Circle circle) {
		return false;
	}

	@Override
	public boolean collidesAAB(AAB aab) {
		return false;
	}

	@Override
	public boolean collidesCompoundShape(CompoundShape compound) {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

}
