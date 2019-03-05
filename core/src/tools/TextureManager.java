package tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.badlogic.gdx.Gdx;
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

		brick = new Texture(Gdx.files.internal("brick1.png"));
		coin = new Texture("coin.png");
		pole = new Texture("pole.png");
		spikes = new Texture("spikes.png");
		bush = new Texture("bushes.png");
		planet = new Texture("planet.png");
		planet2 = new Texture("planet2.png");
		planet3 = new Texture("planet3.png");
		back = new Texture("background.png");
		enemy = new Texture("enemy.png");
		cannon = new Texture("cannon.png");
		bullet = new Texture("bullet.png");
		platform = new Texture("platform.png");
		weekBrick = new Texture("weekBrick.png");
		gunItem = new Texture("gunItem.png");
		playerBullet = new Texture("playerBullet.png");
		speed = new Texture("speed.png");
		checkpoint = new Texture("cp.png");
		box = new Texture("Box.png");
		medKit = new Texture("medKit.png");
		AI = new Texture("AI.png");
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
