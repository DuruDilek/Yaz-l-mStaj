import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    // shiftStock isimli bir HashMap oluşturulur.HashMap anahtar değer şeklinde kullanılır. Anahtarlar vardiya adlarını temsil ederken (fabrika 3 vardiya olarak çalışmakta,
    // değerler (ArrayList<Medicine>) bu vardiyaya ait ilaç stok listesini temsil eder.
    private static Map<String, ArrayList<Medicine>> shiftStock = new HashMap<>();

    // Statik bir blok ile shiftStock'a üç adet vardiya tanımlanyıp  her biri için boş bir ArrayList oluşturulur.
    static {
        shiftStock.put("Vardiya 1", new ArrayList<>());
        shiftStock.put("Vardiya 2", new ArrayList<>());
        shiftStock.put("Vardiya 3", new ArrayList<>());
    }

    // Belirtilen vardiyaya (shift) ait ilaç stok listesini döndüren bir metod getstock metodu.
    public static ArrayList<Medicine> getStock(String shift) {
        return shiftStock.get(shift);
    }

    // Belirtilen vardiyaya (shift) yeni bir ilaç (Medicine) ekleyen metod addmedicine.
    public static void addMedicine(String shift, Medicine medicine) {
        shiftStock.get(shift).add(medicine);
    }

    // Belirtilen vardiyada (shift) belirtilen indeksteki (index) ilacı kaldıran  removemedicine metodu.
    public static void removeMedicine(String shift, int index) {
        shiftStock.get(shift).remove(index);
    }

    // Belirtilen vardiyada (shift) belirtilen indeksteki (index) ilacın miktarını  güncelleyen metod olarak updatemedicine kullanıldı.
    public static void updateMedicine(String shift, int index, int quantity) {
        // İlgili vardiyadaki ilaç alınır.
        Medicine medicine = shiftStock.get(shift).get(index);
        // Eğer ilaç mevcutsa, miktarı güncellenir.
        if (medicine != null) {
            medicine.setQuantity(quantity);
        }
    }
}
