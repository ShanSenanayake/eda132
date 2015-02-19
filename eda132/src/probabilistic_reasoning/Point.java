package probabilistic_reasoning;

public class Point {
	 	int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public boolean equals(Object obj){
			Point p = (Point)obj;
			return (p.x == x && p.y == y);
		}
	}