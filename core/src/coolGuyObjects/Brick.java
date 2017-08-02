package coolGuyObjects;

import java.util.ArrayList;
import tools.TextureManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Brick extends GameObject{
	
	private Rectangle hitBox, top;
	private Sprite sprite;
	private ArrayList<Sprite> sprites;
	private boolean one, yAxis = false;
	private float sizeX, sizeY;
	public boolean playerOnBrick;
	
	public Brick(int x, int y, byte type){
		one = true;
		playerOnBrick = false;
		hitBox = new Rectangle(x, y, 64, 64);
		top = new Rectangle(0, 44, 64, 20);
		if(type == 1){
			sprite = new Sprite(TextureManager.brick);
		}else if(type == 2){
			sprite = new Sprite(TextureManager.brick);
		}
		sprite.setSize(64, 64);
		setPosition(x, y);
	}

	public Brick(int x, int y, float sizeX, float sizeY, byte type){
		one = false;
		playerOnBrick = false;
		this.sizeX = sizeX * 64;
		this.sizeY = sizeY * 64;
		hitBox = new Rectangle(x, y, this.sizeX, this.sizeY);
		top = new Rectangle(0, this.sizeY - 20, this.sizeX, 20);
		sprites = new ArrayList<Sprite>();
		
		if(sizeX == 1){
			yAxis = true;
			for(int i=0; i<sizeY; i++){
				if(type == 1){
					sprites.add(new Sprite(TextureManager.brick));
				}else if(type == 2){
					sprites.add(new Sprite(TextureManager.brick));
				}
				sprites.get(i).setSize(64, 64);
			}
		}else if(sizeY == 1){
			for(int i=0; i<sizeX; i++){
				if(type == 1){
					sprites.add(new Sprite(TextureManager.brick));
				}else if(type == 2){
					sprites.add(new Sprite(TextureManager.brick));
				}
				sprites.get(i).setSize(64, 64);
			}
		}
		
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		return -1;
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
	public void setPosition(float x, float y) {
		hitBox.x = x;
		hitBox.y = y;
		
		if(one){
			top.x = x;
			top.y = y + 44;
			sprite.setPosition(x, y);
		}else{
			float tmpX = x;
			float tmpY = y;
			top.x = x;
			top.y = y + (sizeY - 20);
			if(yAxis){
				for(Sprite s : sprites){
					s.setPosition(x, tmpY);
					tmpY += 64;
				}
			}else{
				for(Sprite s : sprites){
					s.setPosition(tmpX, y);
					tmpX += 64;
				}
			}
		}
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
		if(!one){
			for(Sprite s : sprites){
				s.draw(batch);
			}
		}else{
			sprite.draw(batch);
		}
	}

	@Override
	public Rectangle getHitBox() {
		return hitBox;
	}
	public Rectangle getTop() {
		return top;
	}

	@Override
	public int hitAction(int side) {
		return 1;
	}

	@Override
	public int hits2(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(float delta, int number, float playerPosition,
			float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
		
	}

}
