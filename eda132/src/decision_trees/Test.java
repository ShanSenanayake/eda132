package decision_trees;

import java.io.File;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		DecisionTreeParser dtp = new DecisionTreeParser(new File("arff18-3.txt"),"yes");
		Relation rel = dtp.getRelation();
		rel.calculateGain();
		DecisionTreeAlgorithm dta = new DecisionTreeAlgorithm(rel);
		System.out.println(dta.dtl().print(0));
		
		dtp = new DecisionTreeParser(new File("weather.nominal.arff"),"yes");
		rel = dtp.getRelation();
		rel.calculateGain();
		dta = new DecisionTreeAlgorithm(rel);
		System.out.println(dta.dtl().print(0));
		
		dtp = new DecisionTreeParser(new File("diabetes.arff"),"tested_positive");
		rel = dtp.getRelation();
		ArrayList<Double> splitPoints = new ArrayList<Double>();
		splitPoints.add(3.8);
		splitPoints.add(120.9);
		splitPoints.add(69.1);
		splitPoints.add(20.5);
		splitPoints.add(79.8);
		splitPoints.add(32.0);
		splitPoints.add(0.5);
		splitPoints.add(33.2);
		for (int i = 0; i<rel.getAttributes().size(); i++){
			rel.getAttributes().get(i).setSplitPoint(splitPoints.get(i));
		}
		rel.calculateGain();
		dta = new DecisionTreeAlgorithm(rel);
		System.out.println(dta.dtl().print(0));
	}

}
