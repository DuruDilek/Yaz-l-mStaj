💊 Java ile Vardiya Bazlı İlaç Stok Yönetim Sistemi
(Software Internship Project – Pharmaceutical Factory)

Bu proje, ilaç fabrikasında yaptığım yazılım stajı kapsamında geliştirilmiş,
vardiya bazlı stok yönetimini sağlayan Java Swing tabanlı masaüstü uygulamasıdır.

Gerçek bir fabrika senaryosu baz alınarak;
rol bazlı yetkilendirme, vardiya ayrımı, stok filtreleme ve kullanıcı dostu GUI özellikleriyle tasarlanmıştır.


🎯 Projenin Amacı

İlaç stoklarının vardiya bazlı yönetilmesi

Yetkilendirme (Yönetici / Çalışan) ile kontrollü erişim

Stok arama, filtreleme ve ilaç tipi bazlı listeleme

Kullanıcı dostu grafik arayüz (GUI)


🛠 Kullanılan Teknolojiler

Java

Java Swing (GUI)

OOP (Nesne Yönelimli Programlama)

Collections API (HashMap, ArrayList)

Katmanlı Mimari


👤 Kullanıcı Rolleri
🔑 Yönetici

İlaç ekleme

İlaç silme

Stok miktarı güncelleme

Arama ve filtreleme

👷 Çalışan

Stok görüntüleme

Arama ve filtreleme

❌ Veri üzerinde değişiklik yapamaz


🕒 Vardiya Sistemi

Uygulama 3 vardiya üzerinden çalışmaktadır:

Vardiya 1

Vardiya 2

Vardiya 3

Her vardiya için ayrı bir ilaç stok listesi tutulur.


🔍 Özellikler

🔐 Kullanıcı giriş ekranı (Login)

🏭 Vardiya bazlı stok yönetimi

🔎 İlaç adına göre arama

📦 Stok durumu filtresi

Stokta var

Stokta yok

💊 İlaç tipi filtresi

Antibiyotik

Ağrı Kesici

Vitamin

🧾 Gerçek zamanlı stok güncelleme

🚪 Güvenli çıkış (Logout)


🧩 Proje Yapısı
├── Main.java
├── LoginScreen.java
├── StockManagementSystem.java
├── DataStorage.java
├── Medicine.java
└── User.java



📁 Sınıfların Görevleri

Main: Uygulama başlangıç noktası

LoginScreen: Kullanıcı giriş ve vardiya seçimi

StockManagementSystem: Ana stok yönetim ekranı

DataStorage: Vardiya bazlı stok verilerinin tutulması

Medicine: İlaç modeli (ad, miktar, tip)

User: Kullanıcı bilgileri ve rol yönetimi



▶️ Çalıştırma

Projeyi klonlayın:

git clone https://github.com/DuruDilek/Yaz-l-mStaj.git


Java IDE (IntelliJ IDEA / Eclipse) ile açın

Main.java dosyasını çalıştırın

🔐 Varsayılan Kullanıcılar
Kullanıcı	  Şifre	    Rol
admin	      admin123	Yönetici
user	      user123	  Çalışan
