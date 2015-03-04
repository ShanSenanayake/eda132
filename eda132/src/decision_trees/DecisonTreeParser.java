package decision_trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class DecisonTreeParser {
	private Scanner scan;

	public DecisonTreeParser(File file) {
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
		Relation rel = null;
		while (!lines.isEmpty()) {
			String line = lines.removeFirst();
			String[] elements = line.split(" ");
			if (elements[0].equals("@relation")) {
				System.out.println(elements[1]);
				attributes = new ArrayList<Attribute>();
				rel = new Relation(elements[1], attributes);
			} else if (elements[0].equals("@attribute")) {
				ArrayList<String> values = new ArrayList<String>();
				String[] stringValues = elements[2].substring(1,
						elements[2].length() - 1).split(",");
				for (String value : stringValues) {
					values.add(value);
				}
				attributes.add(new Attribute(elements[1], values));
				System.out.println(elements[1] + " " + values);
			} else if (elements[0].equals("@data")) {
				while (!lines.isEmpty()
						&& !lines.peekFirst().startsWith("@relation")
						&& !lines.peekFirst().startsWith("@data")) {
					String[] example = lines.removeFirst().split(",");
					System.out.println(example);
				}
			}
		}

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
}
