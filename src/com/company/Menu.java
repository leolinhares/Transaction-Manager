package com.company;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by thiagoisaias on 5/12/16.
 * Modified by leolinhares on 6/2/16.
 */

public class Menu {

    ArrayList<DataItem> itemlist;
    LockManager lock = new LockManager();

    private int ts = 0;

    private int getTs() {
        ts = ts+1;
        return ts;
    }

    public Menu(ArrayList<DataItem> itemlist){
        this.itemlist = itemlist;
    }

    public void main_menu(TR_Graph graph, ArrayList<Transaction> list){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("########### - Transaction Manager - ############");
        System.out.println("[1] - TR_Begin" + "\n[2] - List Transactions" + "\n[3] - Lock Table" +"\n[4] - Wait List" +"\n[0] - Exit\n");
        int action = sc.nextInt();
        while(action!=1 && action!=2 && action!=3 && action!=4 && action!=0){
            System.out.println("Try Again!");
            action = sc.nextInt();
        }
        switch (action){
            case 1:
                System.out.println("What's the transaction name?");
                String tr_name = sc2.nextLine();
                Transaction tr = new Transaction(graph);
                tr.setName(tr_name);
                tr.setTimestamp(getTs());
                list.add(tr);
                main_menu(graph,list);
                break;
            case 2:
                transaction_list(list,graph);
                break;
            case 3:
                System.out.println(lock.getLocktable().toString());
                main_menu(graph,list);
                break;
            case 4:
                System.out.println(lock.getWaitqueue().toString());
                main_menu(graph,list);
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("\nTry Again!\n");
                main_menu(graph,list);
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

        switch (action){
            case "2":
                //Mostrar Itens
                System.out.println("\n########### - Data itens - ############\n");
                for (int i=1;i<=itemlist.size();i++) {
                    System.out.println("["+i+"] - "+ itemlist.get(i-1).getName());
                }
                System.out.println("\nChoose a data item: ");
                int it = sc2.nextInt();

                lock.LS(tr, itemlist.get(it-1));

                break;
            case "3":
                //Mostrar Itens
                System.out.println("\n########### - Data itens - ############\n");
                for (int i=1;i<=itemlist.size();i++) {
                    System.out.println("["+i+"] - "+ itemlist.get(i-1).getName());
                }
                System.out.println("\nChoose a data item: ");
                int it2 = sc2.nextInt();

                lock.LX(tr, itemlist.get(it2-1));

                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                //Nao existe aresta "commit" saindo do estado ativo
                //TODO: Tratar esse problema
                lock.U(tr);
                break;
            case "7":
                break;
            default:
                System.out.println("\nTry Again");
                action_list(list,tr,graph);
                break;

        }

        graph.search(tr,action);
        main_menu(graph,list);
    }
}
