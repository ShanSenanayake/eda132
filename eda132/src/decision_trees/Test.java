package decision_trees;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		DecisionTreeParser dtp = new DecisionTreeParser(new File("arff18-3.txt"));
		Relation rel = dtp.getRelation();
		DecisionTreeAlgorithm dta = new DecisionTreeAlgorithm();
		System.out.println(dta.decisionTreeLearning(rel.getExamples(), rel.getAttributes(), rel.getExamples()).print(0));
	}

}
