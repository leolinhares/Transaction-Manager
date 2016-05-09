package com.company;

/**
 * Created by thiagoisaias on 5/9/16.
 */
public class TR_Graph {

    Node tr_iniciada;
    Node ativa;
    Node processo_cancelamento;
    Node processo_efetivacao;
    Node efetivada;
    Node tr_finalizada;

    public TR_Graph(){
        this.tr_iniciada.current = "TR_Iniciada";
        this.tr_iniciada.next.add(this.ativa);

        this.ativa.current = "Ativa";
        this.ativa.next.add(this.ativa);
        this.ativa.next.add(this.processo_efetivacao);
        this.ativa.next.add(this.processo_cancelamento);

        this.processo_cancelamento.current = "Processo_Cancelamento";
        this.processo_cancelamento.next.add(this.tr_finalizada);

        this.processo_efetivacao.current = ("Processo_Efetivacao");
        this.processo_efetivacao.next.add(this.processo_cancelamento);
        this.processo_efetivacao.next.add(this.efetivada);

        this.efetivada.current = "Efetivada";
        this.efetivada.next.add(this.tr_finalizada);
    }

    public void search(){

    }

}
