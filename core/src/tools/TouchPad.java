package tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class TouchPad {
	private Stage stage;
	private Touchpad touchpad;
	private TouchpadStyle touchpadStyle;
	private Skin touchpadSkin;
	private Drawable touchBackground;
	private Drawable touchKnob;
	private float ratioX, ratioY;
	
	public TouchPad(){
		float aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
		touchpadSkin = new Skin();
		touchpadSkin.add("touchBackground", new Texture("data/touchBackground.png"));
		touchpadSkin.add("touchKnob", new Texture("data/touchKnob.png"));
		touchpadStyle = new TouchpadStyle();
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		touchpad = new Touchpad(3, touchpadStyle);
		touchpad.setBounds(10 * ratioX, 10 * ratioY, 60 * aspectRatio, 60 * aspectRatio);
 
		stage = new Stage();
		stage.addActor(touchpad);			
		Gdx.input.setInputProcessor(stage);
	}
	
	public void draw(Batch batch){
		stage.draw();
	}
	public float getKnobPercentX(){
		return touchpad.getKnobPercentX();
	}
	public float getKnobPercentY(){
		return touchpad.getKnobPercentY();
	}
	public void setRatios(float x, float y){
		ratioX = x;
		ratioY = y;
	}
}
