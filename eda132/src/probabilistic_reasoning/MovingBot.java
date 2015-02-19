package probabilistic_reasoning;

import java.util.Random;

public class MovingBot {
	private int sizeOfBoard;
	private Point start;
	private Random rnd;
	
	public MovingBot(int sizeOfBoard){
		this.sizeOfBoard = sizeOfBoard;
		rnd = new Random();
		start = new Point(rnd.nextInt(sizeOfBoard),rnd.nextInt(sizeOfBoard));
		
	}


	public Point sensorOutput(){
		double prob = rand.nextDouble();
		if (prob < 0.1){
			return new Point()
		}else if (prob < 0.5){

		}else if (prob < 0.9){

		}else{

		}
	}
	
}
