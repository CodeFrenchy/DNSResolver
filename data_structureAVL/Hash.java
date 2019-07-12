package data_structures;

import java.util.Iterator;

/**
 * The Hash data structure has O(1) time complexity (best case) for add, remove, and find
 * for an object in the data structure. The methods in the Hash data structure are defined
 * by the HashI interface. The Hash consists of an array of Linked Lists,
 * the Linked Lists are defined by the HashListI interface.
 * 
 * @author
 *
 * @param <K> The key for entries in the hash
 * @param <V> The value for entries in the hash
 */

public class Hash<K, V> implements HashI<K, V> {
double maxLoadfactor;
int numElements, tableSize;

private LinkedList<HashElement<K,V>>[] harray;

	/**
	 * @param <K>
	 * @param <V>
	 */
	class HashElement<K,V> implements Comparable<HashElement<K,V>>{
		K key;
		V value;
		public HashElement(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public int compareTo(HashElement<K,V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	/**
	 * @param size
	 */
	public Hash(int size) {
		tableSize = size;
		harray = (LinkedList<HashElement<K,V>>[])new LinkedList[tableSize];
		for(int i = 0; i < size; i++)
			harray[i] = new LinkedList<HashElement<K,V>>();
	}
	
	
	/** 
	 * Adds element to hasharray
	 * @see data_structures.HashI#add(java.lang.Object, java.lang.Object)
	 * @param K,V
	 * @return true or false
	 */
	@Override
	public boolean add(K key, V value) {
		if(loadFactor() > maxLoadfactor)
			resize(tableSize*2);
		HashElement<K,V> he = new HashElement(key,value);
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		harray[hashval].add(he);
		numElements++;
		return true;
	}

	/** Removes an element given the key
	 * @see data_structures.HashI#remove(java.lang.Object)
	 * @param K key
	 * @return true if removed, false if not found
	 */
	@Override
	public boolean remove(K key) {
		boolean deleted = false;
		HashElement<K,V> he = new HashElement(key,null);
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		deleted = harray[hashval].remove(he) != null;
		if(deleted == true) {
			numElements--;
		}
		return deleted;
	}

	/** Changes value at given key, with new value
	 * @see data_structures.HashI#changeValue(java.lang.Object, java.lang.Object)
	 * @param K key, V value
	 * @return true or false if key was found and value was changed
	 */
	@Override
	public boolean changeValue(K key, V value) {
		if(remove(key) == true) {
			add(key,value);
			return true;
		}
		return false;
	}

	/**
	 * @see data_structures.HashI#contains(java.lang.Object)
	 * @param K key
	 * @return true or false if array contains key
	 */
	@Override
	public boolean contains(K key) {
		boolean match = false;
		HashElement<K,V> he = new HashElement(key,null);
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		Iterator<HashElement<K,V>> it = harray[hashval].iterator();
		while(it.hasNext()) {
			if(it.next().compareTo(he) == 0) {
				match = true;
			}
		}
		return match;
	}

	/** Gets value at given key
	 * @see data_structures.HashI#getValue(java.lang.Object)
	 * @param K key
	 * @return V value
	 */
	@Override
	public V getValue(K key) {
		V value = null;
		HashElement<K,V> he = new HashElement(key,null);
		int hashval = key.hashCode();
		hashval = hashval & 0x7FFFFFFF;
		hashval = hashval % tableSize;
		Iterator<HashElement<K,V>> it = harray[hashval].iterator();
		while(it.hasNext()) {
			HashElement<K,V> element = it.next();
			if(element.compareTo(he) == 0) {
				value = element.value;
			}
		}
		return value;
	}

	/**
	 * @return numElements
	 */
	@Override
	public int size() {
		return numElements;
	}

	/**
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return (numElements == 0);
	}

	/** Empty the array
	 * @return void
	 */
	@Override
	public void makeEmpty() {
		for(int i = 0; i < tableSize; i++) {
			harray[i].makeEmpty();
		}
		numElements = 0;
	}

	/**
	 * @return loadFactor
	 */
	@Override
	public double loadFactor() {
		return (numElements/tableSize);
	}

	/**
	 * @return maxLoadfactor
	 */
	@Override
	public double getMaxLoadFactor() {
		return maxLoadfactor;
	}

	/** Sets maxLoadfactor to loadfactor
	 * @return void
	 */
	@Override
	public void setMaxLoadFActor(double loadfactor) {
		maxLoadfactor = loadfactor;
	}

	/** Resize the array 
	 * @param newSize
	 * @return void
	 */
	@Override
	public void resize(int newSize) {
		LinkedList<HashElement<K,V>>[] newarray = (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];
		for(int i = 0; i < newSize; i++) {
			newarray[i] = new LinkedList<HashElement<K,V>>();
		}
		for(K key:this) {
			V val = getValue(key);
			HashElement<K,V> he = new HashElement<K,V>(key, val);
			int hashval = (key.hashCode() &0x7FFFFFFF) % newSize;
			newarray[hashval].add(he);
		}
		harray = newarray;
		tableSize = newSize;
	}

	@Override
	public Iterator<K> iterator() {
	
		return new IteratorHelper<K>();
	}
	
	/**
	 * Iterator used to iterate through array
	 * @param <T>
	 */
	class IteratorHelper<T> implements Iterator<T>{
		T[] keys;
		int position;
		/**
		 * Iterator constructor
		 */
		public IteratorHelper() {
			keys = (T[]) new Object[numElements];
			int p = 0;
			for(int i= 0; i< tableSize; i++) {
				LinkedList<HashElement<K,V>> list = harray[i];
					for(HashElement<K,V> h:list)
						keys[p++] = (T)h.key;
			}
			position = 0;
		}
		/**
		 * @return true if there is next
		 */
		public boolean hasNext() {
			return position < keys.length;
		}
		
		/**
		 * @return <T>
		 */
		public T next() {
			if(!hasNext())
				return null;
			return keys[position++];
		}
	}
}
