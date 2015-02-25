package probabilistic_reasoning;

public class State {
	Point p;
	int heading;
	
	public State(Point p,int heading){
		this.p = p;
		this.heading = heading;
	}
	
	public boolean equals(Object obj){
		State s = (State)obj;
		return (s.p.equals(p) && s.heading == heading);
	}
	
	@Override
	public int hashCode() {
		   int hash = 7;
		    hash = 71 * hash + p.hashCode();
		    hash = 71 * hash + heading;
		    return hash;
	}
}
