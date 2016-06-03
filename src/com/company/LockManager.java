package com.company;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.javatuples.Pair;
import java.util.ArrayDeque;
import java.util.HashMap;

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

        if(i.getLocktype() == "U" || i.getLocktype() == "S"){
            if(i.getWaitqueue().isEmpty()) {
                locktable.put(t, Pair.with(i, "S")); // Adiciona na key(transaction) t, o par (i,S)
                i.setLocktype("S");
                System.out.println("Bloqueio compartilhado de "+ i.toString() +" concedido a transação "+ t.toString());
            }else{
                //TODO: o que fazer quando a lista já tem algo não pode conceder o bloqueio então?
            }
        }
        else if(i.getLocktype() == "X"){
            //Coloca a transaction t na waitqueue de i
            i.getWaitqueue().add(Pair.with(t,"S"));
            waitqueue.put(i,i.getWaitqueue());
            System.out.println("Bloqueio compartilhado não concedido. Transação" + t.toString() +"foi colocada na wait_list de "+ i.toString());
        }
        else{
            //Error
        }
    }

    public void LX(Transaction t, DataItem i){

        if(i.getLocktype() == "U"){
            // O pedido de bloqueio aqui é exclusivo, então se o objeto está
            // desbloqueado, o bloqueio é concedido e vai pra lock_table.

            //TODO: precisa verificar se a fila ta vazia se ele está unlocked?
            locktable.put(t,Pair.with(i,"X"));
            i.setLocktype("X");
            System.out.println("Bloqueio exclusivo de "+ i.toString() +" concedido a transação "+ t.toString());
        }
        else if(i.getLocktype() == "S" || i.getLocktype() == "X"){
            // O pedido de bloqueio aqui é exclusivo, então se o objeto já está
            // sendo lido, o bloqueio não é concedido e vai pra wait_queue.
            i.getWaitqueue().add(Pair.with(t,"X"));
            waitqueue.put(i,i.getWaitqueue());
            System.out.println("Bloqueio exclusivo não concedido. Transação" + t.toString() +"foi colocada na wait_list de "+ i.toString());
        }else{
            //Error
        }
    }

    public void U(Transaction t){

        //TODO: Locktable -> apagar todos os dados da transação t e colocar todos os dados como unlocked
//        Pair d,s = locktable.get(t);

        locktable.get(t).clear();

        //TODO: Pegar automaticamente alguem da waitqueue?
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
