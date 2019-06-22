package hr.fer.zemris.java.tecaj.hw4.collections;

import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable;
import hr.fer.zemris.java.tecaj.hw4.collections.SimpleHashtable.TableEntry;

import java.security.InvalidParameterException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that contains unit tests for {@code SimpleHashtable}
 * @author Vilim Staroveški
 *
 */
public class SimpleHashtableTests {
	
	/**
	 * Test for constructor without parameters. 
	 */
	@Test
	public void testDefaultConstructor() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		assertTrue(table.toString().equals("[]"));
		assertTrue(table.isEmpty());
		assertTrue(table.size() == 0);
	}
	
	/**
	 * Test for constructor with one parameter: capacity. 
	 */
	@Test
	public void testConstructorWithOneParameter() {
		
		SimpleHashtable table = new SimpleHashtable(6);
		
		assertTrue(table.toString().equals("[]"));
		assertTrue(table.isEmpty());
		assertTrue(table.size() == 0);
		
		SimpleHashtable table2 = new SimpleHashtable(8);
		
		assertTrue(table.getTableCapacity() == table2.getTableCapacity());
	}
	
	/**
	 * Test for expected Exception to be thrown for invalid parameter in constructor.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorException() {
		
		SimpleHashtable table = new SimpleHashtable(-6);
	}
	
	/**
	 * Test for getTableCapacity(): int.
	 */
	@Test
	public void testGetTableCapacity() {
		
		SimpleHashtable table = new SimpleHashtable(6);
		assertTrue(table.getTableCapacity() == 8);
		
		table.put("key", "value");
		
		assertTrue(table.getTableCapacity() == 8);
	}
	
	/**
	 * Test for method put(Object value): void. 
	 */
	@Test
	public void testPut() {
		
		SimpleHashtable table = new SimpleHashtable(2);
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("Test3", new Double(5.3));
		
		assertTrue(table.containsValue("Test1Value"));
		assertTrue(table.containsValue(new Float(2.2)));
		assertTrue(table.containsValue(new Double(5.3)));
		assertFalse(table.containsValue("Test4Value"));
		
		assertTrue(table.containsKey("Test1"));
		assertTrue(table.containsKey(new Integer(4)));
		assertTrue(table.containsKey("Test3"));
		assertFalse(table.containsKey("Test4"));
		
		assertTrue(table.size() == 3);
		assertTrue(table.getTableCapacity() == 8);
		
		table.put("Test3", "NewValueForTest3");
		
		assertTrue(table.containsValue("NewValueForTest3"));
		assertTrue(table.containsKey("Test3"));
		assertFalse(table.containsValue(new Double(5.3)));
		
		assertTrue(table.size() == 3);
	}
	
	/**
	 * Test for method put(Object value): void with expected exception for invalid arg. 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testPutException() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put(null, "Test1Value");
		
	}
	
	/**
	 * Test for method containsKey(Object key): boolean. 
	 */
	@Test
	public void testContainsKey() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("Test3", new Double(5.3));
		
		assertTrue(table.containsKey("Test1"));
		assertTrue(table.containsKey(new Integer(4)));
		assertTrue(table.containsKey("Test3"));
		assertFalse(table.containsKey("Test4"));
	}
	
	/**
	 * Test for method containsKey(Object key): boolean with expected exception. 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testContainsKeyExcep() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		boolean b = table.containsKey(null);
	}
	
	/**
	 * Test for method get(Object key): Object with expected exception NonExistingKeyException. 
	 */
	@Test(expected=NonExistingKeyException.class)
	public void testGetExcep() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		TableEntry t = (TableEntry) table.get("neki");
	}
	
	/**
	 * Test for method get(Object key): Object with expected exception InvalidParameterException. 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testGetExcep2() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		TableEntry t = (TableEntry) table.get(null);
	}
	
	/**
	 * Test for method containsValue(Object value): boolean. 
	 */
	@Test
	public void testContainsValue() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("Test3", new Double(5.3));
		
		assertTrue(table.containsValue("Test1Value"));
		assertTrue(table.containsValue(new Float(2.2)));
		assertTrue(table.containsValue(new Double(5.3)));
		assertFalse(table.containsValue("Test4Value"));
	}
	
	/**
	 * Test for method containsValue(Object value): boolean with expected exception InvalidParameterException. 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testContainsValueExcep() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		boolean b = table.containsValue(null);
	}
	
	/**
	 * Test for method get(Object key): Object
	 */
	@Test
	public void testGet() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("Test3", new Double(5.3));
		
		assertTrue(table.get("Test1").equals("Test1Value"));
		assertTrue(Math.abs((float)table.get(4) - 2.2) < 0.0000001);
		assertTrue(table.get("Test3").equals(new Double(5.3)));
	}
	
	/**
	 * Test for method size(): int
	 */
	@Test
	public void testSize() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		
		assertTrue(table.size() == 2);
		
		assertTrue(table.containsKey("Test1"));
		table.remove("Test1");
		
		assertTrue(table.size() == 1);
	}
	
	/**
	 * Test for method remove(Object key): void
	 */
	@Test
	public void testRemove() {
		
		SimpleHashtable table = new SimpleHashtable(1);
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("Test2", "Test1Value");
		table.put(new Integer(5), new Float(2.2));
		table.put("Test3", "Test1Value");
		table.put(new Integer(6), new Float(2.2));
		table.put("Test4", "Test1Value");
		table.put(new Integer(7), new Float(2.2));
		table.put("Test5", "Test1Value");
		table.put(new Integer(8), new Float(2.2));
		table.put("Test6", "Test1Value");
		
		assertTrue(table.containsKey("Test1"));
		table.remove("Test1");
		assertFalse(table.containsKey("Test1"));
		
		table.remove("Test2");
		table.remove("Test3");
		table.remove("Test4");
		table.remove("Test5");
		table.remove("Test6");
		table.remove(7);
		table.remove(8);
		table.remove(4);
		table.remove(5);
		table.remove(6);
		
		assertTrue(table.size() == 0);
		
		
	}
	
	/**
	 * Test for method remove(Object key): void with expected exception InvalidParameterException. 
	 */
	@Test(expected=InvalidParameterException.class)
	public void testRemoveExcep() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.remove(null);
	}
	
	/**
	 * Test for method remove(Object key): void with expected exception NonExistingKeyException. 
	 */
	@Test(expected=NonExistingKeyException.class)
	public void testRemoveExcep2() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.remove("neki");
	}
	
	/**
	 * Test for method isEmpty(): boolean
	 */
	@Test
	public void testIsEmpty() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		assertTrue(table.isEmpty());
		table.put("Test", "Value");
		assertFalse(table.isEmpty());
		table.remove("Test");
		assertTrue(table.isEmpty());
	}
	
	/**
	 * Test for method toString(): String
	 */
	@Test
	public void testToString() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		
		String expectedString = new String("[4=2.2, Test1=Test1Value]");

		assertTrue(table.toString().equals(expectedString));
	}

	/**
	 * Test for method clear(): void
	 */
	@Test
	public void testClear() {
	
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		
		table.clear();
		
		assertTrue(table.isEmpty());
		
		assertFalse(table.containsKey("Test1"));
		assertFalse(table.containsKey(4));
	}
	
	/**
	 * Test for method rearrange(): void
	 */
	@Test
	public void testRearrange() {
		
		SimpleHashtable table = new SimpleHashtable(2);
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		
		assertTrue(table.getTableCapacity() == 4);
		
		table.put("test2", "value");
		
		assertTrue(table.getTableCapacity() == 8);
	}
	
	
	/**
	 * Test for method keyValidation(Object key): void
	 */
	@Test(expected=NonExistingKeyException.class)
	public void testKeyValidation() {
		
		SimpleHashtable table = new SimpleHashtable(2);
		
		table.keyValidation("neki");
	}
	
	/**
	 * Test for iterator.
	 */
	@Test
	public void testIterator() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("test2", "value");
		
		Iterator<SimpleHashtable.TableEntry> iterator = table.iterator();
		
		assertTrue(iterator.hasNext());
		
		while(iterator.hasNext()) {
			TableEntry obj = iterator.next();
			assertTrue(obj != null);
			
			if(obj.getKey().equals("Test1")) {
				
				iterator.remove();
			}
		}
	}
	
	/**
	 * Test for iterator. With exception: ConcurrentModificationException
	 */
	@Test(expected=ConcurrentModificationException.class)
	public void testIteratorExcep() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		
		Iterator<SimpleHashtable.TableEntry> iterator = table.iterator();
		
		table.remove("Test1");
		assertTrue(iterator.hasNext());
	}
	
	/**
	 * Test for iterator. With exception: NoSuchElementException
	 */
	@Test(expected=NoSuchElementException.class)
	public void testIteratorExcep2() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		
		Iterator<SimpleHashtable.TableEntry> iterator = table.iterator();
		
		iterator.next();
		iterator.next();
	}
	
	/**
	 * Test for iterator. With exception: IllegalStateException
	 */
	@Test(expected=IllegalStateException.class)
	public void testIteratorExcep3() {
		
		SimpleHashtable table = new SimpleHashtable();
		
		table.put("Test1", "Test1Value");
		table.put(new Integer(4), new Float(2.2));
		table.put("test2", "value");
		
		Iterator<SimpleHashtable.TableEntry> iterator = table.iterator();
		
		while(iterator.hasNext()) {
			
			iterator.remove();
			iterator.remove();
		}
	}
	
	
	
	
	
	
}

