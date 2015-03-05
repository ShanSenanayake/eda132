package decision_trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class DecisionTreeParser {
	private Scanner scan;
	private Relation rel;

	public DecisionTreeParser(File file, String positiveClass) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LinkedList<String> lines = new LinkedList<String>();
		scan = new Scanner(br);
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (!line.startsWith("%") && !line.isEmpty()) {
				lines.addLast(line.toLowerCase());
			}
		}
		ArrayList<Attribute> attributes = null;
		ArrayList<Example> examples = null;
		Relation rel = null;
		String relation = null;
		while (!lines.isEmpty()) {
			String line = lines.removeFirst();
			String[] elements = line.split(" ");
			if (elements[0].equals("@relation")) {
				relation = elements[1];
//				System.out.println(relation);
//				System.out.println("------------------------------------------------");
				attributes = new ArrayList<Attribute>();
				examples = new ArrayList<Example>();
			} else if (elements[0].equals("@attribute")) {
				HashSet<String> values = new HashSet<String>();
				String[] stringValues = elements[2].substring(1,
						elements[2].length() - 1).split(",");
				for (String value : stringValues) {
					values.add(value);
				}
				attributes.add(new Attribute(elements[1], values));
//				System.out.println(elements[1] + " " + values);
			} else if (elements[0].equals("@data")) {
				while (!lines.isEmpty()
						&& !lines.peekFirst().startsWith("@relation")
						&& !lines.peekFirst().startsWith("@data")) {
//					System.out.println("------------------------------------------------");
					String[] example = lines.removeFirst().split(",");
					HashMap<Attribute,String> ex = new HashMap<Attribute,String>();
					for ( int i = 0; i< example.length-1; i++){
						Attribute a = attributes.get(i);
						String value = example[i];
						if (!a.validValue(value)){
							System.err.println("Value not defined!");
							System.exit(1);
						}
						ex.put(a,value);
//						System.out.println(attributes.get(i) + " " + example[i]);
					}
					Attribute a = attributes.get(example.length-1);
					String value = example[example.length-1];
					if (!a.validValue(value)){
						System.err.println("Value not defined!");
						System.exit(1);
					}
					Goal goal = new Goal(a,value, value.equals(positiveClass));
//					System.out.println(ex);
					examples.add(new Example(ex,goal));
				}
				//If several relations are to be dealt with, this is the place to do it
			}
		}
		attributes.remove(attributes.size()-1);
		rel = new Relation(relation, attributes, examples);
		this.rel = rel;
		

		// String relation =
		// scan.nextLine().substring(10);//scan.next(Pattern.compile("@RELATION (.)*")).substring(10);
		// System.out.println(relation);
		// ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		// Relation rel = new Relation(relation,attributes);
		// String line;
		// while ((line = scan.nextLine()).isEmpty() || line.startsWith("%")){}
		//
		// while (scan.hasNext("@ATTRIBUTE")){
		// line = scan.nextLine();
		// String[] elements = line.split(" ");
		// if (elements[0].equals("@ATTRIBUTE")){
		// HashSet<String> values = new HashSet<String>();
		// String[] stringValues = elements[2].substring(1,
		// elements[2].length()-1).split(",");
		// for (String value: stringValues){
		// values.add(value);
		// }
		// attributes.add(new Attribute(elements[1],values));
		// System.out.println(elements[1] + " " + values);
		// }
		// }
		// while ((line = scan.nextLine()).isEmpty() || line.startsWith("%")){}
		// if (line.equals("@DATA")){
		// while ((line = scan.nextLine()).isEmpty() || line.startsWith("%")){}
		// while (!line.isEmpty()){
		// String[] example = line.split(",");
		// System.out.println(example);
		// line = scan.nextLine();
		// if (line.startsWith("%")){
		// line = scan.nextLine();
		// }
		// }
		// while ((line = scan.nextLine()).isEmpty() || line.startsWith("%")){}
		// }
		//
		// }
		//

	}
	
	public Relation getRelation(){
		return rel;
	}
}
