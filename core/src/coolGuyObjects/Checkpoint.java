package coolGuyObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import tools.TextureManager;

public class Checkpoint extends GameObject{

    private Rectangle hitBox;
    private Sprite sprite;
    private int x,y;

    public Checkpoint(int x, int y){
        hitBox = new Rectangle(0, 0, 140, 280);
        sprite = new Sprite(TextureManager.checkpoint);
        sprite.setSize(14, 28);
        setPosition(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public int hits(Rectangle r) {
        if(hitBox.overlaps(r)){
            return 1;
        }
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
        // TODO Auto-generated method stub
        return hitBox;
    }

    @Override
    public int hitAction(int side) {
        // TODO Auto-generated method stub

        return 0;
    }

    public void update(float delta, int number, float playerPosition, int screenWidth) {
        // TODO Auto-generated method stub

    }

    @Override
    public int hits2(Rectangle r) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getX(){
        return x;
}
    public int getY(){
        return y;
    }

	@Override
	public void update(float delta, int number, float playerPosition,
			float playerPositionY, int screenWidth) {
		// TODO Auto-generated method stub
		
	}

}
