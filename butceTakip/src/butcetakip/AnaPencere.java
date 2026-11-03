/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package butcetakip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
        

/**
 *
 * @author İnci Nisa
 */
public class AnaPencere extends JFrame {
    //Backend liste bağlantıları
    private LinkedList harcamaListesi;
    private LinkedList islemListesi;
    
    // Arayüz bileşenleri
    private JLabel lblToplamGelir;
    private JLabel lblToplamGider;
    private JLabel lblNetBakiye;
    private JTable tabloHarcamalar;
    private DefaultTableModel tabloModeli;
    
    //Renk paleti
    
    private static final Color RENK_YAN_MENU = new Color(30, 41, 59);
    private static final Color RENK_ANA_BUTON = new Color(37,99,235);
    private static final Color RENK_ARKA_PLAN = new Color(243, 244, 246);
    private static final Color RENK_KART_BG = Color.WHITE;
    private static final Color RENK_TABLO_BASLIK = new Color(226, 232, 240);
    private static final Color RENK_YESIL = new Color (34,139,34);
    private static final Color RENK_KIRMIZI = new Color(211,47,47);
    private static final Color RENK_METIN_KOYU = new Color( 31,41,55);
    
    public AnaPencere(){
        //nesne başlatma ve veri yükleme
        harcamaListesi = new LinkedList();
        islemListesi= new LinkedList();
        
        pencereAyarlariniYap();
        anaDuzenOlustur();
        listedenTabloyuGuncelle();
        bakiyeleriGuncelle();
    }
      private void pencereAyarlariniYap(){
          setTitle("Kişisel Bütçe ve Harcama Takip Uygulaması | Stack Team");
          setSize(1100,700);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          getContentPane().setBackground(RENK_ARKA_PLAN);
          setLayout(new BorderLayout(15,15));
      } 
    private void anaDuzenOlustur(){
        //sol menü
        add(solMenuOlustur(), BorderLayout.WEST);
        
        //Orta içerik alanı
        JPanel icerikPaneli = new JPanel(new BorderLayout(15,15));
        icerikPaneli.setBackground(RENK_ARKA_PLAN);
        icerikPaneli.setBorder(new EmptyBorder(15,0,15,15));
        
        icerikPaneli.add(ustBakiyeKartlariOlustur(), BorderLayout.NORTH);
        icerikPaneli.add(tabloPaneliOlustur(), BorderLayout.CENTER);
        
        add(icerikPaneli, BorderLayout.CENTER);
    }
    private JPanel solMenuOlustur(){
        JPanel solPanel = new JPanel();
        solPanel.setLayout(new BoxLayout(solPanel, BoxLayout.Y_AXIS));
        solPanel.setBackground(RENK_YAN_MENU);
        solPanel.setPreferredSize(new Dimension (230,0));
        solPanel.setBorder(new EmptyBorder(25,15,20,15));
        
        //başlık
        JLabel lblBaslik = new JLabel("BÜTÇE TAKİP");
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBaslik.setForeground(Color.WHITE);
        lblBaslik.setAlignmentX(Component.CENTER_ALIGNMENT);
        solPanel.add(lblBaslik);
        
        JLabel lblAltBaslik = new JLabel("Stack Team");
        lblAltBaslik.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblAltBaslik.setForeground(new Color(148,163,184));
        lblAltBaslik.setAlignmentX(Component.CENTER_ALIGNMENT);
        solPanel.add(lblAltBaslik);
        
        solPanel.add(Box.createRigidArea(new Dimension(0,40)));
        
        //süha ekleme yapacak
        
        
        
        solPanel.add(btnEkle);
        solPanel.add(Box.createRigidArea(new Dimension(0,12)));
        solPanel.add(btnSil);
        solPanel.add(Box.createRigidArea(new Dimension(0,12)));
        solPanel.add(btnKategori);
        solPanel.add(Box.createRigidArea(new Dimension(0,12)));
        solPanel.add(btnSirala);
        solPanel.add(Box.createRigidArea(new Dimension(0,12)));
        solPanel.add(btnSorgula);
        
        return solPanel;        
    }
    
    private JPanel ustBakiyeKartlariOlustur(){
        JPanel kartlarPaneli = new JPanel(new GridLayout(1,3,15,0));
        kartlarPaneli.setBackground(RENK_ARKA_PLAN);
        kartlarPaneli.setPreferredSize(new Dimension(0,110));
        
        lblToplamGelir = new JLabel ("₺0.00", SwingConstants.CENTER) ;
        lblToplamGider = new JLabel("₺0.00", SwingConstants.CENTER);
        lblNetBakiye = new JLabel("₺0.00", SwingConstants.CENTER);
        
        kartlarPaneli.add(tekKartOlustur("Toplam Gelir", lblToplamGelir, RENK_YESIL));
        kartlarPaneli.add(tekKartOlustur("Toplam Gider", lblToplamGider, RENK_KIRMIZI));
        kartlarPaneli.add(tekKartOlustur("Net Bakiye", lblNetBakiye, RENK_ANA_BUTON));
        
        return kartlarPaneli;
    }
    
