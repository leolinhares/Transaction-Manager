package com.company;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.javatuples.Pair;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created by leolinhares on 01/06/2016.
 */
public class LockManager {

    //LockTable -> Transaction : List of (DataItem,String)
    private MultiValuedMap<Transaction,Pair<DataItem,String>> locktable = new HashSetValuedHashMap<>();
    private HashMap<DataItem,ArrayDeque<Pair<Transaction,String>>> waitqueue = new HashMap<>();

    public void LS(Transaction t, DataItem i){

        if(i.getLocktype() == "U" || i.getLocktype() == "S"){
            if(i.getWaitqueue().isEmpty()) {
                locktable.put(t, Pair.with(i, "S")); // Adiciona na key(transaction) t, o par (i,S)
                i.setLocktype("S");
            }
        }
        else if(i.getLocktype() == "X"){
            //Coloca a transaction t na waitqueue de i
            i.getWaitqueue().add(Pair.with(t,"S"));
            waitqueue.put(i,i.getWaitqueue());
        }
        else{
            //Error
        }
    }

    public void LX(Transaction t, DataItem i){

        if(i.getLocktype() == "U" || i.getLocktype() == "S" ){
            //Colocar na lock_table
            locktable.put(t,Pair.with(i,"X"));
            i.setLocktype("X");
        }
        else if(i.getLocktype() == "X"){
            i.getWaitqueue().add(Pair.with(t,"X"));
            waitqueue.put(i,i.getWaitqueue());
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

    public MultiValuedMap<Transaction, Pair<DataItem, String>> getLocktable() {
        return locktable;
    }

    public HashMap<DataItem, ArrayDeque<Pair<Transaction, String>>> getWaitqueue() {
        return waitqueue;
    }
}
