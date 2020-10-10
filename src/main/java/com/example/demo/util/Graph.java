package com.example.demo.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph{

    private Set<Vertex> vertices; //collection of all verices 

    public Graph() {
        vertices = new HashSet<>();
    } 

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }   

    public boolean addVertex(Vertex vertex){
        return vertices.add(vertex);
    }
}
