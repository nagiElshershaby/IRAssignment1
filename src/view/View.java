package view;

import file_connections.DocumentsFileReader;
import models.DictEntry;
import models.Posting;

import java.io.IOException;
import java.util.TreeMap;

public class View {
    public static void view() throws IOException {
        TreeMap<String, DictEntry> tokens =new TreeMap<>();


        DocumentsFileReader.breakFileIntoTokens("doc1.txt",1,tokens);
        DocumentsFileReader.breakFileIntoTokens("doc2.txt",2,tokens);

        for (String key : tokens.keySet()) {
            System.out.print(key + "|  doc_freq: " + tokens.get(key).doc_freq);
            System.out.print("   |  term_freq: " + tokens.get(key).term_freq + "|  ");

            Posting header = tokens.get(key).pList;
            while (tokens.get(key).pList != null) {
                System.out.print("-->" + tokens.get(key).pList.docId);
                tokens.get(key).pList = tokens.get(key).pList.next;
            }

            System.out.println("|  doc_term_freq: " + header.dtf);
        }

    }

}
