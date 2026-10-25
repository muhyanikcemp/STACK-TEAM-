# KİŞİSEL BÜTÇE VE HARCAMA TAKİBİ UYGULAMASI
Bu proje, Veri Yapıları dersi kapsamında STACK-TEAM ekibi tarafından geliştirilen, gelir ve harcamaları kategorilere ayırarak aylık bütçeyi takip eden bir uygulamadır. Kullanıcıların toplam gelirini, giderini ve kategori bazındaki bütçe kullanımını detaylı bir şekilde görüntülenmesini hedefler.

**GELİŞTİRİCİ EKİP VE GÖREV DAĞILIMI**:

**Backend Ekibi** (Gamze ve Muhammed): Uygulamanın temel veri yapılarının kurulması, hesaplama algoritmaları, veri tabanı/ dosya işlemleri ve arka plandaki tüm mantıksal mimarinin kodlanmasından sorumlular.

**Frontend Ekibi** (İnci ve Süha): Kullanıcı deneyiminin oluşturulması, arayüz tasarımlarının yapılması, bütçe grafiklerinin ve menülerin ekrana yansıtılması gibi projenin görsel ve etkileşimli tüm adımlarını yönetiyorlar.

**UYGULAMANIN TEMEL ÖZELLİKLERİ**:

-Kaydetme: Kategori, aylık bütçe, gelir ve harcama kaydı oluşturma.

-Silme: Hatalı gelir veya harcamayı silme; işlem içermeyen kategoriyi kaldırma.

-Güncelleme: İşlem tutarını, tarihini, kategorisini, açıklamasını ve aylık bütçeyi değiştirme.

-Sıralama: İşlemleri tarihe veya tutara göre; kategorileri toplam harcamaya göre sıralama.

-Sorgulama: Kategori, işlem türü, tarih aralığı ve bütçesini aşan kategorilerle arama. 

**İŞLEYİŞ KURALLARI VE KAPSAM**:

Sisteme yeni bir veri eklendiğinde, silindiğinde veya güncellendiğinde tüm bütçe toplamları anında yeniden hesaplanır. Veri tutarlılığını sağlamak amacıyla tutarlar negatif olamaz; gelir ve gider ayrı işlem türleri olarak değerlendirilir. Ayrıca, herhangi bir kategori bütçesi aşılırsa arayüz üzerinden kullanıcıya anında uyarı gösterilir.
