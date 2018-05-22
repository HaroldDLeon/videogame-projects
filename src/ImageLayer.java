import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer {
	
	int x = 0;
	int right_x;
	int left_x;
	int y = 0;
	int z;
	
	int width;
	
	Image image;
		int current = 0;
		
	public ImageLayer(String file, int z){
		image 	= Toolkit.getDefaultToolkit().getImage(file);
		this.width = 1600;
		this.right_x = width*z;
		this.left_x = -width*z;
		this.z = z;
	}
	public void draw(Graphics g){
		int center_delta = x/z;
		int right_delta = right_x/z ;
		int left_delta = left_x/z;
		
		if(center_delta > width) {
			this.x = -z*(width-10); 
		}
		else if (center_delta < -width) {
			this.x = z*(width-10);
			
		}
		if(right_delta < -width) {
			this.right_x = width*z;
		}
		else if ( 0 <= center_delta && center_delta <= 20) {
	
			this.right_x = width*z;
		}
		if (left_delta > width) {
			this.left_x = z*-width;
		}
		else if ( -20 <= center_delta && center_delta <= 0) {
			this.left_x = z*-width;
		}
		
		g.drawImage(image, center_delta , y, null);
		g.drawImage(image, right_delta , y, null);
		g.drawImage(image, left_delta , y, null);
	}
	 
	public void moveLeftBy(int dx){
		x -= dx;
		right_x -= dx;
		left_x -= dx;
		
		
	}
	public void moveRightBy(int dx){
		x += dx;
		right_x += dx;
		left_x += dx;
	}
	
	
}
