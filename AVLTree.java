/**
 * 
 */
package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import data_structures.LinkedList;

/**
 * @author nathanazoulay
 *
 */
public class AVLTree<K,V> implements AVLTreeI<K, V> {

	/**
	 * @param <K>
	 * @param <V>
	 */

	class Node<K,V>{
		public V data;
		public K key;
		public Node<K,V> left;
		public Node<K,V> right;
		public Node<K,V> parent;

		public Node(K key, V data) {
			this.key = key;
			this.data = data;
			parent = left = right = null;
		}

		/**
		 * @return int height
		 */
		public int height() {
			int leftHeight,rightHeight;
			if(left == null) {
				leftHeight = -1;
			}
			else
				leftHeight = left.height();

			if(right == null)
				rightHeight = -1;
			else
				rightHeight = right.height();

			if(leftHeight > rightHeight)
				return leftHeight+1;
			else
				return rightHeight+1;
		}
	}
	/**
	 * Iterator used to iterate through tree
	 * @param <K>
	 */
	class IteratorHelper implements Iterator<K>{


		Node<K,V> index;

		/**
		 * Iterator constructor
		 */
		public IteratorHelper() {
			index = root;	
			while(index.left != null)
				index = index.left;
		}

		/**
		 * @return boolean
		 */
		public boolean hasNext() {
			return index != null;
		}

		/**
		 * @return V value
		 */

		public V getValue() {
			return index.data;
		}

		/**
		 * @return E
		 */
		public K next() {
			if(!hasNext())
				throw new NoSuchElementException();
			Node<K,V> r = index;
			if(index.right != null) {
				index = index.right;
				while(index.left != null)
					index = index.left;
				return r.key;
			}
			while(true) {
				if(index.parent == null) {
					index = null;
					return r.key;
				}
				if(index.parent.left == index) {
					index = index.parent;
					return r.key;
				}
				index = index.parent;
			}
		}
	}
	/**
	 * 
	 */
	Node<K,V> root;
	int currentSize;

	public AVLTree() {
		root = null;
		currentSize = 0;
	}


	/**
	 * @param K key, V value
	 * @return void
	 */

	@Override
	public void add(K key, V value) {
		Node<K,V> newNode = new Node<K,V>(key,value);
		Node<K,V> travel = root;
		while(travel != null) {
			newNode.parent = travel;
			if(((Comparable<K>)newNode.key).compareTo(travel.key) > 0) {
				travel = travel.right;
			}
			else
				travel = travel.left;
		}
		if(newNode.parent !=null) {
			if(((Comparable<K>)newNode.key).compareTo(newNode.parent.key) > 0) {
				newNode.parent.right = newNode;
			}
			else
				newNode.parent.left = newNode;
		}
		else
			root = newNode;
		checkBalance(newNode);
	}

	/**
	 * @param K key
	 * @return boolean
	 */

	@Override
	public boolean contains(K key) {
		Iterator<K> it = iterator();
		while(it.hasNext())
			if(it.next() == key)
				return true;
		return false;
	}

	/**
	 * @param K key
	 * @return V value
	 */

	@Override
	public V getValue(K key) {
		IteratorHelper it = iterator();
		while(it.hasNext())
			if(it.next() == key)
				return it.getValue();
		return null;
	}

	/**
	 * @return int currentSize
	 */

	@Override
	public int size() {
		return currentSize;
	}

	/**
	 * @return boolean
	 */

	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	@Override
	public int height() {

		return root.height();
	}

	/**
	 * @param Node<K,V>
	 * @return int height
	 */
	private int height(Node<K,V> node) {
		if(node == null)
			return -1;
		else
			return node.height();
	}

	@Override
	public IteratorHelper iterator() {
		return new IteratorHelper();
	}

	@Override
	public void print() {
		Iterator<K> it = iterator();
		while(it.hasNext())
			System.out.println(it.next());

	}

	/**
	 * @param Node<K,V>
	 * @return Node<K,V>
	 */
	private Node<K,V> leftRotate(Node<K,V> node) {
		Node<K,V> tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;
		tmp.parent = node.parent;
		node.parent = tmp;
		if(node.right != null)
			node.right.parent = node;
		if(tmp.parent != null) {
			if(tmp.parent.left == node)
				tmp.parent.left = tmp;
			else
				tmp.parent.right = tmp;
		}

		return tmp;

	}

	/**
	 * @param Node<K,V>
	 * @return Node<K,V>
	 */
	private Node<K,V> rightRotate(Node<K,V> node) {
		Node<K,V> tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;
		tmp.parent = node.parent;
		node.parent = tmp;
		if(node.left != null)
			node.left.parent = node;
		if(tmp.parent != null) {
			if(tmp.parent.left == node)
				tmp.parent.left = tmp;
			else
				tmp.parent.right = tmp;
		}
		return tmp;
	}

	/**
	 * @param Node<K,V>
	 * @return Node<K,V>
	 */
	private Node<K,V> rightLeftRotate(Node<K,V> node) {
		node.right = rightRotate(node.right);
		return leftRotate(node);
	}

	/**
	 * @param Node<K,V>
	 * @return Node<K,V>
	 */
	private Node<K,V> leftRightRotate(Node<K,V> node) {
		node.left = leftRotate(node.left);
		return rightRotate(node);

	}

	/**
	 * @param Node<K,V>
	 */
	private void rotate(Node<K,V> node) {

		if(height(node.left) - height(node.right) > 1) {

			if(height(node.left.left) > height(node.left.right))
				node = rightRotate(node);
			else
				node = leftRightRotate(node);
		}
		else if(height(node.left) - height(node.right) < -1) {
			if(height(node.right.right) > height(node.right.left))
				node = leftRotate(node);
			else
				node = rightLeftRotate(node);
		}
		if(node.parent == null)
			root = node;
	}

	/**
	 * @param Node<K,V>
	 */
	public void checkBalance(Node<K,V> node) {
		int leftHeight,rightHeight;
		if(node.left == null)
			leftHeight = -1;
		else
			leftHeight = node.left.height();
		if(node.right == null)
			rightHeight = -1;
		else
			rightHeight = node.right.height();
		if((leftHeight - rightHeight > 1) || (leftHeight - rightHeight < -1))
			rotate(node);
		if(node.parent == null)
			return;
		checkBalance(node.parent);
	}

}

