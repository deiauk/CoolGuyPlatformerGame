package items;

import tools.TextureManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Gun extends ItemObject{
	
	private Sprite sprite;
	private Rectangle full;
	
	public Gun(int x, int y){
		sprite = new Sprite(TextureManager.gunItem);
		sprite.setSize(75, 55);
		
		full = new Rectangle(0, 0, 75, 55);
		
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(full.overlaps(r)){
			PlayerBullet.gunCharger += PlayerBullet.MAX_CHARGERS;
			PlayerBullet.bulletsQuantity = PlayerBullet.MAX_BULLETS;
			PlayerBullet.setShoot(true);
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
		
	}
}
