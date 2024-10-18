package assign07;

/**
 * This class represents an edge between a source vertex and a destination
 * vertex in a directed graph.
 * 
 * The source of this edge is the Vertex whose object has an adjacency list
 * containing this edge.
 * 
 * @author Erin Parker, Zoe Linn, and Madison Murray
 * @version March 18, 2024
 */
public class Edge<Type> {

	// source vertex of this edge
	public Vertex<Type> src;
	// destination vertex of this edge
	private Vertex<Type> dst;

	/**
	 * Creates an Edge object, given the Vertex that is the destination. (The Vertex
	 * that stores this Edge object is the source.)
	 * 
	 * @param dst - the destination Vertex
	 */
	public Edge(Vertex<Type> dst) {
		this.dst = dst;
	}

	/**
	 * gets the source of this edge
	 * 
	 * @return the source vertex
	 */
	public Vertex<Type> getSource() {
		return this.src;
	}

	/**
	 * @return the destination Vertex of this Edge
	 */
	public Vertex<Type> getOtherVertex() {
		return this.dst;
	}

	/**
	 * Returns the name of the destination Vertex as a textual representation of
	 * this Edge.
	 */
	public Type getEdgeData() {
		return this.dst.getData();
	}
}