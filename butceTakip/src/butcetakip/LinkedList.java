package butcetakip;

import java.io.*;

public class LinkedList {
    Node head;
    KategoriNode kategoriHead;//ekleme
    String dosyaAdi = "harcamalar.cml"; 

    public LinkedList() {
        this.head = null;
        dosyadanYukle(); 
    }

    public void insert(int id, String harcamaAdi, double tutar) {
        Node newNode = new Node(id, harcamaAdi, tutar);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        dosyayaKaydet(); 
    }

    public void delete(int id) {
        if (head == null) return;
        if (head.id == id) {
            head = head.next;
            dosyayaKaydet(); 
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.id == id) {
                current.next = current.next.next;
                dosyayaKaydet(); 
                return;
            }
            current = current.next;
        }
    }

    private void dosyayaKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))) {
            Node current = head;
            while (current != null) {
                writer.write(current.id + "," + current.harcamaAdi + "," + current.tutar);
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            System.out.println("Dosya kaydedilirken hata oluştu: " + e.getMessage());
        }
    }

    private void dosyadanYukle() {
        File dosya = new File(dosyaAdi);
        if (!dosya.exists()) return; 

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length == 3) {
                    int id = Integer.parseInt(parcalar[0]);
                    String harcamaAdi = parcalar[1];
                    double tutar = Double.parseDouble(parcalar[2]);
                    
                    Node newNode = new Node(id, harcamaAdi, tutar);
                    if (head == null) {
                        head = newNode;
                    } else {
                        Node current = head;
                        while (current.next != null) {
                            current = current.next;
                        }
                        current.next = newNode;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }
    //ekleme yaptığım kısım!!!!!!!!
    public void butceAsimKontrol(){
        KategoriNode current=kategoriHead;
        //liste sonuna null olana kadar kategorileri tek tek gezmeye başlıyoruz
        while (current!=null){ 
            if (current.mevcutHarcama>current.aylikButce){//o anki kategorinin mevcut harcaması,belirlenen aylık bütçesinden büyük mü diye kontrol ediyoruz
                System.out.println("Uyarı"+current.kategoriAdi+"kategorisi bütçesini aştınız!");
            }
            current=current.next;// sıradaki kategoriye geçmek için current işaretçisini bir sonraki düğüme (next) kaydırıyoruz
        }
    }
    public void sırala (){
        //liste boşsa sıralama yapmaya gerek yok direkt aç
        if(head==null || head.next==null){
            return;
        }
        boolean degisken; //sıralamanın bitip bitmediğini anlamak için değişken açıyoruz
        Node current; //listeyi gezmek için kullanacağımz düğüm
        Node lprt = null; // sınır işareti 
        
        do{// sıralama bitene kadar dönecek olan dış döngü
            degisken=false;
            current=head;
            
            while(current.next!=lprt){ //  listenin başından sınıra kadar karıştırarak ilerle
                // eğer mevcut harcamanın tutarı,bir sonraki harcamadan küçükse yer değistir (büyükten küçüğe sırala)
                if(current.tutar<current.next.tutar){
                    // swap işlemleri
                    //tutarları birbiriyle yer değiştir
                    double tempTutar=current.tutar;
                    current.tutar=current.next.tutar;
                    current.next.tutar=tempTutar;
                    //harcama adlarını birbiriyle yer değiştiriyoruz 
                    String tempAd=current.harcamaAdi;
                    current.harcamaAdi=current.next.harcamaAdi;
                    current.next.harcamaAdi=tempAd;
                    // id leri birbiriyle yer değiştiriyoruz 
                    int tempId=current.id;
                    current.id=current.next.id;
                    current.next.id=tempId;
                    
                    degisken=true;
                }
                current=current.next;
            }
            lptr = current;
        }while(degisken);
        System.out.println("Harcamalar tutarına göre büyükten küçüğe sıralandı!");
    }
}

