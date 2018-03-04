import java.applet.Applet;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener {
	
	boolean lt_pressed  = false;
	boolean rt_pressed  = false;
	boolean up_pressed  = false;
	boolean dn_pressed  = false;
	
	boolean mouse_inside 	= false;
	
	int mx;
	int my;


	Tank[] tank_array = new Tank[4];
	Tank active_tank;
	
	Thread t;
		
	public void init(){
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		t = new Thread(this);
		t.start();
		
		tank_array[0] = new Tank(100,  100,   90);
		tank_array[1] = new Tank(300,  100,   90);
		tank_array[2] = new Tank(500,  100,   90);
		tank_array[3] = new Tank(700,  100,   90);
		active_tank = tank_array[0];
		
	}
	
	@SuppressWarnings("static-access")
	public void run() {
		while(true){
				if (lt_pressed)	{ active_tank.rotateLeftBy(1);	}
				if (rt_pressed)	{ active_tank.rotateRightBy(1);	}
				if (up_pressed)	{ active_tank.moveForwardBy(1);	}
				if (dn_pressed)	{ active_tank.moveForwardBy(-1); 	}
			
			repaint();
			try {
				t.sleep(15);
			} catch(Exception e){}
		}
	}
	
	public void paint(Graphics g){
		this.setSize(300*5, 160*5);
		for(int i = 0; i < tank_array.length; i++){
			tank_array[i].draw(g);;
		}
		
		if (mouse_inside)	g.drawString("Inside",  mx, my);
		else 				g.drawString("Outside", mx, my);

	}
	/* The Key Press events are handled here due to the way the OS calls this functions when key presses are detected. */
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
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		for(int i = 0; i < tank_array.length; i++){
			if(tank_array[i].contains(mx, my)) active_tank = tank_array[i];
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e ) {
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		mouse_inside = false;
		for(int i = 0; i < tank_array.length; i++){
			mouse_inside = tank_array[i].contains(mx, my);
			if(mouse_inside) break;
		}
	}
	

}