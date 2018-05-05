import java.awt.*;

public abstract class PolygonModel3D {
	int angle;
	
	double x;
	double y;
	double z;
	
	final int x_origin = 787;
	final int y_origin = 337;
	Rect container;
	
	int radius = 35;
	boolean selected;
	
	final int[][] structure_x = structure_x();
	final int[][] structure_y = structure_y();
	final int[][] structure_z = structure_z();
	
	public abstract int[][] structure_x();
	public abstract int[][] structure_y();
	public abstract int[][] structure_z();
	
			
	public PolygonModel3D (double x, double y, double z, int angle){
		this.x = x;
		this.y = y;
		this.z = z;
		this.angle = angle;
	}

	public void drawPart(int[] array_x, int[] array_y, int[] array_z, Graphics g){
		final int d = 500;
		double sinA = Lookup.sin[angle];
		double cosA = Lookup.cos[angle];

		int[] xp = new int[4];
		int[] yp = new int[4];
//		int[] zp = new int[4];
		
		for(int vertice = 0; vertice < array_x.length; vertice++){
 
			// Rotate Vertex
			double xr = array_x[vertice] * cosA - array_y[vertice]  * sinA;
			double yr = array_y[vertice] * sinA + array_y[vertice]  * cosA;
			double zr = array_z[vertice] ;
			
			// Translate Vertex
			double xt = array_x[vertice] + x;
			double yt = array_y[vertice] + y;
			double zt = array_z[vertice] + z;
			
			// 3D Perspective Transformation.
			xp[vertice] = (int) (d * xt/ zt) + x_origin; // * cosA - array_y[vertice] * sinA+ x);
			yp[vertice] = (int) (d * yt/ zt) + y_origin; // * sinA + array_y[vertice] * cosA+ y);
//			zp[vertice] = (int) (d * array_z[vertice] * sinA + array_y[vertice] * cosA+ y);
			
		
		}
		g.drawPolygon(xp, yp, array_x.length);
	}
	public void draw(Graphics g){

		for(int polygon = 0; polygon < structure_x.length; polygon++){
			this.drawPart(structure_x[polygon], structure_y[polygon], structure_z[polygon],  g);
		}
		container = new Rect((int)x-30, (int)y-35, 60, 70);
//		if (selected)	{ container.draw(g);}
	}

	public void moveBy(int delta_x, int delta_y, int delta_z){
		x += delta_x;
		y += delta_y;
		z += delta_z;
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
