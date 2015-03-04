package decision_trees;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		DecisonTreeParser dtp = new DecisonTreeParser(new File("arff18-3.txt"));
		Relation rel = dtp.getRelation();
		DecisionTreeAlgorithm dta = new DecisionTreeAlgorithm();
	}

}
