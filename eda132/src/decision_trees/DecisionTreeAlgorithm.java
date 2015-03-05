package decision_trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DecisionTreeAlgorithm {
	private Relation rel;

	public DecisionTreeAlgorithm(Relation rel) {
		this.rel = rel;
	}
	public DecisionNode dtl(){
		return decisionTreeLearning(rel.getExamples(),rel.getAttributes(),rel.getExamples());
	}
	
	private DecisionNode decisionTreeLearning(ArrayList<Example> examples,
			ArrayList<Attribute> attributes, ArrayList<Example> parentExamples) {
		if (examples.isEmpty()) {
//			System.out.println("in empty examples");
			return pluralityValue(parentExamples);
		} else if (hasSameGoals(examples)) {
//			System.out.println("has same goals");
			return new TerminalNode(examples.get(0).getGoal());
		} else if (attributes.isEmpty()) {
//			System.out.println("no attributes");
			return pluralityValue(examples);
		} else {
//			System.out.println("making subtree");
			Attribute attr = mostImporatant(attributes, examples);
//			System.out.println(attr);
			AttributeNode root = new AttributeNode(attr);
			for (String value : attr) {
//				System.out.println(value);
				ArrayList<Example> rootExamples = new ArrayList<Example>();
				for (Example ex : examples) {
//					System.out.println("examples:" + ex.getValue(attr));
					if (attr.test(value,ex)) {
//					if (value.equals(ex.getValue(attr))) {
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
		double max = -1;
		Attribute attribute = null;
		for(Attribute attr: attributes){
			double temp = rel.getGain(attr);
			if(temp>max){
				attribute = attr;
				max = temp;
			}
		}
		return attribute;
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
