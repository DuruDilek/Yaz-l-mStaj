import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StockManagementSystem extends JFrame {

    // ListModel, stokta bulunan ilaçları JList'te göstermek için kullanılır
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> stockList = new JList<>(listModel);
    private final JTextField searchField = new JTextField(15); // Arama için metin alanı
    private final JComboBox<String> filterComboBox = new JComboBox<>(new String[]{"Hepsi", "Stokta var", "Stokta yok"}); // Stok filtresi
    private final JComboBox<String> typeFilterComboBox = new JComboBox<>(new String[]{"Hepsi", "Antibiyotik", "Ağrı Kesici", "Vitamin"}); // İlaç tipi için yeni comboBox
    private final User user;
    private final String shift;

    // Kurucu metod sınıf ismiyle aynı
    public StockManagementSystem(User user, String shift) {
        this.user = user;
        this.shift = shift;
        setTitle("Stok Takip Uygulaması - " + user.getRole() + " - " + shift);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Pencere ekranın ortasında gösterilir

        // Ana panel ve layout ayarları
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(stockList), BorderLayout.CENTER);

        // Üst panel: arama ve filtreler
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Ara:"));
        topPanel.add(searchField);
        topPanel.add(new JLabel("Filtrele:"));
        topPanel.add(filterComboBox);
        topPanel.add(new JLabel("Tip:")); // İlaç tipi etiketi
        topPanel.add(typeFilterComboBox);  // İlaç tipi combobox'ı

        JButton searchButton = new JButton("Ara"); // Ara butonu
        topPanel.add(searchButton);

        panel.add(topPanel, BorderLayout.NORTH);

        // Alt panel: işlemler için butonlar
        JButton addButton = new JButton("İlaç Ekle");
        JButton deleteButton = new JButton("İlaç Sil");
        JButton updateButton = new JButton("Stok Güncelle");
        JButton logoutButton = new JButton("Çıkış Yap");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(logoutButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        // İlaç listesinin sadece tekli seçim yapmasını sağla
        stockList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Kullanıcı rolü "Yönetici" değilse, bazı butonları devre dışı bırak
        if (!user.getRole().equals("Yönetici")) {
            addButton.setEnabled(false);
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
        }

        loadStockList(); // Stok listesini yükle

        // Butonlara eylemler eklenir
        addButton.addActionListener(e -> addMedicine());
        deleteButton.addActionListener(e -> deleteMedicine());
        updateButton.addActionListener(e -> updateStock());
        logoutButton.addActionListener(e -> {
            dispose(); // Bu pencereyi kapat
            SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true)); // Giriş ekranını tekrar aç
        });

        // Arama ve filtreleme eylemleri
        searchButton.addActionListener(e -> applySearchAndFilter());
        searchField.addActionListener(e -> applySearchAndFilter());
        filterComboBox.addActionListener(e -> applySearchAndFilter());
        typeFilterComboBox.addActionListener(e -> applySearchAndFilter()); // İlaç tipi filtresi eklendi
    }

    // Stok listesini yükler
    private void loadStockList() {
        listModel.clear();
        ArrayList<Medicine> stock = DataStorage.getStock(shift);
        for (Medicine medicine : stock) {
            listModel.addElement(medicine.toString());
        }
    }

    // Yeni ilaç ekleme işlemi
    private void addMedicine() {
        String name = JOptionPane.showInputDialog(this, "İlacın adını girin:");
        String[] types = {"Antibiyotik", "Ağrı Kesici", "Vitamin"};  // İlaç tipi seçenekleri
        String type = (String) JOptionPane.showInputDialog(this, "İlacın tipini seçin:", "İlaç Tipi", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);

        // Kullanıcı geçerli bir ad ve tip girdiyse
        if (name != null && !name.trim().isEmpty() && type != null) {
            Medicine medicine = new Medicine(name, 0, type);  // İlaç tipiyle birlikte ilaç oluştur
            DataStorage.addMedicine(shift, medicine);  // Vardiyaya ilaç ekle
            listModel.addElement(medicine.toString());
        } else {
            JOptionPane.showMessageDialog(this, "İlaç adı veya tipi boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    // İlaç silme işlemi
    private void deleteMedicine() {
        int selectedIndex = stockList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Seçili ilacı silmek istediğinize emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DataStorage.removeMedicine(shift, selectedIndex);
                listModel.remove(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Silmek için bir ilaç seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Stok miktarını güncelleme işlemi
    private void updateStock() {
        int selectedIndex = stockList.getSelectedIndex();
        if (selectedIndex != -1) {
            Medicine selectedMedicine = DataStorage.getStock(shift).get(selectedIndex);
            String quantityStr = JOptionPane.showInputDialog(this, "Yeni stok miktarını girin:", String.valueOf(selectedMedicine.getQuantity()));
            if (quantityStr != null) {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity >= 0) {
                        DataStorage.updateMedicine(shift, selectedIndex, quantity);
                        listModel.set(selectedIndex, selectedMedicine.toString());
                    } else {
                        JOptionPane.showMessageDialog(this, "Stok miktarı negatif olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Geçersiz stok miktarı.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Güncellemek için bir ilaç seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Arama ve filtreleme işlemleri
    private void applySearchAndFilter() {
        String searchText = searchField.getText().toLowerCase();
        String filter = (String) filterComboBox.getSelectedItem();
        String typeFilter = (String) typeFilterComboBox.getSelectedItem(); // Tip filtresi eklendi

        ArrayList<Medicine> stock = DataStorage.getStock(shift);
        listModel.clear();

        for (Medicine medicine : stock) {
            boolean matchesSearch = medicine.getName().toLowerCase().contains(searchText);
            boolean matchesFilter = filter.equals("Hepsi") ||
                    (filter.equals("Stokta var") && medicine.getQuantity() > 0) ||
                    (filter.equals("Stokta yok") && medicine.getQuantity() == 0);
            boolean matchesType = typeFilter.equals("Hepsi") || medicine.getType().equals(typeFilter); // Tip filtresi kontrolü

            // Tüm koşullar sağlandığında ilaç listeye eklenir
            if (matchesSearch && matchesFilter && matchesType) {
                listModel.addElement(medicine.toString());
            }
        }
    }
}
