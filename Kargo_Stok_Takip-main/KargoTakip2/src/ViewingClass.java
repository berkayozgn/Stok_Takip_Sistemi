import javax.swing.*;
import java.awt.*;

public class ViewingClass {
    static Color backGroundColor = Color.CYAN;
    public static LogicClass logicClass = new LogicClass();
    static JTextField idTextField;
    static JPasswordField passwordTextField;
    static JPasswordField oldPasswordTextField;
    static JPasswordField newPasswordTextField;
    static JPasswordField newPasswordAgainTextField;
    static JButton loginButton;
    static JButton backButton;
    static JButton changePasswordButton2;
    static JButton changePasswordButton;
    static JFrame frame;
    static JButton addButtonStock;
    static JButton deleteButtonStock;
    static JButton updateButtonStock;
    static String[][]dataStock = new String[50][6];
    static String[] tittleStock = {"urunNo","kategori","isim","fiyat","subeNo","siparisDurumu"};
    static String[][] dataStaff =new String[50][6];
    static String[] tittleStaff = {"ÇalışanId","ÇalışanPozisyon","ÇalışanAd","ÇalışanSoyad","ŞubeNo","Şifre"};
    static JScrollPane staffTableSP;
    static JScrollPane stockTableSP;
    static JButton addButtonStaff;
    static JButton deleteButtonStaff;
    static JButton updateButtonStaff;
    static JButton refreshButtonStaff;
    static JButton refreshButtonStock;
    static JTable stockTable;
    static JTextField urunNoTextField;
    static JTextField kategoriTextField;
    static JTextField isimTextField;
    static JTextField fiyatTextField;
    static JTextField subeNoTextField;
    static JTextField siparisDurumuTextField;
    static JPanel stockTab;
    static JTextField calisanIdTextField;
    static JTextField calisanPozisyonTextField;
    static JTextField calisanAdTextField;
    static JTextField calisanSoyAdTextField;
    static JTextField calisanSubeNoTextField;
    static JTextField calisanSifreTextField;
    static JTable staffTable;
    static JTextField getIdForChangePass;
    static JButton exitButton;
    static JButton exitButtonStaff;
    static JButton exitButtonStock;


    public static void LoginScreen(){
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(400,400);
        panel.setLayout(null);

        panel.setBackground(backGroundColor);
        idTextField = new JTextField();
        idTextField.setSize(200,30);
        idTextField.setLocation(100,50);
        JLabel idLabel = new JLabel("ID :");
        idLabel.setSize(100,30);
        idLabel.setLocation(70,50);


        passwordTextField = new JPasswordField();
        passwordTextField.setSize(200,30);
        passwordTextField.setLocation(100,100);
        JLabel passwordLabel = new JLabel("PASSWORD :");
        passwordLabel.setSize(100,30);
        passwordLabel.setLocation(10,100);

        loginButton = new JButton("Login");
        loginButton.setSize(80,30);
        loginButton.setLocation(100,140);

        changePasswordButton = new JButton("Şifre Değiştir");
        changePasswordButton.setSize(110,30);
        changePasswordButton.setLocation(190,140);

        JLabel girisLabel = new JLabel("GİRİŞ EKRANI");
        girisLabel.setBounds(125,10,200,30);
        girisLabel.setFont(new Font("GİRİŞ EKRANI", Font.PLAIN,20));

        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("C:\\Users\\berka\\OneDrive\\Belgeler\\GitHub\\Kargo_Stok_Takip\\KargoTakip2\\src\\images\\Login.png"));
        imgLabel.setBounds(150,200,100,100);

