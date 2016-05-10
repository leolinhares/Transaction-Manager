package com.company;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by thiagoisaias on 5/9/16.
 * Modified by leolinhares on 5/10/16.
 */
public class TR_Graph {

    ArrayList<Node> graph;
    private String[] nodelabels = {"Inicio","TR_Iniciada", "Ativa",
            "Processo_Efetivacao", "Processo_Cancelamento", "Efetivada", "Finalizada"};

    // Criando o grafo
    public TR_Graph() {
        graph = new ArrayList<>();
        this.populate();
    }

    // Inicializando o grafo de transição com os nós e as arestas adequados
    private void populate(){

        // Criando os nós
        for (int i = 0; i < 7; i++) {
            Node node = new Node(i,nodelabels[i]);
            this.graph.add(node);
        }

        // Adicionando as arestas
        for (int i = 0; i < 7; i++) {
            Node node = this.graph.get(i);
            switch (i){
                case 0:
                    node.setEdges("TR_Begin",1);
                    break;
                case 1:
                    node.setEdges("READ",2);
                    node.setEdges("WRITE",2);
                    break;
                case 2:
                    node.setEdges("READ",2);
                    node.setEdges("WRITE",2);
                    node.setEdges("TR_Rollback",4);
                    node.setEdges("TR_Terminate",4);
                    break;
                case 3:
                    node.setEdges("TR_Commit",5);
                    node.setEdges("TR_Rollback",4);
                    break;
                case 4:
                    node.setEdges("TR_Finish",6);
                    break;
                case 5:
                    node.setEdges("TR_Finish",6);
                    break;
                default:
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
