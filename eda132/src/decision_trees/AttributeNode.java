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
	public String print(int level) {
		StringBuilder printString = new StringBuilder();
		HashSet<String> values = attribute.getValues();
		for (String value : values) {
			for (int i = 0; i < level; i++) {
				printString.append(" ");
			}

			printString.append(attribute.toString() + " = " + value);
			DecisionNode node = branches.get(value);
			String temp = null;
			if (node.isTerminal()) {
				printString.append(node.print(level)+"\n");

			} else {
				printString.append("\n" + (node.print(level + 1)));
			}
		}
		return printString.toString();
	}

	@Override
	public boolean isTerminal() {
		return false;
	}

}
