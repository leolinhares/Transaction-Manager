package com.company;

import org.javatuples.Pair;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;.
/**
 * Created by leolinhares on 01/06/2016.
 */
public class LockManager {

    // Transaction -> < (item, locktype), (item, locktype), ... >
    private HashMap<Transaction, ArrayList> lock_table = new HashMap<>();
    private ArrayList<Pair<DataItem,String>> tuple_list = new ArrayList();

    // Item -> < (transaction, locktype), (transaction, locktype), ... >
    private HashMap<DataItem, ArrayList> wait_queue = new HashMap<>();

    public void LS(Transaction t, DataItem i){
        //se adicionar na lock_table
        tuple_list.add(Pair.with(i,"S"));
        lock_table.put(t,tuple_list);


        System.out.println(lock_table.get(t));
        //se adicionar na wait_queue

    }

    public void LX(Transaction t, DataItem i){

    }

    public void U(Transaction t, DataItem i){

    }

}