        panel.add(idTextField);
        panel.add(idLabel);
        panel.add(passwordLabel);
        panel.add(passwordTextField);
        panel.add(loginButton);
        panel.add(changePasswordButton);
        panel.add(imgLabel);
        panel.add(girisLabel);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);



        logicClass.LoginUILogic();
    }
    public static void StaffPanScreen(){
        frame = new JFrame();
        frame.setBackground(backGroundColor);
        frame.setSize(710,760);
        stockTab = new JPanel(new BorderLayout());
        stockTab.setBackground(backGroundColor);
        JPanel stockTab2 = new JPanel(null);
        stockTab2.setBackground(backGroundColor);
        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, stockTab,stockTab2);
        splitPane.setBackground(backGroundColor);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(backGroundColor);
        tabbedPane.setBounds(0,0,695,720);
        tabbedPane.add("Stok Işlemleri",splitPane);

        stockTable = new JTable(dataStock,tittleStock);
        stockTableSP = new JScrollPane(stockTable);

        addButtonStock = new JButton("EKLE");
        addButtonStock.setBounds(10,10,150,30);

        deleteButtonStock = new JButton("SIL");
        deleteButtonStock.setBounds(10,50,150,30);

        updateButtonStock = new JButton("GÜNCELLE");
        updateButtonStock.setBounds(10,90,150,30);

        refreshButtonStock = new JButton("YENİLE");
        refreshButtonStock.setBounds(10,130,150,30);

        urunNoTextField = new JTextField();
        urunNoTextField.setBounds(280,10,200,30);
        urunNoTextField.setEditable(false);
        JLabel urunNoLabel = new JLabel("Ürün No :");
        urunNoLabel.setBounds(180,10,100,30);

        kategoriTextField = new JTextField();
        kategoriTextField.setBounds(280,50,200,30);
        JLabel kategoriLabel = new JLabel("Kategori :");
        kategoriLabel.setBounds(180,50,100,30);

        isimTextField = new JTextField();
        isimTextField.setBounds(280,90,200,30);
        JLabel isimLabel = new JLabel("İsim :");
        isimLabel.setBounds(180,90,100,30);

        fiyatTextField = new JTextField();
        fiyatTextField.setBounds(280,130,200,30);
        JLabel fiyatLabel = new JLabel("Fiyat :");
        fiyatLabel.setBounds(180,130,100,30);

        subeNoTextField = new JTextField();
        subeNoTextField.setBounds(280,170,200,30);
        JLabel subeNoLabel = new JLabel("Şube No :");
        subeNoLabel.setBounds(180,170,100,30);

        siparisDurumuTextField = new JTextField();
        siparisDurumuTextField.setBounds(280,210,200,30);
        JLabel siparisDurumuLabel = new JLabel("Sipariş Durumu :");
        siparisDurumuLabel.setBounds(180,210,100,30);

        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("C:\\Users\\berka\\OneDrive\\Belgeler\\GitHub\\Kargo_Stok_Takip\\KargoTakip2\\src\\images\\personel.png"));
        imgLabel.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel.setBounds(550,10,150,150);

        exitButton = new JButton("ÇIKIŞ YAP");
        exitButton.setBounds(10,170,150,30);

        stockTab2.add(imgLabel);
        stockTab2.add(exitButton);
        stockTab.add(stockTableSP);
        stockTab2.add(urunNoTextField);
        stockTab2.add(urunNoLabel);
        stockTab2.add(kategoriTextField);
        stockTab2.add(kategoriLabel);
        stockTab2.add(isimTextField);
        stockTab2.add(isimLabel);
        stockTab2.add(fiyatTextField);
        stockTab2.add(fiyatLabel);
        stockTab2.add(subeNoTextField);
        stockTab2.add(subeNoLabel);
        stockTab2.add(siparisDurumuTextField);
        stockTab2.add(siparisDurumuLabel);
        stockTab2.add(refreshButtonStock);
        stockTab2.add(addButtonStock);
        stockTab2.add(deleteButtonStock);
        stockTab2.add(updateButtonStock);

        frame.add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        logicClass.StockPanScreen();
    }

    public static void AdminPanScreen(){
        frame = new JFrame();
        //admin girişi doğrulandıktan sonra gösterilecek screen
        frame.setSize(710,760);
        frame.setBackground(backGroundColor);
        stockTab = new JPanel(new BorderLayout());
        JPanel stockTab2 = new JPanel(null);
        stockTab2.setBackground(backGroundColor);
        JPanel staffTab = new JPanel(new BorderLayout());
        staffTab.setBackground(backGroundColor);
        JPanel staffTab2 = new JPanel(null);
        staffTab2.setBackground(backGroundColor);
        JSplitPane splitPane = new JSplitPane(SwingConstants.HORIZONTAL, stockTab,stockTab2);
        JSplitPane splitPane2 = new JSplitPane(SwingConstants.HORIZONTAL,staffTab,staffTab2);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0,0,695,720);
        tabbedPane.add("Stok Işlemleri",splitPane);
        tabbedPane.add("Çalışan Işlemleri",splitPane2);


        //stok işlemleri

        stockTable = new JTable(dataStock,tittleStock);
        stockTableSP = new JScrollPane(stockTable);

        addButtonStock = new JButton("EKLE");
        addButtonStock.setBounds(10,10,150,30);

        deleteButtonStock = new JButton("SIL");
        deleteButtonStock.setBounds(10,50,150,30);

        updateButtonStock = new JButton("GÜNCELLE");
        updateButtonStock.setBounds(10,90,150,30);

        refreshButtonStock = new JButton("YENİLE");
        refreshButtonStock.setBounds(10,130,150,30);


        urunNoTextField = new JTextField();
        urunNoTextField.setBounds(280,10,200,30);
        urunNoTextField.setEditable(false);
        JLabel urunNoLabel = new JLabel("Ürün No :");
        urunNoLabel.setBounds(180,10,100,30);

        kategoriTextField = new JTextField();
        kategoriTextField.setBounds(280,50,200,30);
        JLabel kategoriLabel = new JLabel("Kategori :");
        kategoriLabel.setBounds(180,50,100,30);

        isimTextField = new JTextField();
        isimTextField.setBounds(280,90,200,30);
        JLabel isimLabel = new JLabel("İsim :");
        isimLabel.setBounds(180,90,100,30);

        fiyatTextField = new JTextField();
        fiyatTextField.setBounds(280,130,200,30);
        JLabel fiyatLabel = new JLabel("Fiyat :");
        fiyatLabel.setBounds(180,130,100,30);

        subeNoTextField = new JTextField();
        subeNoTextField.setBounds(280,170,200,30);
        JLabel subeNoLabel = new JLabel("Şube No :");
        subeNoLabel.setBounds(180,170,100,30);

        siparisDurumuTextField = new JTextField();
        siparisDurumuTextField.setBounds(280,210,200,30);
        JLabel siparisDurumuLabel = new JLabel("Sipariş Durumu :");
        siparisDurumuLabel.setBounds(180,210,100,30);

        JLabel imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon("C:\\Users\\berka\\OneDrive\\Belgeler\\GitHub\\Kargo_Stok_Takip\\KargoTakip2\\src\\images\\Admin-icon.png"));
        imgLabel.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel.setBounds(550,10,150,150);

        exitButtonStock = new JButton("ÇIKIŞ YAP");
        exitButtonStock.setBounds(10,170,150,30);

        stockTab2.add(exitButtonStock);
        stockTab2.add(imgLabel);
        stockTab.add(stockTableSP);
        stockTab2.add(urunNoTextField);
        stockTab2.add(urunNoLabel);
        stockTab2.add(kategoriTextField);
        stockTab2.add(kategoriLabel);
        stockTab2.add(isimTextField);
        stockTab2.add(isimLabel);
        stockTab2.add(fiyatTextField);
        stockTab2.add(fiyatLabel);
        stockTab2.add(subeNoTextField);
        stockTab2.add(subeNoLabel);
        stockTab2.add(siparisDurumuTextField);
        stockTab2.add(siparisDurumuLabel);
        stockTab2.add(refreshButtonStock);
        stockTab2.add(addButtonStock);
        stockTab2.add(deleteButtonStock);
        stockTab2.add(updateButtonStock);

        //çalışan işlemleri
        staffTable = new JTable(dataStaff,tittleStaff);
        staffTableSP = new JScrollPane(staffTable);

        addButtonStaff = new JButton("EKLE");
        addButtonStaff.setBounds(10,10,150,30);

        deleteButtonStaff = new JButton("SIL");
        deleteButtonStaff.setBounds(10,50,150,30);

        updateButtonStaff = new JButton("GÜNCELLE");
        updateButtonStaff.setBounds(10,90,150,30);

        refreshButtonStaff = new JButton("YENİLE");
        refreshButtonStaff.setBounds(10,130,150,30);

        calisanIdTextField = new JTextField();
        calisanIdTextField.setBounds(280,10,200,30);
        calisanIdTextField.setEditable(false);
        JLabel calisanIdLabel = new JLabel("ÇalışanID :");
        calisanIdLabel.setBounds(180,10,100,30);

        calisanPozisyonTextField = new JTextField();
        calisanPozisyonTextField.setBounds(280,50,200,30);
        JLabel calisanPozisyonLabel = new JLabel("Pozisyon :");
        calisanPozisyonLabel.setBounds(180,50,100,30);

        calisanAdTextField = new JTextField();
        calisanAdTextField.setBounds(280,90,200,30);
        JLabel calisanIsimLabel = new JLabel("İsim :");
        calisanIsimLabel.setBounds(180,90,100,30);

        calisanSoyAdTextField = new JTextField();
        calisanSoyAdTextField.setBounds(280,130,200,30);
        JLabel calisanSoyAdLabel = new JLabel("Soyisim :");
        calisanSoyAdLabel.setBounds(180,130,100,30);

        calisanSubeNoTextField = new JTextField();
        calisanSubeNoTextField.setBounds(280,170,200,30);
        JLabel calisanSubeNoLabel = new JLabel("Şube No :");
        calisanSubeNoLabel.setBounds(180,170,100,30);

        calisanSifreTextField = new JTextField();
        calisanSifreTextField.setBounds(280,210,200,30);
        JLabel calisanSifreLabel = new JLabel("Şifre :");
        calisanSifreLabel.setBounds(180,210,100,30);

        JLabel imgLabel2 = new JLabel();
        imgLabel2.setIcon(new ImageIcon("C:\\Users\\berka\\OneDrive\\Belgeler\\GitHub\\Kargo_Stok_Takip\\KargoTakip2\\src\\images\\Admin-icon.png"));
        imgLabel2.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel2.setIcon(new ImageIcon("images\\Admin-icon.png"));
        imgLabel2.setBounds(550,10,150,150);

        exitButtonStaff = new JButton("ÇIKIŞ YAP");
        exitButtonStaff.setBounds(10,170,150,30);

        staffTab2.add(exitButtonStaff);
        staffTab.add(staffTableSP);
        staffTab2.add(calisanIdTextField);
        staffTab2.add(imgLabel2);
        staffTab2.add(calisanPozisyonTextField);
        staffTab2.add(calisanAdTextField);
        staffTab2.add(calisanSoyAdTextField);
        staffTab2.add(calisanSubeNoTextField);
        staffTab2.add(calisanSifreTextField);
        staffTab2.add(refreshButtonStaff);
        staffTab2.add(addButtonStaff);
        staffTab2.add(deleteButtonStaff);
        staffTab2.add(updateButtonStaff);
        staffTab2.add(calisanIdLabel);
        staffTab2.add(calisanPozisyonLabel);
        staffTab2.add(calisanIsimLabel);
        staffTab2.add(calisanSoyAdLabel);
        staffTab2.add(calisanSubeNoLabel);
        staffTab2.add(calisanSifreLabel);

