package butcetakip;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author İnci Nisa
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class AnaEkran  extends JFrame{
    private ButceLinkedList butcem;
    
    private JTextField txtTutar;
    private JTextField txtTarih;
    private JComboBox<String> cmbKategori;
    private JTextField txtAciklama;
    private JComboBox<String> cmbIslemTuru;
    
    private JTable islemTablosu;
    private DefaultTableModel tabloModeli;
    private JLabel lblNetBakiye;
    
    protected JPanel altIslemPaneli;
    
    public AnaEkran(){
        butcem= new ButceLinkedList();
        arayuzuHazirla();
        tabloyuVeBakiyeyiGuncelle();
    }
    private void arayuzuHazirla(){
        setTitle("Kişisel Bütçe ve Harcama Takibi");
        setSize(850,520);   
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));
        
        JPanel formPaneli= new JPanel(new GridLayout(2,6,8,8));
        formPaneli.setBorder(BorderFactory.createTitledBorder("Yeni İşlem Ekle"));
        
        cmbIslemTuru = new JComboBox<>(new String []{"Gider", "Gelir"});
        cmbKategori = new JComboBox<>(new String[]{"Market", "Yemek", "Ulaşım", "Fatura", "Eğlence", "Maaş", "Diğer"});
        txtTutar= new JTextField();
        txtTarih = new JTextField("21.11.2026");
        txtAciklama= new JTextField();
        JButton btnEkle= new JButton("İşlemi Ekle");
        
        formPaneli.add(new JLabel("İşlem Türü:"));
        formPaneli.add(new JLabel("Kategori"));
        formPaneli.add(new JLabel("Tutar(TL):"));
        formPaneli.add(new JLabel("Tarih:"));
        formPaneli.add(new JLabel ("Açıklama:"));
        formPaneli.add(new JLabel("")); 
        
        formPaneli.add(cmbIslemTuru);
        formPaneli.add(cmbKategori);
        formPaneli.add(txtTutar);
        formPaneli.add(txtTarih);
        formPaneli.add(txtAciklama);
        formPaneli.add(btnEkle);
        
        add(formPaneli, BorderLayout.NORTH);
        
        
        String[] kolonlar = {"ID", "Tür", "Kategori", "Tutar(TL)", "Tarih", "Açıklama"};
        tabloModeli = new DefaultTableModel(kolonlar,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        islemTablosu = new JTable(tabloModeli);
        add(new JScrollPane(islemTablosu), BorderLayout.CENTER);
        
        
        altIslemPaneli = new JPanel(new BorderLayout());
        altIslemPaneli.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
        
        lblNetBakiye = new JLabel("Net Bakiye: 0.0 TL");
        lblNetBakiye.setFont(new Font("SansSerif", Font.BOLD,16));
        altIslemPaneli.add(lblNetBakiye, BorderLayout.WEST);
        altIslemPaneli.add(new IslemKontrolPaneli(butcem, islemTablosu, tabloModeli, this::tabloyuVeBakiyeyiGuncelle), BorderLayout.EAST);
        add (altIslemPaneli, BorderLayout.SOUTH);
        
        btnEkle.addActionListener(e-> yeniIslemKaydet());
    }
    private void yeniIslemKaydet(){
        try{
            double tutar = Double.parseDouble(txtTutar.getText().trim().replace(",", "."));
            String tarih = txtTarih.getText().trim().replace(",", " ");
            String kategori = cmbKategori.getSelectedItem().toString();
            String aciklama= txtAciklama.getText().trim().replace(",", " ");
            String islemTuru= cmbIslemTuru.getSelectedItem().toString();
            
            if (aciklama.isEmpty()){
                aciklama="-";
        }
        int yeniId= siradakiIdyiBul();
        butcem.islemEkle(yeniId, tutar, tarih, kategori, aciklama, islemTuru);
        
        tabloyuVeBakiyeyiGuncelle();
        txtTutar.setText("");
        txtAciklama.setText("");
    } catch (NumberFormatException ex){
        JOptionPane.showMessageDialog(this," Lütfen geçerli bir sayısal tutar girin!", "Hatalı Giriş", JOptionPane.WARNING_MESSAGE);

}
} 
 private int siradakiIdyiBul(){
     int maxId =0;
     IslemNode temp = butcem.head;
     while(temp!= null){
         if(temp.id > maxId){
             maxId= temp.id;
         }
         temp= temp.next;
     }
     return maxId + 1;
 }
  protected void tabloyuVeBakiyeyiGuncelle(){
      tabloModeli.setRowCount(0);
      
      double toplamGelir = 0;
      double toplamGider = 0;
      
      IslemNode temp = butcem.head;
      while(temp !=null){
          Object[] satir = {
              temp.id,
              temp.islemTuru,
              temp.kategori,
              temp.tutar,
              temp.tarih,
              temp.aciklama
          };
          tabloModeli.addRow(satir);
          
          if (temp.islemTuru.equalsIgnoreCase("Gelir")){
              toplamGelir += temp.tutar;
          } else if (temp.islemTuru.equalsIgnoreCase("Gider")){
              toplamGider += temp.tutar;
          }
          temp = temp.next;
      }
      double netBakiye= toplamGelir - toplamGider;
      lblNetBakiye.setText(String.format("Net Bakiye: %.2f TL", netBakiye));
      
  }
public static void main(String[]args){
    SwingUtilities.invokeLater(()-> {
        new AnaEkran().setVisible(true);
    });
}
}