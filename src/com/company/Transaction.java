package com.company;

/**
 * Created by thiagoisaias on 5/9/16.
 * Modified by leolinhares on 5/10/16.
 */
public class Transaction {

    private String name;
    private Node currentNode;
    private int timestamp;

    public Transaction(TR_Graph tr_graph){
        // 0 seria o no inicial
        currentNode = tr_graph.graph.get(0);
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public String toString() {
        return "Transaction{" + name + '}';
    }
}
