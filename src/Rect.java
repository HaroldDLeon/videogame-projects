import java.awt.Graphics;

public class Rect {

	double x;
	double y;
	double width;
	double height;
	double vx = 0;
	double vy = 0;
	
	static final double g = 0.2;
	
	double ax = 0;
	double ay = g;
	
	
	boolean held = false;

	
	public Rect(double x, double y, double width, double height){
		this.x = 		x;
		this.y = 		y;
		this.width 	= 	width;
		this.height = 	height;
	}
	public void draw(Graphics g){
		g.drawRect((int) x, (int) y, (int) width,(int) height);
	}
	public void drawFull(Graphics g){
		g.fillRect((int) x, (int) y, (int) width, (int)height);
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
		vx += ax;
		vy += ay;
		
		x += vx;
		y += vy;
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
		width  += delta_w;
		height += delta_h;
	}
	public void resizeTo(double dw, double dh){
		height 	= dh;
		width 	= dw;
	}
	public void setVelocity(int velocity_x, int velocity_y){
		this.vx = velocity_x;
		this.vy = velocity_y;
	}
	public void setAcceleration(double acceleration_x, double acceleration_y){
		this.ax = acceleration_x;
		this.ay = acceleration_y;
	}
	public void modifyRect(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

}
