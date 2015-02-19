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
		
		@Override
		public int hashCode() {
			   int hash = 7;
			    hash = 71 * hash + this.x;
			    hash = 71 * hash + this.y;
			    return hash;
		}

		public void addPoint(Point p){
			this.x += p.x;
			this.y += p.y;
		}
	}