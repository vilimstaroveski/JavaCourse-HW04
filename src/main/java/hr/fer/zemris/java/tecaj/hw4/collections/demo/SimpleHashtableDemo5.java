package hr.fer.zemris.java.tecaj.hw4.collections.demo;

import java.util.Iterator;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;

/**
 * Class used for SimpleHashtable demonstration. 
 * @author Vilim Staroveški
 *
 */
public class SimpleHashtableDemo5 {

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
		
		Iterator<SimpleHashtable.TableEntry> iter = examMarks.iterator();
		
		while(iter.hasNext()) {
			
			SimpleHashtable.TableEntry pair = iter.next();
			
			if(pair.getKey().equals("Ivana")) {
				
				examMarks.remove("Ivana");//program will cause exception in this line
			}
		}
	}

}
