package com.company;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.javatuples.Pair;
import java.util.*;

/**
 * Created by leolinhares on 01/06/2016.
 * Modified by thiagoisaias on 02/06/2016.
 */
public class LockManager {

    //LockTable -> Transaction : List of (DataItem,String)
    private MultiValuedMap<Transaction,Pair<DataItem,String>> locktable = new HashSetValuedHashMap<>();

    //WaitQueue -> DataItem : FIFO of (Transaction,String)
    private HashMap<DataItem,ArrayDeque<Pair<Transaction,String>>> waitqueue = new HashMap<>();

    public void LS(Transaction t, DataItem i){

        if(locktable.get(t).contains(Pair.with(i,"S")) || locktable.get(t).contains(Pair.with(i,"X"))){
            System.out.println("A transação {"+t.getName()+"} já possui o bloqueio do item {"+i.getName()+"}\n");
        }

        else if(i.getLocktype() == "U" || i.getLocktype() == "S"){
            if(i.getWaitqueue().isEmpty()) {
                locktable.put(t, Pair.with(i, "S")); // Adiciona na key(transaction) t, o par (i,S)
                i.setLocktype("S");
                System.out.println("Bloqueio compartilhado de "+ i.toString() +" concedido a transação "+ t.toString());
            }else{
                i.getWaitqueue().add(Pair.with(t,"S"));
                waitqueue.put(i,i.getWaitqueue());
            }
        }

        else if(i.getLocktype() == "X"){
            if(locktable.get(t).contains(Pair.with(i,"X"))){
                System.out.println("A transação {"+t.getName()+"} já possui o bloqueio do item {"+i.getName()+"}\n");
            }
            else{
                //Coloca a transaction t na waitqueue de i
                i.getWaitqueue().add(Pair.with(t, "S"));
                waitqueue.put(i, i.getWaitqueue());
                System.out.println("Bloqueio compartilhado não concedido. Transação " + t.toString() + " foi colocada na wait_list de " + i.toString());
            }
        }
    }

    public void LX(Transaction t, DataItem i){

        if(locktable.get(t).contains(Pair.with(i,"X"))){
            System.out.println("A transação {"+t.getName()+"} já possui o bloqueio exclusivo do item {"+i.getName()+"}\n");
        }
        //Se a transacao tem um bloqueio S e quer um bloqueio X e a fila do item esta vazia
        else if (locktable.get(t).contains(Pair.with(i,"S")) && i.getWaitqueue().isEmpty()){
            i.setLocktype("X");
            Collection<Pair<DataItem,String>> lista = locktable.get(t);
        }

        else if(i.getLocktype() == "U"){
            // O pedido de bloqueio aqui é exclusivo, então se o objeto está
            // desbloqueado, o bloqueio é concedido e vai pra lock_table.
            locktable.put(t,Pair.with(i,"X"));
            i.setLocktype("X");
            System.out.println("Bloqueio exclusivo de "+ i.toString() +" concedido a transação "+ t.toString());
        }

        else if(i.getLocktype() == "S" || i.getLocktype() == "X"){
            // O pedido de bloqueio aqui é exclusivo, então se o objeto já está
            // sendo lido, o bloqueio não é concedido e vai pra wait_queue.
            i.getWaitqueue().add(Pair.with(t,"X"));
            waitqueue.put(i,i.getWaitqueue());
            System.out.println("Bloqueio exclusivo não concedido. Transação " + t.toString() +" foi colocada na wait_list de "+ i.toString());
        }
    }

    public void U(Transaction t){

        Iterator<Pair<DataItem,String>> iterator = locktable.get(t).iterator();

        while(iterator.hasNext()){
            Pair<DataItem,String> item_block = iterator.next();
            if(item_block.getValue0().getWaitqueue().isEmpty()) { //Se a wailist do DataItem estiver vazia
                item_block.getValue0().setLocktype("U"); //DataItem fica unlocked
            }
            else{
                //Remove o primeiro elemento da waitlist do DataItem referente ao pair
                Pair<Transaction,String> transaction_block = item_block.getValue0().getWaitqueue().poll();
                if (waitqueue.get(item_block.getValue0()).isEmpty()){ //se a waitqueue do item i ficar vazia, apaga ela
                    waitqueue.remove(item_block.getValue0());
                }
                //Adiciona na locktable
                locktable.put(transaction_block.getValue0(),Pair.with(item_block.getValue0(),transaction_block.getValue1()));
                //Atualiza o estado de bloqueio do DataItem para "bloqueio solicitado pela transaction"
                item_block.getValue0().setLocktype(transaction_block.getValue1());
                System.out.println("Bloqueio de "+ item_block.getValue0().toString() +" concedido a transação "+ transaction_block.getValue0().toString());
            }
        }
        locktable.get(t).clear();
    }

    public MultiValuedMap<Transaction, Pair<DataItem, String>> getLocktable() {
        return locktable;
    }

    public HashMap<DataItem, ArrayDeque<Pair<Transaction, String>>> getWaitqueue() {
        return waitqueue;
    }
}
