package com.company;

import java.util.ArrayList;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class Node {

    String current;
    ArrayList<Node> next = new ArrayList<Node>();
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public Node(String a, ArrayList<Node> b){
        this.current = a;
        this.next = b;
    }
}
