
import java.util.*;
public class Dijkstra{

  public static void main(String[] arg){
		
		Dijkstra obj = new Dijkstra();
		
		// Create a new graph.
		Graph g = new Graph(59);
		
		// graph for ground floor
        // courtyard stairs - 15m //else stairs - 20m
		// 0th node not taken for index matching

        g.addEdge(1, 2, 5);
        g.addEdge(2, 1, 5); g.addEdge(2, 3, 5); g.addEdge(2, 23, 25); g.addEdge(2, 57, 20); 
        g.addEdge(3, 2, 5); g.addEdge(3, 4, 10);
        g.addEdge(4, 3, 10); g.addEdge(4, 5, 5);
        g.addEdge(5, 4, 5); g.addEdge(5, 6, 5); g.addEdge(5, 28, 15);
        g.addEdge(6, 5, 5); g.addEdge(6, 7, 10);
        g.addEdge(7, 6, 10); g.addEdge(7, 8, 0); g.addEdge(7, 9, 20); g.addEdge(7, 11, 10); g.addEdge(7, 12, 35);
        g.addEdge(8, 7, 0); //don't consider lift connections (edges)
        g.addEdge(9, 7, 20); g.addEdge(9, 10, 10); g.addEdge(9, 11, 20); g.addEdge(9, 12, 45);
        g.addEdge(10, 9, 10); g.addEdge(10, 32, 20);
        g.addEdge(11, 9, 20); g.addEdge(11, 7, 10); g.addEdge(11, 12, 35);
        g.addEdge(12, 7, 35); g.addEdge(12, 9, 45); g.addEdge(12, 11, 35); g.addEdge(12, 14, 15);
        g.addEdge(13, 12, 10); g.addEdge(13, 14, 5); g.addEdge(13, 15, 10); g.addEdge(13, 36, 20);
        g.addEdge(14, 13, 5);
        g.addEdge(15, 13, 10); g.addEdge(15, 16, 2);
        g.addEdge(16, 15, 2); g.addEdge(16, 17, 10);
        g.addEdge(17, 16, 10); g.addEdge(17, 18, 2);
        g.addEdge(18, 17, 2); g.addEdge(18, 19, 21); g.addEdge(18, 20, 21); g.addEdge(18, 22, 21);
        g.addEdge(19, 18, 21); g.addEdge(19, 20, 10); g.addEdge(19, 22, 10);
        g.addEdge(20, 18, 21); g.addEdge(20, 19, 10); g.addEdge(20, 21, 5); g.addEdge(20, 22, 10);
        g.addEdge(21, 20, 5);
        g.addEdge(22, 18, 21); g.addEdge(22, 19, 10); g.addEdge(22, 20, 10); g.addEdge(22, 23, 10); g.addEdge(22, 55, 20);
        g.addEdge(23, 2, 25); g.addEdge(23, 22, 10);
		
        //graph for 1st floor
        g.addEdge(24, 31, 66.6); g.addEdge(24, 57, 2.4);
        g.addEdge(25, 26, 13.5); g.addEdge(25, 57, 12);
        g.addEdge(26, 25, 13.5); g.addEdge(26, 27, 13.5);
        g.addEdge(27, 26, 13.5); g.addEdge(27, 28, 4.5);
        g.addEdge(28, 27, 4.5); g.addEdge(28, 29, 9); g.addEdge(28, 5, 20); g.addEdge(28, 58, 0);
        g.addEdge(29, 28, 9); g.addEdge(29, 30, 16.5); g.addEdge(29, 33, 16.5); g.addEdge(29, 34, 14.1);
        g.addEdge(30, 29, 16.5); g.addEdge(30, 31, 12); g.addEdge(30, 33, 24); g.addEdge(30, 34, 21.6);
        g.addEdge(31, 24, 66.6); g.addEdge(31, 30, 12); g.addEdge(31, 32, 2.4);
        g.addEdge(32, 31, 2.4); g.addEdge(32, 10, 20); g.addEdge(36, 13, 20);
        g.addEdge(33, 29, 16.5); g.addEdge(33, 30, 24); g.addEdge(33, 34, 7.2);
        g.addEdge(34, 29, 14.1); g.addEdge(34, 30, 21.6); g.addEdge(34, 33, 7.2); g.addEdge(34, 35, 7.2);
        g.addEdge(35, 34, 7.2); g.addEdge(35, 36, 12);
        g.addEdge(36, 35, 12); g.addEdge(36, 37, 12); g.addEdge(36, 38, 1.5); 
        g.addEdge(37, 36,12); g.addEdge(37, 38, 4.5);
        g.addEdge(38, 36, 1.5); g.addEdge(38, 37, 4.5); g.addEdge(38, 39, 4.5);
        g.addEdge(39, 38,4.5); g.addEdge(39, 40, 4.5);
        g.addEdge(40, 39,4.5); g.addEdge(40, 41, 4.5);
        g.addEdge(41, 40,4.5); g.addEdge(41, 42, 4.5);
        g.addEdge(42, 41,4.5); g.addEdge(42, 43, 4.5);
        g.addEdge(43, 42, 4.5); g.addEdge(43, 44, 0); g.addEdge(43, 45, 4.5);
        g.addEdge(44, 43, 0);
        g.addEdge(45, 43,4.5); g.addEdge(45, 46, 4.5);
        g.addEdge(46, 45,4.5); g.addEdge(46, 47, 4.5);
        g.addEdge(47, 46,4.5); g.addEdge(47, 48, 4.5);
        g.addEdge(48, 47,4.5); g.addEdge(48, 49, 4.5);
        g.addEdge(49, 48,4.5); g.addEdge(49, 50, 4.5);
        g.addEdge(50, 49,4.5); g.addEdge(50, 51, 6);
        g.addEdge(51, 50, 6); g.addEdge(51, 52, 5); g.addEdge(51, 54, 4.8);
        g.addEdge(52, 51,5); g.addEdge(52, 53, 5);
        g.addEdge(53, 52,5); 
        g.addEdge(54, 51,4.8); g.addEdge(54, 55, 7.2);
        g.addEdge(55, 54,7.2);  g.addEdge(55, 56,14.4); g.addEdge(55, 22, 20);
        g.addEdge(56, 55,14.4); g.addEdge(56, 57, 4.8);
        g.addEdge(57, 24,2.4); g.addEdge(57, 25, 12); g.addEdge(57, 56,4.8);
        g.addEdge(58, 28, 0);

		// Calculate shortest path
		obj.calculate(g.getVertex(1));	

		// Print shortest path
		for(Vertex v:g.getVertices()){
             if(v.getName().equals("v0"))
            	 	continue;
			System.out.print("Vertex - "+v+" , Dist - "+ v.minDistance+" , Path - ");
			for(Vertex pathvert:v.path) {
				System.out.print(pathvert+" ");
			}
			System.out.println(""+v);
		}
	}

	public void calculate(Vertex source){
		
		source.minDistance = 0;
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);
		
		while(!queue.isEmpty()){
			
			Vertex u = queue.poll();
		
			for(Edge neighbour:u.neighbours){
				Double newDist = u.minDistance+neighbour.weight;
				
				if(neighbour.target.minDistance>newDist){
					// Remove the node from the queue to update the distance value.
					queue.remove(neighbour.target);
					neighbour.target.minDistance = newDist;
					
					// Take the path visited till now and add the new node.s
					neighbour.target.path = new LinkedList<Vertex>(u.path);
					neighbour.target.path.add(u);
					
					//Re-enter the node with new distance.
					queue.add(neighbour.target);					
				}
			}
		}
	}

}