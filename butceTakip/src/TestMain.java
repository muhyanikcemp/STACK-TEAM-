/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MUHAMMED
 */
public class TestMain {
    public static void main(String[] args){
        System.out.println("---BÜTÇE TAKİP SİSTEMİ BAŞLATILIYOR...\n");
        ButceLinkedList benimButcem = new ButceLinkedList();
        benimButcem.islemEkle(1,5000, "23.10.2026", "Maaş", "Ekim Ayı Harçlığı","Gelir");
        benimButcem.islemEkle(2,4000, "24.10.2026", "Burs", "Ekim Ayı Harçlığı","Gelir");
        benimButcem.islemEkle(3,-4000, "25.10.2026", "Fatura", "Elektrik","Gider");
        benimButcem.islemEkle(4,-1000, "26.10.2026", "Eğlence", "Sİnema","Gider");
    }
    
}
