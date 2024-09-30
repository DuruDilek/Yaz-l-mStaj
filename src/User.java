public class User {
    // Kullanıcı adı, parola ve rol bilgilerini saklamak için üç özel (private) değişken tanımlanıdım.
    private String username;
    private String password;
    private String role;

    // Kullanıcı sınıfı için bir kurucu (constructor) metod kullandım.
    // Bu metod, kullanıcı adı, parola ve rol bilgilerini alarak ilgili değişkenlere atama yapar.
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Kullanıcı adını döndüren getter metodu.
    public String getUsername() {
        return username;
    }

    // Parolayı döndüren getter metodu.
    public String getPassword() {
        return password;
    }

    // Kullanıcı rolünü döndüren getter metodu.
    public String getRole() {
        return role;
    }
}
