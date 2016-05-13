package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /*
    * Menu 1
    *
    * [1] - Begin Transaction
    * [2] - List Transactions
    * [0] - Exit
    *
    * Menu 2
    * Lista de Transacoes - Escolher uma transacao
    * [1] - Transaction 1
    * [2] - Transaction 2
    * ...
    * [0] - Exit
    *
    * Menu 3
    * Lista de Acoes
    * Exibir a Transacao escolhida
    * [1] - READ
    * [2] - WRITE
    * [3] - TR_Rollback
    * [4] - TR_Terminate
    * [5] - TR_Commit
    * [6] - TR_Finish
    * */


    public static void main(String[] args) {
        TR_Graph tr_graph = new TR_Graph();
        ArrayList<Transaction> transactions = new ArrayList<>();
        UserInterface ui = new UserInterface();

        ui.main_menu(tr_graph,transactions);
        ui.transaction_list(transactions,tr_graph);
    }
}
