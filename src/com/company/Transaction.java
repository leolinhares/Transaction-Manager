package com.company;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class Transaction {

    private String name;
    private Node currentNode;

    public Transaction(TR_Graph tr_graph){
        // 0 seria o no inicial
        currentNode = tr_graph.graph.get(0);
    }

    public String getName() {
        return name;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Transaction(String name){
        this.name = name;
    }

}
