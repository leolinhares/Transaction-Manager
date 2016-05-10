package com.company;

import java.util.ArrayList;

public class Main {


    public void menu(){
        //retorna uma action sobre uma transacao
        //retorna um par (transacao,action) ?
        //Chama a Tr_Graph.search
    }

    public void printAll(ArrayList<Transaction> list){
        //Imprimir o estado de todas as transacoes
        //Verificar onde a lista de transacoes vai ser usada/modificada
    }

    public static void main(String[] args) {
        TR_Graph tr_graph = new TR_Graph();
        ArrayList<Transaction> transactions = new ArrayList<>();

        //caso 1: criando a transacao
        Transaction t1 = new Transaction(tr_graph);
        transactions.add(t1);

        //caso 2: utilizando uma transacao ja criada para alterar o estado dela
        tr_graph.search(t1,"TR_Begin");
        tr_graph.search(t1,"READ");
        tr_graph.search(t1,"WRITE");
        tr_graph.search(t1,"WRITE");
        tr_graph.search(t1,"READ");
        tr_graph.search(t1,"TR_Terminate");


        for (Transaction t: transactions) {
            System.out.println(t.getCurrentNode().getDescription());
        }

    }
}
