package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends GameObject{
	
	private Rectangle full;
	private Sprite sprite;
	private boolean direction;
	private int speed;
	
	public Bullet(float x, float y, boolean direction){
		full = new Rectangle(0, 0, 15, 15);
		sprite = new Sprite(TextureManager.bullet);
		sprite.setSize(15, 15);
		this.direction = direction;
		if(direction){
			sprite.flip(true, false);
		}
		speed = 500;
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(full.overlaps(r)){
			return 7;
		}
		return -1;
	}

	@Override
	public int hits2(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		
	}
	@Override
	public void setPosition(float x, float y) {
		full.x = x;
		full.y = y;
		sprite.setPosition(x, y);
	}
	
	public float getX(){
		return full.x;
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

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public Rectangle getHitBox() {
		// TODO Auto-generated method stub
		return full;
	}

	@Override
	public int hitAction(int side) {
		return 4;
	}
	
	@Override
	public void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth) {
		if(direction){
			full.x -= (speed * delta);
		}else{
			full.x += (speed * delta);
		}
		setPosition(full.x, full.y);
	}
}
