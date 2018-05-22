import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Kid extends Rect {
	
	/* Poses */
	static final int LEFT   = 0;
	static final int RIGHT  = 1;
	static final int UP_L   = 2;
	static final int UP_R   = 3;
	static final int DIE_L 	= 4;
	static final int DIE_R	= 5;
	static final int DOWN 	= 6;
	
	int pose = LEFT;
	int previous_pose = LEFT;
	
	/* Properties */	
	static final double gravity  = 0.2;
	int health 		= 100;
	double radius_y = 66;
	double radius_x = 32.5;

	boolean moving 	= false;
	boolean jumping = false;
	boolean selected= false;
	boolean is_dead;
	
	Animation[] animation;
	String location =  "../assets/kid/";
	
	Image profile = Toolkit.getDefaultToolkit().getImage("../assets/kid/profile.png"); 
	Rect health_container = new Rect(190, 20, 300, 35);
	Rect health_bar	= new Rect(health_container.x,health_container.y ,health_container.width,health_container.height);
	
	public Kid(int x, int y)	{
		super(x+95, y+60, 65, 127);			
		
		animation = new Animation[7];
		String[] action = {"Walk_L", "Walk_R","JMP_L", "JMP_R","Die_L", "Die_R", "Dash_L", "Dash_R", };
		
		animation[0] = new Animation(location, action[0], 16, 1, 9);
		animation[1] = new Animation(location, action[1], 16, 1, 9);
		animation[2] = new Animation(location, action[2],  4, 1);
		animation[3] = new Animation(location, action[3],  4, 1);
		animation[4] = new Animation(location, action[4],  8, 7, false);
		animation[5] = new Animation(location, action[5],  8, 7, false);
		
		super.setAcceleration(0, 0);
		super.setVelocity(0, 0);	
	}
	
	public boolean hasMoved(){
		return jumping;
	};
	
	public void move(){
//		if (669 <= y+height  && y+height >= 672){
		if (y +height > 672 ){
			limit();
			this.jumping = false;
			pose = previous_pose;
		}
		super.move();
	}
	public void moveDownBy(int dy)	{
		super.moveBy(0, dy);
		pose = DOWN;
	}
	public void moveLeftBy(int dx)	{
		super.moveBy(-dx,  0);
		moving = true;
		pose = LEFT;
	}
	public void moveRightBy(int dx)	{
		super.moveBy(dx,  0);
		moving = true;
		pose = RIGHT;	
	}
	public void limit(){
		if (x <= 100 ){
			x = 100;
		}
		else if (x >= 1400){
			x = 1400;
		}
		if (super.y + super.height > 672 ){
			y = 543;
			jumping = false;
			pose = previous_pose;
		}
	}
	public void moveUpBy(int dy){
		super.y -= dy;
		moving = true;		
	}
	public void jump(){
		int d = 10;
		previous_pose = pose;
		
		if		(pose == LEFT)	{ pose = UP_L;	}
		else if (pose == RIGHT) { pose = UP_R;	}
		
		int velocity_x = (int) (d * 0);
		int velocity_y = (int) (d * -1);
		
		super.setAcceleration(0, gravity);
		super.setVelocity(velocity_x, velocity_y);
		
		this.jumping = true;
	}
	public void stop() {
		super.setVelocity(0, 0);
		super.setAcceleration(0, 0);
		jumping = false;
		pose = LEFT;		
	}
	public void reduceHealth(int dh) {
		health -= dh;
		int dw = (int) (health_container.width/100)*dh;
		health_bar.resizeBy(-dw, 0);
		if (health <= 0){
			if (pose == LEFT){
				pose = DIE_L;	
			}
			else{
				pose = DIE_R;
			}
			moving= true;
			is_dead = true;
		}
	}
	public boolean is_overlaping(Rect rectangle){
		return super.overlaps(rectangle); 
	}
	public void draw(Graphics g)	{
		limit();
		int draw_x = (int) x-95;
		int draw_y = (int) y-60;
		
		if		(moving )	{g.drawImage(animation[pose].nextImage(),  draw_x, draw_y, null);}
		else if (is_dead)	{g.drawImage(animation[DIE_R].nextImage(), draw_x, draw_y, null);}
		else{			
			g.drawImage(animation[pose].stillImage(), (int)x-95, (int)y-60, null);
			super.setAcceleration(0,  gravity);
		}
		moving = false;
		
		health_container.draw(g);
		health_bar.drawFull(g);
		g.drawImage(profile, (int)health_container.x-30, (int)health_container.y-20, null);
		
	}
	public boolean isDead(){
		return is_dead;
	}

	public void shoot(Ball ball) {
		int d = 10;
		int velocity_x;
		int velocity_y;
		if(pose == LEFT || pose == UP_L){
			velocity_x = (int) (d * -1);
			velocity_y = (int) (d * 0);
		}
		else {
			velocity_x = (int) (d * 1);
			velocity_y = (int) (d * 0);
		}		
		ball.setLocation((int)x+20, (int)y+30);
		ball.setVelocity(velocity_x, velocity_y);
		ball.registered = false;
	}

	public boolean shot_by(Ball ball) {
		return 	(
				(x 			< ball.x + ball.width)	&&
				(x + width 	> ball.x) 				&&
				(y 			< ball.y + ball.height)	&&
				(y + height > ball.y));
	}

}