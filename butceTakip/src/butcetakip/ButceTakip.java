/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package butcetakip;

/**
 *
 * @author MUHAMMED
 */
public class ButceTakip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("----Butce Takip Sistemi Baslatiliyor\n");
        ButceLinkedList butcem = new ButceLinkedList();
butcem.islemEkle(1, 1500.50, "23.10.2026","Market","Market Alışverişi","Gider");
butcem.islemEkle(2,5000.00,"24.10.2026","Maas", "Ekim  Maası", "Gelir");
butcem.listele();
System.out.println("SIRALAMA YAPILIYOR");
butcem.sirala();
butcem.listele();
System.out.println("Sistem basariyla calisti, harcama dosyaya eklendi!");
    }
    
}
