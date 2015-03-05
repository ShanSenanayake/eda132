package decision_trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Relation {
	private String name;
	private ArrayList<Attribute> attributes;
	private ArrayList<Example> examples;
	private HashMap<Attribute, Double> gain;
	private HashMap<Attribute,Double> deviation;

	public Relation(String name, ArrayList<Attribute> attributes,
			ArrayList<Example> examples) {
		this.name = name;
		this.attributes = attributes;
		this.examples = examples;
		gain = new HashMap<Attribute, Double>();
		deviation = new HashMap<Attribute,Double>();
		calculateGain();
	}

	public ArrayList<Example> getExamples() {
		return examples;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
	
	public double getGain(Attribute attr){
		return gain.get(attr);
	}
	
	public double getDeviation(Attribute attr){
		return deviation.get(attr);
	}
	
	private void calcDeviation(Attribute attr, HashMap<String, Integer> positives, HashMap<String, Integer> negatives, int totPos){
		Set<String> keys = attr.getValues();
		double deviationSum = 0;
		for (String s : keys) {
			int positive = positives.get(s) == null ? 0:positives.get(s);
			int negative = negatives.get(s) == null ? 0:negatives.get(s);
			double estimateP = estimatedValue(positive,negative,totPos);
			double estimateN = estimatedValue(positive,negative,examples.size()-totPos);
			deviationSum += Math.pow(positive-estimateP, 2)/estimateP + Math.pow(negative-estimateN, 2)/estimateN;
		}
		deviation.put(attr, deviationSum);
	}
	
	private double estimatedValue(int p, int n, int totPart){
		return totPart * ((double)(p+n))/examples.size();
	}
	private void calculateGain() {
		for (Attribute attr : attributes) {
			HashMap<String, Integer> positives = new HashMap<String, Integer>();
			HashMap<String, Integer> negatives = new HashMap<String, Integer>();
			int totPos = 0;
			for (Example ex : examples) {
				int i = 1;
				String value = ex.getValue(attr);
				HashMap<String, Integer> tempMap;
				if (ex.getGoal().getClassification()) {
					tempMap = positives;
					totPos++;
				} else {
					tempMap = negatives;
				}
				if (tempMap.containsKey(value)) {
					i = tempMap.get(value);
					i++;
				}
				tempMap.put(value, i);
			}
			calcDeviation(attr,positives,negatives,totPos);
			double temp = bFunc(totPos, examples.size() - totPos);
			temp -= remainder(attr,positives, negatives);
			temp = Math.round(temp*1000)/1000.0;
			System.out.println(temp + " " + attr);
			gain.put(attr, temp);
		}
	}

	private double remainder(Attribute attr,HashMap<String, Integer> positives,
			HashMap<String, Integer> negatives) {
		Set<String> keys = attr.getValues();
		double sum = 0;
		for (String s : keys) {
			int positive = positives.get(s) == null ? 0:positives.get(s);
			int negative = negatives.get(s) == null ? 0:negatives.get(s);
			sum += (((double) (positive + negative)) / examples.size())
					* bFunc(positive, negative);
		}
		return sum;
	}

	private double bFunc(int p, int n) {
		double q = ((double) (p)) / (p + n);
		if(q>0 && q<1){
			return -(q * (Math.log(q) / Math.log(2)) + (1 - q) *( Math.log(1 - q)
					/ Math.log(2)));
		}else{
			return Math.log(1)/ Math.log(2);
		}

	}

}
