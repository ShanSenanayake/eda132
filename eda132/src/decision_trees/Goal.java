package decision_trees;

public class Goal {
	private Attribute attribute;
	private String value;
	
	
	public Goal(Attribute attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}



	@Override
	public boolean equals(Object obj) {
		Goal other = (Goal)obj;
		return other.attribute.equals(attribute) && other.value.equals(value);
	}
	
	
	
}
