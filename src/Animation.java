import java.awt.Image;
import java.awt.Toolkit;


public class Animation {
	Image[] image;
	int current = 1;
	int delay;
	int duration;
	
	
	public Animation(String filename, int count, int duration){
		image = new Image[count];
		
		for (int i = 1; i < count; i++) {
			image[i] = Toolkit.getDefaultToolkit().getImage(filename+i+".png");
			
		}
		this.duration = duration;
		delay = duration;
	}
	public Image nextImage(){
		if (delay == 0 ){
			current++;
			if (current == image.length) { current = 1;}	// If you have a  position for 0, change current = 0
			delay = duration;
		}
		else{
			delay--;
		}
		return image[current];
	}
	public Image stillImage(){
		return image[1];
	}
	
}
