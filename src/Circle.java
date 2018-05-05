import java.awt.Graphics;

public class Circle {

	double x;
	double y;
	double radius;
	int angle;

	public Circle(double x, double y, double radius, int angle){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
	}
	public void moveBy(int dx, int dy){
		x += dx;
		y += dy;
	}
	public void draw(Graphics g){
		double cosA = Lookup.cos[(int)angle];

		double sinA = Lookup.sin[(int)angle];

		g.drawOval((int)(x-radius), (int)(y-radius), (int) radius*2, (int) radius*2);
		g.drawLine((int)x, (int)y, (int)(x + radius*cosA), (int)(y + radius*sinA));
	}
	public void moveForwardBy(int distance)
	{
		x += distance * Lookup.cos[angle];
		y += distance * Lookup.sin[angle];
	}

	public void rotateLeftBy(int dA)
	{
		angle -= dA;

		if(angle < 0){
			angle += 360;
		}
	}

	public void rotateRightBy(int dA)
	{
		angle += dA;

		if(angle >= 360){  
			angle-= 360;
		}
	}
}
