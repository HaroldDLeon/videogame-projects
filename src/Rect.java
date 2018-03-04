import java.awt.*;

public class Rect {

	int x;
	int y;
	int width;
	int height;
	
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
	
	public void moveBy(int delta_x, int delta_y){
		x += delta_x;
		y += delta_y;
	}
	
	public void resizeBy(int delta_w, int delta_h){
		width += delta_w;
		height += delta_h;
	}
	 
}
