package com.example.demo.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex { 

    private Person label;
    private Set<Edge> edges; //collection of edges to neighbors 

    public Vertex(Person pageObject) {
        this.label = pageObject;
        edges = new HashSet<>();
    }     

    public Person getLabel() {
		return label;
	}

	public void setLabel(Person label) {
		this.label = label;
	}

	public Set<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}

	public boolean addEdge(Edge edge){
        return edges.add(edge);
    }

  

    //todo override hashCode()
    
}

