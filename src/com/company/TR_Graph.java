package com.company;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class TR_Graph {

    ArrayList<Node> graph;
    private String[] nodelabels = {"Inicio","TR_Iniciada", "Ativa", "Processo_Efetivacao", "Processo_Cancelamento", "Efetivada", "Finalizada"};
//    private String[] transactionLabels = {"TR_Begin", "READ", "WRITE", "TR_Terminate", "TR_Rollback", "TR_Commit", "TR_Finish"};


    //Criando o grafo
    public TR_Graph() {
        graph = new ArrayList<>();
        this.populate();
    }

    private void populate(){
        Transaction begin = new Transaction(0,"TR_Begin");
        Transaction read = new Transaction(1,"READ");
        Transaction write = new Transaction(2,"WRITE");
        Transaction terminate = new Transaction(3,"TR_Terminate");
        Transaction rollback = new Transaction(4,"TR_Rollback");
        Transaction commit = new Transaction(5,"TR_Commit");
        Transaction finish = new Transaction(6,"TR_Finish");

        for (int i = 0; i < 7; i++) {
            Node node = new Node(i,nodelabels[i]);
            this.graph.add(node);
        }

        for (int i = 0; i < 7; i++) {
            Node node = this.graph.get(i);
            switch (i){
                case 0:
                    node.setEdges(begin,this.graph.get(1));
                    break;
                case 1:
                    node.setEdges(read,this.graph.get(2));
                    node.setEdges(write,this.graph.get(2));
                    break;
                case 2:
                    node.setEdges(read,this.graph.get(2));
                    node.setEdges(write,this.graph.get(2));
                    node.setEdges(rollback,this.graph.get(4));
                    node.setEdges(terminate,this.graph.get(3));
                    break;
                case 3:
                    node.setEdges(commit,this.graph.get(5));
                    node.setEdges(rollback,this.graph.get(4));
                    break;
                case 4:
                    node.setEdges(finish,this.graph.get(6));
                    break;
                case 5:
                    node.setEdges(finish,this.graph.get(6));
                    break;
                case 6:
                    break;
            }
        }
    }


    //    Node root;
//    Node tr_iniciada;
//    Node ativa;
//    Node processo_cancelamento;
//    Node processo_efetivacao;
//    Node efetivada;
//    Node tr_finalizada;
//
//    public TR_Graph(){
//
//
//        //Node root
//        this.root.current = "Root";
//        this.root.next.put("TR_Begin",this.tr_iniciada);
//
//        //Node 1
//        this.tr_iniciada.current = "TR_Iniciada";
//        this.tr_iniciada.next.put("READ",this.ativa);
//        this.tr_iniciada.next.put("WRITE",this.ativa);
//
//        //Node 2
//        this.ativa.current = "Ativa";
//        this.ativa.next.put("READ",this.ativa);
//        this.ativa.next.put("WRITE",this.ativa);
//        this.ativa.next.put("TR_Terminate",this.processo_efetivacao);
//        this.ativa.next.put("TR_Rollback",this.processo_cancelamento);
//
//        //Node 3
//        this.processo_cancelamento.current = "Processo_Cancelamento";
//        this.processo_cancelamento.next.put("TR_Finish",this.tr_finalizada);
//
//        //Node 4
//        this.processo_efetivacao.current = ("Processo_Efetivacao");
//        this.processo_efetivacao.next.put("TR_Rollback",this.processo_cancelamento);
//        this.processo_efetivacao.next.put("TR_Commit",this.efetivada);
//
//        //Node 5
//        this.efetivada.current = "Efetivada";
//        this.efetivada.next.put("TR_Finish",this.tr_finalizada);
//    }

    public void search(Transaction t1, String action){
        //Dada uma transacão, verificar t1.current_state.next("action",???)
    }

}