//--------------------------------------------------------------------------------------------------------------------------
        frame.add(tabbedPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        logicClass.AdminPanUILogic();
    }
    public static void ChangePasswordScreen(){
        frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(backGroundColor);
        frame.setSize(400,400);
        panel.setLayout(null);

        oldPasswordTextField = new JPasswordField();
        oldPasswordTextField.setSize(200,30);
        oldPasswordTextField.setLocation(100,60);
        JLabel oldPassLabel = new JLabel("ESKI ŞIFRE :");
        oldPassLabel.setSize(100,30);
        oldPassLabel.setLocation(10,60);

        newPasswordTextField = new JPasswordField();
        newPasswordTextField.setSize(200,30);
        newPasswordTextField.setLocation(100,100);
        JLabel newPassLabel = new JLabel("YENI ŞIFRE :");
        newPassLabel.setSize(100,30);
        newPassLabel.setLocation(10,100);

        newPasswordAgainTextField = new JPasswordField();
        newPasswordAgainTextField.setSize(200,30);
        newPasswordAgainTextField.setLocation(100,140);
        JLabel newPassLabel2 = new JLabel("YENI ŞIFRE :");
        newPassLabel2.setSize(100,30);
        newPassLabel2.setLocation(10,140);

        changePasswordButton2 = new JButton("Şifre Değiştir");
        changePasswordButton2.setSize(110,30);
        changePasswordButton2.setLocation(100,180);

        backButton = new JButton("GERİ");
        backButton.setBounds(215,180,85,30);

        getIdForChangePass = new JTextField();
        getIdForChangePass.setBounds(100,20,110,30);
        JLabel idLabel = new JLabel("ID :");
        idLabel.setBounds(10,20,50,30);

        JLabel imgLabel = new JLabel();

        imgLabel.setIcon(new ImageIcon("C:\\Users\\berka\\OneDrive\\Belgeler\\GitHub\\Kargo_Stok_Takip\\KargoTakip2\\src\\images\\sifre degistir.png"));

        imgLabel.setIcon(new ImageIcon("images\\sifre degistir.png"));
        imgLabel.setIcon(new ImageIcon("images\\sifre degistir.png"));
        imgLabel.setBounds(150,220,100,100);

        panel.add(imgLabel);
        panel.add(oldPasswordTextField);
        panel.add(newPasswordAgainTextField);
        panel.add(oldPassLabel);
        panel.add(idLabel);
        panel.add(newPasswordTextField);
        panel.add(newPassLabel);
        panel.add(newPassLabel2);
        panel.add(changePasswordButton2);
        panel.add(backButton);
        panel.add(getIdForChangePass);


        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        logicClass.ChangePasswordUILogic();
    }
}
