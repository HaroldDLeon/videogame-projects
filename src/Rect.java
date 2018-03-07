import java.awt.*;

public class Rect {

	int x;
	int y;
	int width;
	int height;
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
				(r.x < x + width)		&& 	// To the right of the rectangle.
				(r.x + r.width < x) 	&& 	// To the left of the rectangle. 
				(r.y + r.height > y)	&& 	// Above the rectangle.
				(r.y < y + height)			// Below the rectangle.
				);
	}
	public void grab(){
		held = true;
	}
	public void drop(){
		held = false;
	}
	public void moveBy(int delta_x, int delta_y){
		x += delta_x;
		y += delta_y;
	}
	
	public void resizeBy(int delta_w, int delta_h){
		width += delta_w;
		height += delta_h;
	}

}
