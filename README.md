# API Attendance App
Proyek sederhana yang dikembangkan untuk menangani pengecekan kehadiran serta aktvitias staff. 

# Fitur
- Pengelolaan Pengguna seperti Registrasi, Login, Logout, Change Password dan Delete User
- Pencatatan kehadiran staff mulai dari waktu check-in dan waktu checkout serta menentukan status kehadiran staff
- Pencatatan laporan aktivitas staff

# Teknologi
- Java 17++
- Spring Boot 3.3.2
- PostgreSQL 
- Apache Maven

# Setup
1. Clone repository
git clone https://github.com/Nielsen67/attendance-shrimpy

2. Konfigurasi Database
Pastikan telah membuat database dan atur konfirgurasi melalui file application.properties. Contoh : 
`spring.datasource.url=jdbc:postgresql://localhost:5432/attendance_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update`

3. Instalasi Dependencies
`mvn clean install`

4. Jalankan Aplikasi

# Dokumentasi API
Dokumentasi Request Body, Response dan Endpoint dapat diakses melalui https://documenter.getpostman.com/view/37120004/2sB2j3DCUD
