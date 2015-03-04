package decision_trees;

import java.util.ArrayList;

public class Relation {
	private String name;
	private ArrayList<Attribute> attributes;
	
	public Relation(String name, ArrayList<Attribute> attributes) {
		this.name = name;
		this.attributes = attributes;
	}
	
}
