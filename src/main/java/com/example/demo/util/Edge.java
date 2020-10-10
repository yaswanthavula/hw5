package com.example.demo.util;

public class Edge {

    private Vertex to; 
    private int weight;

    public Edge(Vertex to, int weight) {
        super();
        this.to = to;
        this.weight = weight;
    }

    public Vertex getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }   

    //todo override hashCode()
}