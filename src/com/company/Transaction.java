package com.company;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class Transaction {

    int id;
    String name;

    public Transaction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Transaction(String name){
        this.name = name;
    }
}
