import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

// Assets used from https://www.graphictoon.com/product-page/limbo-style-art

@SuppressWarnings("serial")
public class GameBackup extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	/* Game timers */
	private static final int cx 			= 10;
	private static final int kshot_delay 	= 10;
	private static final int bshot_delay 	= 50;
	private static int 		 dying_delay 	= 300;
	private static int 		 boss_delay 	= 1000;
	

	boolean lt_pressed  = false;
	boolean rt_pressed  = false;
	boolean up_pressed  = false;
	boolean dn_pressed  = false;
	boolean jp_pressed 	= false;
	boolean sp_pressed 	= false;
	boolean overlap 	= false;

	/* Thread variables */
	Thread t;
	Graphics off_g;
	Image off_screen;

	int mx;
	int my;

	/* Window's Variables */
	int window_width = 1600;
	int window_height = 900;	
	boolean beginning_screen = false;
	boolean ending_screen	 = false;

	/*  Game Variables  */
	int timer;
	int cb 			= 0;
	int c_shell 	= 0;
	int boss_timer 	= 0;
	int shell_timer = 0;

	boolean game_paused = false;
	boolean boss_drawn 	= false;

	Kid boy 	= new Kid(130, 483);
	Font timer_font = new Font("Roboto Light", Font.PLAIN, 36);
	Font score_font = new Font("Roboto Light", Font.PLAIN, 72);

	Boss[] boss 	= new Boss[7];
	Enemy[] lacays 	= new Enemy[15];
	Ball[] friendly_bullet 		= new Ball[10];
	Ball[] enemy_bullet 		= new Ball[10];

	Image game_fg 	= Toolkit.getDefaultToolkit().getImage("../assets/map/game_overlay.png");

	/* Game Intro */
	Rect start_btn	= new Rect(window_width/2 -200 ,window_height/2 -50, 400, 100);
	Image start_img = Toolkit.getDefaultToolkit().getImage("../assets/Title/start_btn.png");
	Image intro_fg 	= Toolkit.getDefaultToolkit().getImage("../assets/Title/intro_fg.png");
	Image intro_bg 	= Toolkit.getDefaultToolkit().getImage("../assets/Title/intro_bg.png");

	/* In-Game */
	Map game_map = new Map();
	Image pause_img = Toolkit.getDefaultToolkit().getImage("../assets/map/pause.png");
	Image play_img 	= Toolkit.getDefaultToolkit().getImage("../assets/map/play.png");
	Rect pause_btn	= new Rect(700, 12, 50, 50);
	ImageLayer base_layer	= new ImageLayer("../assets/map/layer_3.png", 	1);

	/* Testing variables and objects */
	Image testing_img = Toolkit.getDefaultToolkit().getImage("../assets/Boss/bullet.png");
	Boss boss1 	= new Boss(1290, 498, 1);
	Rect base	= new Rect(0, 672, window_width, window_height);
	

	public void init(){
		initializeArrays();
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		t = new Thread(this);
		t.start();

		this.off_screen = createImage(window_width, window_height);
		this.off_g 		=   off_screen.getGraphics();
	}

	@SuppressWarnings("static-access")
	public void run() {
		while(true){
			if(!beginning_screen && !game_paused && !ending_screen){
				if(!boy.isDead()){
					if 	(lt_pressed){	this.moveMap(cx); 	}
					if 	(rt_pressed){	this.moveMap(-cx); 	}
					if 	(up_pressed && !boy.jumping){ 	
						boy.jump();
					}
					if	(dn_pressed){	boy.reduceHealth(10);		}
					if	(sp_pressed){
						if(shell_timer > kshot_delay){
							boy.shoot(friendly_bullet[c_shell]);
							c_shell++;
							if (c_shell== friendly_bullet.length) c_shell = 0;
							shell_timer = 0;
						}
						shell_timer++;
					}	
					/* Make the boss shoot. */ 
					if (boss[cb].shoot(enemy_bullet[c_shell],  boss_timer)){
						c_shell++;
						if (c_shell== enemy_bullet.length) c_shell = 0;
					}
					boss_timer++;
					if (boss_timer > bshot_delay ){
						boss_timer = 0;
					}
					if (boy.jumping){ 	boy.move();	}
					timer += 1;
					if( boy.overlaps(boss[cb]) && boss_drawn){
						boy.reduceHealth(10);
					}
					if (!boss_drawn && boss_delay <= 0){
						boss_drawn = true;
						boss_delay = 600;
					}
					else{
						boss_delay--;
					}
				}
				for (int i = 0; i < enemy_bullet.length; i++) {
					if(boy.shot_by(enemy_bullet[i]) && !enemy_bullet[i].registered && boss_drawn) {
						boy.reduceHealth(10);
						enemy_bullet[i].registered = true;
					}
					if(boss[cb].shot_by(friendly_bullet[i]) && !friendly_bullet[i].registered && boss_drawn) {
						boss[cb].reduceHealth(10);
//						System.out.println("Current health:"+boss[cb].health);
						friendly_bullet[i].registered = true;
						if(boss[cb].health <= 0) {
							boss[cb].reinitialize();
							boss_drawn = false;
							cb++;
							if(cb == boss.length) { cb = 0;}
						}
					}
				}
				if(boy.is_dead){
					dying_delay--;
					if( dying_delay == 0)ending_screen = true;	
				}
				moveBullets();
				moveEnemy();
			}
			System.out.println(boss_delay);
			repaint(); 	
			try {
				t.sleep(15);
			} catch(Exception e){}
		}
	}
	public void moveBullets(){
		for (int i = 0; i < friendly_bullet.length; i++) {
			friendly_bullet[i].move();
			if(boss_drawn){enemy_bullet[i].move();}
		}
	}
	private void initializeArrays() {
		int rand_type;
		for(int i = 0; i < boss.length; i++){
			rand_type  = new Random().nextInt(2);
			boss[i] = new Boss(1290, 498, rand_type);
		}
		for(int i = 0; i < lacays.length; i++){
			lacays[i] = new Enemy(-100, -100);
		}
		for (int i = 0; i < friendly_bullet.length; i++) {
			enemy_bullet[i]		= new Ball(-1000, 0, false);
			friendly_bullet[i]	= new Ball(-1000, 0, true);
			
		}	
	}
	public void update(Graphics g){
		off_g.clearRect(0, 0, window_width, window_height);
		paint(off_g);
		g.drawImage(off_screen, 0, 0, null);

	}
	public void paint(Graphics g){
		this.setSize(window_width, window_height);
		g.setColor(Color.white);
		g.setFont(timer_font);

		if (beginning_screen){
			this.drawIntro(g);
		}
		else if(ending_screen){
			this.drawEnding(g);
		}
		else if(!beginning_screen){
			this.drawMap(g);
		}
	}

	public void moveMap(int dx){
		if (dx < 0){ 
			game_map.moveRightBy(dx);
			base_layer.moveRightBy(dx);
			boy.moveRightBy(6);
		}
		else{
			game_map.moveRightBy(dx);
			base_layer.moveRightBy(dx);
			boy.moveLeftBy(6);
		}
	}
	public void drawIntro(Graphics g){
		g.drawImage(intro_bg, 0, 0, null);
		g.drawImage(intro_fg, 0, 0,  null);
		g.drawImage(start_img, (int)start_btn.x, (int)start_btn.y, (int)start_btn.width, (int)start_btn.height, null);
		//		start_btn.draw(g);
	}
	public void drawMap(Graphics g){
		game_map.draw(g);
		boy.draw(g);
		if(boss_drawn)	{ boss[cb].draw(g);}
		base_layer.draw(g);
		
		String time_passed = "Time: " + Integer.toString((int)(timer/60));
		g.drawString(time_passed, 750, 50);
		
		if(!game_paused){ g.drawImage(pause_img, 700, 12, 50, 50, null);	}
		else			{ g.drawImage(play_img,  700, 12, 50, 50, null);	}
		
		g.drawImage(game_fg, 0,  0,1600, 900,  null);

		/* Testing Variables TODO: Remove after done*/
		for (int i = 0; i < friendly_bullet.length; i++) {
			if(boss_drawn) {enemy_bullet[i].draw(g);}
			friendly_bullet[i].draw(g);
			
		}
//		base.draw(g);
//		g.drawImage(testing_img, (window_width/2 -200) ,window_height/2 -50, 24, 24,  null);

	}
	public void drawEnding(Graphics g){
		g.drawImage(intro_bg, 0, 0, null);
		g.drawImage(intro_fg, 0, 0,  null);
		String final_score = "Game Over: " + Integer.toString((int)(timer/60));
		g.setFont(score_font);
		g.setColor(Color.BLACK);
		g.drawString(final_score, 590, 450);

		g.drawImage(game_fg, 0,  0,1600, 900,  null);
	}
	public void moveEnemy(){
		if( boss_drawn ){	boss[cb].interact(); }
	}
	@SuppressWarnings("static-access")
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == e.VK_LEFT) 	{ lt_pressed = true; }
		if (code == e.VK_RIGHT)	{ rt_pressed = true; }
		if (code == e.VK_UP)	{ up_pressed = true; }
		if (code == e.VK_DOWN)	{ dn_pressed = true; }
		if (code == e.VK_SPACE)	{ sp_pressed = true; }
	}
	@SuppressWarnings("static-access")
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == e.VK_LEFT) 	{ lt_pressed = false; }
		if (code == e.VK_RIGHT)	{ rt_pressed = false; }
		if (code == e.VK_UP)	{ up_pressed = false; }
		if (code == e.VK_DOWN)	{ dn_pressed = false; }
		if (code == e.VK_SPACE)	{ sp_pressed = false; }

	}
	public void keyTyped(KeyEvent e) {
	}
	public void mouseClicked(MouseEvent e) {	
		int mx = e.getX();
		int my = e.getY();

		if(start_btn.contains(mx, my)){ beginning_screen = false;}
		else if(pause_btn.contains(mx, my)){ game_paused = !game_paused;}
	}
	public void mouseEntered(MouseEvent e) {	
	}
	public void mouseExited(MouseEvent e) {	}
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

		System.out.println("mx: " + Integer.toString(mx) +" my: " + Integer.toString(my)); 
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e ) {
	}
	public void mouseMoved(MouseEvent e) {}

}