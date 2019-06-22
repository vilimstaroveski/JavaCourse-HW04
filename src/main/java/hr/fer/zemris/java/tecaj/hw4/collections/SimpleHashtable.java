package hr.fer.zemris.java.tecaj.hw4.collections;

import java.security.InvalidParameterException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Class with 2 private variables, size as {@code int} and table as
 * {@link TableEntry} representing hash table. Size represents how many entries
 * are there in this table.
 * 
 * @author Vilim Staroveski
 *
 */
public class SimpleHashtable implements Iterable<SimpleHashtable.TableEntry>{

	/**
	 * Private variable that tells how many entries are there in this table.
	 */
	private int size;

	/**
	 * Array of table slots required for hash table principle.
	 */
	private TableEntry[] table;
	
	/**
	 * Number of slots in this table.
	 */
	private int tableCapacity;
	
	/**
	 * Number of modifications that happened.
	 */
	private int modificationCount;
	
	/**
	 * Default constructor with no parameters, sets capacity of the table to 16
	 * entries.
	 */
	public SimpleHashtable() {

		this.tableCapacity = 16;
		this.size = 0;
		this.table = new TableEntry[16];
		this.modificationCount = 0;
	}

	/**
	 * Constructor with one parameter which is wanted minimum capacity of hash
	 * table.
	 * 
	 * @param minCapacity
	 *            - capacity {@code int}, must be a positive value.
	 * @throws IllegalArgumentException
	 *             if the given argument is a nonpositive number.
	 */
	public SimpleHashtable(int minCapacity) {

		if (minCapacity < 1) {

			throw new IllegalArgumentException("Parameter must be positive nuber of type int");
		}

		double exponent = Math.log10(minCapacity) / Math.log10(2);
		long roundedExponent = Math.round(exponent);
		int capacity = 0;

		if (Math.pow(2, roundedExponent) < minCapacity) {

			capacity = (int) Math.pow(2, roundedExponent + 1);
		} else {

			capacity = (int) Math.pow(2, roundedExponent);
		}
		
		this.tableCapacity = capacity;
		this.table = new TableEntry[capacity];
		this.size = 0;
		this.modificationCount = 0;
	}
	
	/**
	 * Simple get method for collections capacity.
	 * 
	 * @return capacity of the collection.
	 */
	public int getTableCapacity() {
		
		return tableCapacity;
	}
	

	/**
	 * Method that puts new element in table.
	 * 
	 * @param key
	 *            - unique ID of the entry, must be non-null value
	 * @param value
	 *            - value of the entry
	 * @throws IllegalArgumentException
	 *             if the parameter key is given as a null value.
	 */
	public void put(Object key, Object value) {

		if (key == null) {

			throw new IllegalArgumentException("Null value as key of the entry is not allowed!");
		}

		TableEntry newEntry = null;
		try {

			newEntry = new TableEntry(key, value, null);
		} catch (InvalidParameterException e) {

			System.err.println(e.getMessage());
		}

		int slot = Math.abs(key.hashCode()) % this.tableCapacity;

		if (this.containsKey(key)) {

			// update value-a u tablici
			TableEntry oldEntry = this.table[slot];

			while (!oldEntry.getKey().equals(key)) {

				oldEntry = oldEntry.next;
			}

			oldEntry.setValue(value);
		}

		else if (this.table[slot] == null) {

			// ako u slotu nema još nikakvih entry-a
			this.table[slot] = newEntry;
			this.size++;
			this.modificationCount++;
		}

		else {

			TableEntry temp = this.table[slot];
			while (temp.next != null) {

				temp = temp.next;
			}
			temp.next = newEntry;
			this.size++;
			this.modificationCount++;
		}
		
		if (this.size >= 0.75*this.tableCapacity) {
			
			rearrangeTable();
		}
	}

	/**
	 * Method that doubles the capacity of this {@code SimpleHashtable} and rearranges the entries trough the slots.
	 */
	private void rearrangeTable() {
		
		TableEntry[] newTable = new TableEntry[this.tableCapacity*2];
		int newCapacity = 2*this.tableCapacity;
		TableEntry[] oldTable = this.table;
		int oldCapacity = this.tableCapacity;
		
		this.table = newTable;
		this.tableCapacity = newCapacity;
		this.size = 0;
		
		for (int i = 0; i < oldCapacity; i++) {

			TableEntry temp = oldTable[i];

			while (temp != null) {
				
				put(temp.getKey(), temp.getValue());
				temp = temp.next;
			}
		}
		modificationCount++;
	}
	
	/**
	 * Removes all entries from the collection.
	 */
	public void clear() {
		
		for(int i = 0; i < this.tableCapacity; i++) {
			
			this.table[i] = null;
		}
		
		this.size = 0;
		this.modificationCount++;
	}

