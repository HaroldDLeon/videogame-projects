import java.awt.*;

public class Tank {
	
	double x;
	double y;
	int A;
	
	static final int radius = 35;
	static final int[] body_x 			=	{ 35, 35, -35, -35};
	static final int[] body_y 			=	{-25, 25,  25, -25};
	
	static final int[] turret_x 		=	{ 05, 05, -05, -05};
	static final int[] turret_y 		= 	{-12, 12,  12, -12};
	
	static final int[] gun_x 			= 	{30, 30, 05, 05};
	static final int[] gun_y 			= 	{-2,  2,  2, -2};
	
	static final int[] left_tire_x 		=  	{27, 27, -27, -27};
	static final int[] left_tire_y 		=  	{25, 30,  30,  25};
	
	static final int[] right_tire_x 	=  	{ 27,  27,  -27,  -27};
	static final int[] right_tire_y 	=  	{-25, -30,  -30,  -25};
	
	public Tank(int x, int y, int angle){
		this.x = x;
		this.y = y;
		this.A = angle;
	}
	
	public void drawPart(int[] array_x, int[] array_y, Graphics g){
		
		double sinA = Lookup.sin[A];
		double cosA = Lookup.cos[A];
		
		int[] xp = new int[4];
		int[] yp = new int[4];
		for(int i = 0; i <4; i++){
			xp[i] = (int) (array_x[i] * cosA - array_y[i] * sinA+ x);
			yp[i] = (int) (array_x[i] * sinA + array_y[i] * cosA+ y);
		}
		g.drawPolygon(xp, yp, 4);
	}
	public void draw(Graphics g){

		this.drawPart(body_x, 		body_y, 		g);
		this.drawPart(turret_x, 	turret_y, 		g);
		this.drawPart(gun_x, 		gun_y, 			g);
		this.drawPart(left_tire_x, 	left_tire_y, 	g);
		this.drawPart(right_tire_x, right_tire_y, 	g);

	}
	public void moveBy(int delta_x, int delta_y){
		x += delta_x;
		y += delta_y;
	}
	public void moveForwardBy(int d){
		x += d * Math.cos(A * Math.PI/180);
		y += d * Math.sin(A * Math.PI/180);
	}
	public void rotateLeftBy(int delta_a){
		A -= delta_a;
		if (A < 0)		{ A += 360;}
	}
	public void rotateRightBy(int delta_a){
		A += delta_a;
		if (A >= 360)	{ A -= 360;}

	}
	public boolean contains(int mx, int my){
		double distance_squared = (mx - x )*(mx-x) + (my -y)*(my-y);
		return (distance_squared < radius * radius);
		
	}
}
