import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Objects;

public class LogicClass {
    ConnectionDB connectionDB = new ConnectionDB();
    String id;
    String password;
    String position;
    int indexOfCalisanId = 0;
    int indexOfUrunId = 0;
    int flag = 0;
    public void LoginUILogic(){
        ViewingClass.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //login butonuna basılınca çalışacak logic
                //Objects.equals(ViewingClass.idTextField.getText(), "123"

                String getIdFromDb = "SELECT calisanId, sifre, calisanPozisyon FROM calisanlar";

                try {
                    Statement statement = connectionDB.connection().createStatement();
                    ResultSet resultSet = statement.executeQuery(getIdFromDb);

                    while (resultSet.next()){
                        id = resultSet.getString("calisanId");
                        password = resultSet.getString("sifre");
                        position = resultSet.getString("calisanPozisyon");

                        if ((Objects.equals(ViewingClass.idTextField.getText(), id)) &&
                                Objects.equals(ViewingClass.passwordTextField.getText(),password) &&
                                (Objects.equals(position, "müdür"))){
                            flag = 1;
                            break;
                        }else if ((Objects.equals(ViewingClass.idTextField.getText(), id)) &&
                                Objects.equals(ViewingClass.passwordTextField.getText(),password) &&
                                (Objects.equals(position, "işci"))){
                            flag = 2;
                            break;
                        }
                        else {
                            flag = 0;
                        }
                    }
                    if (flag == 1){
                        ViewingClass.frame.setVisible(false);
                        ViewingClass.AdminPanScreen();
                    } else if (flag == 2){
                        ViewingClass.frame.setVisible(false);
                        ViewingClass.StaffPanScreen();
                    }
                    else {
                        JOptionPane.showMessageDialog(ViewingClass.frame,"Hatalı giriş");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ViewingClass.changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewingClass.frame.setVisible(false);
                ViewingClass.ChangePasswordScreen();
            }
        });

    }

    public void AdminPanUILogic(){
        //TODO:AREA WHERE STOCK OPERATIONS ARE CARRIED OUT
        //yeni stok eklemek için kullanılan button-------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.addButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statement statement;
                try {
                    String getMaxUrunId = "SELECT MAX(urunId) FROM urunler";
                    statement = connectionDB.connection().createStatement();
                    ResultSet resultSet = statement.executeQuery(getMaxUrunId);

                    if (resultSet.next()){
                        indexOfUrunId = resultSet.getInt(1) + 1;
                    }

                    try {
                        String urunAd = ViewingClass.isimTextField.getText();
                        String urunKategori = ViewingClass.kategoriTextField.getText();
                        int subeNo = Integer.parseInt(ViewingClass.subeNoTextField.getText());
                        int urunFiyat = Integer.parseInt(ViewingClass.fiyatTextField.getText());
                        String urunSiparisDurum = ViewingClass.siparisDurumuTextField.getText();

                        String sqlQuery = "CALL stokEkle(?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                        preparedStatement.setInt(1, indexOfUrunId);
                        preparedStatement.setString(2,urunAd);
                        preparedStatement.setString(3,urunKategori);
                        preparedStatement.setInt(4,subeNo);
                        preparedStatement.setInt(5,urunFiyat);
                        preparedStatement.setString(6,urunSiparisDurum);

                        preparedStatement.execute();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //stoktan veri silmek için kullanılan button-----------------------------------------------------------------------------------------------------------------------------
        ViewingClass.deleteButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int urunId = Integer.parseInt(ViewingClass.urunNoTextField.getText());
                    String sqlQuery = "DELETE FROM urunler WHERE urunId="+urunId;

                    PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                    preparedStatement.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //stoktaki veriyi güncellemek için kullanılan buton----------------------------------------------------------------------------------------------------------------------
        ViewingClass.updateButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String kategori = ViewingClass.kategoriTextField.getText();
                    String isim = ViewingClass.isimTextField.getText();
                    int fiyat = Integer.parseInt(ViewingClass.fiyatTextField.getText());
                    int subeNo = Integer.parseInt(ViewingClass.subeNoTextField.getText());
                    String siparisDurumu = ViewingClass.siparisDurumuTextField.getText();
                    int urunNo = Integer.parseInt(ViewingClass.urunNoTextField.getText());

                    Statement statement = connectionDB.connection().createStatement();
                    String query = "UPDATE urunler SET  urunKategori='" +
                            kategori + "' ,urunAd='" +isim+ "' ,urunFiyat=" +fiyat+ " ,subeNo=" +subeNo+ " ,urunSiparisDurum='" +siparisDurumu+
                            "' WHERE urunId=" +urunNo+";";

                    statement.execute(query);
                    statement.executeUpdate(query);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Tablodan seçilen ürünü textField'lara doldurmak için kullanılan button-------------------------------------------------------------------------------------------------
        ViewingClass.stockTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = ViewingClass.stockTable.getSelectedRow();
                String setToTextField;
                String[] arrayForData = new String[ViewingClass.stockTable.getColumnCount()];
                for (int i = 0; i < ViewingClass.stockTable.getColumnCount(); i++){
                    setToTextField = (String) ViewingClass.stockTable.getValueAt(selectedRow, i);
                    arrayForData[i] = setToTextField;
                }
                ViewingClass.urunNoTextField.setText(arrayForData[0]);
                ViewingClass.kategoriTextField.setText(arrayForData[1]);
                ViewingClass.isimTextField.setText(arrayForData[2]);
                ViewingClass.fiyatTextField.setText(arrayForData[3]);
                ViewingClass.subeNoTextField.setText(arrayForData[4]);
                ViewingClass.siparisDurumuTextField.setText(arrayForData[5]);
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //Tabloyu yenilemek için kullanılan button-------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.refreshButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getStockFromDb = "SELECT * FROM urunler ORDER BY urunid";
                try {
                    Statement statement = connectionDB.connection().createStatement();
                    ResultSet resultSet = statement.executeQuery(getStockFromDb);
                    int i = 0;
                    while (resultSet.next()){
                        int urunNo = resultSet.getInt("urunId");
                        String kategori = resultSet.getString("urunKategori");
                        String isim = resultSet.getString("urunAd");
                        int fiyat = resultSet.getInt("urunFiyat");
                        int subeNo = resultSet.getInt("subeNo");
                        String siparisDurumu = resultSet.getString("urunSiparisDurum");

                        ViewingClass.dataStock[i][0] = String.valueOf(urunNo);
                        ViewingClass.dataStock[i][1] = String.valueOf(kategori);
                        ViewingClass.dataStock[i][2] = String.valueOf(isim);
                        ViewingClass.dataStock[i][3] = String.valueOf(fiyat);
                        ViewingClass.dataStock[i][4] = String.valueOf(subeNo);
                        ViewingClass.dataStock[i][5] = String.valueOf(siparisDurumu);
                        i++;
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ViewingClass.exitButtonStock.addActionListener(e -> {
            ViewingClass.frame.setVisible(false);
            ViewingClass.LoginScreen();
        });

        //TODO:AREA WHERE STAFF OPERATIONS ARE CARRIED OUT
        //yeni çalışan eklemek için kullanılan button----------------------------------------------------------------------------------------------------------------------------
        ViewingClass.addButtonStaff.addActionListener(e -> {
            Statement statement;
            try {
                String getMaxCalisanId = "SELECT MAX(calisanId) FROM calisanlar";
                statement = connectionDB.connection().createStatement();
                ResultSet resultSet = statement.executeQuery(getMaxCalisanId);

                if (resultSet.next()){
                    indexOfCalisanId = resultSet.getInt(1) + 1;
                }

                try {
                    String calisanPozison = ViewingClass.calisanPozisyonTextField.getText();
                    String calisanAd = ViewingClass.calisanAdTextField.getText();
                    String calisanSoyad = ViewingClass.calisanSoyAdTextField.getText();
                    int subeNo = Integer.parseInt(ViewingClass.calisanSubeNoTextField.getText());
                    String calisanSifre = ViewingClass.calisanSifreTextField.getText();

                    String sqlQuery = "CALL calisanEkle(?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                    preparedStatement.setInt(1, indexOfCalisanId);
                    preparedStatement.setString(2,calisanPozison);
                    preparedStatement.setString(3,calisanAd);
                    preparedStatement.setString(4,calisanSoyad);
                    preparedStatement.setInt(5,subeNo);
                    preparedStatement.setString(6,calisanSifre);

                    preparedStatement.execute();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //çalışan silmek için kullanılan button----------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.deleteButtonStaff.addActionListener(e -> {
            try {
                int calisanId = Integer.parseInt(ViewingClass.calisanIdTextField.getText());
                String sqlQuery = "DELETE FROM calisanlar WHERE calisanId="+calisanId;

                PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //çalışan verisini güncellemek için kullanılan buton---------------------------------------------------------------------------------------------------------------------
        ViewingClass.updateButtonStaff.addActionListener(e -> {
            try {
                String calisanPozisyon = ViewingClass.calisanPozisyonTextField.getText();
                String calisanAd = ViewingClass.calisanAdTextField.getText();
                String calisanSoyad = ViewingClass.calisanSoyAdTextField.getText();
                int subeNo = Integer.parseInt(ViewingClass.calisanSubeNoTextField.getText());
                int sifre = Integer.parseInt(ViewingClass.calisanSifreTextField.getText());
                int calisanId = Integer.parseInt(ViewingClass.calisanIdTextField.getText());

                Statement statement = connectionDB.connection().createStatement();
                String query = "UPDATE calisanlar SET  calisanPozisyon='" +
                        calisanPozisyon + "' ,calisanAd='" +calisanAd+ "' ,calisanSoyad='" +calisanSoyad+ "' ,subeNo=" +subeNo+ " ,sifre='" +sifre+
                        "' WHERE calisanId=" +calisanId+";";

                statement.execute(query);
                statement.executeUpdate(query);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //Tablodan seçilen ürünü textField'lara doldurmak için kullanılan button-------------------------------------------------------------------------------------------------
        ViewingClass.staffTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = ViewingClass.staffTable.getSelectedRow();
                String setToTextField;
                String[] arrayForData = new String[ViewingClass.staffTable.getColumnCount()];
                for (int i = 0; i < ViewingClass.staffTable.getColumnCount(); i++){
                    setToTextField = (String) ViewingClass.staffTable.getValueAt(selectedRow, i);
                    arrayForData[i] = setToTextField;
                }
                ViewingClass.calisanIdTextField.setText(arrayForData[0]);
                ViewingClass.calisanPozisyonTextField.setText(arrayForData[1]);
                ViewingClass.calisanAdTextField.setText(arrayForData[2]);
                ViewingClass.calisanSoyAdTextField.setText(arrayForData[3]);
                ViewingClass.calisanSubeNoTextField.setText(arrayForData[4]);
                ViewingClass.calisanSifreTextField.setText(arrayForData[5]);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        //Tabloyu yenilemek için kullanılan button-------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.refreshButtonStaff.addActionListener(e -> {
            String getStaffFromDb = "SELECT * FROM calisanlar ORDER BY calisanid";
            try {
                Statement statement = connectionDB.connection().createStatement();
                ResultSet resultSet = statement.executeQuery(getStaffFromDb);
                int i = 0;
                while (resultSet.next()){
                    int calisanId = resultSet.getInt("calisanId");
                    String calisanPozisyon = resultSet.getString("calisanPozisyon");
                    String calisanAd = resultSet.getString("calisanAd");
                    String calisanSoyad = resultSet.getString("calisanSoyad");
                    int calisanSubeNo = resultSet.getInt("subeNo");
                    String calisanSifre = resultSet.getString("sifre");

                    ViewingClass.dataStaff[i][0] = String.valueOf(calisanId);
                    ViewingClass.dataStaff[i][1] = String.valueOf(calisanPozisyon);
                    ViewingClass.dataStaff[i][2] = String.valueOf(calisanAd);
                    ViewingClass.dataStaff[i][3] = String.valueOf(calisanSoyad);
                    ViewingClass.dataStaff[i][4] = String.valueOf(calisanSubeNo);
                    ViewingClass.dataStaff[i][5] = String.valueOf(calisanSifre);
                    i++;
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        ViewingClass.exitButtonStaff.addActionListener(e -> {
            ViewingClass.frame.setVisible(false);
            ViewingClass.LoginScreen();
        });
    }
    public void StockPanScreen(){
        //TODO:AREA WHERE STOCK OPERATIONS ARE CARRIED OUT
        //yeni stok eklemek için kullanılan button-------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.addButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Statement statement;
                try {
                    String getMaxUrunId = "SELECT MAX(urunId) FROM urunler";
                    statement = connectionDB.connection().createStatement();
                    ResultSet resultSet = statement.executeQuery(getMaxUrunId);

                    if (resultSet.next()){
                        indexOfUrunId = resultSet.getInt(1) + 1;
                    }

                    try {
                        String urunAd = ViewingClass.isimTextField.getText();
                        String urunKategori = ViewingClass.kategoriTextField.getText();
                        int subeNo = Integer.parseInt(ViewingClass.subeNoTextField.getText());
                        int urunFiyat = Integer.parseInt(ViewingClass.fiyatTextField.getText());
                        String urunSiparisDurum = ViewingClass.siparisDurumuTextField.getText();

                        String sqlQuery = "CALL stokEkle(?,?,?,?,?,?)";
                        PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                        preparedStatement.setInt(1, indexOfUrunId);
                        preparedStatement.setString(2,urunAd);
                        preparedStatement.setString(3,urunKategori);
                        preparedStatement.setInt(4,subeNo);
                        preparedStatement.setInt(5,urunFiyat);
                        preparedStatement.setString(6,urunSiparisDurum);

                        preparedStatement.execute();

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //stoktan veri silmek için kullanılan button-----------------------------------------------------------------------------------------------------------------------------
        ViewingClass.deleteButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int urunId = Integer.parseInt(ViewingClass.urunNoTextField.getText());
                    String sqlQuery = "DELETE FROM urunler WHERE urunId="+urunId;

                    PreparedStatement preparedStatement = connectionDB.connection().prepareStatement(sqlQuery);
                    preparedStatement.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //stoktaki veriyi güncellemek için kullanılan buton----------------------------------------------------------------------------------------------------------------------
        ViewingClass.updateButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String kategori = ViewingClass.kategoriTextField.getText();
                    String isim = ViewingClass.isimTextField.getText();
                    int fiyat = Integer.parseInt(ViewingClass.fiyatTextField.getText());
                    int subeNo = Integer.parseInt(ViewingClass.subeNoTextField.getText());
                    String siparisDurumu = ViewingClass.siparisDurumuTextField.getText();
                    int urunNo = Integer.parseInt(ViewingClass.urunNoTextField.getText());

                    Statement statement = connectionDB.connection().createStatement();
                    String query = "UPDATE urunler SET  urunKategori='" +
                            kategori + "' ,urunAd='" +isim+ "' ,urunFiyat=" +fiyat+ " ,subeNo=" +subeNo+ " ,urunSiparisDurum='" +siparisDurumu+
                            "' WHERE urunId=" +urunNo+";";

                    statement.execute(query);
                    statement.executeUpdate(query);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //Tablodan seçilen ürünü textField'lara doldurmak için kullanılan button-------------------------------------------------------------------------------------------------
        ViewingClass.stockTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = ViewingClass.stockTable.getSelectedRow();
                String setToTextField;
                String[] arrayForData = new String[ViewingClass.stockTable.getColumnCount()];
                for (int i = 0; i < ViewingClass.stockTable.getColumnCount(); i++){
                    setToTextField = (String) ViewingClass.stockTable.getValueAt(selectedRow, i);
                    arrayForData[i] = setToTextField;
                }
                ViewingClass.urunNoTextField.setText(arrayForData[0]);
                ViewingClass.kategoriTextField.setText(arrayForData[1]);
                ViewingClass.isimTextField.setText(arrayForData[2]);
                ViewingClass.fiyatTextField.setText(arrayForData[3]);
                ViewingClass.subeNoTextField.setText(arrayForData[4]);
                ViewingClass.siparisDurumuTextField.setText(arrayForData[5]);
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //Tabloyu yenilemek için kullanılan button-------------------------------------------------------------------------------------------------------------------------------
        ViewingClass.refreshButtonStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getStockFromDb = "SELECT * FROM urunler order by urunid";
                try {
                    Statement statement = connectionDB.connection().createStatement();
                    ResultSet resultSet = statement.executeQuery(getStockFromDb);
                    int i = 0;
                    while (resultSet.next()){
                        int urunNo = resultSet.getInt("urunId");
                        String kategori = resultSet.getString("urunKategori");
                        String isim = resultSet.getString("urunAd");
                        int fiyat = resultSet.getInt("urunFiyat");
                        int subeNo = resultSet.getInt("subeNo");
                        String siparisDurumu = resultSet.getString("urunSiparisDurum");

                        ViewingClass.dataStock[i][0] = String.valueOf(urunNo);
                        ViewingClass.dataStock[i][1] = String.valueOf(kategori);
                        ViewingClass.dataStock[i][2] = String.valueOf(isim);
                        ViewingClass.dataStock[i][3] = String.valueOf(fiyat);
                        ViewingClass.dataStock[i][4] = String.valueOf(subeNo);
                        ViewingClass.dataStock[i][5] = String.valueOf(siparisDurumu);
                        i++;
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ViewingClass.exitButton.addActionListener(e -> {
            ViewingClass.frame.setVisible(false);
            ViewingClass.LoginScreen();
        });
    }

    public void ChangePasswordUILogic(){
        ViewingClass.backButton.addActionListener(e -> {
            ViewingClass.frame.setVisible(false);
            ViewingClass.LoginScreen();
        });
        ViewingClass.changePasswordButton2.addActionListener(e -> {
            boolean flag = false;
            int getId = Integer.parseInt(ViewingClass.getIdForChangePass.getText());

            String sqlQueryForId = "SELECT calisanId FROM calisanlar WHERE calisanId="+getId;

            Statement statement = null;
            try {
                statement = connectionDB.connection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQueryForId);
                int calisanIdFromDb = 0;
                if (resultSet.next()) {
                    calisanIdFromDb = resultSet.getInt(1);
                }
                if (Objects.equals(ViewingClass.newPasswordTextField.getText(), ViewingClass.newPasswordAgainTextField.getText()) && calisanIdFromDb == getId) {
                    String sqlQuery ="UPDATE calisanlar SET sifre='"+ViewingClass.newPasswordAgainTextField.getText()+"' WHERE calisanId="+calisanIdFromDb;
                    statement.executeUpdate(sqlQuery);
                } else {
                    JOptionPane.showMessageDialog(ViewingClass.frame, "Hatalı giriş");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }





        });
    }
}
