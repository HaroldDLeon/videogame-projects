import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	
	boolean lt_pressed  = false;
	boolean rt_pressed  = false;
	boolean up_pressed  = false;
	boolean dn_pressed  = false;
	
	boolean overlap 	= false;
	
	int mx;
	int my;
	
	Thread t;
	
	Image image = Toolkit.getDefaultToolkit().getImage("C://Home//videogame-projects//src//gengar.gif");
	Animation anim = new Animation("C://Home//videogame-projects//src//tea//", 19, 3);
	
	Rect r 	= new Rect(50,50, 0, 0);
	Rect r1 = new Rect(400,400, 350, 100);

	Tank[] tank_array = new Tank[4];
//	Tank[] active_tanks = new Tank[4];
//	PolygonModel[] tank_array = {new PolygonModel(100, 100, 90)  };
	
	Image off_screen ;
	Graphics off_g;
	UFO alien = new UFO(50,50, 90);
	
	
		
	public void init(){
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);

		t = new Thread(this);
		t.start();
		
		tank_array[0] = new Tank(100,  100,   90);
		tank_array[1] = new Tank(200,  100,   90);
		tank_array[2] = new Tank(300,  100,   90);
		tank_array[3] = new Tank(400,  100,   90);
		
		this.off_screen = createImage(1575, 741);
		this.off_g 		=   off_screen.getGraphics();
	}
	
	@SuppressWarnings("static-access")
	public void run() {
		try{
			tank_array[0].selected = true;
		} catch(Exception e){}
		while(true){
			for (int i = 0; i < tank_array.length; i++) {
				if (tank_array[i].selected){
				if (lt_pressed)	{ tank_array[i].rotateLeftBy(1);		}
				if (rt_pressed)	{ tank_array[i].rotateRightBy(1);		}
				if (up_pressed)	{ tank_array[i].moveForwardBy(1);		}
				if (dn_pressed)	{ tank_array[i].moveForwardBy(-1); 	}
				}
			}
			
//			if (lt_pressed)	{ active_tank.moveBy(-1, 	0);		}
//			if (rt_pressed)	{ active_tank.moveBy(1,		0);		}
//			if (up_pressed)	{ active_tank.moveBy(0, 	1);		}
//			if (dn_pressed)	{ active_tank.moveBy(0,	   -1); 	}
			
			repaint();
			try {
				t.sleep(15);
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
		r1.draw(g);
		alien.draw(g);
		for(int i = 0; i < tank_array.length; i++){
			tank_array[i].draw(g);;
		}

//		g.drawImage(anim.nextImage(), 21*75-496, 9*75-320, this);		
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
				
	}
	
	@SuppressWarnings("static-access")
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT) 	{ lt_pressed = false; }
		if (code == e.VK_RIGHT)	{ rt_pressed = false; }
		if (code == e.VK_UP)	{ up_pressed = false; }
		if (code == e.VK_DOWN)	{ dn_pressed = false; }

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