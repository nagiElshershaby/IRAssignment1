package view;

import file_connections.DocumentsFileReader;
import models.DictEntry;

import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class View {
    public static void view() throws IOException {
        TreeMap<String, DictEntry> tokens =new TreeMap<>();

        for (int i = 1; i <= 10; i++) {
            DocumentsFileReader.breakFileIntoTokens("doc" + i + ".txt",i,tokens);
        }

        int choice;
        do{
            System.out.println("1. search for a word");
            System.out.println("2. Print all info");
            System.out.println("0. Exit");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            if(choice == 1)printUserToken(tokens);
            else if (choice == 2)printAllTokens(tokens);
            else System.out.println("there was no "+choice+" choice");
        }while (choice != 0);


    }
    private static void printUserToken(TreeMap<String, DictEntry> tokens){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the word u r searching for!: ");
        String word = input.nextLine();

        if(!tokens.containsKey(word)){
            System.out.println("404 Not found");
        }else{
            //Posting header = tokens.get(word).pList;
            System.out.print("The word is found in the following files:  ");
            while (tokens.get(word).pList != null) {
                System.out.print(tokens.get(word).pList.docId + ", ");
                tokens.get(word).pList = tokens.get(word).pList.next;
            }
            tokens.get(word).pList = tokens.get(word).head;

            System.out.println();
            System.out.println("number of documents containing the word: " + tokens.get(word).doc_freq);
            System.out.println("number of times the word was mentioned in all documents: " + tokens.get(word).term_freq);

            System.out.println("doc_term_freq: ");
            while (tokens.get(word).pList != null) {
                System.out.print(tokens.get(word).pList.dtf + ", ");
                tokens.get(word).pList = tokens.get(word).pList.next;
            }
            tokens.get(word).pList = tokens.get(word).head;


            System.out.println();
            System.out.println();
        }
    }
    private static void printAllTokens(TreeMap<String, DictEntry> tokens){
        for (String key : tokens.keySet()) {
            System.out.print(key + "|  doc_freq: " + tokens.get(key).doc_freq);
            System.out.print("   |  term_freq: " + tokens.get(key).term_freq + "|  ");

            while (tokens.get(key).pList != null) {
                System.out.print("-->" + tokens.get(key).pList.docId);
                tokens.get(key).pList = tokens.get(key).pList.next;
            }
            tokens.get(key).pList = tokens.get(key).head;

            System.out.println("|  head_doc_term_freq: " + tokens.get(key).pList.dtf);
        }
        System.out.println();
        System.out.println();
    }

}
