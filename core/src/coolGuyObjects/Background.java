package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background{

	private Sprite sprite, sprite2;
	private static float x = 50, y = 0, tmpX = 0;
	
	public Background(){
		sprite = new Sprite(TextureManager.back);
		sprite2 = new Sprite(TextureManager.back);
	}
	
	public void setPosition(float x1) {
		x = x1;
		tmpX = x - sprite.getWidth();
		sprite.setPosition(x, y);
		sprite2.setPosition(tmpX, y);
	}
	
	public void setY(float y1){
		y = y1;
		sprite.setPosition(x, y);
		sprite2.setPosition(tmpX, y);
	}
	
	public float getSPRITES(){
		return sprite.getX();
	}
	public float getSPRITES2(){
		return sprite.getY();
	}

	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
		sprite2.draw(batch);
	}
	
	public float getWidth(){
		return sprite.getWidth();
	}
	public float getHeight(){
		return sprite.getHeight();
	}
	public float getY(){
		return y;
	}
	public float getX(){
		return x;
	}
}
