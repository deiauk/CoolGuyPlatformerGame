package items;

import tools.Constants;
import tools.TextureManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import coolGuyObjects.CoolGuy;
import coolGuyObjects.Planet;

public class SpeedItem extends ItemObject{
	
	private Rectangle full;
	private Sprite sprite;
	private static float time;
	private static boolean delete;
	private static boolean one;
	
	public SpeedItem(int x, int y){
		full = new Rectangle(0, 0, 37, 70);
		sprite = new Sprite(TextureManager.speed);
		time = 0;
		delete = true;
		one = true;
		
		setPosition(x, y);
	}

	@Override
	public int hits(Rectangle r) {
		if(full.overlaps(r)){
			return 1;
		}
		return -1;
	}

	@Override
	public void setPosition(float x, float y) {
		full.x = x;
		full.y = y;
		
		sprite.setPosition(x, y);
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public Rectangle getFull() {
		return full;
	}

	@Override
	public void update(float delta) {
		
	}
	
	public static void update2(float delta) {
		if(!delete){
			time += delta;
			delete = time > 15 ? true : false;
			if(one){
				CoolGuy.changeSpeed(CoolGuy.speed + 100);
				Planet.changeDiv(5);
				one = false;
			}
			System.out.println("ik " + CoolGuy.speed);
		}else if(!one){
			CoolGuy.changeSpeed(Constants.speed);
			Planet.changeDiv(4);
			one = true;
			System.out.println("iddddddddddk " + CoolGuy.speed);
		}
	}
	public static void letUpdate(boolean t){
		delete = !t;
	}
}
