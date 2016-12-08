//To represent the edges in the graph
public class Edge{
	
	//target of edge
	public final Vertex target;
	//weight of the edge which basically represents the distance of  1 node to other 
	public final double weight;
	
	//constructor
	public Edge(Vertex target, double weight){
		this.target = target;
		this.weight = weight;
	}
}