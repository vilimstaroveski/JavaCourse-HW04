package hr.fer.zemris.java.tecaj.hw4.collections.demo;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;

/**
 * Class used for SimpleHashtable demonstration. 
 * @author Vilim Staroveški
 *
 */
public class SimpleHashtableDemo3 {

	/**
	 * Method called when program starts. 
	 */
	public static void main(String args[]) {
		
		// create collection:
		SimpleHashtable examMarks = new SimpleHashtable(2);
		// fill data:
		examMarks.put("Ivana", Integer.valueOf(2));
		examMarks.put("Ante", Integer.valueOf(2));
		examMarks.put("Jasna", Integer.valueOf(2));
		examMarks.put("Kristina", Integer.valueOf(5));
		examMarks.put("Ivana", Integer.valueOf(5)); // overwrites old grade for Ivana
		for(SimpleHashtable.TableEntry pair1 : examMarks) {

			for(SimpleHashtable.TableEntry pair2 : examMarks) {
				
				System.out.printf("(%s => %d) - (%s => %d)%n",
						pair1.getKey(), pair1.getValue(),
						pair2.getKey(), pair2.getValue());
			}
		}
	}
}
