public class Tank extends PolygonModel {

	public Tank(double x, double y, int angle){
		super(x,y,angle);
	}
	public int[][] structure_x (){
		final int[][] structure_x = {	// Structure of the Model is as follows:
				{ 35, 	35, 	-35, 	-35},	// Body
				{ 05, 	05, 	-05, 	-05},	// Turret
				{ 30, 	30,	 	 05,  	 05},	// Gun
				{ 27, 	27, 	-27, 	-27},	// Left Tire
				{ 27, 	27,	 	-27, 	-27}	// Right Tire
		}; 
		return structure_x;
	}
	public int[][] structure_y (){
		final int[][] structure_y = {
				{-25,	25,		25, 	-25},
				{-12, 	12,  	12, 	-12},
				{-02,  	02,   	02, 	 -2},
				{ 25, 	30,   	30,  	 25},
				{-25,  -30,    -30,  	-25}
		}; 
		return structure_y;
	}
	public void shoot(Rect shell){
		int d = 5;
		shell.setLocation((int) x, (int) y);
		int velocity_x = (int) (d * Math.cos(A * Math.PI/180));
		int velocity_y = (int) (d * Math.sin(A * Math.PI/180));
		shell.setVelocity(velocity_x, velocity_y);
	}
}
