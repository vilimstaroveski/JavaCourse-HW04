package hr.fer.zemris.java.tecaj.hw4.collections.demo;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;

/**
 * Class used for SimpleHashtable demonstration. 
 * @author Vilim Staroveški
 *
 */
public class SimpleHashtableDemo2 {

	/**
	 * Method called when program starts. 
	 */
	public static void main(String[] args) {
		// create collection:
		SimpleHashtable examMarks = new SimpleHashtable(2);
		// fill data:
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5)); // overwrites old grade for Ivana
		for(SimpleHashtable.TableEntry pair : examMarks) {
			
			System.out.printf("%s => %d%n", pair.getKey(), pair.getValue());
		}
	}
}
