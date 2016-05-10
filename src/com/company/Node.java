package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class Node {

    private int id;
    private String description;
    private Map<String,Integer> edges = new HashMap<>();

    public Map<String, Integer> getEdges() {
        return edges;
    }

    public void setEdges(String action, int destinationNode) {
        this.edges.put(action,destinationNode);
    }

    public Node(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void printNode(){
        System.out.println(this.id);
        System.out.println(this.description);

    }
}
