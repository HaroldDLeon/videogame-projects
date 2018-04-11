import java.awt.Graphics;

public class Sprite extends Rect {
	

	int pose = 0;
	
	static final int up 	= 0;
	static final int down  	= 1;
	static final int left 	= 0;
	static final int right 	= 1;
	
	boolean moving;
	
	Animation[] animation;
	
	public Sprite(int x, int y, String filename, String[] action,  int count, int duration){
		super(x,y,20,5);
		animation = new Animation[action.length];
		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(filename+action[i], count, duration);
		}
	}
	public void moveBy(int dx, int dy){
		x += dx;
		y += dy;
		moving = true;
	}
	
	public void moveUpBy(int dy){
		y -= dy;
		pose = up;
		moving = true;
	}
	public void moveDownBy(int dy){
		y += dy;
		pose = down;
		moving = true;
	}

	public void moveLeftBy(int dx){
		x -= dx;
		pose = left;
		moving = true;
	}
	public void moveRightBy(int dx){
		x += dx;
		pose = right;
		moving = true;
	}
	public void draw(Graphics g){
		if (moving){ g.drawImage(animation[pose].nextImage(),  (int) x, (int) y, null); }
		else 	   { g.drawImage(animation[pose].stillImage(), (int) x, (int) y, null); }
		moving = false;
	}
	public void setStill(int newStill){
		for(int i = 0; i < this.animation.length; i++){
			animation[i].current = newStill;
		}
	}
}
