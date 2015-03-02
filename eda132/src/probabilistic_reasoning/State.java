package probabilistic_reasoning;

public class State {
	public static final int N = 0;
	public static final int E = 1;
	public static final int S = 2;
	public static final int W = 3;
	public static final Point NOTHING = new Point(-1,-1);

	Point p;
	int heading;

	public State(Point p, int heading) {
		this.p = p;
		this.heading = heading;
	}

	@Override
	public boolean equals(Object obj) {
		State s = (State) obj;
		return (s.p.equals(p) && s.heading == heading);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + p.hashCode();
		hash = 71 * hash + heading;
		return hash;
	}

	public boolean isNeighbour(State state) {
		Point point = state.p;
		if (point.x == p.x && point.y == p.y-1 && state.heading == State.N
				|| point.x == p.x && point.y == p.y+1
				&& state.heading == State.S || point.x == p.x+1
				&& point.y == p.y && state.heading == State.E
				|| point.x == p.x-1 && point.y == p.y
				&& state.heading == State.W) {
			return true;
		}
		return false;
	}

	public int amountOfNeighbours(int size){
		int amount = 4;
		if(p.x<= 0 || p.x>= size-1){
			amount--;
		}
		if(p.y<= 0 || p.y>= size-1){
			amount--;
		}
		return amount;
	}

	public boolean encounterWall(int size) {
		if (heading == State.N && p.y <= 0 || heading == State.S
				&& p.y >= size - 1 || heading == State.W && p.x <= 0
				|| heading == State.E && p.x >= size - 1) {
			return true;
		}
		return false;
	}
}
