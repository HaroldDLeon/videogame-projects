import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Ball extends Rect{
	Image enemy_img    = Toolkit.getDefaultToolkit().getImage("../assets/Boss/bullet_enemy.png");
	Image friendly_img = Toolkit.getDefaultToolkit().getImage("../assets/Boss/bullet_friendly.png");
	
	boolean is_friendly;
	boolean registered;
	
	public Ball(int x, int y, boolean friendly){
		super(x, y, 20, 20);
		this.is_friendly = friendly;
	}
	public void draw(Graphics g){
		if(is_friendly){
			g.drawImage(friendly_img, (int)x-5, (int)y-5,  null);
		}
		else {
			g.drawImage(enemy_img, (int)x-5, (int)y-5,  null);
		}
//		super.draw(g);
	}
}
