package ro7.engine.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.List;

import cs195n.Vec2f;

public class AAB extends SingleShape {

	private Vec2f dimensions;

	public AAB(Vec2f position, Color borderColor,
			Color fillColor, Vec2f dimensions) {
		super(position, borderColor, fillColor);
		this.dimensions = dimensions;
	}

	@Override
	public boolean collides(CollidingShape shape) {
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
		List<CollidingShape> shapes = compound.getShapes();
		for (CollidingShape shape : shapes) {
			if (shape.collidesAAB(this)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		Rectangle2D rectangle = new Rectangle2D.Float(position.x, position.y,
				dimensions.x, dimensions.y);

		g.setColor(borderColor);
		g.draw(rectangle);

		g.setColor(fillColor);
		g.fill(rectangle);
	}

	public Vec2f getDimensions() {
		return dimensions;
	}

	public Shape getShape() {
		return new Rectangle2D.Float(position.x, position.y,
				dimensions.x, dimensions.y);
	}

}
