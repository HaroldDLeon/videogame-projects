
public class UFO extends PolygonModel {

	public UFO(int x, int y, int angle) {
		super(x, y, angle);
	} 
	public int[][] structure_x (){
		final int[][] structure_x = {	// Structure of the Model is as follows:
				{0, 	20,		-20},
				{0, 	20,		-20},
		}; 
		return structure_x;
	}
	public int[][] structure_y (){
		final int[][] structure_y = {
				{30,	-20,	-20},
				{-30, 	 20,	 20},
		}; 
		return structure_y;
	}
}
