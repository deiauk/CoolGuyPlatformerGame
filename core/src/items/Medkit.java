package items;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Medkit extends ItemObject {
	
	private Sprite sprite;
	private Rectangle full;
	
	public Medkit(int x, int y){
		sprite = new Sprite(TextureManager.medKit);
		sprite.setSize(32, 32);
		full = new Rectangle(0, 0, 32, 32);
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

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

}
