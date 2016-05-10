package com.company;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class TR_Graph {

    ArrayList<Node> graph;
    private String[] nodelabels = {"Inicio","TR_Iniciada", "Ativa", "Processo_Efetivacao", "Processo_Cancelamento", "Efetivada", "Finalizada"};

    //Criando o grafo
    public TR_Graph() {
        graph = new ArrayList<>();
        this.populate();
    }

    private void populate(){

        for (int i = 0; i < 7; i++) {
            Node node = new Node(i,nodelabels[i]);
            this.graph.add(node);
        }

        for (int i = 0; i < 7; i++) {
            Node node = this.graph.get(i);
            switch (i){
                case 0:
                    node.setEdges("TR_Begin",this.graph.get(1).getId());
                    break;
                case 1:
                    node.setEdges("READ",this.graph.get(2).getId());
                    node.setEdges("WRITE",this.graph.get(2).getId());
                    break;
                case 2:
                    node.setEdges("READ",this.graph.get(2).getId());
                    node.setEdges("WRITE",this.graph.get(2).getId());
                    node.setEdges("TR_Rollback",this.graph.get(4).getId());
                    node.setEdges("TR_Terminate",this.graph.get(3).getId());
                    break;
                case 3:
                    node.setEdges("TR_Commit",this.graph.get(5).getId());
                    node.setEdges("TR_Rollback",this.graph.get(4).getId());
                    break;
                case 4:
                    node.setEdges("TR_Finish",this.graph.get(6).getId());
                    break;
                case 5:
                    node.setEdges("TR_Finish",this.graph.get(6).getId());
                    break;
                case 6:
                    break;
            }
        }
    }

    public void search(Transaction transaction, String action){
        //Dada uma transacão, verificar t1.current_state.next("action",???)
        Node node = transaction.getCurrentNode();
        int destinationNodeID = node.getEdges().get(action);
        transaction.setCurrentNode(this.graph.get(destinationNodeID));
    }

}
