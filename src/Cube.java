
public class Cube extends PolygonModel3D {
	public Cube(double x, double y, int width, int height){
		super(x,y,width, height);
	}
	public int[][] structure_x (){
		final int[][] structure_x = {
				{ 50, 	50, 	-50, 	-50},
				{ 50, 	50, 	-50, 	-50},
				{ 50, 	50, 	 50, 	 50},
				{ -50, 	-50, 	-50, 	-50},
		}; 
		return structure_x;
	}
	public int[][] structure_y (){
		final int[][] structure_x = {	
				{ 50, 	-50, 	-50, 	50},
				{ 50, 	-50, 	-50, 	50},
				{ 50, 	-50, 	-50, 	50},
				{ 50, 	-50, 	-50, 	50},
		}; 
		return structure_x;
	}
	public int[][] structure_z (){
		final int[][] structure_z = {
				{  50,	 50,	 50, 	 50},
				{ -50, 	-50, 	-50, 	-50},
				{ 50, 	50, 	-50, 	-50},
				{ 50, 	50, 	-50, 	-50},

		}; 
		return structure_z;
	}
}
