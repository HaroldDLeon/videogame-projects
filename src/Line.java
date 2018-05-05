import java.awt.Graphics;

public class Line {
	int x0;
	int y0;
	
	int x1;
	int y1;
	
	double x_normal;
	double y_normal;
	
	public Line(int x0, int y0, int x1, int y1){
		this.x0 = x0;
		this.y0 = y0;
		
		this.x1 = x1;
		this.y1 = y1;
		
		double delta_x = x1 - x0;
		double delta_y = y1 - y0;
		
		double magnitude = Math.sqrt(delta_x*delta_x + delta_y*delta_y );
		
		double xu = delta_x/magnitude;
		double yu = delta_y/magnitude;
		
		x_normal = -yu;
		y_normal = xu;
	}
	
	public double distanceTo(double x, double y){
		return x_normal * (x - x0) + y_normal * (y - y0);
	}
	
	public void draw(Graphics g){
		g.drawLine(x0, y0, x1, y1);
	}
}
