package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

//TODO delete all stuff near the bottom

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author CS 2420 instructors, Madison Murray, and Zoe Linn
 * @version March 18, 2024
 */
public class GraphUtility {

	private static boolean connected;

	/**
	 * This method uses the recursive depth-first search algorithm to determine
	 * whether there is a path from the vertex with srcData to the vertex with
	 * dstData in the graph. Throws an IllegalArgumentException if there does not
	 * exist a vertex in the graph with srcData, and likewise for dstData.
	 * 
	 * @param <Type>       - generic data attached to a vertex
	 * @param sources      - list of sources to be connected with destinations
	 * @param destinations - list of sources that are connected from sources
	 * @param srcData      - the source data to be checked if connected with
	 *                     destination data
	 * @param dstData      - the destination data to be checked if it was connected
	 *                     from source data
	 * @return - true if connected, false if not
	 * @throws IllegalArgumentException is thrown if srcData or dstData is not found
	 *                                  in the lists of sources and destinations
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {

		// creates a graph using these sources and destinations
		Graph<Type> graph = createGraph(sources, destinations);

		// if lists aren't same size, throw exception
		if (!(sources.size() == destinations.size())) {
			throw new IllegalArgumentException("lists need to be the same size");
		}

		// checks if the srcData and dstData vertices exist within the sources and
		// destinations
		vertexExists(sources, destinations, srcData, dstData);

		// call DFS with the vertex of source and destination
		dfs(graph.getVertex(srcData), graph.getVertex(dstData), graph);

		// resets all vertices(sources and destinations) to be marked as not visited
		graph.resetVertices(sources, destinations);

		// dfs returns a boolean connected true if the sources/destinations were
		// connected, false if not
		if (!connected) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Depth first search recursive private method used in areConnected
	 * 
	 * @param <Type>    - generic data attached to a vertex
	 * @param current   - the vertex that is currently being searched
	 * @param dstVertex - the vertex we are looking for
	 * @param graph     - the graph we are searching
	 */
	private static <Type> void dfs(Vertex<Type> current, Vertex<Type> dstVertex, Graph<Type> graph) {
		// if the vertex (current) is the destination, return true
		connected = false;
		current.visited = true;

		if (current.equals(dstVertex)) {
			connected = true;
			return;
		}

		List<Vertex<Type>> neighbors = current.getNeighbors();

		for (int i = 0; i < neighbors.size(); i++) {
			if (connected == true) {
				return;
			}

			// if this neighbor hasn't been visited, call it recursively
			if (!(neighbors.get(i).visited)) {
				dfs(neighbors.get(i), dstVertex, graph);
			} else {
				graph.cyclic = true;
			}

		}

	}

