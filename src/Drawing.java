import java.applet.Applet;
import java.awt.*;


public class Drawing extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void paint(Graphics g){
		this.setSize(160*5, 90*5);
		g.setColor(Color.darkGray);
		int[] x_value = {50, 80, 120, 50};
		int[] y_value = {20, 30,  70, 80};

		drawPoly(x_value, y_value, 4, g);
		g.drawPolygon(x_value, y_value, 4);
	}
	public void drawPoint(int x, int y, Graphics g){
		g.drawLine(x, y, x, y);
	}
	public void drawLine(int x1, int y1, int x2, int y2, Graphics g){
		int y = 200;
		for(int x = 100; x <= 900; x++){
			drawPoint(x, y, g);
		}
	}
	public void drawHLine(int x1, int x2, int y, Graphics g){
		for(int x = x1; x <= x2; x++){
			drawPoint(x, y, g);
		}
	}
	public void drawVLine(int y1, int y2, int x, Graphics g){
		for(int y = y1; y <= y2; y++){
			drawPoint(x, y, g);
		}
	}
	public void drawDLine(int x1, int y1, Graphics g){
		for(int x = x1; x <= 1000; x++){
			drawPoint(x, x, g);
		}
	}
	public void drawD2Line(int x1, int y1, int x2, int y2, Graphics g){
		double slope = (double) (y2 - y1)/ (x2-x1);
		double y = (double) y1;
		for(int x = x1; x <= x2; x++){
			drawPoint(x, (int) y, g);
			drawVLine((int)(y), (int)(y+slope), x, g);
			y += slope;
		}
		/*Professor code:
		if (m >1){
			double y = (double) y1;
			for(int x = x1; x <= x2; x++){
				drawPoint(x, (int) y, g);
				y += slope;
			} 
		}
		else{
			double x = x1;
			for(int y = y1; y <= x2; y++){
				drawPoint((int) x, y, g);
				x += 1/m;
			} 		
		}
		*/
	}
	public void drawSLine(int x1, int y1, int slope, Graphics g){
		for(int x = x1; x <= 1000; x++){
			drawPoint(x, x*slope, g);
		}
	}
	public void drawHLetter(int x1, int y1, int x2, int y2, Graphics g){
		drawHLine(x1, x2, (y2-y1)/2, 	g);
		drawVLine(y1, y2, x1, 			g);
		drawVLine(y1, y2, x2, 			g);
	}
	public void drawPoly(int[] x_values, int[] y_values, int n, Graphics g){
		for (int i = 0; i < n-1; i++){
			g.drawLine( x_values[i], y_values[i], x_values[i+1], y_values[i+1]);
		}
		g.drawLine(x_values[n-1], y_values[n-1], x_values[0], y_values[0]);
	}
}
