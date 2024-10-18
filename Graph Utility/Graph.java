package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set
 * of edges). The graph is generic and each vertex has generic data attached to
 * it.
 * 
 * @author Erin Parker, Madison Murray, and Zoe Linn
 * @version March 18, 2024
 */
public class Graph<Type> {

	public boolean cyclic = false;
	// the graph -- a set of vertices (vertex data(generic) mapped to Vertex
	// instance)
	private HashMap<Type, Vertex<Type>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<Type, Vertex<Type>>();
	}

	/**
	 * returns the vertex connected to certain data
	 * 
	 * @param data
	 * @return
	 */
	public Vertex<Type> getVertex(Type data) {
		return vertices.get(data);
	}

	/**
	 * resets each vertices visited variable as false
	 * 
	 * @param sources      - a list of sources
	 * @param destinations - a list of destinations
	 */
	public void resetVertices(List<Type> sources, List<Type> destinations) {
		for (Type v : sources) {
			if (vertices.containsKey(v)) {
				vertices.get(v).visited = false;
			}
		}
		for (Type v : destinations) {
			if (vertices.containsKey(v)) {
				vertices.get(v).visited = false;
			}
		}
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" to the
	 * vertex with name "name2". (If either vertex does not already exist in the
	 * graph, it is added.)
	 * 
	 * @param data1 - data for source vertex
	 * @param data2 - data for destination vertex
	 */
	public void addEdge(Type data1, Type data2) {
		Vertex<Type> vertex1;

		// if vertex already exists in graph, get its object
		if (vertices.containsKey(data1))
			vertex1 = vertices.get(data1);
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<Type>(data1);
			vertices.put(data1, vertex1);
		}

		Vertex<Type> vertex2;
		if (vertices.containsKey(data2))
			vertex2 = vertices.get(data2);
		else {
			vertex2 = new Vertex<Type>(data2);
			vertices.put(data2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2 and increases indegree of the
		// vertex being pointed to by vertex 1
		vertex1.addEdge(vertex2);
		vertex2.indegree++;
	}

	/**
	 * Generates the DOT encoding of this graph as string, which can be pasted into
	 * http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");

		// for every vertex
		for (Vertex<Type> v : vertices.values()) {
			// for every edge
			Iterator<Edge<Type>> edges = v.edges();
			while (edges.hasNext())
				dot.append("\t\"" + v.getData() + "\" -> \"" + edges.next() + "\"\n");

		}

		return dot.toString() + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Vertex<Type> v : vertices.values())
			result.append(v + "\n");

		return result.toString();
	}
}