package coolGuyObjects;

import java.util.ArrayList;

import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class WeakBrick extends GameObject{
	
	private Rectangle full, top;
	private Sprite sprite;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private int x, y;
	public static int count, discount;
	private boolean copy;
	
	public WeakBrick(int x, int y){
		this.x = x;
		this.y = y;
		
		list.add(x);
		list.add(y);
		
		count++;
		discount = count;
		full = new Rectangle(0, 0, 64, 64); 
		top = new Rectangle(0, 46, 64, 20);
		sprite = new Sprite(TextureManager.weekBrick);
		sprite.setSize(64, 64);
		setPosition(x, y);
	}
	
	public WeakBrick(int x, int y, boolean check){
		this.x = x;
		this.y = y;
		copy = true;
		full = new Rectangle(0, 0, 64, 64); 
		top = new Rectangle(0, 46, 64, 20);
		sprite = new Sprite(TextureManager.weekBrick);
		sprite.setSize(64, 64);
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(top.overlaps(r)){
			return 1;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, int number, float playerPosition, float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setPosition(float x, float y) {
		full.x = x;
		full.y = y;
		
		top.x = x;
		top.y = y + 46;
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
	
	public Rectangle getTop() {
		return top;
	}

	@Override
	public int hitAction(int side) {
		return 1;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getCount(){
		return count;
	}
	public static int getItemFromList(int index){
		return list.size() > index ? list.get(index) : -1;
	}
	public boolean copy(){
		return copy;
	}
	public static void clearList(){
		list.clear();
	}
}