    private JPanel tekKartOlustur(String baslik, JLabel degerLabel,Color vurguRengi){
        JPanel kart= new JPanel(new BorderLayout(5,5));
        kart.setBackground(RENK_KART_BG);
        kart.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226,232,240),1),
                new EmptyBorder(15,15,15,15)
        ));
        
        JLabel lblBaslik = new JLabel(baslik);
        lblBaslik.setFont(new Font("Segoe UI", Font.BOLD,13));
        lblBaslik.setForeground(new Color(100,116,139));
        
        degerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        degerLabel.setForeground(vurguRengi);
        
        kart.add(lblBaslik, BorderLayout.NORTH);
        kart.add(degerLabel, BorderLayout.CENTER);
        
        return kart;
    } 
    private JPanel tabloPaneliOlustur(){
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(RENK_KART_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
                 BorderFactory.createLineBorder(new Color(226,232,240), 1),
                 new EmptyBorder(15,15,15,15)
                         ));
        JLabel lblTabloBaslik = new JLabel("Harcama ve İşlem Geçmişi");
        lblTabloBaslik.setFont(new Font("Segoe UI", Font.BOLD,16));
        lblTabloBaslik.setForeground(RENK_METIN_KOYU);
        lblTabloBaslik.setBorder(new EmptyBorder(0,0,10,0));
        panel.add(lblTabloBaslik, BorderLayout.NORTH);
        
        String[] kolonlar ={"İşlem ID", "İşlem Adı/Kategori", "Tutar(TL)"};
        tabloModeli = new DefaultTableModel(kolonlar,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        tabloHarcamalar= new JTable(tabloModeli);
        tabloHarcamalar.setRowHeight(35);
        tabloHarcamalar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabloHarcamalar.setGridColor(new Color( 241,245,249));
        tabloHarcamalar.setSelectionBackground(new Color(219,234,254));
        tabloHarcamalar.setSelectionForeground(RENK_METIN_KOYU);
        
        tabloHarcamalar.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,13));
        tabloHarcamalar.getTableHeader().setBackground(RENK_TABLO_BASLIK);
        tabloHarcamalar.getTableHeader().setForeground(RENK_METIN_KOYU);
        tabloHarcamalar.getTableHeader().setPreferredSize(new Dimension(0,40));
        
        
        //tutar kolonunu sağa hizalayacağız
        DefaultTableCellRenderer sagHizala = new DefaultTableCellRenderer();
        sagHizala.setHorizontalAlignment(SwingConstants.RIGHT);
        tabloHarcamalar.getColumnModel().getColumn(2).setCellRenderer(sagHizala);
        
        JScrollPane scrollPane = new JScrollPane(tabloHarcamalar);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(226,232,240)));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
        
    }
    
    private JButton menuButonuOlustur(String metin){
        JButton btn = new JButton(metin);
        btn.setMaximumSize(new Dimension(200,42));
        btn.setFont(new Font("Segoe UI", Font.BOLD,13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(RENK_ANA_BUTON);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10,12,10,12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        return btn;
    }
    
    // entegre metodlar
    public void listedenTabloyuGuncelle(){
        tabloModeli.setRowCount(0);
        Node current = harcamaListesi.head;
        
        while (current != null){
            tabloModeli.addRow(new Object[]{ current.id, current.harcamaAdi, String.format("%.2f TL", current.tutar)});
            current= current.next;
            
        }
    }
     public void bakiyeleriGuncelle(){
         double toplam= 0;
         Node current = harcamaListesi.head;
         while(current != null){
             toplam += current.tutar;
             current = current.next;
         }
         lblToplamGider.setText(String.format("%.2f TL", toplam));
         lblNetBakiye.setText(String.format("%.2f TL", -toplam));
                 
     }
     
     //sühanın bağlayacağı fonksiyonlar
     
     
     
     private void islemSilPenceresiAc(){
         String idStr= JOptionPane.showInputDialog(this, "Silinecek işlem ID'si:");
         if (idStr != null){
             try{
                 int id = Integer.parseInt(idStr);
                 harcamaListesi.delete(id);
                 listedenTabloyuGuncelle();
                 bakiyeleriGuncelle();
             } catch (NumberFormatException ex){
                 JOptionPane.showMessageDialog(this, "Geçersiz ID!", "Hata", JOptionPane.ERROR_MESSAGE);
                 
             }
         }
     }

    private void tutaraGoreSirala(){
        harcamaListesi.sirala();
        listedenTabloyuGuncelle();
        JOptionPane.showMessageDialog(this, "Harcamalar tutara göre sıralandı.");
        
    }

private void butceAsimKontroluYap(){
    harcamaListesi.butceAsimKontrol();
}

public static void main (String[] args){
    try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
    } catch(Exception e){
        e.printStackTrace();
    }
    
    SwingUtilities.invokeLater(() -> {
        new AnaPencere().setVisible(true);
    });
  }
}



    