	/**
	 * This method uses the breadth-first search algorithm to find a shortest path
	 * from the vertex with srcData to the vertex with dstData in the graph. Throws
	 * an IllegalArgumentException if there does not exist a vertex in the graph
	 * with srcData, and likewise for dstData. Also, throws an
	 * IllegalArgumentException if there does not exist a path between the two
	 * vertices.
	 * 
	 * @param <Type>       - generic data attached to a vertex
	 * @param sources      - list of sources to be connected with destinations
	 * @param destinations - list of sources that are connected from sources
	 * @param srcData      - the source data to be checked if connected with
	 *                     destination data
	 * @param dstData      - the destination data to be checked if it was connected
	 *                     from source data
	 * @return a list of vertices in order of the shortest path of our graph
	 * @throws IllegalArgumentException is thrown if srcData or dstData is not found
	 *                                  in the lists of sources and destinations
	 * @throws IllegalArgumentException if a path between vertices does not exist
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) throws IllegalArgumentException {

		// creates a graph using these sources and destinations
		Graph<Type> graph = createGraph(sources, destinations);

		// checks if the srcData and dstData vertices exist within the sources and
		// destinations
		vertexExists(sources, destinations, srcData, dstData);

		List<Type> list = new ArrayList<Type>();
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();

		// add srcData to the queue
		queue.offer(graph.getVertex(srcData));

		// mark srcData as visited
		graph.getVertex(srcData).visited = true;

		Vertex<Type> current = null;

		// while vertices are in the queue...
		while (queue.size() != 0) {

			current = queue.poll();

			// exits while loop when we find our destination
			if (current.getData().equals(dstData)) {
				break;
			}

			List<Vertex<Type>> neighbors = current.getNeighbors();

			// checks each neighbor of a vertex, marks them as visited, keeps track of
			// parent vertex, and adds those vertices to the queue
			for (int i = 0; i < neighbors.size(); i++) {
				
				if (!(neighbors.get(i).visited)) {
					queue.offer(neighbors.get(i));
					neighbors.get(i).visited = true;
					neighbors.get(i).cameFrom = current.getData();
				}
			}
			
		}

		Vertex<Type> v = current;

		if (!(v.equals(graph.getVertex(dstData)))) {
			throw new IllegalArgumentException("path between two verticies does not exist");
		}

		// starting with our current vertex, takes the vertex that our current vertex
		// came from and adds it to a list
		list.add(current.getData());
		while (v.cameFrom != null) {
			list.add(v.cameFrom);
			v = graph.getVertex(v.cameFrom);
		}

		List<Type> temp = new ArrayList<Type>();

		// flips the list to be in order
		for (int i = list.size() - 1; i >= 0; i--) {
			temp.add(list.get(i));
		}

		return temp;
	}

	/**
	 * This method uses the topological sort algorithm to generate a sorted ordering
	 * of the vertices in the graph. Note that a graph may have more than one valid
	 * ordering, and any such ordering is accepted. Throws an
	 * IllegalArgumentException if the graph contains a cycle (recall topological
	 * sort works only on acyclic graphs).
	 * 
	 * @param <Type>
	 * @param sources      - list of sources to be connected with destinations
	 * @param destinations - list of sources that are connected from sources
	 * @return a sorted list of vertices sorted from least indegrees to most
	 *         indegrees
	 * @throws IllegalArgumentException is thrown if the graph contains a cycle
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		List<Type> list = new ArrayList<Type>();
		Set<Vertex<Type>> set = new HashSet<Vertex<Type>>();
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();

		Graph<Type> graph = createGraph(sources, destinations);

		// adds all vertices in sources to a set
		for (Type s : sources) {
			if (!(set.contains(s))) {
				set.add(graph.getVertex(s));
			}
		}

		// adds all vertices in destinations to a set
		for (Type s : destinations) {
			if (!(set.contains(s))) {
				set.add(graph.getVertex(s));
			}
		}

		// adds all vertices of indegree 0 to the topological sort list and adds them to
		// the queue
		for (Vertex<Type> v : set) {
			if (v.indegree == 0) {
				list.add(v.getData());
				queue.offer(v);
			}
		}

		Vertex<Type> current = null;

		// while the queue is not empty...
		while (!(queue.isEmpty())) {
			current = queue.poll();
			List<Vertex<Type>> neighbors = current.getNeighbors();

			// subtracts the indegree of the current vertex's neighbors, adds them to the
			// list if their indegree is 0, and adds them to the queue to check their
			// neighbors
			for (int i = 0; i < neighbors.size(); i++) {
				neighbors.get(i).indegree--;
				if (neighbors.get(i).indegree == 0) {
					list.add(neighbors.get(i).getData());
					queue.offer(neighbors.get(i));
				}
			}

		}

		// cycle is found if the number of vertices is not equal to the size of our
		// topological sort list
		if (!(list.size() == set.size())) {
			throw new IllegalArgumentException("Graph has cycles so sort cannot be performed");

		}

		return list;
	}

	/**
	 * checks if the srcData and dstData exists within the list of sources and
	 * destinations
	 * 
	 * @param <Type>       - generic data attached to a vertex
	 * @param sources      - list of sources to be connected with destinations
	 * @param destinations - list of sources that are connected from sources
	 * @param srcData      - the source data to be checked if connected with
	 *                     destination data
	 * @param dstData      - the destination data to be checked if it was connected
	 *                     from source data
	 * @return - true if the vertex data exists, false if not
	 * @throws IllegalArgumentException throws if the srcData/dstData does not exist
	 *                                  within the lists
	 */
	private static <Type> boolean vertexExists(List<Type> sources, List<Type> destinations, Type srcData,
			Type dstData) {
		boolean found = false;

		// search for srcData
		for (Type source : sources) {
			if (source.equals(srcData)) {
				found = true;
				break;
			}

		}
		for (Type source : destinations) {
			if (source.equals(srcData)) {
				found = true;
				break;
			}
		}

		// find src and make sure it exists - if not throw exception
		if (found == false) {
			throw new IllegalArgumentException("srcData is not in the list of sources");
		}

		found = false;
		// search for dstData
		for (Type source : destinations) {
			if (source.equals(dstData)) {
				found = true;
				break;
			}
		}

		for (Type source : sources) {
			if (source.equals(dstData)) {
				found = true;
				break;
			}
		}

		// find dst and make sure it exists - if not throw exception
		if (found == false) {
			throw new IllegalArgumentException("dstData is not in the list of desitnations");
		}

		return found;
	}

	/**
	 * Creates and returns a graph object using the list of sources and destinations
	 * 
	 * @param <Type>       - generic data attached to a vertex
	 * @param sources      - list of sources to be connected with destinations
	 * @param destinations - list of sources that are connected from sources
	 * @return
	 */
	private static <Type> Graph<Type> createGraph(List<Type> sources, List<Type> destinations) {
		// create a graph from imported data
		Graph<Type> graph = new Graph<Type>();
		// add edges to graph from sources and destinations
		for (int i = 0; i < sources.size(); i++) {
			graph.addEdge(sources.get(i), destinations.get(i));
		}

		return graph;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges specified in
	 * the given DOT file (e.g., "a -> b"). Assumes that the vertex data type is
	 * String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments --accepts one edge per line or edges terminated
	 * with ; --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename     - name of the DOT file
	 * @param sources      - empty ArrayList, when method returns it is a valid
	 *                     "sources" list that can be passed to the public methods
	 *                     in this class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *                     "destinations" list that can be passed to the public
	 *                     methods in this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while (scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if (line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if (edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while (scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for (int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if (vertex1.equals("")) {
					continue;
				}

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if (vertex2.equals("")) {
					continue;
				}

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if (substring[substring.length - 1].indexOf("}") >= 0) {
				break;
			}

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}

	
}
