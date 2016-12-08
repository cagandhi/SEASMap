//header files
import java.util.*;

public class Graph {
		//stores vertices and maintains a arraylist of vertices
		private ArrayList<Vertex> vertices;
		
		//constructor
		public Graph(int numberVertices)
		{
			//initializing arrayList
			vertices = new ArrayList<Vertex>(numberVertices);
			
			//giving name to vertex
			for(int i=0;i<numberVertices;i++)
				vertices.add(new Vertex("v"+Integer.toString(i)));
		}
		
		//getter for arraylist of vertices
		public ArrayList<Vertex> getVertices() 
		{
			return vertices;
		}
		
		//returns a specific vertex from arraylist with index 'index'
		public Vertex getVertex(int index)
		{
			return vertices.get(index);
		}
		
		public void addEdge(int src, int dest, double weight)
		{
			//copy source vertex
			Vertex s = vertices.get(src);
			
			//initialize a new edge
			Edge new_edge = new Edge(vertices.get(dest),weight);
			
			//add the edge information to neighbours linkedlist
			s.neighbours.add(new_edge);
		}
	
	
}