package assign09;

import java.util.ArrayList;

import java.util.List;

/**
 * Represents a HashTable filled with Map entries that contain a key associated
 * with a value
 * 
 * @param <K> - a generic type to represent a key
 * @param <V> - a generic type to represent a value
 * 
 * @author Madison Murray and Zoe Linn
 * @version April 5, 2024
 */
public class HashTable<K, V> implements Map<K, V> {

	private ArrayList<MapEntry<K, V>> table;

	// USED FOR COLLISION COUNT
	public int collisions;

	private int[] primes = new int[] { 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
			205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881,
			421439783, 842879579, 1685759167 };
	private boolean[] removed; // keeps track of what indexes have been removed. T if removed, F if not

	public int capacity;
	private int size;
	private double loadFactor;

	/**
	 * Constructs an empty hashtable using map entries
	 */
	public HashTable() {

		// COUNTS THE NUMBERS OF COLLISION
		collisions = 0;

		capacity = primes[0];

		removed = new boolean[capacity];

		table = new ArrayList<MapEntry<K, V>>();

		for (int i = 0; i < capacity; i++) {

			table.add(null);

		}

		size = 0;

		loadFactor = size / capacity;

	}

	@Override

	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	public void clear() {

		table = new ArrayList<MapEntry<K, V>>();

		size = 0;

	}

	@Override
	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {

		// if the key is null, catch an exception and return false
		try {

			table.get(findIndex(key)).equals(null);

		} catch (NullPointerException e) {

			return false;

		}

		return true;

	}

	@Override
	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length) for quadratic probing O(table length + N) for separate
	 * chaining
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {

		for (int i = 0; i < table.size(); i++) {

			MapEntry<K, V> searchValue = table.get(i);

			try {

				if (searchValue.getValue().equals(value)) {
					return true;
				}

			} catch (NullPointerException e) {
				// if caught, do nothing
			}

		}

		return false;

	}

	@Override
	/**
	 * Returns a List view of the mappings contained in this map, where the ordering
	 * of mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing O(table length + N) for separate
	 * chaining
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries() {

		List<MapEntry<K, V>> toReturn = new ArrayList<MapEntry<K, V>>();

		for (int i = 0; i < table.size(); i++) {

			// only add to list if the current index is not null
			if (table.get(i) != null)

				toReturn.add(table.get(i));

		}

		return toReturn;

	}

	@Override
	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {

		// get the index mapping of this key
		if (containsKey(key)) {

			return table.get(findIndex(key)).getValue();

		}

		return null;

	}

	@Override
	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {

		if (size == 0) {

			return true;

		}

		return false;

	}

	@Override
	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {

		V toReturn;

		int index = findIndex(key);

		// if the key is null(doesnt exist in the table), catch an exception and return
		// false
		try {

			table.get(index).equals(null);

		} catch (NullPointerException e) {
			// not replacing an entry, check if the load factor is over .5
			if (loadFactor() > 0.5) {
				// expand table
				rehash();
				index = findIndex(key);

			}

			// add entry
			table.set(index, new MapEntry<>(key, value));

			size++;

			return null;

		}
		// keeps track of the old value to return
		toReturn = this.get(key);

		// add entry
		table.set(index, new MapEntry<>(key, value));

		return toReturn;

	}

	/**
	 * private helper method that rehashes this table
	 */
	private void rehash() {

		int i = 0;

		// get the next prime number for our capacity array's size to be
		while (primes[i] <= capacity) {

			i++;

		}

		capacity = primes[i];

		int oldSize = size;

		// reset the removed array's size
		removed = new boolean[capacity];

		loadFactor = size / capacity;

		// get the list of MapValue entries and rehash every key to it's value
		List<MapEntry<K, V>> entries = this.entries();

		// reset the table to the next prime number(capacity)
		this.table = new ArrayList<>();

		for (int j = 0; j < capacity; j++) {

			table.add(null);

		}

		// readd each entry based on the new capacity size
		for (MapEntry<K, V> entry : entries) {

			this.put(entry.getKey(), entry.getValue());

		}

		// size is the same as before since nothing new was added
		size = oldSize;
	}

	/**
	 * private helper method that locates the index that a key should end up at
	 * 
	 * @param goal - a specific key that we want to locate an index for
	 * @return an int index in the array
	 */
	public int findIndex(K goal) {

		// use hashCode % capacity
		int index = Math.abs(goal.hashCode()) % capacity;// keeps track of original mapping index
		int foundIndex;// keeps track of return index

		MapEntry<K, V> entry = table.get(index);

		// used for quadratic probing
		int count = 0;

		// while the index is not null or the index has been marked as removed...
		while ((entry != null) || (removed[index] == true)) {
			if (entry.getKey().equals(goal)) {

				// we found the index the Key param lives at
				foundIndex = ((index + ((int) Math.pow(count, 2))));

				// keep return index within capacity
				return foundIndex % capacity;

			}

			// haven't found the key yet, so lets quadratic probe

			count++;

			foundIndex = ((index + ((int) Math.pow(count, 2))));

			entry = table.get(((index + ((int) Math.pow(count, 2))) % capacity));
		}

		// we didn't find goal, so it's going at a new index(we are stopping at
		// null(returning the index of the first null slot on this path))

		foundIndex = ((index + ((int) Math.pow(count, 2))));

		// UPDATE TOTAL COLLISIONS COUNT
		collisions += count;

		return foundIndex % capacity;
	}

	@Override
	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {

		if (!this.containsKey(key)) {

			return null;

		}

		int index = findIndex(key);

		// lazy deletion; mark this index as removed
		removed[index] = true;

		V val = table.get(index).getValue();

		table.set(index, null);

		size--;

		return val;

	}

	@Override

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	public int size() {

		return size;

	}

	/**
	 * @return the updated load factor
	 */
	private double loadFactor() {

		loadFactor = ((double) (size + 1) / (double) capacity);

		return loadFactor;

	}

}