	/**
	 * Method that checks is there an entry with given ID in this table.
	 * 
	 * @param key
	 *            - ID of searched entry.
	 * @return {@code boolean} true if there is an entry with wanted ID. False
	 *         otherwise.
	 * @throws InvalidParameterException
	 *             if the given argument key is not of type {@code Object}
	 */
	public boolean containsKey(Object key) {

		argumentValidation(key);

		int possibleSlot = Math.abs(key.hashCode()) % this.tableCapacity;

		TableEntry temp = (TableEntry) this.table[possibleSlot];

		if (temp == null) {

			return false;
		}

		while (true) {

			if (temp == null) {
				return false;
			}
			if (temp.getKey().equals(key)) {
				return true;
			}
			temp = temp.next;
		}
	}

	/**
	 * Method that takes wanted entry out of the table.
	 * 
	 * @param key
	 *            - unique ID of the wanted entry
	 * @return - value of the entry in type {@code Object} if it is present.
	 * @throws InvalidParameterException
	 *             if the given argument key is not of type {@code Object}.
	 * @throws NonExistingKeyException
	 *             if the entry with the ID matching the key is not present in
	 *             the table.
	 */
	public Object get(Object key) {

		argumentValidation(key);

		keyValidation(key);

		int slot = Math.abs(key.hashCode()) % this.tableCapacity;
		TableEntry temp = this.table[slot];

		while (true) {

			if (temp == null) {

				return null;
			}

			if (temp.getKey().equals(key)) {

				return temp.getValue();
			}

			temp = temp.next;
		}
	}

	/**
	 * Method that gives information of how many elements there is in table.
	 * 
	 * @return - size {@code int} of the table.
	 */
	public int size() {

		return this.size;
	}

	/**
	 * Method that checks is there an entry with value given as parameter.
	 * 
	 * @param value
	 *            - value of type {@code Object}
	 * @return {@code boolean} true if there is an entry with value given as
	 *         parameter. False otherwise.
	 * @throws InvalidParameterException
	 *             if the given argument value is not of type {@code Object}
	 */
	public boolean containsValue(Object value) {

		argumentValidation(value);

		for (int i = 0; i < this.tableCapacity; i++) {

			TableEntry pomocna = this.table[i];

			while (pomocna != null) {

				if (pomocna.getValue().equals(value)) {

					return true;
				}

				pomocna = pomocna.next;
			}
		}
		return false;
	}

	/**
	 * Removes wanted element from table if it is present in it.
	 * 
	 * @param key
	 *            - ID of the entry we want to remove
	 * @throws InvalidParameterException
	 *             if the given argument key is not of type {@code Object}.
	 * @throws NonExistingKeyException
	 *             if the entry with the ID matching the key is not present in
	 *             the table.
	 */
	public void remove(Object key) {

		argumentValidation(key);

		keyValidation(key);

		int slot = Math.abs(key.hashCode()) % this.tableCapacity;
		TableEntry temp = this.table[slot];

		if (temp.getKey().equals(key)) {

			this.table[slot] = temp.next;
			size--;
			this.modificationCount++;
			return;
		}

		while (true) {

			if (temp.next == null) {

				return;
			}

			if (temp.next.getKey().equals(key)) {
				
				temp = temp.next.next;
				size--;
				this.modificationCount++;
				break;
			}
			temp = temp.next;
		}
	}

	/**
	 * Method that checks if the given argument is compatible for this
	 * {@code SimpleHashtable}.
	 * 
	 * @param argument
	 *            - value we are checking for validation.
	 * @throws InvalidParameterException
	 *             if the argument fail the validation
	 */
	public void argumentValidation(Object argument) {

		if (!(argument instanceof Object)) {

			throw new InvalidParameterException("Parameters must be of type Object");
		}
	}

	/**
	 * Method that checks if the given key is present for this
	 * {@code SimpleHashtable}.
	 * 
	 * @param argument
	 *            - value we are checking for validation.
	 * @throws NonExistingKeyException
	 *             if the key is not present in this collection.
	 */
	public void keyValidation(Object key) {

		if (!(this.containsKey(key))) {

			throw new NonExistingKeyException("This hash table doesn't have wanted object: " + key);
		}
	}

	/**
	 * Method that checks if this table has no entrys in it.
	 * 
	 * @return {@code boolean} true if the table is empty. False otherwise.
	 */
	public boolean isEmpty() {

		return this.size == 0;
	}

	/**
	 * Returns {@code String} representation of this collection.
	 * 
	 */
	public String toString() {

		String tableAsString = "[";
		
		for (int i = 0; i < this.tableCapacity; i++) {

			TableEntry temp = this.table[i];

			while (temp != null) {
				
				tableAsString += temp.toString()+", ";
				temp = temp.next;
			}
		}
		
		//uklanjanje nepotrebnog zadnjeg zareza ako je postavljen
		if (this.size > 0) {
			
			tableAsString = tableAsString.substring(0, tableAsString.length()-2);
		}
		
		tableAsString += "]";
		return tableAsString;
	}

	/**
	 * Class that represents an entry in the {@code SimpleHashtable}. It has
	 * private {@code Object} key as unique ID, private {@code Object} value
	 * that is stored under given key and reference to the next entry in the
	 * table.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	public static class TableEntry {

		/**
		 * Represents unique ID for a value paired with it.
		 */
		private Object key;

