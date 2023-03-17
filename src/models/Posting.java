package models;

public class Posting {
    public Posting next = null;
    public int docId;
    public int dtf = 1;

    public Posting(int docId) {
        this.docId = docId;
    }
}
