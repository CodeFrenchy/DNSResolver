/**
 * 
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.LinkedList.Node;

/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author
 *
 */
public class LinkedList<E> implements HashListI<E> {
	private Node<E> head;
	private int currentSize;
	private Iterator<E> iter;
	
	/**
	 *
	 * @param <E>
	 */
	class Node<E>{
			E data;
			Node<E> next;
			
			/**
			 * @param obj
			 */
			public Node(E obj) {
				data = obj;
				next = null;
			}
	}
	
	/**
	 * @param E obj
	 * @return void
	 */
	@Override
	public void add(E obj) {
		Node<E> node = new Node<E>(obj);
		node.next = head;
		head = node;
		currentSize++;
		
	}

	/**
	 * @param E obj
	 * @return E
	 */
	@Override
	public E remove(E obj) {
		Node<E> tmp = head;
		Node<E> previous = null;
		
		while(tmp != null) {
			if(((Comparable<E>)tmp.data).compareTo(obj)==0) {
				currentSize--;
				if(previous == null) 
					head = tmp.next;
				else
					previous.next = tmp.next;
				return tmp.data;
			}
			previous = tmp;
			tmp = tmp.next;
		}
		return null;
	}

	/**
	 * @return void
	 */
	@Override
	public void makeEmpty() {
		head = null;
		currentSize = 0;
	}

	/**
	 * @return true or false 
	 */
	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	/**
	 * @return int currentSize
	 */
	@Override
	public int size() {
		return currentSize;
	}

	/**
	 * @param E
	 * @return boolean
	 */
	@Override
	public boolean contains(E obj) {
		Node<E> tmp = head;
		while(tmp != null) {
			if(((Comparable<E>)tmp.data).compareTo(obj)==0)
				return true;
			tmp = tmp.next;
		}
		return false;
	}

	
	
	@Override
	public Iterator<E> iterator() {
		
		return new IteratorHelper();
	}
	

class IteratorHelper implements Iterator<E>{
		
		
		Node<E> index;
		
		public IteratorHelper() {
			index = head;	
		}
		
		/**
		 * @return boolean
		 */
		public boolean hasNext() {
			return index != null;
		}
		
		/**
		 * @return E
		 */
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			E tmp = index.data;
			index = index.next;
			return tmp;

}}}
