package decision_trees;

import java.util.HashMap;
import java.util.HashSet;

public class AttributeNode implements DecisionNode {
	private Attribute attribute;
	private HashMap<String, DecisionNode> branches;

	public AttributeNode(Attribute attribute) {
		this.attribute = attribute;
		branches = new HashMap<String, DecisionNode>();
	}

	public void addBranch(String value, DecisionNode node) {
		branches.put(value, node);
	}

	@Override
	public String print() {
		StringBuilder printString = new StringBuilder();
		HashSet<String> values = attribute.getValues();
		for (String value : values) {
			printString.append(attribute.toString() + " = " + value);

			DecisionNode node = branches.get(value);
			if (node.isTerminal()) {
				printString.append(node.print());
			} else {
				printString.append("\n\t" + node.print());
			}
		}
		return printString.toString();
	}

	@Override
	public boolean isTerminal() {
		return false;
	}
}
