package probabilistic_reasoning;

import java.util.ArrayList;
import java.util.Random;

public class MovingBot {
	private int sizeOfBoard;
	private Point position;
	private Random rnd;
	private Point currentHeading;
	private Point[] headings;
	
	public MovingBot(int sizeOfBoard){
		this.sizeOfBoard = sizeOfBoard;
		rnd = new Random();
		position = new Point(rnd.nextInt(sizeOfBoard),rnd.nextInt(sizeOfBoard));
		headings = new Point[] {new Point(0,1), new Point(0,-1),new Point(1,0), new Point(-1,0)};
		
		
	}
	
	private Point newRandomHeading(){
		int index = rnd.nextInt(4);
		if(currentHeading == headings[index]){
			
		}
		
	}
	
	public void move(){
		
	}


	public Point sensorOutput(){
		double prob = rnd.nextDouble();
		if (prob < 0.1){
			return new Point(position.x,position.y);
		}else if (prob < 0.5){

		}else if (prob < 0.9){

		}else{

		}
	}
	
}
