package assign07;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * This class represents a vertex (AKA node) in a directed graph.
 * 
 * @author Erin Parker, Madison Murray, and Zoe Linn
 * @version March 19, 2024
 */
public class Vertex<Type> {

	

	//used to id the Vertex
	private Type data;
	public boolean visited;
	//holds data of where vertex came from
	public Type cameFrom = null;
	public int indegree;
	public int num;

	// adjacency list
	private LinkedList<Edge<Type>> adj;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param name - string used to identify this Vertex
	 */
	public Vertex(Type data) {
		this.data = data;
		this.adj = new LinkedList<Edge<Type>>();
	}

	/**
	 * gets the data attached to this vertex
	 * 
	 * @return the data used to identify this Vertex
	 */
	public Type getData() {
		return data;
	}

	/**
	 * returns the number of edges using the size of a vertex's adjacency list
	 * 
	 * @return num of edges of this vertex
	 */
	public int numEdges() {
		return adj.size();
	}

	/**
	 * returns the edge from adj list at indicated index
	 * 
	 * @return
	 * 
	 */
	public Edge<Type> getEdge(int index) {
		return adj.get(index);
	}

	/**
	 * gets this vertex
	 * 
	 * @return this vertex
	 */
	public Vertex<Type> getVert() {
		return this;
	}

	/**
	 * gets a list of all neighbors attached to this vertex
	 * 
	 * @return all neighbors of this vertex in list form
	 */
	public List<Vertex<Type>> getNeighbors() {
		List<Vertex<Type>> neighbors = new ArrayList<>();
		
		for (int i = 0; i < adj.size(); i++) {
			neighbors.add(adj.get(i).getOtherVertex());
		}
		return neighbors;
	}

	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<Type> otherVertex) {
		Edge<Type> toAdd = new Edge<Type>(otherVertex);
		toAdd.src = this;
		adj.add(toAdd);
	}

	/**
	 * @return a iterator for accessing the edges for which this Vertex is the
	 *         source
	 */
	public Iterator<Edge<Type>> edges() {
		return adj.iterator();
	}

	//TODO delete commented stuff ?
	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = (String) data; 
		return s;
	}
}