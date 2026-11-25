/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package butcetakip;

/**
 *
 * @author İnci Nisa
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class KategoriButcePaneli extends JPanel{
    private ButceLinkedList butcem;
    private JComboBox<String> cmbKategori;
    private JTextField txtAylikLimit;
    private DefaultTableModel kategoriTabloModeli;
    
    public KategoriButcePaneli(ButceLinkedList butcem){
        this.butcem=butcem;
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
      JPanel ustPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
      ustPanel.setBorder(BorderFactory.createTitledBorder("Kategori Aylık Bütçe Limiti Belirle"));
      
      cmbKategori=new JComboBox<>(new String[]{
          "Market", "Yemek", "Ulaşım", "Fatura", "Eğlence", "Diğer"
              
      });
      txtAylikLimit=new JTextField(10);
      JButton btnLimitKaydet=new JButton("Limiti Kaydet/ Güncelle");
      JButton btnAsimKontrol= new JButton("Bütçe Aşım Kontrolü Yap");
      
      ustPanel.add(new JLabel("Kategori:"));
      ustPanel.add(cmbKategori);
      ustPanel.add(new JLabel("Aylık Limit(TL):"));
      ustPanel.add(txtAylikLimit);
      ustPanel.add(btnLimitKaydet);
      ustPanel.add(btnAsimKontrol);
      
      add (ustPanel, BorderLayout.NORTH);
      
      //tablo
      String[] kolonlar={"Kategori", "Aylık Limit(TL)", "Mevcut Harcama(TL)","Kalan Bütçe(TL)","Durum"};
      kategoriTabloModeli=new DefaultTableModel(kolonlar,0){
          @Override
          public boolean isCellEditable(int row,int column){
              return false;
          }
      };
      JTable kategoriTablosu=new JTable(kategoriTabloModeli);
      add(new JScrollPane(kategoriTablosu), BorderLayout.CENTER);
      
      btnLimitKaydet.addActionListener(e-> kategoriLimitiKaydet());
      btnAsimKontrol.addActionListener(e-> asimKontroluGoster());
      
    }
    
    private void kategoriLimitiKaydet(){
        try{
            double limit= Double.parseDouble(txtAylikLimit.getText().trim().replace(",","."));
            if (limit <= 0){
                JOptionPane.showMessageDialog(this, "Aylık bütçe limiti 0'dan büyük olmalıdır!");
                return;
            }
            
            String secilenKategori = cmbKategori.getSelectedItem().toString();
            KategoriNode temp= butcem.kategoriHead;
            boolean bulundu = false;
            
            while (temp!=null){
                if(temp.kategoriAdi.equalsIgnoreCase(secilenKategori)){
                    temp.aylikButce=limit;
                    bulundu=true;
                    break;
                }
                temp=temp.next;
            }
            if(!bulundu){
                KategoriNode yeniNode=new KategoriNode(secilenKategori,limit);
                if (butcem.kategoriHead==null){
                    butcem.kategoriHead=yeniNode;
                }else {
                    KategoriNode current=butcem.kategoriHead;
                    while (current.next != null){
                        current= current.next;
                    }
                    current.next=yeniNode;
                }
            }
            txtAylikLimit.setText("");
            limitleriGuncelle();
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayısal limit girin!", "Hatalı Giriş", JOptionPane.WARNING_MESSAGE);
            
        }
    }
    //gezip harcama hesaplama
    public void limitleriGuncelle(){
        kategoriTabloModeli.setRowCount(0);
        KategoriNode kNode=butcem.kategoriHead;
        
        while(kNode != null){
            double kategoriHarcamasi=0;
            IslemNode iNode =butcem.head;
            
            while(iNode!= null){
                if(iNode.islemTuru.equalsIgnoreCase("Gider")
                        && iNode.kategori.equalsIgnoreCase(kNode.kategoriAdi)){
                    kategoriHarcamasi += iNode.tutar;
                    
                }
                iNode=iNode.next;
            }
            kNode.mevcutHarcama = kategoriHarcamasi;
            double kalan = kNode.aylikButce - kNode.mevcutHarcama;
            String durum=(kNode.mevcutHarcama> kNode.aylikButce) ? "LİMİT AŞILDI!": "Normal";
            
            Object [] satir={
                kNode.kategoriAdi,
                String.format("%.2f", kNode.aylikButce),
                String.format("%.2f", kNode.mevcutHarcama),
                String.format("%.2f", kalan),
                durum
            };
            kategoriTabloModeli.addRow(satir);
            
            kNode = kNode.next;
        }
    }
    //metot çağır
    private void asimKontroluGoster(){
        limitleriGuncelle();
        butcem.butceAsimKontrol(); //metot tetikledik
        
        if (butcem.kategoriHead==null){
            JOptionPane.showMessageDialog(this, "Henüz hiçbir kategori için bütçe limiti belirlenmedi.");
            return;
        }
        
        StringBuilder uyariMetni= new StringBuilder();
        KategoriNode current= butcem.kategoriHead;
        boolean asanVarMi= false;
        
        while (current !=null){
            if(current.mevcutHarcama > current.aylikButce){
                double asimMiktari = current.mevcutHarcama - current.aylikButce;
                uyariMetni.append("- ")
                        .append(current.kategoriAdi)
                        .append(" kategorisi bütçesini")
                        .append(String.format("%.2f TL", asimMiktari))
                        .append(" aştı!\n");
                asanVarMi = true;
            }
            current= current.next;
        }
        if (asanVarMi){
            JOptionPane.showMessageDialog(this, uyariMetni.toString(), "Bütçe Aşım Uyarısı", JOptionPane.WARNING_MESSAGE);
            
        } else {
            JOptionPane.showMessageDialog(this,"Bütçesini aşan herhangi bir kategori bulunmuyor."," Durum Normal", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
