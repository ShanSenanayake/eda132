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
	
	private class Point {
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
