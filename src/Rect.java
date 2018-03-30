import java.awt.*;

public class Rect {

	int x;
	int y;
	int width;
	int height;
	int dx = 0;
	int dy = 0;
	boolean held = false;
	
	public Rect(int x, int y, int width, int height){
		this.x = 		x;
		this.y = 		y;
		this.width 	= 	width;
		this.height = 	height;
	}
	public void draw(Graphics g){
		g.drawRect(x, y, width, height);
	}
	public boolean contains(int mx, int my){
		return (x <= mx) && (mx <= x+width) && (y <= my) && (my <= y+height);
	}
	public boolean overlaps(Rect r){
		return 	(
				(x 			< r.x + r.width)	&& 	// To the right of the rectangle.
				(x + width 	> r.x) 				&& 	// To the left of the rectangle. 
				(y 			< r.y + r.height)	&& 	// Above the rectangle.
				(y + height > r.y)					// Below the rectangle.
				);
	}
	public void grab(){
		held = true;
	}
	public void drop(){
		held = false;
	}
	public void move(){
		x += dx;
		y += dy;
	}
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void moveBy(int delta_x, int delta_y){
		x += delta_x;
		y += delta_y;
	}
	
	public void resizeBy(int delta_w, int delta_h){
		width += delta_w;
		height += delta_h;
	}
	public void setVelocity(int velocity_x, int velocity_y){
		dx = velocity_x;
		dy = velocity_y;
	}
	

}
