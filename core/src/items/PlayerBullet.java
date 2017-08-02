package items;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerBullet extends ItemObject{
	
	private Sprite sprite;
	private Rectangle full;
	public static boolean shoot;
	public static final int MAX_BULLETS = 7, MAX_CHARGERS = 1;
	public static int bulletsQuantity, gunCharger;
	private float time;
	private boolean remove;
	public int direction;
	
	public PlayerBullet(float x, float y, int direction){
		sprite = new Sprite(TextureManager.playerBullet);
		sprite.setSize(15, 15);
		full = new Rectangle(0, 0, 15, 15);
		time = 0;
		this.direction = direction;
		setPosition(x,y);
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
	
	public boolean letRemove(){
		return remove;
	}

	@Override
	public void update(float delta) {
		time += delta;
		if(time > 1){
			remove = true;
			time = 0;
		}
		if(direction == 2){
			full.x += (delta * 800);
		}else{
			full.x -= (delta * 800);
		}
		setPosition(full.x, full.y);
	}
	
	public static void setShoot(boolean logic){
		shoot = logic;
	}
}
