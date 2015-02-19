package probabilistic_reasoning;

import java.util.HashSet;
import java.util.Random;

public class MovingBot {
	private int sizeOfBoard;
	private Point position;
	private Random rnd;
	private Point currentHeading;
	private Point[] headings;

	public MovingBot(int sizeOfBoard) {
		this.sizeOfBoard = sizeOfBoard;
		rnd = new Random();
		position = new Point(rnd.nextInt(sizeOfBoard), rnd.nextInt(sizeOfBoard));
		headings = new Point[] { new Point(0, 1), new Point(0, -1),
				new Point(1, 0), new Point(-1, 0) };
		currentHeading = newRandomHeading();

	}

	private Point newRandomHeading() {
		int index = rnd.nextInt(4);
		if (currentHeading == headings[index]) {
			return newRandomHeading();
		} else {
			return headings[index];
		}

	}

	private Point newRandomHeading(HashSet<Point> illegalHeadings) {
		int index = rnd.nextInt(4);
		if (illegalHeadings.contains(headings[index])) {
			return newRandomHeading(illegalHeadings);
		} else {
			return headings[index];
		}

	}

	public void move() {
		double prob = rnd.nextDouble();
		if ((position.x >= sizeOfBoard - 1 && position.y >= sizeOfBoard - 1)
				|| (position.x <= 0 && position.y <= 0)) {
			HashSet<Point> illegalHeadings = new HashSet<Point>();
			if (position.x > 0) {
				illegalHeadings.add(new Point(1, 0));
				illegalHeadings.add(new Point(0, 1));
			} else {
				illegalHeadings.add(new Point(-1, 0));
				illegalHeadings.add(new Point(0, -1));

			}
			currentHeading = newRandomHeading(illegalHeadings);

		} else if (prob >= 0.7 || (position.x >= sizeOfBoard - 1)
				|| (position.x <= 0) || (position.y >= sizeOfBoard - 1)
				|| (position.y <= 0)) {
			currentHeading = newRandomHeading();
		}
		
	}

	public Point sensorOutput() {
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
