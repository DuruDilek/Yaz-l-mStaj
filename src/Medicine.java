public class Medicine {
    // İlaç adını saklayan özel (private) değişken
    private String name;
    // İlaç miktarını saklayan özel (private) değişken
    private int quantity;
    // İlaç tipini saklayan özel (private) değişken
    private String type;

    // Medicine sınıfı için kurucu (constructor) metod oluşturdum
    // Ad, miktar ve tip parametre olarak alınarak ilgili değişkenlere atama yapmayı sağlar
    public Medicine(String name, int quantity, String type) {
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    // İlaç adını döndüren getter metod
    public String getName() {
        return name;
    }

    // İlaç miktarını döndüren getter metod
    public int getQuantity() {
        return quantity;
    }

    // İlaç miktarını güncelleyen setter metod
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // İlaç tipini döndüren getter metod
    public String getType() {
        return type;
    }

    // Nesnenin metin olarak gösterilmesi için toString metodunu geçersiz kıldım
    @Override
    public String toString() {
        return name + " - Stok: " + quantity + " - Tip: " + type; // İlaç tipini toString'e ekledik
    }
}
