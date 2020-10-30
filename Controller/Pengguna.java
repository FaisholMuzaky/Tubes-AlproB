package Controller;

import Model.modelPengguna;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.*;
import java.sql.SQLException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pengguna extends Authentikasi {
    private String nama;
    private String email;
    private String alamat;
    private String username;
    private String password;
    private Kendaraan[] kendaraan;
    private int countKendaraan;
    private boolean isAdmin;
    //USULAN TAMBAHAN
    private int idPengguna;
    private String subscription;

    Scanner input = new Scanner(System.in);

    public Pengguna() {

    }

    public Pengguna(String nama, String email, String alamat, String username, String password, int countKendaraan) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.countKendaraan = 0;
        this.kendaraan = new Kendaraan[countKendaraan];
        this.isAdmin = false;
    }

    public Pengguna(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Kendaraan[] getKendaraan() {
        return this.kendaraan;
    }

    public void setKendaraan(Kendaraan[] kendaraan) {
        this.kendaraan = kendaraan;
    }


    public String getEmail() {
        return this.email;
    }


    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public boolean isIsAdmin() {
        return this.isAdmin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getIdPengguna() {
        return this.idPengguna;
    }


    public String getSubscription() {
        return this.subscription;
    }

    @Override
    public int login() {
        int id = 0;
        int number = 10;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Email" + " ".repeat(5) + ": ");
        String email = input.next();
        System.out.print("Password" + " ".repeat(2) + ": ");
        String password = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

        // email = "fadil@fadil.com";
        // password = "Fadil15";
        if (!email.isEmpty() && !password.isEmpty()) {
            password = hash(password);
            try {
                ResultSet data = modelPengguna.login(email, password);
                if (data.next()) {
                    System.out.println("Login Berhasil");
                    id = data.getInt("idPengguna");
                    //USULAN TAMBAHAN
                    this.idPengguna = id;
                    this.nama = data.getString("nama");
                    this.email = data.getString("email");
                    this.alamat = data.getString("alamat");
                    this.subscription = data.getString("subscription");
                } else {
                    System.out.println("email atau password salah");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("email atau password tidak boleh kosong");
        }
        return id;
    }

    @Override
    public void registrasi() {
        int number = 10;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama : ");
        nama = input.next();
        System.out.print("Alamat : ");
        alamat = input.next();
        System.out.print("Email" + " ".repeat(5) + ": ");
        email = input.next();
        System.out.print("Password" + " ".repeat(2) + ": ");
        password = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));
        // nama = "fadil";
        // email = "fadil@fadil.com";
        // alamat = "cikoneng";
        // username = "fadil";
        // password = "Fadil15";
        if (!nama.isEmpty() && !email.isEmpty() && !alamat.isEmpty() && !password.isEmpty()) {
            if (isValidPassword(password) && isValidEmail(email)) {
                password = hash(password);
                modelPengguna.insertDataPengguna(nama, email, alamat, password);
            }
            else if(!isValidEmail(email)){
                System.out.println("email sudah digunakan");
            }
            else if(!isValidPassword(password)){
                System.out.println("password yang anda masukkan salah");
            }
            else {
                System.out.println("email atau password salah");
            }

        } else {
            System.out.println("inputan tidak boleh kosong");
        }

    }

    public boolean isValidEmail(String email) {
        boolean valid = false;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

        Pattern p1 = Pattern.compile(emailPattern);
        Pattern p2 = Pattern.compile(emailPattern2);

        Matcher m1 = p1.matcher(email);
        Matcher m2 = p2.matcher(email);
        try {
            boolean status = modelPengguna.searchEmail(email).next();
            if (m1.matches() && !status) {
                valid = true;
            } else if (m2.matches() && !status) {
                valid = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return valid;

    }

    public boolean isValidPassword(String password) {
        String passPattern = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=\\S+$).{6,}$";
        Pattern p = Pattern.compile(passPattern);
        if (password == null) {
            return false;
        }
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewAccount(int id) {
        try {
            ResultSet data = modelPengguna.searchByID(id);
            if (data != null) {
                while (data.next()) {
                    System.out.println("Username : " + data.getString("username"));
                    System.out.println("Nama : " + data.getString("nama"));
                    System.out.println("Email : " + data.getString("email"));
                    System.out.println("Alamat : " + data.getString("Alamat"));
                }
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public void addKendaraan(Kendaraan kendaraan) {
    // this.kendaraan[countKendaraan] = kendaraan;
    // this.countKendaraan++;
    // }

    public void editAccount(int id) {
        String nama = "Walim";
        String alamat = "Bogor";
        int status = modelPengguna.updateDataPengguna(id, nama, alamat);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");
        }
    }

}
