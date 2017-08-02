package coolGuyObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {
	public abstract int hits(Rectangle r);
	public abstract int hits2(Rectangle r);
	public abstract void action(int type, float x, float y);
	public abstract void update(float delta);
	public abstract void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth);
	public abstract void setPosition(float x, float y);
	public abstract void moveLeft(float delta);
	public abstract void moveRight(float delta);
	public abstract void jump();
	public abstract void draw(SpriteBatch batch);
	public abstract Rectangle getHitBox();
	public abstract int hitAction(int side);
}
