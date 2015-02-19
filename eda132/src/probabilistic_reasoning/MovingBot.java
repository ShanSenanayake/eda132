package probabilistic_reasoning;

import java.util.ArrayList;
import java.util.Random;

public class MovingBot {
	private int sizeOfBoard;
	private Point position;
	private Random rnd;
	private ArrayList<Point> headings;
	
	public MovingBot(int sizeOfBoard){
		this.sizeOfBoard = sizeOfBoard;
		rnd = new Random();
		position = new Point(rnd.nextInt(sizeOfBoard),rnd.nextInt(sizeOfBoard));
		headings = new ArrayList<Point>();
		headings.add(new Point(0,1));
		headings.add(new Point(0,-1));
		headings.add(new Point(1,0));
		headings.add(new Point(-1,0));
		
	}
	
	public void move(){
		
	}


	public Point sensorOutput(){
		double prob = rnd.nextDouble();
		if (prob < 0.1){
			return new Point(position.x,position.y);
		}else if (prob < 0.5){
			int tmpX;
			int tmpY;
			Point p;
			do {
				tmpX = rnd.nextInt(3) - 1;
				tmpY = rnd.nextInt(3) - 1;
				p = new Point(position.x+tmpX, position.y+tmpY);
			} while(tmpX == 0 && tmpY == 0 || p.x >= 0 && p.y >= 0 && p.x < sizeOfBoard && p.y < sizeOfBoard));
		}else if (prob < 0.9){
			int tmpX;
			int tmpY;
			Point p;
			do {
				tmpX = (rnd.nextInt(3) - 1)*2;
				tmpY = (rnd.nextInt(3) - 1)*2;
				p = new Point(position.x+tmpX, position.y+tmpY);
			} while(tmpX == 0 && tmpY == 0 || Math.abs(tmpX) == 1 && Math.abs(tmpY) == 1 || p.x >= 0 && p.y >= 0 && p.x < sizeOfBoard && p.y < sizeOfBoard));
		}else{
			return new Point(-1,-1);
		}
	}
	
}
