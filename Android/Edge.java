package com.example.jaydmodi.dsa_project;

//serializable because Graph contains an arraylist of vertices and Vertex class contains arraylist of edges and graph object is stored in file
import java.io.Serializable;

//represents edges in graph
public class Edge implements Serializable
{
    //data members
  	public final Vertex target;
	public final double weight;

    //constructor
	public Edge(Vertex target, double weight){
		this.target = target;
		this.weight = weight;
	}
}