package com.company;

import org.javatuples.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by leolinhares on 01/06/2016.
 */
public class LockManager {

    // Transaction -> < (item, locktype), (item, locktype), ... >
    private HashMap<Transaction,Pair<DataItem,String>> lock_table = new HashMap<>();
    //private ArrayList<Pair<DataItem,String>> lock_tuple_list = new ArrayList();

    // Item -> < (transaction, locktype), (transaction, locktype), ... >
    private HashMap<DataItem, Queue> wait_queue = new HashMap<>();
    private ArrayDeque<Pair<Transaction,String>> queue_tuple_list = new ArrayDeque<>();

    public void LS(Transaction t, DataItem i){
        //se adicionar na lock_table
        if(i.getLocktype() == "U" || i.getLocktype() == "S"){
            lock_table.put(t,Pair.with(i,"S")); // Adiciona na key(transaction) t, o item i
            i.setLocktype("S");
        }
        else if(i.getLocktype() == "X"){ //E se a propria transacao t tiver o bloqueio sobre esse item?
            //Coloca a transaction t na waitqueue de i
        }
        else{
            //Error
        }
    }

    public void LX(Transaction t, DataItem i){

        if(i.getLocktype() == "U" || i.getLocktype() == "S" ){
            //Colocar na lock_table
            i.setLocktype("X");
        }
        else if(i.getLocktype() == "X"){ //E se a propria transacao t tiver o bloqueio sobre esse item?
            //Coloca a transaction t na waitqueue de i
        }
        else{
            //Error
        }
    }

    public void U(Transaction t, DataItem i){

        //Locktable -> apagar na key t, o dado i

        //Pegar automaticamente alguem da waitqueue?
        /*Se a waitqueue de i nao estiver vazia
            pegar o par (Transaction,LockType)
            colocar na locktable -> (Transaction): DataItem i
            i.setLockType = LockType
         */
        /*Senao
            i.setLockType = "U"
         */
    }

}
