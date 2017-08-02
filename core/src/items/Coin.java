package items;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Coin extends ItemObject{
	
	private Rectangle full;
	private Sprite sprite;
	
	public Coin(int x, int y){
		full = new Rectangle(0,0,30,30);
		sprite = new Sprite(TextureManager.coin);
		sprite.setSize(30, 30);
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(full.overlaps(r)){
			return 1;
		}
		return -1;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float x, float y) {
		full.x = x;
		full.y = y;
		sprite.setPosition(x, y);
	}


	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}


	@Override
	public Rectangle getFull() {
		return full;
	}
}
