package decision_trees;

import java.util.ArrayList;

public class DecisionTreeAlgorithm {

	public DecisionTreeAlgorithm() {
	}
	
	public DecisionNode decisionTreeLearning(ArrayList<Example> examples, ArrayList<Attribute> attributes, ArrayList<Example> parentExamples){
		if(examples.isEmpty()){
			return pluralityValue(parentExamples);
		}else if(hasSameGoals(examples)){
			return new TerminalNode(examples.get(0).getGoal());
		}else if(attributes.isEmpty()){
			return pluralityValue(examples);
		}else{
			Attribute attr = mostImporatant(attributes,examples);
			AttributeNode root =  new AttributeNode(attr);
			for(String value: attr){
				ArrayList<Example> rootExamples = new ArrayList<Example>();
				for(Example ex: examples){
					if(value == ex.getValue(attr)){
						rootExamples.add(ex);
					}
				}
				ArrayList<Attribute> newAttributes = new ArrayList<Attribute>();
				for(Attribute a: attributes){
					if(!a.equals(attr)){
						newAttributes.add(a);
					}
				}
				root.addBranch(value,decisionTreeLearning(rootExamples,newAttributes,examples));
			}
			return root;
		}
	}

	private Attribute mostImporatant(ArrayList<Attribute> attributes,
			ArrayList<Example> examples) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean hasSameGoals(ArrayList<Example> examples) {
		// TODO Auto-generated method stub
		return false;
	}

	private DecisionNode pluralityValue(ArrayList<Example> parentExamples) {
		// TODO Auto-generated method stub
		return null;
	}
}
