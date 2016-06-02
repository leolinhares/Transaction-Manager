package com.company;

/**
 * Created by thiagoisaias on 6/1/16.
 */
public class DataItem {
    private int id;
    private String name;
    private String locktype; //Current lockType

    @Override
    public String toString() {
        return "DataItem{" +
                "name='" + name + '\'' +
                '}';
    }

    public DataItem(int id, String name){
        this.id = id;
        this.name = name;
        this.locktype = "U";
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
}
