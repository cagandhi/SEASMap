import java.util.*;

public class Vertex implements Comparable<Vertex>
{
	//data members
	public String name;
	public ArrayList<Edge> neighbours;
	public LinkedList<Vertex> path;
	public double minDistance = Double.POSITIVE_INFINITY;

	//constructor
	public Vertex(String name){
		this.name = name;
		neighbours = new ArrayList<Edge>();
		path = new LinkedList<Vertex>();
	}
	
	//getters and setters
	public void setName(String name)
	{
		this.name = name;
	}
	public String toString(){
		return name;
	}	
	public String getName()
	{
		return name;
	}
	
	//for implementing comparable class in vertex
	public int compareTo(Vertex other){
		return Double.compare(minDistance,other.minDistance);		
	}
	
}