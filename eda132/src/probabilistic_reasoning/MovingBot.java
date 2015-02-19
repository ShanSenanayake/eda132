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
			return newRandomHeading();
		}else{
			return newRandomHeading();
		}
		
	}
	
	public void move(){
		
	}


	public Point sensorOutput(){
		double prob = rnd.nextDouble();
		if (prob < 0.1){
//			System.out.println("IN1");

//			System.out.println("OUT1");

			return new Point(position.x,position.y);
		}else if (prob < 0.5){
//			System.out.println("IN2");

			int tmpX;
			int tmpY;
			Point p;
			do {
//				System.out.println("Loop2");
				tmpX = rnd.nextInt(3) - 1;
				tmpY = rnd.nextInt(3) - 1;
				p = new Point(position.x+tmpX, position.y+tmpY);
			} while(tmpX == 0 && tmpY == 0 || p.x < 0 || p.y < 0 || p.x >= sizeOfBoard || p.y >= sizeOfBoard);
//			System.out.println("OUT2");

			return p;
		}else if (prob < 0.9){
//			System.out.println("IN3");

			int tmpX;
			int tmpY;
			Point p;
			do {
//				System.out.println("Loop2");

				tmpX = (rnd.nextInt(3) - 1)*2;
				tmpY = (rnd.nextInt(3) - 1)*2;
				p = new Point(position.x+tmpX, position.y+tmpY);
			} while(Math.abs(tmpX) + Math.abs(tmpY) > 2 || p.x < 0 || p.y < 0 || p.x >= sizeOfBoard || p.y >= sizeOfBoard);
//			System.out.println("OUT3");

			return p;
		}else{
//			System.out.println("IN4");

//			System.out.println("OUT4");

			return new Point(-1,-1);
		}
	}
	
	public Point pos(){
		return new Point(position.x,position.y);
	}
}
