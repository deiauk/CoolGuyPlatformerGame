package coolGuyObjects;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Planet extends GameObject{
	
	private Sprite sprite;
	private int x, y;
	private static float div;
	
	public Planet(int x, int y, int size, int whichPlanet){
		if(whichPlanet == 1){
			sprite = new Sprite(TextureManager.planet);
			sprite.setSize(64 * size, 64 * size);
		}else if(whichPlanet == 2){
			sprite = new Sprite(TextureManager.planet2);
			sprite.setSize(64 * size, 64 * size);
		}else if(whichPlanet == 3){
			sprite = new Sprite(TextureManager.planet3);
			sprite.setSize(64 * size, 52 * size);
		}
		div = 2;
		this.x = x;
		this.y = y;
		setPosition(x,y);
	}

	@Override
	public int hits(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hits2(Rectangle r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void action(int type, float x, float y) {
		setPosition(sprite.getX() - x/div, sprite.getY());
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
	}

	@Override
	public void moveLeft(float delta) {
		int speed = 150;
		sprite.setPosition(sprite.getX() - (speed * delta), sprite.getY());
	}

	@Override
	public void moveRight(float delta) {
		int speed = 150;
		sprite.setPosition(sprite.getX() + (speed * delta), sprite.getY());
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
		return null;
	}

	@Override
	public int hitAction(int side) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public static void changeDiv(float s){
		div = s;
	}
}
