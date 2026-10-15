package butcetakip;

public class Node {
    int id;
    String harcamaAdi; 
    double tutar;
    Node next;

    public Node(int id, String harcamaAdi, double tutar) {
        this.id = id;
        this.harcamaAdi = harcamaAdi;
        this.tutar = tutar;
        this.next = null;
    }
}