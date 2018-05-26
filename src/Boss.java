import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Boss extends Rect {

	int health = 100;
	int type;
	Animation animation;

	static final int ground = 0;
	static final int walker = 1;

	/* Walker Properties */ 
	int velocity = 5;
	int direction = -1;
	int pose = LEFT;
	static final int LEFT = 0;
	static final int RIGHT = 1;
	boolean is_dead = false;

	/* Images */
	String location =  "../assets/Boss/";
	Image ground_img = Toolkit.getDefaultToolkit().getImage("../assets/map/game_overlay.png");
	Animation explosion = new Animation(location, "explode_", 10, 45, false);
	Animation[] walker_a = {
			new Animation(location, "walker_L", 6, 10, 1),
			new Animation(location, "walker_R", 6, 10, 1),
	};

	Rect health_bar;
	Rect health_container;

	public Boss(int x, int y){
		super(x+50, y+33, 150, 165);		
		this.x = x;
		this.y = y;
		animation = new Animation(location, "ground_", 6, 3, 1);		
	}
	public Boss(int x, int y, int type){
		super(x, y, 150, 165);
		this.type = type;
		if(type == ground){
			modifyRect(x+165, y, 150, 173);
			//			this.health = 150;
			animation = new Animation(location, "ground_", 6, 3, 1);
			health_container 	= new Rect(x+190,y-10, 100, 10);
		}
		else if(type == walker){
			modifyRect(x+40, y+50, 113, 125);
			//			this.health = 75;	
			health_container 	= new Rect(x+35,y-10, 100, 10);
		}	
		health = 100;
		health_bar		= new Rect(health_container.x,health_container.y ,health_container.width,health_container.height);
	}
	public void reinitialize() {
		this.health = 100;
		if		(type == ground) { modifyRect(1455, 498, 150, 173);}
		else if	(type == walker) { modifyRect(1330, 548, 113, 125);}
		//		x = 1340;
		//		y = 531;

	}
	public void interact(){
		if(type == walker){
			this.moveBy(velocity*direction, 0);
			health_bar.moveBy(velocity*direction, 0);
			health_container.moveBy(velocity*direction, 0);
		}

		if (super.x <= -200 || super.x >= 1700){ 
			direction = -direction;
			if(pose == 0){pose = 1;}
			else		{ pose = 0;}
		}
	}
	public void reduceHealth(int dh) {
		health -= dh;
		int dw = (int) (health_container.width/100)*dh;
		health_bar.resizeBy(-dw, 0);
	}
	public void moveBy(){

	}
	public boolean shoot(Ball bullet, int delay){
		if (delay < 1 && type == ground){
			int d = 10;
			bullet.setLocation((int) x, (int) y);
			bullet.setVelocity(-d, 0);
			bullet.registered = false;
			return true;
		}
		else{
			return false;
		}
	}
	public boolean shot_by(Ball ball) {
		return 	(
				(x 			< ball.x + ball.width)	&&
				(x + width 	> ball.x) 				&&
				(y 			< ball.y + ball.height)	&&
				(y + height > ball.y));
	}
	public void draw(Graphics g){
		if(health <= 0 || is_dead){
			g.drawImage(explosion.nextImage(),(int)x, (int)y, null);
		}
		if (type == walker){
			g.drawImage(walker_a[pose].nextImage(),(int)x, (int)y, null);
		}
		else {
			g.drawImage(animation.nextImage(),(int)x-50, (int)y-25, null);
		}
//		super.draw(g);
		health_container.draw(g);
		health_bar.drawFull(g);
	}
}
