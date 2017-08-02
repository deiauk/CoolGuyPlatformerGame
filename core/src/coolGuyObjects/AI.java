package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AI extends GameObject{

	private Rectangle bottom, left, right, top, full;
	private Rectangle helperL, helperR, helperLL, helperLR, helperTop;
	private Sprite sprite;
	private float x, y;
	public static int count;
	public static Vector2 v;
	public static Vector2 v2;
	private int speed;
	private boolean bHelperL, bHelperR;
	private float velocity;
	private boolean canSetTarget = true;
	private static boolean overlapsTarget = false;
	private static boolean brick;
	private boolean stop;
	public boolean blockPos = false;
	
	
	private float aifloat;
	
	private static float w;
	int jumpTime = 0;
	
	public AI(float x, float y){
		count++;
		
		this.x = x;
		this.y = y;
		
		full = new Rectangle(0, 0, 50, 50);
		bottom = new Rectangle(0, 0, 50, 15);
		top = new Rectangle(0, 35, 50, 15);
		left = new Rectangle(0, 15, 25, 20);
		right = new Rectangle(25, 15, 25, 20);
		helperL = new Rectangle(-15, -10, 15, 50);
		helperR = new Rectangle(65, -10, 15, 50);
		helperLL = new Rectangle(0, 0, 100, 10);
		helperLR = new Rectangle(0, 0, 100, 10);
		helperTop = new Rectangle(0, 50, 50, 60);
		
		sprite = new Sprite(TextureManager.AI);
		sprite.setSize(50, 50);
		
		setPosition(x, y+200);
		v = new Vector2();
		v2 = new Vector2();
		
		speed = MathUtils.random(200,300);
		
		w = full.width;
	}
	@Override
	public int hits(Rectangle r) {
		if(helperL.overlaps(r)){
			bHelperL = true;
		}else{
			bHelperL = false;
		}
		if(helperR.overlaps(r)){
			bHelperR = true;
		}else{
			bHelperR = false;
		}
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
	
	public boolean canSetTarget(){
		return canSetTarget;
	}
	/*
	public void friendCollision(AI r){
		if(count > 1){
			if(this.getRight().overlaps(r.getLeft())){
				r.setPosition(this.full.x + r.getHitBox().width, r.full.y);
				if(overlapsTarget && v.y > full.y){
					r.setPosition(this.full.x, r.full.y + r.full.getHeight());
				}
			}
		}
	}
	*/
	public void friendCollision(AI r){
		if(count > 1){
			if(this.full.overlaps(r.full)){
				r.setPosition(this.full.x + r.getHitBox().width, r.full.y);
				if(overlapsTarget && v.y > full.y){
					r.setPosition(this.full.x, r.full.y + r.full.getHeight());
				}
			}
		}
	}

	@Override
	public int hits2(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
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
	
	public static void setCollision(boolean test){
		
	}

	@Override
	public void update(float delta) {
		changeVelocity(delta);
		if(handle()){
			if(full.x - v.x < 2 && !stop){
				moveRight(delta);
			}else{
				moveLeft(delta);
			}
		}
		if(!bHelperL && full.x < v.x && bHelperR){
			moveRight(delta);
		}else if(!bHelperR && full.x > v.x && bHelperL){
			moveLeft(delta);
		}
		sprite.setPosition(full.x, full.y);
		
		if(full.y + full.height * 2 > v.y){
			brick = true;
		}else{ // set block pos
			//System.out.println("brick");
			brick = false;
		}
	}
	
	private boolean handle(){
		if(bHelperL == false || bHelperR == false){
			return false;
		}
		if(full.x - 5 <= v.x && v.x <= full.x + 5){
			return false;
		}
		return true;
	}

	@Override
	public void update(float delta, int number, float playerPosition,
			float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
		
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
		helperR.x = x + 50;
		helperR.y = y - 50;
		helperL.x = x - 15;
		helperL.y = y - 50;
		helperLL.x = x - 100;
		helperLL.y = y + 20;
		helperLR.x = x + 50;
		helperLR.y = y + 20;
		helperTop.x = x;
		helperTop.y = y + 50;
		
		sprite.setPosition(full.x, full.y);
	}

	@Override
	public void moveLeft(float delta) {
		full.x -=speed * delta;
		right.x -=speed * delta;
		left.x -=speed * delta;
		top.x -=speed * delta;
		bottom.x -=speed * delta;
		helperL.x -=speed * delta;
		helperR.x -=speed * delta;
		helperLL.x -= speed * delta;
		helperLR.x -= speed * delta;
		helperTop.x -= speed * delta;
		
		sprite.setPosition(full.x, full.y);
	}

	@Override
	public void moveRight(float delta) {
		full.x +=speed * delta;
		right.x +=speed * delta;
		left.x +=speed * delta;
		top.x +=speed * delta;
		bottom.x +=speed * delta;
		helperL.x +=speed * delta;
		helperR.x +=speed * delta;
		helperLL.x += speed * delta;
		helperLR.x += speed * delta;
		helperTop.x += speed * delta;
		
		sprite.setPosition(full.x, full.y);
	}
	
	private void changeVelocity(float delta){
		if(!overlapsTarget){
			velocity -= (10 * delta);
			full.y += velocity;
			right.y += velocity;
			left.y += velocity;
			top.y += velocity;
			bottom.y += velocity;
			helperL.y += velocity;
			helperR.y += velocity;
			helperLL.y += velocity;
			helperLR.y += velocity;
			helperTop.y += velocity;
		}
		sprite.setPosition(x, y);
	}

	@Override
	public void jump() {
		velocity = 20;
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public Rectangle getHitBox() {
		return full;
	}
	
	public Rectangle getTop() {
		return top;
	}
	
	public Rectangle getBottom() {
		return bottom;
	}
	
	public Rectangle getRight() {
		return right;
	}
	
	public Rectangle getLeft() {
		return left;
		
	}
	
	public Rectangle gethelperL() {
		return helperL;
	}
	
	public Rectangle getHelperTop() {
		return helperTop;
	}
	
	public Rectangle gethelperR() {
		return helperR;
	}
	
	public Rectangle gethelperLL() {
		return helperLL;
	}
	
	public Rectangle gethelperLR() {
		return helperLR;
	}

	@Override
	public int hitAction(int side) {
		return side == 9 ? 1 : 4;
	}
	
	public void topCollision(Rectangle r){
		
	}
	
	public void ai_test(){
		velocity = 15;
	}
	
	public static void setTarget(float x, float y, float x1, float y1){
		if(brick){
			v.set(x, y);
			overlapsTarget = false;
		}else{
			v.set(x1 - w * 2, y1);
			overlapsTarget = true;
		}
	}
	
}
