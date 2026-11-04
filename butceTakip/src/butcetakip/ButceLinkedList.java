/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MUHAMMED
 */
package butcetakip;
import java.io.*;

public class ButceLinkedList {
    IslemNode head;
    KategoriNode kategoriHead;
    String dosyaAdi = "harcamalar.cml";

public ButceLinkedList(){
    this.head= null;
    this.kategoriHead= null;
    this.dosyadanYukle();
}
public void islemEkle(int id,double tutar,String tarih,String kategori, String aciklama,String islemTuru){
    IslemNode yeniIslem = new IslemNode(id,tutar,tarih,kategori,aciklama,islemTuru);
    if (head == null){
        head = yeniIslem;
        System.out.println("Ilk islme eklendi:" + islemTuru + "->" + tutar + "TL");
        
    }else{
        IslemNode temp =head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = yeniIslem;
    }
    System.out.println("Sisteme islendi:" + "->" + tutar +"TL");
    this.bakiyeHesapla();
    this.dosyayaKaydet();
}
public void bakiyeHesapla(){
    double toplamGelir=0;
    double toplamGider=0;
    IslemNode temp = head;
    while(temp != null){
        if(temp.islemTuru.equalsIgnoreCase("Gelir")){
            toplamGelir += temp.tutar;
        }else if(temp.islemTuru.equalsIgnoreCase("Gider")){
            toplamGider += temp.tutar;
        }
        temp = temp.next;
    }
    System.out.println("..Butce Durumu: Net Bakiye = " + (toplamGelir-toplamGider) + "TL\n");
    
}
public void islemSil(int id){
    if(head == null)return;
    if(head.id == id){
        head= head.next;
        this.dosyayaKaydet();
        System.out.println(id + "numarali islem silindi");
        return;
    }
    IslemNode current = head;
    while(current.next != null){
        if(current.next.id == id){
            current.next = current.next.next;
            this.dosyayaKaydet();
            System.out.println(id + "numarali islem silindi");
            return;
        }
        current = current.next;
    }
}
private void dosyayaKaydet(){
    try(BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi))){
        IslemNode current = head;
        while(current != null){
            
            writer.write(current.id +","+ current.tutar + ","+ current.tarih + ","+ current.kategori + ","+current.aciklama+","+ current.islemTuru);
            writer.newLine();
            current = current.next;
        }
    }catch (IOException e){}
}
    private void dosyadanYukle(){
        File dosya = new File(dosyaAdi);
        if (!dosya.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))){
            String satir;
            while((satir = reader.readLine())!=null){
                String[] parcalar = satir.split(",");
                if(parcalar.length ==6){
                    IslemNode newNode = new IslemNode(
                    Integer.parseInt(parcalar[0]),Double.parseDouble(parcalar[1]),parcalar[2],parcalar[3],parcalar[4],parcalar[5]);
                    if (head == null) head = newNode;
                    else {
                        IslemNode current = head;
                        while(current.next != null) current = current.next;
                        current.next = newNode;
                    }
        }
    }
        }  catch (IOException | NumberFormatException e){
        System.out.println("dosya okunamadı"+e.getMessage());}
        }
        public void sirala(){
            if(head == null || head.next == null) return;
            boolean degisken;
            IslemNode current;
            IslemNode lptr = null;
            do{
                degisken = false;
                current = head;
                while(current.next != lptr){
                    if(current.tutar<current.next.tutar){
                        double tempTutar = current.tutar; current.tutar = current.next.tutar; current.next.tutar = tempTutar;
                        int tempId = current.id; current.id= current.next.id; current.next.id= tempId;
                        String tempTarih = current.tarih; current.tarih = current.next.tarih;current.next.tarih = tempTarih;
                        String tempKategori = current.kategori; current.kategori = current.next.kategori; current.next.kategori = tempKategori;
                        String tempAciklama = current.aciklama; current.aciklama =current.next.aciklama; current.next.aciklama =tempAciklama;
                        String tempIslemTuru = current.islemTuru; current.islemTuru = current.next.islemTuru; current.next.islemTuru = tempIslemTuru;
                        degisken=true;
                    }
                    current = current.next;
                }
                lptr = current;
            }while(degisken);
            System.out.println("Harcamalar tutarina gore buyukten kucuge siralandi");
        }
        public void listele(){
            if(head == null){
                System.out.println("Sistemde kayitli herhangi bir islem bulunamadi");
                return;
            }
            System.out.println("\n--- KAYITLI ISLEMLER LISTESI ---");
            IslemNode temp = head;
            while(temp != null){
                System.out.println("ID:" + temp.id + " | Tur:" + temp.islemTuru + "| Tutar:" + temp.tutar + "TL | Kategori:" + temp.kategori + "| Tarih:" + temp.tarih);
                temp = temp.next;
            }
            System.out.println("-------------------\n");
        }
}
