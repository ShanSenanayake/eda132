package decision_trees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DecisonTreeParser {
	private Scanner scan;
	

	
	public DecisonTreeParser(File file){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scan = new Scanner(br);
		String relation;
		System.out.println(scan.nextLine().substring(10));
		System.out.println(scan.nextLine());
		String attribute;
		while(scan.hasNext(Pattern.compile("@ATTRIBUTE"))){
			attribute = scan.nextLine();
			
		}
		
	}
}
