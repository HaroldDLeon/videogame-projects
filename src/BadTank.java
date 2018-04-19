
public class BadTank extends Tank {
	
	public BadTank(double x, double y, int angle) {
		super(x, y, angle);
	}
	
	public boolean shoot(Rect shell, Tank objective){
		boolean shot_fired = false;
		turnTowards(objective);
		
		double vx = objective.x - x;
		double vy = objective.y - y;
		
		double ux = Lookup.cos[angle];
		double uy = Lookup.sin[angle];
		
		double nx = -uy;
		double ny = ux;
		
		double d1 = vx*nx + vy*ny;
		double d2 = vx*ux + vy*uy; 
		
		
		if (d1 < 50 && d2 < 300){
			super.shoot(shell);
			shot_fired = true;
		}
		return shot_fired;
		
		
	}
	
	@SuppressWarnings("unused")
	public void turnTowards(Tank objective){
		
		double vx = objective.x - x;
		double vy = objective.y - y;
		
		double ux = Lookup.cos[angle];
		double uy = Lookup.sin[angle];
		
		double d1 = ux * vy - uy * vx;
		
		if(d1 > 10)
		{
			angle += 3;
			if (angle >= 360)  angle -= 360;
		}
		if(d1 < -10)
		{
			angle -= 3;
			if(angle  < 0)  angle += 360;
		}


	}
	public void moveTowards(Tank objective){
		turnTowards(objective);
		
		double vx = objective.x - x;
		double vy = objective.y - y;
		
		double ux = Lookup.cos[angle];
		double uy = Lookup.sin[angle];
		
		double nx = -uy;
		double ny = ux;
		
		double d1 = vx*ux + vy*uy;
		
		
		if (d1 > 300)			this.moveForwardBy(1);
		
	}

}
