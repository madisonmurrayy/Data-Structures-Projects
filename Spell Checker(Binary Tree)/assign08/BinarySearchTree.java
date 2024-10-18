package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a generically-typed binary search tree.
 * 
 * For every node X, all elements in X's left subtree are smaller than X.element
 * and all elements in X's right subtree are larger then X.element.
 * 
 * @author Eric Heisler
 * @version June 23, 2023
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	//BinaryNode<Type> binaryNode;
	
	// representation of a binary search tree
	public BinaryNode<Type> root;
	private int size;
	
	// additional member used only for generating DOT format
	private int dotLabelCount;

	public BinarySearchTree() {
		root = null;
		size = 0;
		dotLabelCount = 0;
	}
	
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually inserted); otherwise, returns false
	 */
	@Override
	public boolean add(Type item) {
		//if root is null, root = item, return true
		if(root==null) {
			root = new BinaryNode<Type>(item, null, null);
			size++;
			return true;
		}
		
        // - Temp node = root
		BinaryNode<Type> temp = root;
		BinaryNode<Type> current = null;
		boolean child = false;
		
		
		
        // - While temp is not null
		while(temp != null) {
			current = temp;

			if(temp.getData().compareTo(item) < 0) {
				temp = temp.getRightChild();
				child = false;
			} else if(temp.getData().compareTo(item) > 0) {
				temp = temp.getLeftChild();
				child = true;
			} else {
				return false;
			}
		}
		
		if(child == true) {
			current.setLeftChild(new BinaryNode<Type>(item, null, null));
		} else {
			current.setRightChild(new BinaryNode<Type>(item, null, null));
		}
		
		size++;
		return true;
		
		
        // - Compare item to add with node 
		// - If equal to, return false!
        // - If less than, that node’s.left = new Node(num)
        // - If greater than, opp.
		
		// - increase size
		
		//return true if item was added
		//return false if item was not and was already in set
		
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items - the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually inserted); otherwise,
	 *         returns false
	 */
	@Override
	public boolean addAll(Collection<? extends Type> items) {
		//can we sort the collection and add in order?(what is bigO)
		
		boolean added = false;
		boolean returnType = false;
		
		
		//use add on each item in collection IF it's not already there
		//increase size for each time that add returned true(already done in add)
		
		for(Type item : items) {
			added = add(item);
			if(added = true) {
				returnType = true;
			}
		}
		
		//anytime true is returned once by add, return true overall?
		
		//return true if any new stuff was added -> keep counter of 
		//amt added = items (if add==true, counter++)
		//false if everything was already in there
		
		return returnType;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		//just make a new empty object
		//size to 0
		size = 0;
		root = null;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input item;
	 *         otherwise, returns false
	 */
	@Override
	public boolean contains(Type item) {
		//temp node = root
		int count = 0;
		BinaryNode<Type> temp = root;

		//while temp is not null
		while(temp != null) {
			count++;
			
			if(temp.getData().compareTo(item) < 0) {
				temp = temp.getRightChild();
				
			} else if(temp.getData().compareTo(item) > 0) {
				temp = temp.getLeftChild();
			} else {
				//System.out.println("had to count " + count + "times");

				return true;
			}
		}
		//if contains is greater than temp, temp = right child
		//if contains is less than temp, temp = left child
		//if contains is == to temp, return true
		//when temp is null, return false(we're at the end)
		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item in
	 * this set that is equal to it.
	 * 
	 * @param items - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		
		boolean contains = true;
		
		for(Type item: items) {
			contains = contains(item);
			if(contains == false) {
				return false;
			}
		}
		
		//use contains on each item in collection
		//return true if ALL items are in set?
		//return false if ALL items WEREN'T in set?
		
		//if only one item is in the set from collection does it pass?
		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type first() throws NoSuchElementException {
		//if isEmpty true, throw
		if(root == null) {
			throw new NoSuchElementException("Set is empty!");
		}
		
		
		//findMin of binaryTree (below)
		BinaryNode<Type> temp = root;
		BinaryNode<Type> current = null;
		
		while(temp != null) {
			current = temp;
			temp = temp.getLeftChild();
		}
		
		return current.getData();
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		//check if size is 0
		if(size == 0) {
			return true;
		}	
		return false;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException if the set is empty
	 */
	@Override
	public Type last() throws NoSuchElementException {
		// if isEmpty true, throw
		if (root == null) {
			throw new NoSuchElementException("Set is empty!");
		}

		// findMin of binaryTree (below)
		BinaryNode<Type> temp = root;
		BinaryNode<Type> current = null;

		while (temp != null) {
			current = temp;
			temp = temp.getRightChild();
		}

		return current.getData();
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         the input item was actually removed); otherwise, returns false
	 */
	@Override
	public boolean remove(Type item) {
//	    - HERE’S WHAT’S GONNA HAPPEN
//	        - Set temp node to root
//        - Create a while loop:
//        - If temp = item, remove (can be separate method?)
//        - If item is bigger than rightmost child, return
//        - If item is smaller than leftmost child, return
		BinaryNode<Type> temp = root;
		BinaryNode<Type> parent = null;
	
		
		while(temp != null) {
			if(temp.getData().compareTo(item) == 0) {
			removeHelper(temp, parent);
			return true;
			} else if(temp.getData().compareTo(item) > 0) {
				if(temp.getLeftChild() == null) {
					return false;
				}
				parent = temp;
				temp = temp.getLeftChild();
			} else if(temp.getData().compareTo(item) < 0) {

				if(temp.getRightChild() == null) {
					return false;
				}
				parent = temp;
				temp = temp.getRightChild();
			}
		}
		return false;
	}
	
	private void removeHelper(BinaryNode<Type> item, BinaryNode<Type> parent) {
//System.out.println("paret: " + parent.getData().toString() + ", item: " + item.getData().toString());
		//	    - removing: 
		// - Removing a leaf: find node’s reference and remove(parent.left = null)
//		if(item.getData().equals(root.getData())) {
//			if(item.getRightChild() != null) {
//				this.root = item.getRightChild();
//				item.setThisNode(null);
//			}
//		}
		if(size == 1) {
			this.clear();
			return;
		}
		
		if(parent == null) {
			System.out.println("parent is null");
			BinarySearchTree<Type> subtree = new BinarySearchTree<Type>();
			subtree.root = item.getRightChild();
			Type data = subtree.first();
			remove(subtree.first());
			item.setThisNode(data);
			return;
		}
		
		
		BinaryNode<Type> replacement;
		
		if(item.getRightChild() == null && item.getLeftChild() == null) {
			size--;
			if(item.getData().compareTo(parent.getData()) > 0) {
				parent.setRightChild(null);
				return;
			}
			parent.setLeftChild(null);
			
		} else if(item.getRightChild() == null || item.getLeftChild() == null) {
			size--;
			//find the single child of item
			if(item.getRightChild() == null) {
				replacement = item.getLeftChild();
			}else {
				replacement = item.getRightChild();
			}
			//replace item with single child
			if(parent.getData().compareTo(item.getData())<0) {
				parent.setRightChild(replacement);
			} else {
				parent.setLeftChild(replacement);
			}
		} else {
			BinarySearchTree<Type> subtree = new BinarySearchTree<Type>();
			subtree.root = item.getRightChild();
			Type data = subtree.first();
			remove(subtree.first());
			item.setThisNode(data);
			
		}
//		    - Removing w/ one child: connect node’s parent to its child(parent.left = node’s child)
//		    - Removing two children: replace with min of right subtree or max of left subtree(use one or other if there is no successor/predecessor)
//		        - Create successor/predecessor variable 
//		        - this.data = successor.data / predecessor data

		//meaning --> MAKE SUCCESSOR/PRECESSOR VARIABLE
		//how? private method to find min of right subtree(successor) or max of left(predecessor)
		
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is, if
	 *         any item in the input collection was actually removed); otherwise,
	 *         returns false
	 */
	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		//remove all items in collection
		boolean removed = false;
		boolean returnType = false;
		
		for(Type item : items) {
			removed = remove(item);
			if(removed == true) {
				returnType = true;
			}
		}
		return returnType;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<Type> toArrayList() {		
		ArrayList<Type> list = new ArrayList<Type>();
		return inorder(root, list);
	}
	
	private ArrayList<Type> inorder(BinaryNode<Type> node, ArrayList<Type> list) {
		if(node == null) {
			return list;
		}
		
		inorder(node.getRightChild(), list);
		list.add(node.getData());
		inorder(node.getLeftChild(), list);
		
		list.sort(Comparator.naturalOrder());
		
		return list;
	}

	/**
	 * Write a DOT representation of this BST to file.
	 * 
	 * @param filename
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Tree {\n\tnode [shape=record]\n");

			if(root == null)
				out.println("");
			else
				out.print(root.generateDot());

			out.println("}");
			out.close();
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}

}