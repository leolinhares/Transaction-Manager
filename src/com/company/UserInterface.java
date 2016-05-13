package com.company;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by thiagoisaias on 5/12/16.
 */

public class UserInterface {

    public UserInterface(){

    }

    public void main_menu(TR_Graph graph, ArrayList<Transaction> list){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        //Retornar um par (Transacao,String acao)
        System.out.println("########### - Transaction Manager - ############");
        System.out.println("[1] - Begin Transaction" + "\n[2] - List Transactions" + "\n[0] - Exit\n");
        int action = sc.nextInt();
        while(action!=1 && action!=2 && action!=3){
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
        for (int i = 0; i<list.size();i++) {
            System.out.println("[" + i + "] - " + list.get(i).getName() + " - " + list.get(i).getCurrentNode().getDescription());
        }
        if(list.size()>0){
            System.out.println("\nChoose a transaction: ");
            int id = sc.nextInt();
            action_list(list.get(id),graph);
        }
        else{
            System.out.println("Empty. There are no transactions");
            System.out.println("Press Enter to return");
            sc.nextLine();
        }
    }

    public void action_list(Transaction tr, TR_Graph graph){
        Scanner sc = new Scanner(System.in);
        System.out.println("Transaction - " + tr.getName());
        System.out.println("[1] - BEGIN\n[2] - WRITE\n[3] - TR_Rollback\n[4] - TR_Terminate\n[5] - TR_Commit\n[6] - TR_Finish ");
        System.out.println("\n Write the action");
        String action = sc.nextLine();
        graph.search(tr,action);
    }
}
