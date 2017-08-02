package coolGuyObjects;

import java.util.ArrayList;

import tools.SoundManager;
import tools.TextureManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cannon extends GameObject{

	private Sprite sprite;
	boolean test = true, letRotate = true;
	private ArrayList<Bullet> bullets;
	private boolean direction;
	private float x, y;
	private boolean addBullet;
	private float volume;
	private float cannonX, cannonY;
	
	public Cannon(float x, float y, boolean direction){
		this.x = x;
		this.y = y;
		this.cannonX = x;
		this.cannonY = y;
		sprite = new Sprite(TextureManager.cannon);
		sprite.setSize(200, 70);
		
		bullets = new ArrayList<Bullet>();
		addBullet = true;
		
		this.direction = direction;
		if(!this.direction){
			sprite.flip(true, false);
		}
		setPosition(x,y);
	}
	
	@Override
	public int hits(Rectangle r) {
		if(bullets.size() != 0){
			return bullets.get(0).hits(r);
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
	private float volume(float playerPosition, float screenWidth, boolean cannonAndPlayer){
		float position = direction ? (cannonX - playerPosition) : (playerPosition - cannonX);
		if(position > (screenWidth/2)){
			volume = .1f;
		}else if(position > (screenWidth/4)){
			volume = .3f;
		}else if(position > (screenWidth/8)){
			volume = .8f;
		}
		return volume;
	}
	@Override
	public void update(float delta, int number, float playerPositionX, float playerPositionY, int screenWidth){
		float width = screenWidth * 1.5f;
		boolean test = direction ? (playerPositionX < (x + width) && ((x - playerPositionX) < width) && 
		(y - 800 < playerPositionY && y + 800 > playerPositionY)) : 
		((playerPositionX - x) < width) && ((x - playerPositionX) < width * 2 && 
		(y - 300 < playerPositionY && y + 800 > playerPositionY));
		if(test){
			volume = volume(playerPositionX, width, true);
			if(number == -15 && direction && addBullet){
				bullets.add(new Bullet(x, (y + sprite.getHeight()/2), true));
				SoundManager.shoot.play(volume);
				addBullet = false;
			}else if(number == -15 && !direction && addBullet){
				bullets.add(new Bullet(x + sprite.getWidth(), (y + sprite.getHeight()/2), false));
				SoundManager.shoot.play(volume);
				addBullet = false;
			}
			bullets.get(0).update(delta, number, playerPositionX, 0, 0);
		}
		if(bullets.size() > 0){
			x = bullets.get(0).getX();
		}
	}

	@Override
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
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
		if(bullets.size() != 0){
			bullets.get(0).draw(batch);
		}
	}

	@Override
	public Rectangle getHitBox() {
		if(bullets.size() > 0){
			return bullets.get(0).getHitBox();
		}
		return null;
	}

	@Override
	public int hitAction(int side) {
		return 0;
	}
	public void removeBullet(){
		x = cannonX;
		bullets.clear();
		addBullet = true;
	}
}
