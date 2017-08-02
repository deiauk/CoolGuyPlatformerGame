package items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class ItemObject {
	public abstract int hits(Rectangle r);
	public abstract void setPosition(float x, float y);
	public abstract void draw(SpriteBatch batch);
	public abstract Rectangle getFull();
	public abstract void update(float delta);
}
