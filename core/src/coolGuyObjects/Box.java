package coolGuyObjects;

import items.Gun;
import items.Coin;
import items.ItemObject;
import items.Medkit;
import items.SpeedItem;
import tools.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Box extends GameObject{
	
	private Rectangle hitBox, bottom, top;
	private Sprite sprite;
	
	private static int x1;
	private static int y1;
	
	private boolean hits;
	private float velocity;
	
	public Box(int x, int y){
		
		hitBox = new Rectangle(x, y, 64, 64);
		bottom = new Rectangle(x, y, 64, 0);
		top = new Rectangle(x, y, 64, 0);
		
		hits = false;
		
		sprite = new Sprite(TextureManager.box);
		sprite.setSize(64, 64);
		
		setPosition(x, y);
	}
	
	private static ItemObject loadItems(int random){
		if(random == 0){
			return new Medkit(x1+20,y1+30);
		}else if(random == 1){
			return new SpeedItem(x1, y1);
		}else if(random == 2){
			return new Coin(x1+20, y1+30);
		}else if(random == 5 || random == 3 || random == 4){
			return new Gun(x1, y1);
		}
		return null;
	}
	
	public static ItemObject createItem(Rectangle r){
		x1 = (int) r.getX();
		y1 = (int) r.getY();
		int ran = MathUtils.random(0, 4);
		return loadItems(ran);
	}

	@Override
	public int hits(Rectangle r) {
		return -1;
	}

	@Override
	public void setPosition(float x, float y) {
		hitBox.x = x;
		hitBox.y = y;
		
		bottom.x = x + 5;
		bottom.y = y - 2;
		
		top.x = x + 5;
		top.y = y + 66;
		
		sprite.setPosition(x, y);
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public void update(float delta) {
		if(!hits){
			velocity -= (15 * delta);
			hitBox.y += velocity;
			top.y += velocity;
			bottom.y += velocity;
			sprite.setPosition(hitBox.x, hitBox.y);
		}
	}

	@Override
	public int hits2(Rectangle r) {
		if(bottom.overlaps(r) || hitBox.overlaps(r)){
			velocity = 15 * Gdx.graphics.getDeltaTime();
			hits = true;
		}else{
			hits = false;
		}
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		
	}

	@Override
	public void moveLeft(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	
	public void setHit(boolean hits){
		this.hits = hits;
	}

	@Override
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	public Rectangle getTop() {
		return top;
	}
	
	public Rectangle getBottom() {
		return bottom;
	}

	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void update(float delta, int number, float playerPosition,
			float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
		
	}
}
