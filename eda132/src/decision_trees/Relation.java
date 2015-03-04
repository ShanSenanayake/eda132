package decision_trees;

import java.util.ArrayList;

public class Relation {
	private String name;
	private ArrayList<Attribute> attributes;
	private ArrayList<Example> examples;
	
	public Relation(String name, ArrayList<Attribute> attributes, ArrayList<Example> examples) {
		this.name = name;
		this.attributes = attributes;
		this.examples = examples;
	}
	
}
