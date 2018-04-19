 import java.awt.Graphics;

public abstract class PolygonModel {
	
	int angle;
	double x;
	double y;
	Rect container;
	
	int radius = 35;
	boolean selected;
	
	final int[][] structure_x = structure_x();
	final int[][] structure_y = structure_y();
	
	public abstract int[][] structure_x();
	public abstract int[][] structure_y();
	
			
	public PolygonModel(double x, double y, int angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
	}

	public void drawPart(int[] array_x, int[] array_y, Graphics g){

		double sinA = Lookup.sin[angle];
		double cosA = Lookup.cos[angle];

		int[] xp = new int[4];
		int[] yp = new int[4];
		for(int vertice = 0; vertice < array_x.length; vertice++){
			xp[vertice] = (int) (array_x[vertice] * cosA - array_y[vertice] * sinA+ x);
			yp[vertice] = (int) (array_x[vertice] * sinA + array_y[vertice] * cosA+ y);
		}
		g.drawPolygon(xp, yp, array_x.length);
	}
	public void draw(Graphics g){

		for(int polygon = 0; polygon < structure_x.length; polygon++){
			this.drawPart(structure_x[polygon], structure_y[polygon], g);
		}
		container = new Rect((int)x-30, (int)y-35, 60, 70);
//		if (selected)	{ container.draw(g);}
	}

	public void moveBy(int delta_x, int delta_y){
		x += delta_x;
		y += delta_y;
	}
	public void moveForwardBy(int d){
		x += d * Math.cos(angle * Math.PI/180);
		y += d * Math.sin(angle * Math.PI/180);
	}
	public void rotateLeftBy(int delta_a){
		angle -= delta_a;
		if (angle < 0)		{ angle += 360;}
	}
	public void rotateRightBy(int delta_a){
		angle += delta_a;
		if (angle >= 360)	{ angle -= 360;}
	}
	public boolean contains(int mx, int my){
		double distance_squared = (mx - x )*(mx-x) + (my -y)*(my-y);
		return (distance_squared < radius * radius);

	}
	public boolean contained(Rect r1){
		return r1.overlaps(container);
	}

}
