

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class DBConnection extends javax.swing.JFrame {

    private Connection conn = null; //Bağlantı nesnemiz
    private String url = "jdbc:mysql://localhost:3306/";//veritabanının adresi ve portu
    private String dbName = "sks_uygulama"; //veritabanının ismi
    private String driver = "com.mysql.jdbc.Driver";//mySQL ile Java arasındaki bağlantıyı sağlayan JDBC sürücüsü
    private String userName = "root"; //veritabanı için kullanıcı adı
    private String password = ""; //kullanıcı şifresi
    private ResultSet res; // sorgulardan dönecek kayıtlar (sonuç kümesi) bu nesne içerisinde tutulacak
  
   


    public DBConnection() {
        initComponents(); //pencereyi oluştur (Netbeans'in otomatik ürettiği kod)
        setLocationRelativeTo(null);
        TabloDoldur();    //Tabloyu veritabanındaki kayıtlar ile doldur.
    }

    public Statement baglantiAc() throws Exception {
        Class.forName(driver).newInstance(); //My sql JDBC sürücüsü kullanılıyor
        conn = DriverManager.getConnection(url + dbName, userName, password);//bağlantı açılıyor
        return conn.createStatement();
    }

    public void baglantiKapat() throws Exception {
        conn.close();
    }
    public void KayitAra(String aranan) {
        try {

            
            String sql = "(SELECT * FROM sks_tablo WHERE isim LIKE '"
                    +"%"+aranan+"%')";

            System.out.println(sql); //sorgunun doğru yazıldığından emin olmak için ekrana yazdırabilirsiniz.
            Statement st = baglantiAc();

            st.executeUpdate(sql); //sorguyu çalıştır

            baglantiKapat(); //bağlantıyı kapat

            TabloDoldur2(sql); //Penceremizdeki tabloyu yeniden doldur (güncelle)

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "bulunamadi", "MySQL Bağlantısında Hata", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void TabloDoldur2(String sql) {
        try {

            Statement st = baglantiAc(); //Bağlantıyı aç 
            
            ResultSet res = st.executeQuery(sql); //VT'den kayıtları ResultSet'e al
            myTableModel model = new myTableModel(res); //Tablomuza model oluştur
            jTable1.setModel(model); //Tabloyu res'teki kayıtlar ile doldur          
            baglantiKapat(); //Bağlantıyı kapat

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Bağlantı Başarısız", "MySQL Bağlantısı", JOptionPane.PLAIN_MESSAGE);
        }
    }
    public void TabloDoldur() {
        try {

            Statement st = baglantiAc(); //Bağlantıyı aç         
            ResultSet res = st.executeQuery("SELECT * FROM sks_uygulama.sks_tablo"); //VT'den kayıtları ResultSet'e al
            myTableModel model = new myTableModel(res); //Tablomuza model oluştur
            jTable1.setModel(model); //Tabloyu res'teki kayıtlar ile doldur          
            baglantiKapat(); //Bağlantıyı kapat

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Bağlantı Başarısız", "MySQL Bağlantısı", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    
    
    

    public void KayitEkle(String ad, String soyad, String cinsiyet,String yer,String katilimci,String konusmaci) {
        
        try {

            
            String sql = "INSERT INTO sks_tablo (isim, aciklama,yer,faaliyet,katilimci,konusmaci) values ('%s', '%s', '%s','%d','%s','%s' )";
            sql = String.format(sql, ad, soyad,yer, Integer.parseInt(cinsiyet),katilimci,konusmaci);

            System.out.println(sql); //sorgunun doğru yazıldığından emin olmak için ekrana yazdırabilirsiniz.
            Statement st = baglantiAc();

            st.executeUpdate(sql); //sorguyu çalıştır

            baglantiKapat(); //bağlantıyı kapat

            TabloDoldur(); //Penceremizdeki tabloyu yeniden doldur (güncelle)

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Kayıt Eklenemedi", "MySQL Bağlantısında Hata", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void KayitSil(String ID) {
        try {
            Statement st = baglantiAc();
            st.executeUpdate("Delete FROM sks_uygulama.sks_tablo where ID=" + ID);
            baglantiKapat(); //bağlantıyı kapat
            TabloDoldur();  //Penceremizdeki tabloyu yeniden doldur (güncelle)
           

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Kayıt Silinemedi", "MySQL Bağlantısı", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void KayitGuncelle(String ID, String ad,String soyad,String cinsiyet,String yer,String katilimci,String konusmaci) {
        try {

            Statement st = baglantiAc();
            String sql = "UPDATE sks_uygulama.sks_tablo SET " +
    "isim='" + ad + "', " +
    "aciklama='" + soyad + "', " +
    "yer='" + yer + "', " +
    "faaliyet=" + cinsiyet +", " +
    "katilimci='" + katilimci + "', " +
    "konusmaci='" + konusmaci + "' " +                
    " WHERE ID=" + ID ;
            System.out.println(sql);

            //System.out.println(sql); sorgunun doğru yazıldığından emin olmak için ekrana yazdırabilirsiniz.

            st.executeUpdate(sql); //sorguyu çalıştır

            baglantiKapat(); //bağlantıyı kapat

            TabloDoldur(); //Penceremizdeki tabloyu yeniden doldur (güncelle)

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Kayıt Güncellenemedi", "MySQL Bağlantısı", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        silButton = new javax.swing.JButton();
        guncelleButton = new javax.swing.JButton();
        AdTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cinsiyetComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ekleButton = new javax.swing.JButton();
        yeniButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        soyadTextField = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        getir = new javax.swing.JButton();
        getirTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        yerTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        katilimciTextField = new javax.swing.JTextField();
        konusmaciTextField = new javax.swing.JTextField();
        yazdirButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sks Uygulama");
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        )

    );
    jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            jTable1MousePressed(evt);
        }
    });
    jScrollPane1.setViewportView(jTable1);
    jTable1.setRowHeight(35);

    silButton.setText("Sil");
    silButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            silButtonActionPerformed(evt);
        }
    });

    guncelleButton.setText("Güncelle");
    guncelleButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            guncelleButtonActionPerformed(evt);
        }
    });

    AdTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            AdTextFieldActionPerformed(evt);
        }
    });

    jLabel1.setText("Adı:");

    cinsiyetComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Bilimsel Faaliyet", "Sergi", "Fuar", "Kermes", "Festival/Şenlik", "Yarışma", "Söyleşi", "Tanıtım Faaliyeti" }));
    cinsiyetComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cinsiyetComboBoxActionPerformed(evt);
        }
    });

    jLabel2.setText("Açıklama:");

    jLabel4.setText("Faaliyet Türü:");

    ekleButton.setText("Ekle");
    ekleButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ekleButtonActionPerformed(evt);
        }
    });

    yeniButton.setText("Yeni");
    yeniButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            yeniButtonActionPerformed(evt);
        }
    });

    soyadTextField.setColumns(20);
    soyadTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    soyadTextField.setLineWrap(true);
    soyadTextField.setRows(5);
    jScrollPane2.setViewportView(soyadTextField);

    jLabel3.setText("İsme Göre Arama:");

    getir.setText("Getir");
    getir.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            getirActionPerformed(evt);
        }
    });

    jLabel5.setText("Yer:");

    jLabel6.setText("Katılımcı:");

    jLabel7.setText("Konuşmacı:");

    katilimciTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            katilimciTextFieldActionPerformed(evt);
        }
    });

    konusmaciTextField.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            konusmaciTextFieldActionPerformed(evt);
        }
    });

    yazdirButton.setText("Yazdır");
    yazdirButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            yazdirButtonActionPerformed(evt);
        }
    });

    jLabel8.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
    jLabel8.setText("Karabük Üniversitesi Sağlık Kültür Spor Daire Başkanlığı Etkinlik Raporlama Programı");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(224, 224, 224)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(getirTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                    .addGap(22, 22, 22)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2)
                                        .addComponent(AdTextField)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(13, 13, 13)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(yerTextField)
                                        .addComponent(katilimciTextField)
                                        .addComponent(konusmaciTextField))))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(silButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ekleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(guncelleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yeniButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(getir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cinsiyetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(yazdirButton))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(154, 154, 154)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel8)
            .addGap(8, 8, 8)
            .addComponent(yazdirButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(ekleButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(silButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(guncelleButton)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(yeniButton))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(AdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(yerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel6)
                .addComponent(katilimciTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7)
                .addComponent(konusmaciTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4)
                .addComponent(cinsiyetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(getirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(getir))
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guncelleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guncelleButtonActionPerformed

        String ID = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        String ad = AdTextField.getText();
        String soyad = soyadTextField.getText();
        String cinsiyet = null;
        String yer = yerTextField.getText();
        String katilimci = katilimciTextField.getText();
        String konusmaci = konusmaciTextField.getText();
       
       if (cinsiyetComboBox.getSelectedIndex() == 0) {
            cinsiyet = "0";
        } 
       else if(cinsiyetComboBox.getSelectedIndex() == 1) {
            cinsiyet = "1";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 2) {
            cinsiyet = "2";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 3) {
            cinsiyet = "3";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 4) {
            cinsiyet = "4";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 5) {
            cinsiyet = "5";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 6) {
            cinsiyet = "6";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 7) {
            cinsiyet = "7";
        }

        
        KayitGuncelle(ID, ad,soyad,cinsiyet,yer,katilimci,konusmaci);
        // TODO add your handling code here:
    }//GEN-LAST:event_guncelleButtonActionPerformed

    private void ekleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleButtonActionPerformed

        String ad = AdTextField.getText();
        String soyad = soyadTextField.getText();
        String yer = yerTextField.getText();
        String katilimci = katilimciTextField.getText();
        String konusmaci = konusmaciTextField.getText();
       
        String cinsiyet = null;
          if (cinsiyetComboBox.getSelectedIndex() == 0) {
            cinsiyet = "0";
        } 
       else if(cinsiyetComboBox.getSelectedIndex() == 1) {
            cinsiyet = "1";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 2) {
            cinsiyet = "2";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 3) {
            cinsiyet = "3";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 4) {
            cinsiyet = "4";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 5) {
            cinsiyet = "5";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 6) {
            cinsiyet = "6";
        }
       else if(cinsiyetComboBox.getSelectedIndex() == 7) {
            cinsiyet = "7";
        }
         KayitEkle(ad, soyad, cinsiyet,yer,katilimci,konusmaci);

    }//GEN-LAST:event_ekleButtonActionPerformed

    private void silButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silButtonActionPerformed
        
        String ID = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        KayitSil(ID);

    }//GEN-LAST:event_silButtonActionPerformed

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
         //Tabloda üzerine tıklanılan satırın bilgilerini ilgili form elemanlarına yazar.
        AdTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
        soyadTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        yerTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString());
        String cinsiyet = jTable1.getValueAt(jTable1.getSelectedRow(), 4).toString();      
        if (cinsiyet.equals("1")){
            cinsiyetComboBox.setSelectedIndex(1);
        }
        else if(cinsiyet.equals("0")){
            cinsiyetComboBox.setSelectedIndex(0);
        }
        
        else if(cinsiyet.equals("2")){
            cinsiyetComboBox.setSelectedIndex(2);
        }
        else if(cinsiyet.equals("3")){
            cinsiyetComboBox.setSelectedIndex(3);
        }
        else if(cinsiyet.equals("4")){
            cinsiyetComboBox.setSelectedIndex(4);
        }
        else if(cinsiyet.equals("5")){
            cinsiyetComboBox.setSelectedIndex(5);
        }
        else if(cinsiyet.equals("6")){
            cinsiyetComboBox.setSelectedIndex(6);
        }
        
         else if(cinsiyet.equals("7")){
            cinsiyetComboBox.setSelectedIndex(7);
        }
        katilimciTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString());
        konusmaciTextField.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 6).toString());
        
        
        
        
    }//GEN-LAST:event_jTable1MousePressed

    private void yeniButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yeniButtonActionPerformed
        AdTextField.setText("");
        soyadTextField.setText("");
      cinsiyetComboBox.setSelectedIndex(0);
      yerTextField.setText("");
      katilimciTextField.setText("");
      konusmaciTextField.setText("");
    }//GEN-LAST:event_yeniButtonActionPerformed

    private void cinsiyetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cinsiyetComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cinsiyetComboBoxActionPerformed

    private void getirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getirActionPerformed
        // TODO add your handling code here:
        String aranan = getirTextField.getText();
        KayitAra(aranan);
    }//GEN-LAST:event_getirActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_formPropertyChange

    private void katilimciTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_katilimciTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_katilimciTextFieldActionPerformed

    private void konusmaciTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konusmaciTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_konusmaciTextFieldActionPerformed

    private void AdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AdTextFieldActionPerformed

    private void yazdirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yazdirButtonActionPerformed
        // TODO add your handling code here:
         MessageFormat header = new MessageFormat("Etkinlikler Sayfa {0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.FIT_WIDTH, header, null);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_yazdirButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        boolean a;
        
        splash cagir=new splash();
        a=cagir.isVisible();
        
        try{
       Thread.sleep(1000);// milisaniye cinsinden kaç saniye beklemsini istiyorsan 1000 ile çarp. 5000 = 5sn
       java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DBConnection().setVisible(true);
             
            }
     
        });
} catch ( InterruptedException e ){
       System.out.println( "Pramatüre olarak uyandi:)" );
}
   
        
    
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AdTextField;
    private javax.swing.JComboBox cinsiyetComboBox;
    private javax.swing.JButton ekleButton;
    private javax.swing.JButton getir;
    private javax.swing.JTextField getirTextField;
    private javax.swing.JButton guncelleButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField katilimciTextField;
    private javax.swing.JTextField konusmaciTextField;
    private javax.swing.JButton silButton;
    private javax.swing.JTextArea soyadTextField;
    private javax.swing.JButton yazdirButton;
    private javax.swing.JButton yeniButton;
    private javax.swing.JTextField yerTextField;
    // End of variables declaration//GEN-END:variables
}
