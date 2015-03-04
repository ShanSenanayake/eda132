package decision_trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DecisionTreeAlgorithm {

	public DecisionTreeAlgorithm() {
	}

	public DecisionNode decisionTreeLearning(ArrayList<Example> examples,
			ArrayList<Attribute> attributes, ArrayList<Example> parentExamples) {
		if (examples.isEmpty()) {
			return pluralityValue(parentExamples);
		} else if (hasSameGoals(examples)) {
			return new TerminalNode(examples.get(0).getGoal());
		} else if (attributes.isEmpty()) {
			return pluralityValue(examples);
		} else {
			Attribute attr = mostImporatant(attributes, examples);
			AttributeNode root = new AttributeNode(attr);
			for (String value : attr) {
				ArrayList<Example> rootExamples = new ArrayList<Example>();
				for (Example ex : examples) {
					if (value == ex.getValue(attr)) {
						rootExamples.add(ex);
					}
				}
				ArrayList<Attribute> newAttributes = new ArrayList<Attribute>();
				for (Attribute a : attributes) {
					if (!a.equals(attr)) {
						newAttributes.add(a);
					}
				}
				root.addBranch(
						value,
						decisionTreeLearning(rootExamples, newAttributes,
								examples));
			}
			return root;
		}
	}

	private Attribute mostImporatant(ArrayList<Attribute> attributes,
			ArrayList<Example> examples) {
		// TODO Auto-generated method stub
		return attributes.get((int) (Math.random() * attributes.size()));
	}

	private boolean hasSameGoals(ArrayList<Example> examples) {
		Goal g = examples.get(0).getGoal();
		for (Example ex : examples) {
			if (!g.equals(ex.getGoal())) {
				return false;
			}
		}
		return true;
	}

	private DecisionNode pluralityValue(ArrayList<Example> examples) {
		HashMap<Goal, Integer> map = new HashMap<Goal, Integer>();
		for (Example ex : examples) {
			int i = 1;
			if (map.containsKey(ex.getGoal())) {
				i = map.get(ex.getGoal());
				i++;
			}
			map.put(ex.getGoal(), i);
		}
		int occs = 0;
		Goal g = null;
		for (Entry<Goal, Integer> entry : map.entrySet()) {
			if (entry.getValue() > occs) {
				occs = entry.getValue();
				g = entry.getKey();
			}
		}
		return new TerminalNode(g);
	}
}
