package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        TR_Graph tr_graph = new TR_Graph();
        ArrayList<Transaction> transactions = new ArrayList<>();

        DataItem i1 = new DataItem(01,"Item 01");
        DataItem i2 = new DataItem(02,"Item 02");
        DataItem i3 = new DataItem(03,"Item 03");
        ArrayList<DataItem> itemlist = new ArrayList<DataItem>();
        itemlist.add(i1);
        itemlist.add(i2);
        itemlist.add(i3);

        Menu ui = new Menu(itemlist);

        ui.main_menu(tr_graph,transactions);
        ui.transaction_list(transactions,tr_graph);
    }
}
