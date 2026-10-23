/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package butcetakip;

/**
 *
 * @author gmzes
 */
public class KategoriNode {
    String kategoriAdi;
    double aylikButce;
    double mevcutHarcama;
    KategoriNode next; //bağlı liste bağlantısı
    // constructor
    public KategoriNode(String kategoriAdi,double aylikButce){
        this.kategoriAdi=kategoriAdi; //dışarıdan gelen ismi sınıfın içine kaydeder
        this.aylikButce=aylikButce;
        this.mevcutHarcama=0; // ilk açıldığında harcama sıfırdır
        this.next=null;
    } 
}
