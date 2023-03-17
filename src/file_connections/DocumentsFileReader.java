package file_connections;

import models.DictEntry;
import models.Posting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.TreeMap;

public class DocumentsFileReader {
    static BufferedReader reader;
    public static int counter = 0;
    public static void breakFileIntoTokens(String fileName, int docID , TreeMap<String, DictEntry>tokens) throws IOException {
        //initialize a file reader(opining a connection)
        reader = new BufferedReader(new FileReader(fileName));

        //declare the line to read
        String line;

        //while we still got lines to read
        while ((line = reader.readLine())!= null){
            //initialize a word to read
            String word = "";

            //looping on the line characters to get the words from
            for (int i =0 ; i<line.length(); i++){
                //if we reach an end of a word
                if(line.charAt(i) == ' '||line.charAt(i) == ';'||line.charAt(i)== '.'){
                    //if we already have read the word before
                    if(tokens.containsKey(word)){
                        //number of times the word is mentioned += 1
                        tokens.get(word).term_freq++;
                        //if the document is new(the word is repeated in another document)
                        //the document we read from now is not the first to have the word nor the last
                        if(tokens.get(word).pList.docId != docID && docID != tokens.get(word).end.docId){
                            //number of documents containing the word += 1
                            tokens.get(word).doc_freq++;
                            //we add the new document to the linked list
                            tokens.get(word).pList.next = new Posting(docID);//.docId = docID;
                            //we assign the new document as our linked list end
                            tokens.get(word).end = tokens.get(word).pList.next;
                        }
                        //if the document didn't change: document term frequency += 1
                        tokens.get(word).end.dtf++;
                    }else {
                        if(!word.equals("")) tokens.put(word.toLowerCase(Locale.ROOT),new DictEntry(new Posting(docID)));
                    }
                    if(!word.equals(""))counter++;
                    word = "";
                    continue;
                }
                word += line.charAt(i);
                word = word.toLowerCase();
            }
        }
        //closing the connection
        reader.close();
    }
}
