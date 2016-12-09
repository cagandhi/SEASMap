package com.example.jaydmodi.dsa_project;

//Serializable because graph object contains arraylist of vertices
import java.io.Serializable;
import java.util.*;

//implements Serializable because graph object contains arraylist of Vertex and graph object is stored in file
public class Vertex implements Serializable, Comparable<Vertex>
{
    //data members
    public String name;
	public ArrayList<Edge> neighbours;
	public LinkedList<Vertex> path;
	public double minDistance = Double.POSITIVE_INFINITY;

    //because implements comparable interface
	public int compareTo(Vertex other) {
		return Double.compare(minDistance,other.minDistance);		
	}

    //constructor
	public Vertex(String name){
		this.name = name;
		neighbours = new ArrayList<Edge>();
		path = new LinkedList<Vertex>();
	}

    //setter for name of vertex
	public void setName(String name) {
        this.name = name;
	}

    //overrides toString() method so as to directly print vertex's name
	public String toString(){
        return name;
	}

    //getter for name
	public String getName() {
		return name;
	}
}
