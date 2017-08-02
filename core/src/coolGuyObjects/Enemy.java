package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Enemy extends GameObject{

	private Rectangle bottom, left, right, top, full;
	private Sprite sprite;
	private int interval1, interval2;
	private int speed;
	boolean test = true, letRotate = true;
	private float sk1, sk2;
	private float x;
	private float velocity;
	
	public Enemy(float x, float y, int interval1, int interval2){
		this.x = x;
		sprite = new Sprite(TextureManager.enemy);
		sprite.setSize(50, 50);
		
		full = new Rectangle(0, 0, 50, 50);
		bottom = new Rectangle(0, 0, 50, 15);
		top = new Rectangle(0, 35, 50, 15);
		left = new Rectangle(0, 15, 25, 20);
		right = new Rectangle(25, 15, 25, 20);
		
		velocity = 0;
		
		this.interval1 = interval1;
		this.interval2 = interval2;
		speed = 600;
		setPosition(x, y);
	}
	
	@Override
	public int hits(Rectangle r) {
		if(top.overlaps(r)){
			return 4;
		}
		if(full.overlaps(r) || r.overlaps(full)){
			return 5;
		}
		if(left.overlaps(r)){
			return 2;
		}
		if(right.overlaps(r)){
			return 3;
		}
		if(r.overlaps(top)){
			return 4;
		}
		if(bottom.overlaps(r)){
			return 1;
		}
		return -1;
	}
	
	public int hits2(Rectangle r){
		if(left.overlaps(r)){
			return 2;
		}
		if(right.overlaps(r)){
			return 3;
		}
		return -1;
	}

	@Override
	public void action(int type, float x, float y) {
		if(type == 1 || type == 4){
			velocity = 0;
			setPosition(full.x, y);
		}
		if(type == 2 || type == 3){
			velocity = 0;
			setPosition(x, full.y);
		}
	}

	@Override
	public void update(float delta) {
		velocity -= (30 * delta);
		full.y += velocity;
		sprite.setPosition(full.x, full.y);
	}
	public void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth){
		if(playerPosition < (x + screenWidth * 2) && (x - playerPosition) < screenWidth * 2 && (full.y - 800 < playerPositionY && full.y + 800 > playerPositionY)){
			if(sk1 > 0 && test && (number == 1 || number == 0)){
				if(letRotate){
					sprite.flip(true, false);
					letRotate = false;
				}
				sk1 -= (speed * delta);
				full.x -= (speed * delta);
				top.x -= (speed * delta);
				left.x -= (speed * delta);
				right.x -= (speed * delta);
				bottom.x -= (speed * delta);
				sprite.setPosition(full.x, full.y);
			}else{
				test = false;
				if((full.x + x) < sk2 && !test && (number == 2 || number == 0)){
					if(!letRotate){
						sprite.flip(true, false);
						letRotate = true;
					}
					sk1 += (speed * delta);
					full.x += (speed * delta);
					top.x += (speed * delta);
					left.x += (speed * delta);
					right.x += (speed * delta);
					bottom.x += (speed * delta);
					sprite.setPosition(full.x, full.y);
				}else{
					test = true;
				}
			}
		}
	}

	@Override
	public void setPosition(float x, float y) {
		
		full.x = x;
		full.y = y;
		
		bottom.x = x;
		bottom.y = y;
		
		left.x = x;
		left.y = y + 10;
		
		right.x = x + 25;
		right.y = y + 10;
		
		top.x = x;
		top.y = y + 35;
		
		sk1 = x - (x + interval1);
		sk2 = x + (x + interval2);
		
		if(sk1 < 0){
			sk1 = sk1 * (-1);
		}
		if(sk2 < 0){
			sk2 = sk2 * (-1);
		}
		
		sprite.setPosition(x, y);
	}

	@Override
	public void moveLeft(float delta) {
		
	}
	@Override
	public void moveRight(float delta) {
		
	}

	@Override
	public void jump() {
		velocity = 20;
	}
	public boolean bottomCollision(Rectangle r){
		return bottom.overlaps(r) ? true : false;
	}
	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public Rectangle getHitBox() {
		return full;
	}
	
	public Rectangle getBottom() {
		return bottom;
	}
	
	public Rectangle getTop() {
		return top;
	}
	
	public Rectangle getRight() {
		return right;
	}
	
	public Rectangle getLeft(){
		return left;
	}
	public float getSk1(){
		return sk1;
	}
	public float getSk2(){
		return sk2;
	}

	@Override
	public int hitAction(int side) {
		return side == 9 ? 1 : 4;
	}
}
