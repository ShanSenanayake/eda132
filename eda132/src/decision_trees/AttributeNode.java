package decision_trees;

public class AttributeNode implements DecisionNode {
	private Attribute attribute;
	
	
	public AttributeNode(Attribute attribute){
		this.attribute = attribute;
	}
}
