package com.company;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class TR_Graph {

    Node root;
    Node tr_iniciada;
    Node ativa;
    Node processo_cancelamento;
    Node processo_efetivacao;
    Node efetivada;
    Node tr_finalizada;

    public TR_Graph(){


        //Node root
        this.root.current = "Root";
        this.root.next.put("TR_Begin",this.tr_iniciada);

        //Node 1
        this.tr_iniciada.current = "TR_Iniciada";
        this.tr_iniciada.next.put("READ",this.ativa);
        this.tr_iniciada.next.put("WRITE",this.ativa);

        //Node 2
        this.ativa.current = "Ativa";
        this.ativa.next.put("READ",this.ativa);
        this.ativa.next.put("WRITE",this.ativa);
        this.ativa.next.put("TR_Terminate",this.processo_efetivacao);
        this.ativa.next.put("TR_Rollback",this.processo_cancelamento);

        //Node 3
        this.processo_cancelamento.current = "Processo_Cancelamento";
        this.processo_cancelamento.next.put("TR_Finish",this.tr_finalizada);

        //Node 4
        this.processo_efetivacao.current = ("Processo_Efetivacao");
        this.processo_efetivacao.next.put("TR_Rollback",this.processo_cancelamento);
        this.processo_efetivacao.next.put("TR_Commit",this.efetivada);

        //Node 5
        this.efetivada.current = "Efetivada";
        this.efetivada.next.put("TR_Finish",this.tr_finalizada);
    }

    public void search(Transaction t1, String action){
        //Dada uma transacão, verificar t1.current_state.next("action",???)
    }

}
