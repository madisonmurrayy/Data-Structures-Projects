package assign08;

public class BinaryNode<T>{

	
	// representation of a binary node
	private T element;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	// additional member used only for generating DOT format
	public int dotLabel;
	
	public BinaryNode(T element,
			BinaryNode<T> leftChild, BinaryNode<T> rightChild) {
		this.element = element;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		//this.dotLabel = dotLabelCount++;
	}
	
	//will they be needed? who knows?
	
	public void setThisNode(T data) {
		this.element = data;
	}
	
	public void setRightChild(BinaryNode<T> node) {
		this.rightChild = node;
	}
	
	public void setLeftChild(BinaryNode<T> node) {
		this.leftChild = node;
	}
	
	public T getData() {
		return element;
	}
	
	public T getRightNodeData() {
		return this.rightChild.element;
	}
	
	public T getLeftNodeData() {
		return this.leftChild.element;
	}
	
	public BinaryNode<T> getRightChild(){
		return this.rightChild;			
	}
	
	public BinaryNode<T> getLeftChild(){
		return this.leftChild;			
	}
	
	/**
	 * @return a string containing all of the edges in the tree rooted at "this"
	 *         node, in DOT format
	 */
	public String generateDot() {
		String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
		if (leftChild != null) {
			ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
		}
		if (rightChild != null) {
			ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
		}

		return ret;
	}
}