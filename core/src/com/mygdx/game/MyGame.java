package com.mygdx.game;

import items.Coin;
import items.Gun;
import items.ItemObject;
import items.Medkit;
import items.PlayerBullet;
import items.SpeedItem;

import java.util.ArrayList;
import java.util.StringTokenizer;

import tools.SoundManager;
import tools.TextureManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import coolGuyObjects.AI;
import coolGuyObjects.Background;
import coolGuyObjects.Box;
import coolGuyObjects.Brick;
import coolGuyObjects.Bush;
import coolGuyObjects.Cannon;
import coolGuyObjects.Checkpoint;
import coolGuyObjects.CoolGuy;
import coolGuyObjects.Enemy;
import coolGuyObjects.GameObject;
import coolGuyObjects.Planet;
import coolGuyObjects.Platform;
import coolGuyObjects.Pole;
import coolGuyObjects.Spikes;
import coolGuyObjects.WeakBrick;

public class MyGame extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private OrthographicCamera camera, controlCamera;
	private CoolGuy player1;
	private ArrayList<GameObject> list;
	private ArrayList<GameObject> pleaseDelete, deleteEnemy, deleteWeakBrick; 
	private ArrayList<GameObject> firstBackground;
	private ArrayList<GameObject> secondBackground;
	private ArrayList<GameObject> enemies;
	private ArrayList<ItemObject> items, deleteItem;
	private Rectangle leftButton, rightButton, backButton, soundButton;
	private Rectangle startRectangle, jumpButton, fireButton;
	private Sprite spriteLeftButton, spriteRightButton, spriteJumpButton, spriteFireButton, spriteFireButtonDisabled, menuNext, menuStart, menuGameOver, menuBack;
	private Texture texture;
	private Sprite options, exit, home, back, sound, soundMute;
	private Rectangle optionsRectangle, exitRectangle, nextLevelRectangle, homeRectangle;
	private int level;
	private BitmapFont font; 
	private int dead = 0;
	private int jump = 0;
	private int coins = 0;
	private ShapeRenderer render;
	private Brick brick = null;
	private Platform platform = null;
	private int gameState = 1;
	private boolean overlapsFire = false, overlapsJump = false, call, letJump = true;
	private float testPosition = 0;
	private Background background;
	private ArrayList<GameObject> weekList;
	private int cpx = 0;
    private int cpy = 200;
    private float timer = 0;
    private int lifeDecreaser = -40;
    private int hpPlus = 50;
    private float primaryScreenHeight, primaryScreenWidth;
    private float ratioX, ratioY;
    private boolean turnOffD, turnOffA;
    private float tarX = -100, tarY = -100;
    private static boolean androidPlatform;
    private Rectangle trap, playerBounds;
    private Vector2 cam;
    private boolean muteSound;
    private Rectangle screen;
    private boolean rectangleCollision;
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void create () {
		render = new ShapeRenderer();
		TextureManager.create();
		SoundManager.create();
		SoundManager.music.setLooping(true);
		SoundManager.music.setVolume(0.02f);
		SoundManager.music.play();
		
		//pad = new TouchPad();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1600, 800); // 1600 800
		//viewport = new FitViewport(1600,800,camera);
		
		controlCamera = new OrthographicCamera(800, 400);
		
		batch = new SpriteBatch();
		
		generateCoins();
		
		player1 = new CoolGuy();
		player1.setPosition(6800, 300);
		player1.setHealthPoints(100);
		
		trap = new Rectangle(player1.getFull().x - 2 * player1.getFull().width,
				player1.getFull().y + 5 * player1.getFull().getHeight(),
                2 * player1.getFull().width,
                4 * player1.getFull().height);
		playerBounds = new Rectangle(player1.getFull().x, player1.getFull().y, player1.getFull().width, player1.getFull().height);
		
		list = new ArrayList<GameObject>();
		firstBackground = new ArrayList<GameObject>();
		secondBackground = new ArrayList<GameObject>();
		enemies = new ArrayList<GameObject>();
		
		deleteEnemy = new ArrayList<GameObject>();
		pleaseDelete = new ArrayList<GameObject>();
		deleteWeakBrick = new ArrayList<GameObject>();
		
		weekList = new ArrayList<GameObject>();
		
		items = new ArrayList<ItemObject>();
		deleteItem = new ArrayList<ItemObject>();
		
		//android control
		texture = new Texture("data/left.png");
		spriteLeftButton = new Sprite(texture);
		texture = new Texture("data/right.png");
		spriteRightButton = new Sprite(texture);
		texture = new Texture("data/top.png");
		spriteJumpButton = new Sprite(texture);
		texture = new Texture("data/fireButton.png");
		spriteFireButton = new Sprite(texture);
		texture = new Texture("data/fireButtonDisabled.png");
		spriteFireButtonDisabled = new Sprite(texture);
		
		//menu
		texture = new Texture("data/menuBackground.jpg");
		menuBack = new Sprite(texture);
		texture = new Texture("data/start.png");
		menuStart = new Sprite(texture);
		menuStart.setSize(300, 100);
		texture = new Texture("data/options.png");
		options = new Sprite(texture);
		options.setSize(300, 100);
		optionsRectangle = new Rectangle(0, 0, 300, 100);
		texture = new Texture("data/exit.png");
		exit = new Sprite(texture);
		exit.setSize(300, 100);
		exitRectangle = new Rectangle(0, 0, 300, 100);
		texture = new Texture("data/nextLevel.png");
		menuNext = new Sprite(texture);
		nextLevelRectangle = new Rectangle(0,0,300,100);
		menuNext.setSize(300, 100);
		startRectangle = new Rectangle(0, 0, 300, 100);
		texture = new Texture("data/gameOver.png");
		menuGameOver = new Sprite(texture);
		menuGameOver.setSize(300, 100);
		texture = new Texture("data/home.png");
		home = new Sprite(texture);
		home.setSize(30, 30);
		home.setPosition(0, 150);
		homeRectangle = new Rectangle(0,150,30,30);
		texture = new Texture("data/Back.png");
		back = new Sprite(texture);
		back.setSize(300, 100);
		backButton = new Rectangle(0,0,300,100);
		texture = new Texture("data/audio.png");
		sound = new Sprite(texture);
		sound.setSize(250, 250);
		sound.setPosition(700, 300);
		soundButton = new Rectangle(700, 300, 250, 250);
		texture = new Texture("data/audio_no.png");
		soundMute = new Sprite(texture);
		soundMute.setSize(250, 250);
		soundMute.setPosition(700, 300);
		
		spriteLeftButton.setSize(45, 30);
		spriteRightButton.setSize(45, 30);
		spriteJumpButton.setSize(45, 30);
		spriteFireButton.setSize(60, 60);
		spriteFireButtonDisabled.setSize(60, 60);
		
		primaryScreenWidth = Gdx.graphics.getWidth();
		primaryScreenHeight = Gdx.graphics.getHeight();
		
		spriteLeftButton.setPosition(-380, -170);
		spriteRightButton.setPosition(-280, -170);
		spriteJumpButton.setPosition(312, -170);
		spriteFireButton.setPosition(305, -120);
		spriteFireButtonDisabled.setPosition(305, -120);
		
		leftButton = new Rectangle(-380, -170, 45, 30);
		rightButton = new Rectangle(-280, -170, 45, 30);
		jumpButton = new Rectangle(312, -170, 45, 30);
		fireButton = new Rectangle(305, -120, 60, 60);
		
		screen = new Rectangle(0, 0, camera.viewportWidth*2, camera.viewportHeight*2);
		
		background = new Background();
		cam = new Vector2();
		cam.set(camera.position.x, camera.position.y);
		
		switch(Gdx.app.getType()) {
			case Android:
				androidPlatform = true;
			break;
			case Desktop:
				androidPlatform = false;
			break;
		}
		
		font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
		font.setScale(1);
		
		level = 1;
		loadLevel("level1");
	}
	
	@Override
	public void resize (int width, int height) {
		ratioX = width/primaryScreenWidth;
		ratioY = height/primaryScreenHeight;

		camera.update();
	}
	
	@Override
	public void render () {
		switch(this.gameState){
		case 1:
			this.mainMenu();
			break;
		case 2:
			this.mainGame();
			break;
		case 3:
			this.nextLevel();
			break;
		case 4:
			this.gameOver();
			break;
		case 5:
			this.options();
			break;
		}
	}
	
	public void nextLevel(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		weekList.clear();
		enemies.clear();
		items.clear();
		WeakBrick.count = 0;
		AI.count = 0;
		WeakBrick.clearList();
		player1.setToZeroVelTime();
		
		batch.begin();
		background.draw(batch);
		player1.draw(batch);

		for(GameObject t : secondBackground){
			t.draw(batch);
		}
		for(GameObject t : firstBackground){
			t.draw(batch);
		}
		for(GameObject t : list){
			t.draw(batch);
		}

		menuNext.draw(batch);
		nextLevelRectangle.setPosition(menuNext.getX(), menuNext.getY());
		batch.end();

		//Controls
		if(Gdx.input.isTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			Rectangle touch = new Rectangle(t.x - 16, t.y - 16, 20, 20);
			if(touch.overlaps(nextLevelRectangle)){
				loadLevel("level" + level);
				player1.setPosition(0, 400);
				gameState = 2;
			}
		}
	}
	
	public void mainMenu(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		startRectangle.setPosition(500 + menuStart.getWidth()/2, 500);
		optionsRectangle.setPosition(startRectangle.x, (startRectangle.y - startRectangle.getHeight() - 50));
		exitRectangle.setPosition(optionsRectangle.x, (optionsRectangle.y - optionsRectangle.getHeight() - 50));
		
		menuStart.setPosition(startRectangle.getX(), startRectangle.getY());
		options.setPosition(optionsRectangle.getX(), optionsRectangle.getY());
		exit.setPosition(exitRectangle.getX(), exitRectangle.getY());
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		menuBack.draw(batch);
		menuStart.draw(batch);
		options.draw(batch);
		exit.draw(batch);
		batch.enableBlending();
		batch.end();
		
		camera.position.set(cam.x, cam.y, 0);
		camera.update();
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			Rectangle touch = new Rectangle(t.x - 16, t.y - 16, 20, 20);
			if(touch.overlaps(startRectangle)){
				gameState = 2;
			}else if(touch.overlaps(optionsRectangle)){
				gameState = 5;
			}else if(touch.overlaps(exitRectangle)){
				 Gdx.app.exit();
			}
		}
	}
	
	public void gameOver(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.x = cam.x;
		camera.position.y = cam.y;
		
		camera.update();
		
		this.startRectangle.x = 500 + startRectangle.getWidth()/2;
		this.startRectangle.y = 400;
		
		menuGameOver.setPosition(startRectangle.x, startRectangle.y);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		menuBack.draw(batch);
		menuGameOver.draw(batch);
		batch.enableBlending();
		batch.end();
		
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			Rectangle touch = new Rectangle(t.x - 16, t.y - 16, 20, 20);
			if(touch.overlaps(startRectangle)){
				gameState = 2;
			}
		}
	}
	
	public void options(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.x = cam.x;
		camera.position.y = cam.y;
		
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		menuBack.draw(batch);
		back.draw(batch);
		if(!muteSound){
			sound.draw(batch);
		}else{
			soundMute.draw(batch);
		}
		backButton.setPosition(back.getX(), back.getY());
		batch.enableBlending();
		batch.end();
		
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(t);
			Rectangle touch = new Rectangle(t.x - 16, t.y - 16, 20, 20);
			if(touch.overlaps(backButton)){
				gameState = 1;
			}else if(touch.overlaps(soundButton)){
				muteSound = muteSound == true ? false : true;
				if(muteSound){
					SoundManager.music.stop();
					TextureManager.create();
				}else{
					SoundManager.music.play();
				}
			}
		}
	}
	
	public void updateCamera(){
		background.setY(camera.position.y - background.getHeight()/2);
		camera.update();
	}
	
	public void loadLevel(String level){
		list.clear();
		firstBackground.clear();
		secondBackground.clear();
		enemies.clear();
		items.clear();
		
		FileHandle file = Gdx.files.internal("data/" + level + ".txt");
		StringTokenizer tokens = new StringTokenizer(file.readString());
		while(tokens.hasMoreTokens()){
			String type = tokens.nextToken();
			if(type.equals("Brick")){
				list.add(new Brick(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Byte.parseByte(tokens.nextToken())));
			}else if(type.equals("Spike")){
				list.add(new Spikes(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Coin")){
				items.add(new Coin(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Pole")){
				list.add(new Pole(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Bush")){
				firstBackground.add(new Bush(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Planet")){
				secondBackground.add(new Planet(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Enemy")){
				enemies.add(new Enemy(Float.parseFloat(tokens.nextToken()), Float.parseFloat(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("AI")){
				enemies.add(new AI(Float.parseFloat(tokens.nextToken()), Float.parseFloat(tokens.nextToken())));
			}else if(type.equals("Cannon")){
				enemies.add(new Cannon(Float.parseFloat(tokens.nextToken()), Float.parseFloat(tokens.nextToken()), Boolean.parseBoolean(tokens.nextToken())));
			}else if(type.equals("Platform")){
				list.add(new Platform(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Boolean.parseBoolean(tokens.nextToken())));
			}else if(type.equals("WeakBrick")){
				list.add(new WeakBrick(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Gun")){
				items.add(new Gun(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("SpeedItem")){
				items.add(new SpeedItem(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}else if(type.equals("Bricks")){
				list.add(new Brick(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), Float.parseFloat(tokens.nextToken()), Float.parseFloat(tokens.nextToken()), Byte.parseByte(tokens.nextToken())));
			}else if(type.equals("Checkpoint")) {
                list.add(new Checkpoint(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
            }else if(type.equals("Box")){
				list.add(new Box(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
			}
		}
		CoolGuy.setAllLists(list, enemies, items);
	}
	
	
	public void mainGame(){
		timer += Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
		       camera.zoom += 0.02;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.E)) {
		       camera.zoom -= 0.02;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		
		if((player1.getHitBox().getX() + camera.viewportWidth / 2) > testPosition){
			testPosition += background.getWidth();
			background.setPosition(testPosition - background.getWidth());
		}else{
			testPosition -= background.getWidth();
			background.setPosition(testPosition);
		}
		background.draw(batch);

		for(GameObject t : secondBackground){
			t.draw(batch);
		}
		
		for(GameObject t : firstBackground){
			t.draw(batch);
		}
		
		for(ItemObject item : items){
			if(item.getFull().overlaps(screen)){
				item.draw(batch);
			}
		}
		
		player1.draw(batch);
		
		for(GameObject t : list){
			if(t.getHitBox().overlaps(screen)){
				t.draw(batch);
			}
		}
		for(GameObject t : enemies){
			t.draw(batch);
		}
		
		batch.setProjectionMatrix(controlCamera.combined);
		
		home.draw(batch);
		render.setProjectionMatrix(camera.combined);
		render.begin(ShapeType.Line);
		screen.setPosition(player1.getHitBox().getX() - screen.getWidth()/2, player1.getHitBox().getY() - screen.getHeight()/2);
		render.rect(screen.getX(), screen.getY(), screen.getWidth(), screen.getHeight());
		
		
		if(androidPlatform){
			spriteLeftButton.draw(batch);
			spriteRightButton.draw(batch);
			spriteJumpButton.draw(batch);
		}
		
		if(PlayerBullet.shoot){
			spriteFireButton.draw(batch);
		}else{
			spriteFireButtonDisabled.draw(batch);
		}
		
		font.draw(batch, "Deaths: " + dead, -380, 180);
		font.draw(batch, "Coins: " + coins, -380, 150);
		font.draw(batch, "HP: " + player1.getHealthPoints(), -380, 120); 
		if(PlayerBullet.shoot){
			font.draw(batch, "Ammo: " + PlayerBullet.bulletsQuantity, 180, 180);
			font.draw(batch, "Charger: " + PlayerBullet.gunCharger, 180, 150);
		}
		batch.end();
		
		//Updates
		player1.update(Gdx.graphics.getDeltaTime());
		
		playerBounds.x = player1.getFull().x;
		playerBounds.y = player1.getFull().y;
		setNewTrapArea();
		camera.position.x = trap.x;
		
		if(androidPlatform){
			camera.position.y = trap.y + (Gdx.graphics.getHeight()/2)/ratioY - Gdx.graphics.getHeight()*0.4f;
		}else{
			camera.position.y = trap.y + (Gdx.graphics.getHeight()/2)/ratioY;
		}
		
		camera.update();

		if(player1.getHitBox().getY() < -1500){
			player1.setHealthPoints(0);
			gameState = 4;
		}
		if(AI.count > 0){
			for(GameObject ob : enemies){
				if(ob instanceof AI){
					if(((AI) ob).canSetTarget()){
						AI.setTarget(player1.getHitBox().getX(), player1.getHitBox().getY(), tarX, tarY);
						((AI) ob).blockPos = true;
					}
				}
			}
		}
		
		boolean changeLevel = false;
		for(GameObject e : enemies){
			if(e instanceof AI){
				//render.rect(e.getHitBox().getX(), e.getHitBox().getY(), e.getHitBox().getWidth(), e.getHitBox().getHeight());
				render.rect(((AI) e).getBottom().getX(), ((AI) e).getBottom().getY(), ((AI) e).getBottom().getWidth(), ((AI) e).getBottom().getHeight());
			}
			for(GameObject t : list){
				if(t instanceof Brick){
					if(e.hits(t.getHitBox()) == 7 && e instanceof Cannon){
						((Cannon) e).removeBullet();
					}
					if(e.hits(player1.getFull()) == 7){
                        ((Cannon) e).removeBullet();
                        player1.changeHP(-9, timer);
                        break;
					}
					if(t instanceof Brick){
						switch(e.hits(((Brick) t).getTop())){
						case 5:
							e.action(1, 0, t.getHitBox().getY() + t.getHitBox().height);
							break;
						}
					}
				}
			}
		}
		AI.setCollision(false);
		render.end();
		for(GameObject t : list){
			switch(player1.hits(t.getHitBox())){
			case 1:
				switch(t.hitAction(1)){
				case 1:
					tooHigh();
					if(t instanceof Brick){
						((Brick) t).playerOnBrick = true;
						tarX = t.getHitBox().getX();
						tarY = t.getHitBox().getY();
					}
					letJump = true;
					//rectangleCollision = true;
					AI.setCollision(true);
					jump = 0;
					player1.action(1, 0, (t.getHitBox().y + t.getHitBox().height));
					turnOffD = turnOffA = false;
					break;
				case 2:
					player1.changeHP(lifeDecreaser, timer);
                    SoundManager.damaged.play(0.02f);
                    break;
				case 3:
					coins++;
					SoundManager.coin.play(0.05f);
					pleaseDelete.add(t);
					break;
				case 4:
					level++;
					SoundManager.victory.play(.4f);
					changeLevel = true;
					break;
				}
				break;
			case 2:
				switch(t.hitAction(2)){
				case 1:
					float distance = player1.getHitBox().getX() - (t.getHitBox().x + t.getHitBox().width + 1);
					player1.action(2, (t.getHitBox().x + t.getHitBox().width + 1), 0);
					for(GameObject s : secondBackground){
						s.action(0, distance, 0);
					}
					turnOffA = true;
					AI.setCollision(true);
					break;
				case 2:
					player1.changeHP(lifeDecreaser, timer);
                    SoundManager.damaged.play(0.02f);
                    break;
				case 3:
					coins++;
					SoundManager.coin.play(0.05f);
					pleaseDelete.add(t);
					break;	
				case 4:
					level++;
					SoundManager.victory.play(.4f);
					changeLevel = true;
					break;
				}
				break;
			case 3:
				switch(t.hitAction(3)){
				case 1:
					float distance = player1.getHitBox().getX() - (t.getHitBox().x - player1.getHitBox().width);
					for(GameObject t1 : secondBackground){
						t1.action(0, distance, 0);
					}
					player1.action(3, (t.getHitBox().x - player1.getHitBox().width), 0);
					if(player1.getHitBox().getY() > 0){
						turnOffD = true;
					}
					break;	
				case 2:
					for(GameObject t2 : secondBackground){
						if(t2 instanceof Planet){
							t2.setPosition(((Planet) t2).getX(), ((Planet) t2).getY());
						}		
					}
					player1.changeHP(lifeDecreaser, timer);
				case 3:
					coins++;
					SoundManager.coin.play(0.05f);
					pleaseDelete.add(t);
					break;
				case 4:
					level++;
					SoundManager.victory.play(.4f);
					changeLevel = true;
					break;
				}
				break;
			case 4:
				switch(t.hitAction(4)){
				case 1:
					player1.action2(4, player1.getHitBox().getX(), (t.getHitBox().y - player1.getHitBox().height));
					break;
				case 2:
					for(GameObject t2 : secondBackground){
						if(t2 instanceof Planet){
							t2.setPosition(((Planet) t2).getX(), ((Planet) t2).getY());
						}		
					}
					 player1.changeHP(lifeDecreaser, timer);
                     SoundManager.damaged.play(0.02f);
                     break;
				case 3:
					coins++;
					SoundManager.coin.play(0.05f);
					pleaseDelete.add(t);
					break;	
				case 4:
					level++;
					SoundManager.victory.play(.4f);
					changeLevel = true;
					break;	
				}
				break;
			}
		}
		
		//Tikrina ar ivyko zaidejo ir item'o collision
		for(ItemObject item : items){
			if(item instanceof Gun && item.hits(player1.getFull()) == 1){
				SoundManager.gunReload.play(.5f);
				deleteItem.add(item);
			}else if(item instanceof SpeedItem && item.hits(player1.getFull()) == 1){
				//SoundManager.gunReload.play(5f);
				SpeedItem.letUpdate(true);
				deleteItem.add(item);
			}else if(item instanceof Coin && item.hits(player1.getFull()) == 1){
				coins++;
				SoundManager.coin.play(.5f);
				deleteItem.add(item);
			}else if(item instanceof Medkit && item.hits(player1.getFull()) == 1){
				player1.setHealthPoints(player1.getHealthPoints() + hpPlus);
				deleteItem.add(item);
			}
		}
		SpeedItem.update2(Gdx.graphics.getDeltaTime());
		for(GameObject pl : list){
			if(pl instanceof Platform){
				platform = (Platform) pl;
				platform.update(Gdx.graphics.getDeltaTime(), 0, player1.getHitBox().getX(), player1.getHitBox().getY(), Gdx.graphics.getWidth()); //!!!!!!!
				if(platform.hits(player1.getFull()) == 1 || platform.hits(player1.getFull()) == 2){
					letJump = true;
					turnOffD = turnOffA = false;
					jump = 0;
					player1.action2(2, player1.getHitBox().getX() + platform.getUpdateSpeed(), (platform.getHitBox().y + platform.getHitBox().height - 2));
					if(player1.jumpToHigh()){
						player1.setHealthPoints(player1.getHealthPoints() - 30);
						player1.setJumpToHigh(false);
					}
				}
			}
			 if(pl instanceof Checkpoint){
                 if(pl.hits(player1.getHitBox()) == 1){
                     cpx = ((Checkpoint) pl).getX();
                     cpy = ((Checkpoint) pl).getY();

                 }
             }
			for(GameObject pl2 : list){
				if(pl2 instanceof Brick){
					brick = (Brick) pl2;
				}
				if((platform != null && brick != null)){
					if(platform.hits(brick.getHitBox()) == 1){
						platform.setDirection(1);
					}
					if(platform.hits(brick.getHitBox()) == 2){
						platform.setDirection(2);
					}
				}
			}
		}
		
		for(int i=0; i<list.size(); i++){
			Box tmpBox = (Box) (list.get(i) instanceof Box ? list.get(i) : null);
			for(int j=0; j<list.size(); j++){
				if(tmpBox != null){
					Brick brick = (Brick) (list.get(j) instanceof Brick ? list.get(j) : null);
					Box tmpOtherBox = (Box) (list.get(j) instanceof Box ? list.get(j) : null);
					if(brick != null){
						tmpBox.hits2(brick.getTop());
					}
					if(tmpOtherBox != null && tmpOtherBox.hashCode() != tmpBox.hashCode()){
						tmpBox.hits2(tmpOtherBox.getTop());
					}
				}
			}
			if(tmpBox != null){
				tmpBox.update(Gdx.graphics.getDeltaTime());
			}
		}

		//Tikrina ar ivyko zaidejo ir prieso collision
		for(GameObject t : enemies){
			t.update(Gdx.graphics.getDeltaTime());
			if(t instanceof Enemy){
				if(t.getHitBox().getY() < - 20){
					SoundManager.kill.play(.03f);
					deleteEnemy.add(t);
				}
			}
			if(t instanceof Cannon){
				t.update(Gdx.graphics.getDeltaTime(), -15, player1.getFull().x, player1.getFull().y, Gdx.graphics.getWidth());
			}
			int direction = 0;
			for(GameObject t2 : list){
				if(t2 instanceof Brick){
					brick = (Brick) t2;
					if(t.hits2(t2.getHitBox()) == 2){
						direction = 2;
					}else if(t.hits2(t2.getHitBox()) == 3){
						direction = 1;
					}
				}
				if(t2 instanceof WeakBrick){
					if(t2.hits(player1.getHitBox()) == 1){
						letJump = true;
						jump = 0;
						deleteWeakBrick.add(t2);
						call = true;
					}
				}
			}
			if(t instanceof Enemy){
				t.update(Gdx.graphics.getDeltaTime(), direction, player1.getHitBox().getX(), player1.getHitBox().getY(), Gdx.graphics.getWidth());
			}
			if(player1.getHealthPoints() <= 0) {
	                gameState = 4;
	                player1.setPosition(cpx, cpy); //checkpointas
	                player1.setHealthPoints(100);
	                player1.setToZeroVelTime();
	                dead++;
	                letJump = true;
	                jump = 0;
	                SoundManager.dead.play(0.06f);
	                primaryState();
	
	         }
			switch(t.hits(player1.getHitBox())){
			case 4:
				SoundManager.kill.play(.03f);
				deleteEnemy.add(t);
				break;
			case 5:
				player1.changeHP(lifeDecreaser, timer);
                SoundManager.damaged.play(0.02f);
                break;
			}
		}
		AI a = null;
		AI b = null;
		for(int i=0; i<enemies.size(); i++){
			if(enemies.get(i) instanceof AI){
				a = (AI) enemies.get(i);
			}
			for(int j=0; j<enemies.size(); j++){
				if(enemies.get(j) instanceof AI){
					b = (AI) enemies.get(j);
				}
				if(a != null && b != null && a.hashCode() != b.hashCode()){
					a.friendCollision(b);
				}
			}
		}

		while(!pleaseDelete.isEmpty()){
			list.remove(pleaseDelete.get(0));
			pleaseDelete.remove(0);
		}
		
		while(!deleteEnemy.isEmpty()){
			enemies.remove(deleteEnemy.get(0));
			deleteEnemy.remove(0);
		}
		
		while(!deleteWeakBrick.isEmpty()){
			if(list.remove(deleteWeakBrick.get(0))){
				list.remove(deleteWeakBrick.get(0));
				if(call)
					WeakBrick.discount--;
			}
			deleteWeakBrick.remove(0);
		}
		
		while(!deleteItem.isEmpty()){
			items.remove(deleteItem.get(0));
			deleteItem.remove(0);
		}
		
		while(!weekList.isEmpty()){
			list.add(weekList.get(0));
			weekList.remove(0);
		}
		
		if(changeLevel){
			this.gameState = 3;
			this.startRectangle.x = player1.getHitBox().getX() - 50;
			this.startRectangle.y = player1.getHitBox().getY();
			this.menuNext.setPosition(player1.getHitBox().x - 50, player1.getHitBox().getY() - 200);
		}
		
		if(androidPlatform){
			handleAndroidInput();
		}else{
			handleDektopControll();
			//handleAndroidInput();
		}

		player1.update(Gdx.graphics.getDeltaTime(), 0, 0, 0, 0);
		
		updateCamera();
	}
	
	private void setNewTrapArea(){
		if(trap.contains(playerBounds))
	        return;
	    if (playerBounds.x < trap.x)
	        trap.x = playerBounds.x;
	    else if (playerBounds.x + playerBounds.width > trap.x + trap.width)
	        trap.x = playerBounds.x + playerBounds.width - trap.width;
	    if (playerBounds.y < trap.y)
	        trap.y = playerBounds.y;
	    else if (playerBounds.y + playerBounds.height > trap.y + trap.height)
	        trap.y = playerBounds.y + playerBounds.height - trap.height;
	}
	
	private void tooHigh(){
		if(player1.jumpToHigh()){
			player1.decreaseHealth();
			SoundManager.damaged.play(0.02f);
			player1.setJumpToHigh(false);
		}
	}
	
	private void handleAndroidInput(){
		for(int i=0; i<2; i++){
			if(Gdx.input.isTouched(i)){
				Vector3 t = new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0);
				controlCamera.unproject(t);
				Rectangle touch = new Rectangle(t.x , t.y , 10, 10);
				
				if(!touch.overlaps(jumpButton)){
					overlapsJump = false;
					System.out.println("not");
				}
				
				if(touch.overlaps(jumpButton) && letJump && !overlapsJump){
					jump++;
					System.out.println("touch");
					letJump = jump <= 2 ? true : false;
					if(letJump){
						player1.jump();
					}
					overlapsJump = true;
					turnOffA = turnOffD = false;
				}else if(touch.overlaps(fireButton) && !overlapsFire){
					player1.shoot(Gdx.graphics.getDeltaTime());
					overlapsFire = true;
				}
				
				if(touch.overlaps(leftButton) && !turnOffA){
					player1.setRotation(1);
					player1.moveLeft(Gdx.graphics.getDeltaTime());
					for(GameObject t1 : secondBackground){
						t1.moveLeft(Gdx.graphics.getDeltaTime());
					}
					CoolGuy.leftMove = true;
					CoolGuy.rightMove = false;
				}else if(touch.overlaps(rightButton) && !turnOffD){
					player1.setRotation(2);
					player1.moveRight(Gdx.graphics.getDeltaTime());
					for(GameObject t1 : secondBackground){
						t1.moveRight(Gdx.graphics.getDeltaTime());
					}
					CoolGuy.rightMove = true;
					CoolGuy.leftMove = false;
				}
				if(touch.overlaps(homeRectangle)){
					gameState = 1;
				}
			}
			if(!Gdx.input.isTouched()){
				//System.out.println("NANNA");
				overlapsFire = false;
				overlapsJump = false;
				CoolGuy.rightMove = false;
				CoolGuy.leftMove = false;
				turnOffA = turnOffD = false;
			}
		}
	}
	
	private void handleDektopControll(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			player1.shoot(Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A) && !turnOffA){
			player1.setRotation(1);
			CoolGuy.leftMove = true;
			player1.moveLeft(Gdx.graphics.getDeltaTime());
			for(GameObject t : secondBackground){
				t.moveLeft(Gdx.graphics.getDeltaTime());
			}
		}else{
			CoolGuy.leftMove = false;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D) && !turnOffD){
			player1.setRotation(2);
			CoolGuy.rightMove = true;
			player1.moveRight(Gdx.graphics.getDeltaTime());
			for(GameObject t : secondBackground){
				t.moveRight(Gdx.graphics.getDeltaTime());
			}
		}else{
			CoolGuy.rightMove = false;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
			jump++;
			turnOffD = turnOffA = false;
			if(jump <= 2){
				letJump = true;
			}else if(jump > 2){
				letJump = false;
			}
			if(letJump){
				player1.jump();
			}
		}
		
		if(Gdx.input.justTouched()){
			Vector3 t = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			controlCamera.unproject(t);
			Rectangle touch = new Rectangle(t.x , t.y , 10, 10);
			if(touch.overlaps(homeRectangle)){
				gameState = 1;
			}
		}
	}
	
	private void primaryState(){
		background.setPosition(0);
		background.setY(-25);
		testPosition = background.getWidth();
		player1.setHealthPoints(100);
		player1.setToZeroVelTime();
		
		for(GameObject t2 : secondBackground){
			if(t2 instanceof Planet){
				t2.setPosition(((Planet) t2).getX(), ((Planet) t2).getY());
			}	
		}
		if(WeakBrick.discount != WeakBrick.count){
			for(GameObject t : list){
				if(t instanceof WeakBrick){
					call = false;
					deleteWeakBrick.add(t);
				}
			}
			addWeakBricks();
		}
	}

	private void addWeakBricks(){
		for(int i = 0; i < (2 * WeakBrick.count); i++){
			weekList.add(new WeakBrick(WeakBrick.getItemFromList(i), WeakBrick.getItemFromList(++i), true));
		}
		WeakBrick.discount = WeakBrick.count;
	}
	
	//dar nepabaigta. Nemoku regex. kas mokat ar prisimenat is c++ laiku. parasykit turiu darbo :D
	public void generateCoins(){
		/*
		PrintWriter out = null;
		try {
		    out = new PrintWriter(new BufferedWriter(new FileWriter("C:/Users/Deividas/Downloads/studying/core/bin/data/level1.txt", true)));
		   // out.println();
		    for(int i=0; i<10; i++){
		    	int tmpX = MathUtils.random(128, 1400);
		    	int tmpY = MathUtils.random(50, 230);
		    	//out.println("Coin " + tmpX + " " + tmpY);
		    }
		}catch (IOException e) {
		    System.err.println(e);
		}finally{
		    if(out != null){
		        out.close();
		    }
		} 
		*/
	}
}
