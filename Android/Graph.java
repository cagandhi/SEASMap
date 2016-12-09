package com.example.jaydmodi.dsa_project;

//implements Serializable interface becausze graph's object is to be stored in a file
import java.io.Serializable;
import java.util.*;

public class Graph implements Serializable {

	//arraylist of vertices of graph
  	private ArrayList<Vertex> vertices;
	private String name;

	public Graph(String Gname,String Vname){

		name=Gname;

		//initialize vertices arraylist
		vertices = new ArrayList<Vertex>();

		//add 0th & 1st vertex
		vertices.add(new Vertex("empty"));
		vertices.add(new Vertex(Vname));
    }

	//add edge in graph between two vertices
	public void addEdge(int src, int dest, double weight){
		Vertex s = vertices.get(src);
		Vertex d = vertices.get(dest);

		//make 2 edges from source to destination and from destination to src
		Edge new_edgeS = new Edge(vertices.get(dest),weight);
		Edge new_edgeD = new Edge(vertices.get(src),weight);

		//add the neighbouring vertex to vertex s neighbours arraylist
		s.neighbours.add(new_edgeS);
		d.neighbours.add(new_edgeD);
	}

	//Dijkstra's Algorithm for calculating shortest path
	public LinkedList<Vertex> calculateShortestPath(Vertex src,Vertex dest) {
		for (Vertex v : vertices) {
			//initialize distances of all vertices from source to positive infinity
			v.minDistance = Double.POSITIVE_INFINITY;

			//make a new linkedlist for each vertex's path
			v.path = new LinkedList<Vertex>();
		}

		//initialize src distance to 0 from src
		src.minDistance = 0;

		//to implement heap data structure and for extractMin() operation
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();

		//add src to priorityQueue
		queue.add(src);

		while (!queue.isEmpty()) {

			//removes and retrieves head which is node of highest priority in priorityQueue
			Vertex u = queue.poll();

			for (Edge neighbour : u.neighbours) {

				//update the distance of the neighbour of vertex 'u'
				Double newDist = u.minDistance + neighbour.weight;

				//if the distance of neighbour in distance map is greater than newDist
				if (neighbour.target.minDistance > newDist) {

					//remove node from the priorityQueue and update its distance value
					queue.remove(neighbour.target);

					//update the minDistance attribute of a Vertex to newDist
					neighbour.target.minDistance = newDist;

					//add the path to reach 'u' as the path to reach neighbour
					neighbour.target.path = new LinkedList<Vertex>(u.path);

					//append vertex 'u' to neighbour's path to reach neighbour
					neighbour.target.path.add(u);

					//add the neighbour node to queue with updated distance
					queue.add(neighbour.target);
				}
			}
		}

		//return path of destination 'dest'
		return dest.path;
	}

	//get vertex from the arraylist 'vertices'
	public Vertex getVertex(int vert) {
		return vertices.get(vert);
	}

	//return the entire ArrayList vertices
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	//add vertex to graph, i.e., add a new vertex to arrayist vertices
	public void addVertex(String name)
	{
		Vertex vNew = new Vertex(name);
		vertices.add(vNew);
	}

	public String getGraphName()
	{
		return name;
	}
}
