import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends JFrame {

    // Kullanıcı bilgilerini saklamak için bir HashMap tanımlanıdım.
    private final Map<String, User> users = new HashMap<>();
    // Vardiya seçimi için JComboBox kullandım.
    private final JComboBox<String> shiftComboBox;

    // LoginScreen sınıfı için kurucu (constructor) metodu oluşturdum
    public LoginScreen() {

        setTitle("Kullanıcı Girişi");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pencere ekranın ortasında gösterdim.

        // Varsayılan kullanıcılar ekledim.Admin sistemde değişiklik yapan kişidir.User değişiklik yapamaz
        users.put("admin", new User("admin", "admin123", "Yönetici"));
        users.put("user", new User("user", "user123", "Çalışan"));

        // Giriş ekranı arayüzü oluşturma
        JPanel panel = new JPanel(new GridLayout(4, 2)); // 4x2'lik bir grid düzeni oluşturulur.
        JLabel userLabel = new JLabel("Kullanıcı Adı:");
        JTextField userField = new JTextField(); // Kullanıcı adı için metin alanı
        JLabel passLabel = new JLabel("Şifre:");
        JPasswordField passField = new JPasswordField(); // Şifre için parola alanı
        JLabel shiftLabel = new JLabel("Vardiya Seçimi:");
        shiftComboBox = new JComboBox<>(new String[]{"Vardiya 1", "Vardiya 2", "Vardiya 3"}); // Vardiya seçimi için ComboBox
        JButton loginButton = new JButton("Giriş"); // Giriş butonu

        // Panel içerisine bileşenler ekledim
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(shiftLabel);
        panel.add(shiftComboBox);
        panel.add(new JLabel()); // Düzeni korumak için boş bir etiket ekledim
        panel.add(loginButton);

        // Paneli ana pencereye ekle
        add(panel);

        // Giriş butonuna tıklama olayı eklenir
        loginButton.addActionListener(e -> {
            String username = userField.getText(); // Kullanıcı adını al
            String password = new String(passField.getPassword()); // Şifreyi al
            String selectedShift = (String) shiftComboBox.getSelectedItem(); // Seçili vardiyayı al

            // Kullanıcıyı HashMap'ten al
            User user = users.get(username);
            // Kullanıcı adı ve şifre kontrolü
            if (user != null && user.getPassword().equals(password)) {
                // Kullanıcı rolüne göre ana ekranı aç
                StockManagementSystem system = new StockManagementSystem(user, selectedShift);
                system.setVisible(true); // Ana ekranı görünür yap
                dispose(); // Giriş ekranını kapat
            } else {
                // Hatalı giriş durumunda hata mesajı göster
                JOptionPane.showMessageDialog(this, "Geçersiz kullanıcı adı veya şifre.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Programın başlangıç noktası
    public static void main(String[] args) {
        // LoginScreen'i oluşturup görünür hale getir
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}
