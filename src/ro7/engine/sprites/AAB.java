package ro7.engine.sprites;

import java.awt.Graphics2D;

import cs195n.Vec2f;

public class AAB extends Shape {

	private Vec2f dimensions;

	public AAB(Vec2f position, Vec2f dimensions) {
		super(position);
		this.dimensions = dimensions;
	}

	@Override
	public boolean collides(Shape shape) {
		return shape.collidesAAB(this);
	}

	@Override
	public boolean collidesCircle(Circle circle) {
		Vec2f center = circle.getCenter();
		Vec2f minAAB = this.getPosition();
		Vec2f maxAAB = minAAB.plus(this.getDimensions());

		float pointx;
		float pointy;
		if (center.x < minAAB.x) {
			pointx = minAAB.x;
		} else if (center.x > maxAAB.x) {
			pointx = maxAAB.x;
		} else {
			pointx = center.x;
		}

		if (center.y < minAAB.y) {
			pointy = minAAB.y;
		} else if (center.y > maxAAB.y) {
			pointy = maxAAB.y;
		} else {
			pointy = center.y;
		}

		Vec2f point = new Vec2f(pointx, pointy);
		float distance = point.dist2(center);

		float radius = circle.getRadius();

		return distance <= (radius * radius);
	}

	@Override
	public boolean collidesAAB(AAB aab) {
		Vec2f minThis = this.position;
		Vec2f maxThis = minThis.plus(this.dimensions);
		Vec2f minAAB = aab.position;
		Vec2f maxAAB = minThis.plus(aab.dimensions);

		return minThis.x <= maxAAB.x && maxThis.x >= minAAB.x
				&& minThis.y <= maxAAB.y && maxThis.y >= minAAB.y;		
	}

	@Override
	public boolean collidesCompoundShape(CompoundShape compound) {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

	}

	public Vec2f getDimensions() {
		return dimensions;
	}

}
