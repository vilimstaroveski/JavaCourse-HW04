package hr.fer.zemris.java.tecaj.hw4.collections;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable.TableEntry;

import org.junit.Test;

/**
 * Tests for static class TableEntry in class SimpleHashtable.
 */
public class TableEntryTests {

	/**
	 * Test for constructor.
	 */
	@Test
	public void testTableEntry() {
		
		TableEntry entry = new TableEntry("key", "value", null);
		
		assertTrue(entry.next == null);
		assertTrue(entry.toString().equals("key=value"));
	}
	
	/**
	 * Test for constructor. With exception InvalidParameterException.
	 */
	@Test(expected=InvalidParameterException.class)
	public void testTableEntryExcep() {
		
		TableEntry entry = new TableEntry(null, "value", null);
		
	}
	
	/**
	 * Test for getters and setters.
	 */
	@Test
	public void testGettersAndSetters() {
		
		TableEntry entry = new TableEntry("key", "value", null);
		
		assertTrue(entry.getKey().equals("key"));
		assertTrue(entry.getValue().equals("value"));
		
		entry.setValue("druga value");
	
		assertTrue(entry.getValue().equals("druga value"));
	}
	
}
