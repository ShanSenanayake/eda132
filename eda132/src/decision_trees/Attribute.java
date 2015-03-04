package decision_trees;

import java.util.ArrayList;

public class Attribute {
	private String name;
	private ArrayList<String> values;
	
	public Attribute(String name, ArrayList<String> values) {
		this.name = name;
		this.values = values;
	}
	
	public String getValue(int index){
		return values.get(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
	

	
	
}
