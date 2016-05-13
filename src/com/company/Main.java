package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TR_Graph tr_graph = new TR_Graph();
        ArrayList<Transaction> transactions = new ArrayList<>();
        UserInterface ui = new UserInterface();

        ui.main_menu(tr_graph,transactions);
        ui.transaction_list(transactions,tr_graph);
    }
}
