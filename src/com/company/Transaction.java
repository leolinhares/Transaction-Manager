package com.company;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by thiagoisaias on 5/9/16.
 * Modified by leolinhares on 5/10/16.
 */
public class Transaction {

    private String name;
    private Node currentNode;
    private int timestamp;
    private ArrayList<Pair<DataItem,String>> blockedItens;

    public Transaction(TR_Graph tr_graph){
        // 0 seria o no inicial
        currentNode = tr_graph.graph.get(0);
        blockedItens = new ArrayList<>();
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

    public ArrayList<Pair<DataItem, String>> getBlockedItens() {
        return blockedItens;
    }

    public void setBlockedItens(ArrayList<Pair<DataItem, String>> blockedItens) {
        this.blockedItens = blockedItens;
    }

    @Override
    public String toString() {
        return "Transaction{" + name + '}';
    }
}
