import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Animation {

	Image[] image;
	int current = 1;
	int delay;
	int duration;	
	int loop;
	boolean loops;
	Image empty = Toolkit.getDefaultToolkit().getImage("../assets/empty.png");


	public Animation(String location, String filename, int count, int duration){
		this.createAnimation(location, filename, count);
		this.duration = duration;
		this.loop = 0;
		delay = duration;
		this.loops = true;
	}
	public Animation(String location, String filename, int count, int duration, int loop){
		createAnimation(location, filename, count);
		this.duration = duration;
		this.loop = loop;
		delay = duration;
		this.loops = true;
	}
	public Animation(String location, String filename, int count, int duration, boolean loops){
		this.createAnimation(location, filename, count);
		this.duration = duration;
		this.loops = loops;
		this.delay = duration;
	}
	public void createAnimation(String location, String filename, int count ){
		image = new Image[count];
		for (int i = 1; i < count; i++) {
			if( i < 10) { image[i] = Toolkit.getDefaultToolkit().getImage(location+filename+"0"+i+".png"); 	}	
			else 		{ image[i] = Toolkit.getDefaultToolkit().getImage(location+filename+i+".png");		}
		}

	}
	public void draw(Graphics g) {
		Image image = this.nextImage();
		System.out.println(image);
		g.drawImage(image, 175, 700, null);
	}
	public Image nextImage(){
		if (!loops && current == image.length-1){
//			System.out.println("Value of current " + current + " Image's length: " + image.length);
//			int last = image.length-1;
			return image[current];
		}
		else{
			if (delay == 0 ){
				current++;
				if (current == image.length && loops) current = loop;
				delay = duration;
			}
			else{
				delay--;
			}
		}
		return image[current];
	}
	public Image stillImage(){
		return image[1];
	}
}
