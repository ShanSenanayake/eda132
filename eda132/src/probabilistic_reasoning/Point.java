package probabilistic_reasoning;

public class Point {
	 	int x;
		int y;

		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
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

		public String toString(){
			return this.x + ", " + this.y;
		}

		public void addPoint(Point p){
			this.x += p.x;
			this.y += p.y;
		}

		public double distance(Point p){
			int deltaX = this.x-p.x;
			int deltaY = this.y-p.y;
			return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY,2));
		}
	}
