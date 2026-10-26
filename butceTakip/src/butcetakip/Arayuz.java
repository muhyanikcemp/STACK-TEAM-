package butcetakip;

import javax.swing.*;

public class Arayuz {
    public static void main(String[] args) {
        // 1. Temel bir pencere frame oluşturduk
        JFrame pencere = new JFrame("Bütçe Takip");
        pencere.setSize(300, 200);
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel etiket = new JLabel("İlk Hafta Arayüz Taslağı");//basit bir yazı denemesi
        pencere.add(etiket);

        pencere.setVisible(true);//pencereyi görünür yap
    }
}


    

