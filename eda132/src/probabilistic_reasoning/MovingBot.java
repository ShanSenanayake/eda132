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
		double prob = rand.nextDouble();
		if (prob < 0.1){
			return new Point(position.x,position.y);
		}else if (prob < 0.5){

		}else if (prob < 0.9){

		}else{

		}
	}
	
}
