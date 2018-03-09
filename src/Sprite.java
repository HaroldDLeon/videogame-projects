import java.awt.Image;
import java.awt.Graphics;

public class Sprite {
	Animation animation;
	int x;
	int y;
	
	public Sprite(int x, int y, String filename, int count, int duration){
		this.x = x;
		this.y = y;
		animation = new Animation(filename, count, duration);				
	}
	
	public void moveBy(int dx, int dy){
		x += dx;
		y += dy;
	}
	public void draw(Graphics g){
		g.drawImage(animation.getImage(), x, y, null);
	}
}
