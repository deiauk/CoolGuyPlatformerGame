package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;

public class TextureManager {
	
	public static Texture brick;
	public static Texture coin;
	public static Texture pole;
	public static Texture spikes;
	public static Texture bush;
	public static Texture planet, planet2, planet3;
	public static Texture back;
	public static Texture enemy;
	public static Texture cannon;
	public static Texture bullet;
	public static Texture platform;
	public static Texture weekBrick;
	public static Texture gunItem;
	public static Texture playerBullet;
	public static Texture speed;
	public static Texture checkpoint;
	public static Texture box;
	public static Texture medKit;
	public static Texture AI;
	
	public static void create(){
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	
    	SimpleDateFormat hour = new SimpleDateFormat("HH");
    	SimpleDateFormat minute = new SimpleDateFormat("mm");
    	
    	int h = Integer.parseInt(hour.format(cal.getTime()));
    	int min = Integer.parseInt(minute.format(cal.getTime()));

		brick = new Texture("data/brick1.png");
		coin = new Texture("data/coin.png");
		pole = new Texture("data/pole.png");
		spikes = new Texture("data/spikes.png");
		bush = new Texture("data/bushes.png");
		planet = new Texture("data/planet.png");
		planet2 = new Texture("data/planet2.png");
		planet3 = new Texture("data/planet3.png");
		back = new Texture("data/background.png");
		enemy = new Texture("data/enemy.png");
		cannon = new Texture("data/cannon.png");
		bullet = new Texture("data/bullet.png");
		platform = new Texture("data/platform.png");
		weekBrick = new Texture("data/weekBrick.png");
		gunItem = new Texture("data/gunItem.png");
		playerBullet = new Texture("data/playerBullet.png");
		speed = new Texture("data/speed.png");
		checkpoint = new Texture("data/cp.png");
		box = new Texture("data/Box.png");
		medKit = new Texture("data/medKit.png");
		AI = new Texture("data/AI.png");
	}
	public static void dispose(){
		brick.dispose();
		coin.dispose();
		pole.dispose();
		spikes.dispose();
		bush.dispose();
		planet.dispose();
		spikes.dispose();
		bush.dispose();
		planet.dispose();
		planet2.dispose();
		planet3.dispose();
		back.dispose();
		enemy.dispose();
		cannon.dispose();
		bullet.dispose();
		platform.dispose();
		weekBrick.dispose();
		gunItem.dispose();
		playerBullet.dispose();
		speed.dispose();
		checkpoint.dispose();
		box.dispose();
		medKit.dispose();
		AI.dispose();
	}
}
