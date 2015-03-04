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
	
	
}
