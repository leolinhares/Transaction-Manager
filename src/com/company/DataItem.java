package com.company;

import org.javatuples.Pair;

import java.util.ArrayDeque;

/**
 * Created by thiagoisaias on 6/1/16.
 */
public class DataItem {
    private int id;
    private String name;
    private String locktype; //Current lockType
    private ArrayDeque<Pair<Transaction,String>> waitqueue;


    public DataItem(int id, String name){
        this.id = id;
        this.name = name;
        this.locktype = "U";
        this.waitqueue = new ArrayDeque<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocktype() {
        return locktype;
    }

    public void setLocktype(String locktype) {
        this.locktype = locktype;
    }

    public ArrayDeque<Pair<Transaction, String>> getWaitqueue() {
        return waitqueue;
    }

    public void setWaitqueue(ArrayDeque<Pair<Transaction, String>> waitqueue) {
        this.waitqueue = waitqueue;
    }

    @Override
    public String toString() {
        return "DataItem{" + name  + '}';
    }
}
