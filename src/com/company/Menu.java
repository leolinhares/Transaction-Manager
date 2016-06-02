package com.company;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by thiagoisaias on 5/12/16.
 */

public class Menu {

    ArrayList<DataItem> itemlist;
    LockManager lock = new LockManager();

    public Menu(ArrayList<DataItem> itemlist){
        this.itemlist = itemlist;
    }

    public void main_menu(TR_Graph graph, ArrayList<Transaction> list){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("########### - Transaction Manager - ############");
        System.out.println("[1] - TR_Begin" + "\n[2] - List Transactions" + "\n[0] - Exit\n");
        int action = sc.nextInt();
        while(action!=1 && action!=2 && action!=0){
            System.out.println("Try Again!");
            action = sc.nextInt();
        }
        switch (action){
            case 1:
                System.out.println("What's the transaction name?");
                String tr_name = sc2.nextLine();
                Transaction tr = new Transaction(graph);
                tr.setName(tr_name);
                list.add(tr);
                main_menu(graph,list);
                break;
            case 2:
                transaction_list(list,graph);
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public void transaction_list(ArrayList<Transaction> list,TR_Graph graph){
        //Imprimir o estado de todas as transacoes / retornar a transacao escolhida
        Scanner sc = new Scanner(System.in);

        System.out.println("########### - Transaction List - ############");
        for (int i = 1; i<=list.size();i++) {
            System.out.println("[" + i + "] - " + list.get(i-1).getName() + " - " + list.get(i-1).getCurrentNode().getDescription());
        }
        if(list.size()>0){
            System.out.println("\nChoose a transaction: ");
            int id = sc.nextInt();
            while(id<1 || id>list.size()){
                System.out.println("Try Again! Index out of boundaries");
                id = sc.nextInt();
            }
            action_list(list,list.get(id-1),graph);
        }
        else{
            System.out.println("Empty. There are no transactions");
            System.out.println("Press Enter to return");
            sc.nextLine();
            main_menu(graph,list);
        }
    }

    public void action_list(ArrayList<Transaction> list ,Transaction tr, TR_Graph graph){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Transaction - " + tr.getName());
        System.out.println("[2] - READ\n[3] - WRITE\n[4] - TR_Rollback\n[5] - TR_Terminate\n[6] - TR_Commit\n[7] - TR_Finish ");
        System.out.println("\nChoose an action");

        String action = sc.nextLine();

        if(Integer.parseInt(action) == 2 || Integer.parseInt(action) == 3){
            //Mostrar Itens
            System.out.println("\n########### - Data itens - ############\n");
            for (int i=1;i<=itemlist.size();i++) {
                System.out.println("["+i+"] - "+ itemlist.get(i-1).getName());
            }
            System.out.println("\nChoose a data item: ");
            int it = sc2.nextInt();

            switch (action){
                case "2":
                    lock.LS(tr, itemlist.get(it-1));
                    break;
                case "3":
                    lock.LX(tr, itemlist.get(it-1));
                    break;
                default:
                    break;

            }
        }

        graph.search(tr,action);
        main_menu(graph,list);
    }
}
