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
		if (currentHeading != null && currentHeading.equals(headings[index])) {
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

	private Point validHeading(HashSet<Point> illegalHeadings) {
		if (illegalHeadings.contains(currentHeading) || rnd.nextDouble() >= 0.7) {
			illegalHeadings.add(currentHeading);
			return newRandomHeading(illegalHeadings);
		} else {
			return currentHeading;
		}
	}

	public void move() {
		HashSet<Point> illegalHeadings = new HashSet<Point>();
		if (position.x >= sizeOfBoard - 1) {
			illegalHeadings.add(new Point(1, 0));
		} else if (position.x <= 0) {
			illegalHeadings.add(new Point(-1, 0));
		}
		if (position.y >= sizeOfBoard - 1) {
			illegalHeadings.add(new Point(0, 1));
		} else if (position.y <= 0) {
			illegalHeadings.add(new Point(0, -1));
		}
		currentHeading = validHeading(illegalHeadings);
		position.addPoint(currentHeading);

	}

	public Point sensorOutput() {
		double prob = rnd.nextDouble();
		if (prob < 0.1) {
			return new Point(position.x, position.y);
		} else if (prob < 0.5) {
				Point p = new Point(position.x +rnd.nextInt(3) - 1, position.y +rnd.nextInt(3) - 1);
				if(p.x<0 ||p.x>=sizeOfBoard || p.y<0 || p.y>=sizeOfBoard ){
					return new Point(-1,-1);
				}
			
			return p;
		} else if (prob < 0.9) {

				Point p = new Point(position.x + (rnd.nextInt(3) - 1) * 2, position.y + (rnd.nextInt(3) - 1) * 2);
				if(p.x<0 ||p.x>=sizeOfBoard || p.y<0 || p.y>=sizeOfBoard ){
					return new Point(-1,-1);
				}
			
			return p;
		} else {
			return new Point(-1, -1);
		}
	}

	public Point pos() {
		return new Point(position.x, position.y);
	}
}
