package coolGuyObjects;

import java.util.ArrayList;

import items.ItemObject;
import items.PlayerBullet;
import tools.Constants;
import tools.SoundManager;
import tools.TextureManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class CoolGuy extends GameObject {
	private Rectangle bottom, left, right, top, full;
	private float velocity;
	private ArrayList<PlayerBullet> bullets;
	private static ArrayList<GameObject> list, enemies, deleteList;
	private static ArrayList<ItemObject> itemList;
	private int myDirection;
	public static float speed;
	
	private float healthPoints;
    private boolean invincible;
    public float time;
    
    private Animation walkingAnimationRight, runningAnimationRight;
    private Animation walkingAnimationLeft, runningAnimationLeft;
    private float stateTime, jumpTime;
    
    public static boolean rightMove, leftMove, jumpMove, direction = true, jumpToHigh;
	
	public CoolGuy(){
		full = new Rectangle(0, 0, 50, 98);
		bottom = new Rectangle(0, 0, 50, 15);
		top = new Rectangle(0, 83, 50, 15);
		left = new Rectangle(0, 15, 25, 68);
		right = new Rectangle(25, 15, 25, 68);
		bullets = new ArrayList<PlayerBullet>();
		
		itemList = new ArrayList<ItemObject>();
		deleteList = new ArrayList<GameObject>();
		
		this.setPosition(0, 0);
		velocity = 0;
		speed = Constants.speed;
		
		animation_walking();
		animation_running();
	}
	private void animation_walking(){
		TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
		
        TextureRegion[] runningRight = new TextureRegion[Constants.WALKING_RIGHT.length];
        for (int i = 0; i < Constants.WALKING_RIGHT.length; i++) {
            String path = Constants.WALKING_RIGHT[i];
            runningRight[i] = textureAtlas.findRegion(path);
        }
        walkingAnimationRight = new Animation(0.05f, runningRight);
        
        TextureRegion[] runningLeft = new TextureRegion[Constants.WALKING_LEFT.length];
        for (int i = 0; i < Constants.WALKING_LEFT.length; i++) {
            String path = Constants.WALKING_LEFT[i];
            runningLeft[i] = textureAtlas.findRegion(path);
        }
        walkingAnimationLeft = new Animation(0.05f, runningLeft);
        stateTime = 0f;
	}
	
	private void animation_running(){
		TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH2);

        TextureRegion[] runningRight = new TextureRegion[Constants.RUNNING_RIGHT.length];
        for (int i = 0; i < Constants.RUNNING_RIGHT.length; i++) {
            String path = Constants.RUNNING_RIGHT[i];
            runningRight[i] = textureAtlas.findRegion(path);
        }
        runningAnimationRight = new Animation(0.05f, runningRight);

        TextureRegion[] runningLeft = new TextureRegion[Constants.RUNNING_LEFT.length];
        for (int i = 0; i < Constants.RUNNING_LEFT.length; i++) {
            String path = Constants.RUNNING_LEFT[i];
            runningLeft[i] = textureAtlas.findRegion(path);
        }
        runningAnimationLeft = new Animation(0.05f, runningLeft);
        stateTime = 0f;
	}
	public int hits(Rectangle r){
		if(left.overlaps(r)){
			return 2;
		}
		if(right.overlaps(r)){
			return 3;
		}
		if(top.overlaps(r)){
			return 4;
		}
		if(bottom.overlaps(r)){
			return 1;
		}
		return -1;
	}
	public void action(int type, float x, float y){
		if(type == 1 || type == 4){
			velocity = 0;
			setPosition(bottom.x, y);
		}
		if(type == 2 || type == 3){
			velocity = 0;
			setPosition(x, bottom.y);
		}
	}
	public void action2(int type, float x, float y){
		if(type == 1 || type == 4){
			velocity = 0;
			setPosition(x, bottom.y);
		}
		if(type == 2 || type == 3){
			velocity = 0;
			setPosition(x, y);
		}
	}
	public void update(float delta){
		velocity -= (45 * delta);
		if(velocity < -1){
			jumpTime += delta;
		}else{
			jumpToHigh = false;
			jumpTime = 0;
		}
		if(jumpTime > 0.48f){
			jumpToHigh = true;
		}
		bottom.y += velocity;
		top.y += velocity;
		full.y += velocity;
		
	}
	public void setToZeroVelTime(){
		jumpTime = 0;
		velocity = 0;
		jumpToHigh = false;
	}
	public void setPosition(float x, float y){
		full.x = x;
		full.y = y;
		
		bottom.x = x;
		bottom.y = y;
		
		left.x = x;
		left.y = y + 15;
		
		right.x = x + 25;
		right.y = y + 15;
		
		top.x = x;
		top.y = y + 83;
		
	}
	public void setRotation(int r){
		
	}
	public void moveLeft(float delta){
		myDirection = 1;
		bottom.x -= (speed * delta);
		top.x -= (speed * delta);
		full.x -= (speed * delta);
	}
	public void moveRight(float delta){
		myDirection = 2;
		bottom.x += (speed * delta);
		top.x += (speed * delta);
		full.x += (speed * delta);
	}
	public static void setAllLists(ArrayList<GameObject> list1, ArrayList<GameObject> enemies1, ArrayList<ItemObject> a){
		list = list1;
		enemies = enemies1;
		itemList = a;
	}
	public void shoot(float delta){
		if(PlayerBullet.shoot){
			SoundManager.playerBullet.play(.5f);
			if(myDirection == 2){
				bullets.add(new PlayerBullet(full.x + 50, full.y + full.height / 1.1f - 50, 2));
			}else{
				bullets.add(new PlayerBullet(full.x - 25, full.y + full.height / 1.1f - 50, 1));
			}
			PlayerBullet.bulletsQuantity--;
			if(PlayerBullet.bulletsQuantity < 1){
				if(PlayerBullet.gunCharger > 1){
					PlayerBullet.gunCharger--;
					PlayerBullet.bulletsQuantity = PlayerBullet.MAX_BULLETS;
				}else{
				    PlayerBullet.setShoot(false);
				}
			}
		}
	}
	
	public void jump(){
		velocity = 12;
		SoundManager.jump.play(.02f);
	}
	public void decreaseHealth(){
		if(jumpTime > 0.7f){
			//healthPoints -= 30;
		}else{
			//healthPoints -= 10;
		}
	}
	
	@Override
	public Rectangle getHitBox() {
		return bottom;
	}
	public Rectangle getTop() {
		return top;
	}
	public Rectangle getRight() {
		return right;
	}
	public Rectangle getLeft() {
		return left;
	}
	public Rectangle getFull(){
		return full;
	}
	
	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth){
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).update(delta);
			if(bullets.get(i).letRemove()){
				bullets.remove(i);
			}
			for(GameObject t : list){
				if(i < bullets.size() && t instanceof Brick && bullets.get(i).hits(t.getHitBox()) == 1){
					bullets.remove(i);
					break;
					
				}else if(i < bullets.size() && t instanceof Box && bullets.get(i).hits(t.getHitBox()) == 1){
					itemList.add(Box.createItem(t.getHitBox())); 
				   // list.remove(t);
				    deleteList.add(t);
					bullets.remove(i);
				}
				
			}
			for(GameObject t2 : enemies){
				if(i < bullets.size() && t2 instanceof Enemy && bullets.get(i).hits(t2.getHitBox()) == 1){
					enemies.remove(t2);
					bullets.remove(i);
					break;
				}
			}
		}
		
		while(!deleteList.isEmpty()){
			list.remove(deleteList.get(0));
			deleteList.remove(0);
		}
	}
	@Override
	public int hits2(Rectangle r) {
		if(top.overlaps(r)){
			return 1;
		}
		return -1;
	}
	
	public static void changeSpeed(float s){
		speed = s;
	}
	
	 public float getHealthPoints() {
	        return healthPoints;
	 }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void changeHP(int hpDifference, float timer){
        if ((timer - time) > 0.25 || hpDifference >= 0) {
            healthPoints += hpDifference;
            time = timer;
        }
    }

    public boolean isInvincible() {
        return invincible;
    }
    
    public boolean jumpToHigh(){
		return jumpToHigh;
    }
    
    public void setJumpToHigh(boolean jump){
		jumpToHigh = jump;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }
    
	public void draw(SpriteBatch batch){
		if(speed > Constants.speed){
			if(rightMove){
				stateTime += Gdx.graphics.getDeltaTime();
				batch.draw(runningAnimationRight.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				direction = true;
			}else if(leftMove){
				stateTime += Gdx.graphics.getDeltaTime();
				batch.draw(runningAnimationLeft.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				direction = false;
			}else{
				if(direction){
					batch.draw(runningAnimationRight.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				}else{
					batch.draw(runningAnimationLeft.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				}
			}
		}else{
			if(rightMove){
				stateTime += Gdx.graphics.getDeltaTime();
				batch.draw(walkingAnimationRight.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				direction = true;
			}else if(leftMove){
				stateTime += Gdx.graphics.getDeltaTime();
				batch.draw(walkingAnimationLeft.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				direction = false;
			}else{
				if(direction){
					batch.draw(walkingAnimationRight.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				}else{
					batch.draw(walkingAnimationLeft.getKeyFrame(stateTime, true), bottom.x - bottom.width/1.3f, bottom.y, 130, 120);
				}
			}
		}
		if(bullets.size() > 0){
			for(int i=0; i<bullets.size(); i++){
				bullets.get(i).draw(batch);
			}
		}
	}
}
