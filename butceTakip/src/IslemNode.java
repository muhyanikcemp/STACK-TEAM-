/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MUHAMMED
 */
public class IslemNode {
    int id;
    double tutar;
    String tarih;
    String kategori;
    String aciklama;
    String islemTuru; //gelir ve gider kısmı 
    
    IslemNode next; // bağlama kısmı(bağlı liste)
    
    public IslemNode(int id,double tutar,String tarih,String kategori,String aciklama,String islemTuru){
     this.id= id;
     this.tarih=tarih;
     this.kategori=kategori;
     this.aciklama=aciklama;
     this.islemTuru=islemTuru;
     this.next=null;//ilk başta arkasında bağlı bir şey yok
     
     if(tutar<0){
         System.out.println("NEGATİF TUTAR GİRİLEMEZ!! İşlem tutarı 0 olarak başlar");
         this.tutar=0;
     }else{
         this.tutar=tutar;
     }
    }

}
