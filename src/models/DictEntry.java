package models;

public class DictEntry {
    public int doc_freq = 0 ;
    public int term_freq = 0 ;
    public Posting pList;
    public Posting head;
    public Posting end;

    public DictEntry(Posting pList) {
        this.pList = pList;
        this.head = pList;
        this.end = pList;
        doc_freq++;
        term_freq++;
    }
}
