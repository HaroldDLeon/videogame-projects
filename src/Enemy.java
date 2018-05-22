import java.awt.*;

public class Enemy extends Rect {

	int x;
	int y;

	/* Enemy properties */
	int dx = 2;
	int health = 100;

	/* Visual Properties */
	Animation animation;

	public Enemy(int x, int y){
		super(x, y, 30, 30);		
		String location =  "../assets/Boss/";
		this.x = x;
		this.y = y;
		animation = new Animation(location, "enemy_", 5, 1, false);		
	}
	public void reduceHealth(int dh) {
		health -= dh;

	}
	public void draw(Graphics g){
		g.drawImage(animation.nextImage(),(int)super.x, (int)super.y, null);
//		super.draw(g);
	}
	public boolean shot_by(Ball ball) {
		return 	(
				(super.x 			< ball.x + ball.width)	&&
				(super.x + width 	> ball.x) 				&&
				(super.y 			< ball.y + ball.height)	&&
				(super.y + super.height > ball.y));
	}
	public void interact(int mx){
		//			if(mx-100 <= super.x  && super.x <= mx+100){
		//
		//			}
		//			else if(mx < super.x)	{
		if(mx < super.x)	{
			super.moveBy(-dx, 0);
		}
		else if (mx > super.x){
			super.moveBy(dx, 0);
		}
	}
}