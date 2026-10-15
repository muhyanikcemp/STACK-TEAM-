package butcetakip;

import java.io.*;

public class LinkedList {
    Node head;
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
}