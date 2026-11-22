/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package butcetakip;

import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Süha
 */
public class IslemKontrolPaneli extends JPanel {
    private ButceLinkedList butcem;
    private JTable islemTablosu;
    private DefaultTableModel tabloModeli;
    private Runnable ekranGuncelle;
    private JComboBox<String>cmbFiltre;
    
    public IslemKontrolPaneli(ButceLinkedList butcem,JTable islemTablosu,
             DefaultTableModel tabloModeli,Runnable ekranGuncelle){
        this.butcem=butcem;
        this.islemTablosu=islemTablosu;
        this.tabloModeli=tabloModeli;
        this.ekranGuncelle=ekranGuncelle;
        //Sağa yaslamak için(yan yana)
        setLayout(new FlowLayout(FlowLayout.RIGHT,8,0));
        
        cmbFiltre=new JComboBox<>(new String[]{"Tümü","Market","Yemek","Ulaşım",
            "Fatura","Eğlence","Maaş","Diğer"
        });
        JButton btnSirala=new JButton("Tutara Göre Sırala");
        JButton btnSil=new JButton("Seçili işlemi sil");
        
        add(new JLabel("Kategori Filtresi:"));
        add(cmbFiltre);
        add(btnSirala);
        add(btnSil);
        //buton ve liste tıklama olayı
        cmbFiltre.addActionListener(e -> kategoriyeGoreFiltrele());
        btnSirala.addActionListener(e -> tutaraGoreSirala());
        btnSil.addActionListener(e -> seciliIslemiSil());
    }
        // Tablodan seçilen satırı backend'deki islemSil() metoduyla siler
    private void seciliIslemiSil() {
        int seciliSatir = islemTablosu.getSelectedRow();
        if (seciliSatir == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen tablodan silmek istediğiniz satırı seçin");
            return;
        }
        int silinecekId = Integer.parseInt(tabloModeli.getValueAt(seciliSatir, 0).toString());
        butcem.islemSil(silinecekId);
        cmbFiltre.setSelectedIndex(0);
           ekranGuncelle.run();
    }
    //back enddeki siralayla sıralar büyükten kücüğe
    private void tutaraGoreSirala(){
        butcem.sirala();
        cmbFiltre.setSelectedIndex(0);
        ekranGuncelle.run();
    }
       //kategoriye gore bağlı listeyi gezip tablo süzme
    private void kategoriyeGoreFiltrele(){
        String secilenKategori=cmbFiltre.getSelectedItem().toString();
        if(secilenKategori.equals("Tümü")){ekranGuncelle.run();
          return;
        }
        tabloModeli.setRowCount(0);
        IslemNode temp=butcem.head;
        while(temp!=null){
          if (temp.kategori.equalsIgnoreCase(secilenKategori)){
              Object[] satir={
                  temp.id,
                  temp.islemTuru,
                  temp.kategori,
                  temp.tutar,
                  temp.tarih,
                  temp.aciklama
              };
              tabloModeli.addRow(satir);
          }
          temp=temp.next;
        }
    }
}

