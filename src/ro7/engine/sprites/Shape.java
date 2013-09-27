package ro7.engine.sprites;

import cs195n.Vec2f;

public abstract class Shape extends Sprite {

	protected Shape(Vec2f position) {
		super(position);
	}
	
	public abstract boolean collides(Shape shape);
	
	public abstract boolean collidesCircle(Circle circle);
	
	public abstract boolean collidesAAB(AAB aab);
	
	public abstract boolean collidesCompoundShape(CompoundShape compound);
	
	public Vec2f getPosition() {
		return position;
	}

}
