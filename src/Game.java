import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	
	boolean lt_pressed  = false;
	boolean rt_pressed  = false;
	boolean up_pressed  = false;
	boolean dn_pressed  = false;
	
	boolean sp_pressed = false;
	
	boolean overlap 	= false;
	
	int mx;
	int my;
	
	Thread t;
	Graphics off_g;
	Image off_screen;
	
	Image image = Toolkit.getDefaultToolkit().getImage("C://Home//videogame-projects//src//gengar.gif");
	Animation anim = new Animation("C://Home//videogame-projects//src//tea//", 19, 3);
	
	Rect r 	= new Rect(50,50, 0, 0);
	Rect r1 = new Rect(400,400, 350, 100);
	Rect ball = new Rect(10,10, 25, 25);

	Tank[] tank_array = new Tank[4];
	Tank[] active_tanks = new Tank[4];
	
	BadTank bad_tank = new BadTank(900, 500, 40);
	
//	Rect shell = new Rect(-1000, 0, 2, 2);
	
	UFO alien = new UFO(50,50, 0);
//	UFO[] tank_array = {alien};
	
	String w_filename = "C://Home//videogame-projects//src//media//kid//Walk_";
	String[] w_action = {"L", "R"};
	Sprite kid = new Sprite(100, 20, w_filename, w_action, 16, 3 );
	String j_filename = "C://Home//videogame-projects//src//media//kid//JMP_";
	String[] j_action = {"L", "R"};
	Sprite jumper = new Sprite(100, 20, j_filename, j_action, 4, 5);
	
	Rect [] shells = new Rect[10000];
	int current_shell = 0;
	int delay = 0;
		
	public void init(){
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		t = new Thread(this);
		t.start();
		for (int i = 0; i < tank_array.length; i++) {
			tank_array[i] = new Tank(i*100 + 100, 100, 90);
		}
		for (int i = 0; i < shells.length; i++) {
			shells[i]= new Rect(-1000, 0, 2, 2);
		} 
		
//		tank_array[0] = new Tank(100,  100,   90);
//		tank_array[1] = new Tank(200,  100,   90);
//		tank_array[2] = new Tank(300,  100,   90);
//		tank_array[3] = new Tank(400,  100,   90);

		
		this.off_screen = createImage(1575, 741);
		this.off_g 		= off_screen.getGraphics();
		ball.setVelocity(3, 5);
		kid.setStill(5);
	}
	
	@SuppressWarnings("static-access")
	public void run() {
		try{
			tank_array[0].selected = true;
		} catch(Exception e){}
		while(true){
			
			bad_tank.moveTowards(tank_array[0]);
			
			for (int i = 0; i < tank_array.length; i++) {
				if (tank_array[i].selected){
					//				if (lt_pressed)	{ tank_array[i].rotateLeftBy(1);		}
					//				if (rt_pressed)	{ tank_array[i].rotateRightBy(1);		}
					//				if (up_pressed)	{ tank_array[i].moveForwardBy(1);		}
					//				if (dn_pressed)	{ tank_array[i].moveForwardBy(-1); 		}
					if (lt_pressed)	{ tank_array[i].rotateLeftBy(3);		}
					if (rt_pressed)	{ tank_array[i].rotateRightBy(3);		}
					if (up_pressed)	{ tank_array[i].moveForwardBy(3);		}
					if (dn_pressed)	{ tank_array[i].moveForwardBy(-3); 		}
					if (sp_pressed) {
						if(delay > 5){
							tank_array[i].shoot(shells[current_shell]);
							current_shell += 1;
							if (current_shell == shells.length) current_shell = 0;
							delay = 0;
						}
						delay += 1;
					}
					if (bad_tank.shoot(shells[current_shell], tank_array[0])){
						current_shell += 1;
						delay += 1; 
					}
				}
			}
			shells[current_shell].move();
			for (int i = 0; i < shells.length; i++) {
				shells[i].move();
			}
			
//			if (lt_pressed)	{ kid.moveLeftBy(1);		}
//			if (rt_pressed)	{ kid.moveRightBy(1);		}
//			if (up_pressed)	{ jumper.moveUpBy(1);		}
//			if (dn_pressed)	{ jumper.moveDownBy(1); 	}
			if (lt_pressed)	{ ball.moveBy(-1, 0);		}
			if (rt_pressed)	{ ball.moveBy(1, 0);		}
			if (up_pressed)	{ ball.moveBy(0, -1);		}
			if (dn_pressed)	{ ball.moveBy(0, 1);		}
			
			repaint();
			try {
				t.sleep(1000/60);
			} catch(Exception e){}
		}
	}
	public void update(Graphics g){
		
		off_g.clearRect(0, 0, 21*75, 9*75);
		
		paint(off_g);
		
		g.drawImage(off_screen, 0, 0, null);	
		
	}
	public void paint(Graphics g){
		this.setSize(21*75, 9*75);
		r.draw(g);
//		shell.draw(g);
		for (int i = 0; i < shells.length; i++) {
			shells[i].draw(g);
		}
		bad_tank.draw(g);
//		kid.draw(g);
//		jumper.draw(g);
//		ball.draw(g);		
//		r1.draw(g);
//		alien.draw(g);

		for(int i = 0; i < tank_array.length; i++){
			tank_array[i].draw(g);;
		}
		g.drawImage(anim.nextImage(), 21*75-496, 9*75-320, this);		
//		if (overlap)		g.drawString("Rectangle overlaps", mx, my);
//		else 				g.drawString("------------------", mx, my);

	}
	/* The Key Press events are handled on a way where the OS calls this functions when key presses are detected. */
	@SuppressWarnings("static-access")
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		if (code == e.VK_LEFT) 	{ lt_pressed = true; }
		if (code == e.VK_RIGHT)	{ rt_pressed = true; }
		if (code == e.VK_UP)	{ up_pressed = true; }
		if (code == e.VK_DOWN)	{ dn_pressed = true; }
		if (code == e.VK_SPACE)	{ sp_pressed 	= true; }
				
	}
	
	@SuppressWarnings("static-access")
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT) 	{ lt_pressed = false; }
		if (code == e.VK_RIGHT)	{ rt_pressed = false; }
		if (code == e.VK_UP)	{ up_pressed = false; }
		if (code == e.VK_DOWN)	{ dn_pressed = false; }
		if (code == e.VK_SPACE)	{ sp_pressed 	 = false; }
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		r = new Rect(mx, my, 0, 0);
		
		if(r.contains(mx,my)) 	r.grab();
		
	}

	public void mouseReleased(MouseEvent e) {
		r = new Rect(0,0,0,0);
	}

	public void mouseDragged(MouseEvent e ) {
		int temp_x = e.getX();
		int temp_y = e.getY();
		
		int dx = temp_x - this.mx;
		int dy = temp_y - this.my;
		
		r.resizeBy(dx, dy);
		
		this.mx = temp_x;
		this.my = temp_y;
		overlap = r.overlaps(r1);
//		Arrays.fill(active_tanks, null);
		for (int i = 0; i < tank_array.length; i++) {
			if(tank_array[i].contained(r)){ 
//				active_tanks[i] = tank_array[i];
				tank_array[i].selected = true;
			}
			else{
				tank_array[i].selected = false;
			}
		}
		
		
		/*
		int temp_x = e.getX();
		int temp_y = e.getY();
		
		int dx = temp_x - this.mx;
		int dy = temp_y - this.my;
		
		if (r.held) r.moveBy(dx, dy);
		this.mx = temp_x;
		this.my = temp_y;
		
		overlap = r.overlaps(r1); 
		*/
		
	}

	public void mouseMoved(MouseEvent e) {
//		mx = e.getX();
//		my = e.getY();
//		mouse_inside = false;
//		for(int i = 0; i < tank_array.length; i++){
//			mouse_inside = tank_array[i].contains(mx, my);
//			if(mouse_inside) break;
//		}
		
	}
}