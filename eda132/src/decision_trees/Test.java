package decision_trees;

import java.io.File;

public class Test {

	public static void main(String[] args) {
//		DecisionTreeParser dtp = new DecisionTreeParser(new File("arff18-3.txt"),"yes");
//		DecisionTreeParser dtp = new DecisionTreeParser(new File("diabetes.arff"),"tested_positive");
		DecisionTreeParser dtp = new DecisionTreeParser(new File("weather.nominal.arff"),"yes");
		Relation rel = dtp.getRelation();
//		for (Attribute a : rel.getAttributes()){
//			a.setSplitPoint(5);
//		}
		rel.calculateGain();
		DecisionTreeAlgorithm dta = new DecisionTreeAlgorithm(rel);
		System.out.println(dta.dtl().print(0));
	}

}
