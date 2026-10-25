package butcetakip;

import javax. swing.*;

public class Arayuz {
    public static void main (String[]args){
       
        JFrame pencere= new JFrame ("Bütçe Takip Uygulaması");
        pencere.setSize(400,300);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuCubugu = new JMenuBar();
        JMenu islemlerMenusu = new JMenu ("İşlemler");
        JMenuItem kayitEkle = new JMenuItem("Kayıt Ekle");
        
        islemlerMenusu.add(kayitEkle);
        menuCubugu.add(islemlerMenusu);
        pencere.setJMenuBar(menuCubugu);
        
        pencere.setVisible(true);
    }
            
}