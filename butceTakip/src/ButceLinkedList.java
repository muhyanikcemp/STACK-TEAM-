/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MUHAMMED
 */
public class ButceLinkedList {
    IslemNode head;//başlangıç noktası
    public ButceLinkedList(){
        this.head=null;
    }
    public void islemEkle(int id,double tutar,String tarih,String kategori,String aciklama,String islemTuru){
        IslemNode yeniIslem=new IslemNode(id,tutar,tarih,kategori,aciklama,islemTuru);
        if(head == null){
            head = yeniIslem;
            System.out.println("İlk işlem eklendi" + islemTuru + "->" + tutar + "TL");
            
        }
        this.bakiyeHesapla();
        
    }
    public void bakiyeHesapla(){
            double toplamGelir = 0;
            double toplamGider = 0;
            
            IslemNode temp =head;
            while(temp!= null){
                if(temp.islemTuru.equalsIgnoreCase("Gelir")){
                    toplamGelir += temp.tutar;
                }else if(temp.islemTuru.equalsIgnoreCase("Gider")){
                            toplamGider += temp.tutar;}
                            
                     temp = temp.next;
                }
                   
                    double netBakiye =toplamGelir - toplamGider;
                    System.out.println("...Bütce durumu");
                    System.out.println("Toplam Gelir:" + toplamGelir + "TL");
                    System.out.println("Toplam gider:" + toplamGider + "TL");
                    System.out.println("Kalan Bakiye:" +netBakiye + "TL");
                    System.out.println("------------------\n");
                    
                }
            }
        
    
    

