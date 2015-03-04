package decision_trees;

import java.util.HashMap;

public class Example {
	private HashMap<Attribute,String> values;
	private Goal goal;
	
	public Example(HashMap<Attribute,String> values, Goal goal){
		this.values = values;
		this.goal = goal;
	}
	
	public Goal getGoal(){
		return goal;
	}
	
	public String getValue(Attribute attr){
		return values.get(attr);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Example other = (Example) obj;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}
	
	

}
