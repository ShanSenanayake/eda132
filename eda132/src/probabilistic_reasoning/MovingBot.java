package probabilistic_reasoning;

import java.awt.Point;
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
}
