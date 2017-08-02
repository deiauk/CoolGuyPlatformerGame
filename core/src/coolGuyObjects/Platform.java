package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends GameObject{
	
	private Rectangle full, right, left;
	private Sprite sprite;
	public int interval1, interval2, direction;
	private int speed;
	private float x, y, tmpX, tmpY;
	private float updateSpeed, updateYSpeed;
	private boolean updateXorY, once;
	private boolean let = false;
	
	public Platform(float x, float y, int interval1, int interval2, boolean updateXorY){
		this.x = x;
		this.y = y;
		this.tmpX = x;
		this.tmpY = y;
		this.interval1 = interval1;
		this.interval2 = interval2;
		this.updateXorY = updateXorY;
		sprite = new Sprite(TextureManager.platform);
		sprite.setSize(90, 20);
		
		full = new Rectangle(0, 0, 90, 20);
		left = new Rectangle(0, 0, 45, 20);
		right = new Rectangle(45, 0, 45, 20);
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(left.overlaps(r)){
			return 1;
		}
		if(right.overlaps(r)){
			return 2;
		}
		if(full.overlaps(r)){
			return 3;
		}
		return -1;
	}

	@Override
	public int hits2(Rectangle r) {
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, int number, float playerPositionX, float playerPositionY, int screenWidth) {
		boolean test = playerPositionX < (x + screenWidth * 2f) &&
		(x - playerPositionX * 1.5f) < screenWidth && (y - 800 < playerPositionY && y + 800 > playerPositionY);
		if(!updateXorY){
			test = playerPositionX < (x + screenWidth * 2f) && (x - playerPositionX) < screenWidth;
		}
		if(test){
			if(updateXorY){
				speed = 250;
				updateX(delta, number, playerPositionX, screenWidth);
			}else{
				speed = 400;
				updateY(delta, number, playerPositionX, screenWidth);
			}
		}
	}
	
	private void updateX(float delta, int number, float playerPosition, int screenWidth){
		updateSpeed = (delta * speed);
		if(((x > (tmpX - interval1)) && !let) && (direction != 1 || direction == 0)){
			direction = 0;
			x -= updateSpeed;
			updateSpeed = -updateSpeed;
		}else{
			let = true;
		}
		if(((x < (tmpX + interval2)) && let) && (direction != 2 || direction == 0)){
			direction = 0;
			x += updateSpeed;
		}else{
			let = false;
		}
		setPosition(x, y);
	}
	
	private void updateY(float delta, int number, float playerPosition, int screenWidth){
		updateYSpeed = (delta * speed);
		if(((y < (tmpY + interval1))) && !let){
			y += updateYSpeed;
		}else{
			let = true;
		}
		if(y > (tmpY - interval2) && let){
			y -= updateYSpeed;
		}else{
			let = false;
		}
		setPosition(x, y);
	}

	public float getUpdateSpeed(){
		return updateSpeed;
	}
	public void setDirection(int direction){
		this.direction = direction;
	}
	@Override
	public void setPosition(float x, float y){
		full.x = x;
		full.y = y;
		
		left.x = x;
		left.y = y;
		
		right.x = x + 45;
		right.y = y;
		
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
	}

	@Override
	public Rectangle getHitBox() {
		return full;
	}
	
	public Rectangle getLeft() {
		return left;
	}
	
	public Rectangle getRight() {
		return right;
	}

	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isUpdateY(){
		return updateXorY;
	}
	
	
	public boolean isOnce(){
		return once;
	}
}
