/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package butcetakip;

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
    String islemTuru;
    IslemNode next;
    public IslemNode(int id, double tutar, String tarih, String kategori, String aciklama,String islemTuru){
        this.id = id;
        if(tutar<0){
            this.tutar = 0;
        }else {
            this.tutar = tutar;
        }
        this.tarih = tarih;
        this.kategori = kategori;
        this.aciklama = aciklama;
        this.islemTuru = islemTuru;
        this.next = null;
    }
    
}