		/**
		 * Value we want to put in to the table.
		 */
		private Object value;

		/**
		 * Reference to the next entry.
		 */
		public TableEntry next;

		/**
		 * Constructor with 2 parameters of type {@code Object}.
		 * 
		 * @param key
		 *            unique ID of this entry.
		 * @param value
		 *            value we want to put in to the table.
		 * @throws InvalidParameterException
		 *             if both of the arguments are not {@code Object} values.
		 */
		public TableEntry(Object key, Object value, TableEntry next) {

			argumentValidation(value);
			argumentValidation(key);

			this.value = value;
			this.key = key;
			this.next = next;
		}

		/**
		 * Simple get method
		 * 
		 * @return ID of this entry.
		 */
		public Object getKey() {
			
			return key;
		}

		/**
		 * Simple get method
		 * 
		 * @return value of this entry.
		 */
		public Object getValue() {
			
			return value;
		}

		/**
		 * Simple set method.
		 * 
		 * @param value
		 *            - wanted value of this entry.
		 * @throws InvalidParameterException
		 *             if
		 */
		public void setValue(Object value) {

			argumentValidation(value);
			this.value = value;
		}

		/**
		 * This method validates arguments for this entry. Arguments must be of
		 * type {@code Object}
		 * 
		 * @param argument
		 *            - argument that is being validated.
		 * @throws InvalidParameterException
		 *             if argument is invalid.
		 */
		public void argumentValidation(Object argument) {

			if (!(argument instanceof Object)) {

				throw new InvalidParameterException(
						"Parameter must be of type Object!");
			}
		}

		/**
		 * Returns {@code String} representation of this entry in format:
		 * "key=value"
		 */
		@Override
		public String toString() {
			
			return key + "=" + value;
		}
	}

	/**
	 * Returns iterator that can iterate trough this collection.
	 * @return IteratorForSimpleHashtable which is iterator.
	 */
	@Override
	public Iterator<SimpleHashtable.TableEntry> iterator() {

		return new IteratorImpl();
	}
	
	/**
	 * Implementation of iterator for {@code SimpleHashtable}. It iterates from slot 0 to last slot in the table.
	 * 
	 * @author Vilim Staroveški
	 *
	 */
	private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry> {
		
		/**
		 * Reference to the entry which will be returned next with the call of method next().
		 */
		private TableEntry currentEntry;
		
		/**
		 * Reference to the entry that was last returned when the method next() was called.
		 */
		private TableEntry previusEntry;
		
		/**
		 * Private int value that has information of how many entries are there in the collection.
		 */
		private int howManyLeft;
		
		/**
		 * Int value representing the slot in which the iterator iterates currently.
		 */
		private int currentSlot;
		
		/**
		 * Number of modifications that happened during the iteration.
		 */
		private int iteratorModificationCount;
		
		/**
		 * Constructor for iterator implementation without parameters. Initial state is currentEntry on the first element in the slot number 0,
		 * currentSlot is set on 0, howManyLeft is set to the size of the collection, iteratorModificationCount is set to the number of happened modifications in this
		 * collection until the iteration and the previusEntry is set to null. 
		 */
		public IteratorImpl() {
			
			currentEntry = table[0];
			howManyLeft = size;
			currentSlot = 0;
			iteratorModificationCount = SimpleHashtable.this.modificationCount;
			previusEntry = null;
		}
		
		/**
		 * Returns true if there are some entries in the collection left to iterate trough.
		 *  
		 * @return true if the collection is not iterated to the end. False otherwise.
		 * @throws ConcurrentModificationException if the collection had been modified from outside this iterator.
		 */
		@Override
		public boolean hasNext() {
			
			if (iteratorModificationCount != SimpleHashtable.this.modificationCount) {
				
				throw new ConcurrentModificationException();
			}
			
			return howManyLeft > 0;
		}
		
		/**
		 * Method that returns next entry from the collection.
		 * @return {@code TableEntry} representing the next entry from the collection.
		 * @throws NoSuchElementException if there is no more entries in this collection.
		 */
		@Override
		public TableEntry next() {
			
			if (howManyLeft <= 0) {
				
				throw new NoSuchElementException();
			}
			
			while (hasNext()) {
				
				if (currentEntry != null) {
					
					previusEntry = currentEntry;
					currentEntry = currentEntry.next;
					howManyLeft--;
					return previusEntry;
					
					
				}
				else {
					
					currentSlot++;
					currentEntry = table[currentSlot];
				}
			}
			return null;
		}
		
		/**
		 * Method that removes entry that was last returned by the method next().
		 * @throws IllegalStateException if this method was called more than once in current state of the iterator.
		 */
		public void remove() {
			
			iteratorModificationCount++;
			
			if (previusEntry == null) {
				
				throw new IllegalStateException();
			}
			
			try {
				
				SimpleHashtable.this.remove(previusEntry.getKey());
				
			} catch (NonExistingKeyException e) {
				
				iteratorModificationCount--;
			}
			
			previusEntry = null;
		}
	}
}
