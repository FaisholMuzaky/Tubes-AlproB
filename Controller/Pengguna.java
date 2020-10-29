package Controller;

import Model.modelPengguna;
import View.View;

import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.*;
import java.sql.SQLException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pengguna {
    /*
     * Data test
     * 
     * nama = "fadil"; email = "fadil@fadil.com"; alamat = "cikoneng"; username =
     * "fadil"; password = "Fadil15";
     */

    private String nama;
    private String email;
    private String alamat;
    private String username;
    private String password;
    // private boolean isAdmin;

    Scanner input = new Scanner(System.in);

    public Pengguna() {

    }

    public Pengguna(String nama, String email, String alamat, String username, String password, int countKendaraan) {
        this.nama = nama;
        this.email = email;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
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

    public String getEmail() {
        return this.email;
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

    public int login() {
        int id = 0;
        int number = 20;
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

    public void registrasi() {
        int number = 20;
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

        if (!nama.isEmpty() && !email.isEmpty() && !alamat.isEmpty() && !password.isEmpty()) {
            if (isValidPassword(password) && isValidEmail(email)) {
                password = hash(password);
                if (subscription()) {
                    modelPengguna.insertDataPengguna(nama, email, alamat, password, "plus");
                } else {
                    modelPengguna.insertDataPengguna(nama, email, alamat, password, "easy");
                }
                View.pressAnyKey();
            } else if (!isValidEmail(email)) {
                System.out.println("email sudah digunakan");
                View.pressAnyKey();
                View.clrscr();
                registrasi();
            } else if (!isValidPassword(password)) {
                System.out.println("password yang anda masukkan salah");
                View.pressAnyKey();
                View.clrscr();
                registrasi();
            } else {
                System.out.println("email atau password salah");
                View.pressAnyKey();
                View.clrscr();
                registrasi();
            }

        } else {
            System.out.println("inputan tidak boleh kosong");
            View.pressAnyKey();
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
                    System.out.println("Nama   : " + data.getString("nama"));
                    System.out.println("Email  : " + data.getString("email"));
                    System.out.println("Alamat : " + data.getString("Alamat"));
                }
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editAccount(int id) {
        int number = 20;
        String judul = " Edit Profile ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        Scanner sc = new Scanner(System.in);
        System.out.print("Nama   : ");
        String nama = sc.nextLine();
        System.out.print("Alamat : ");
        String alamat = sc.nextLine();
        int status = modelPengguna.updateDataPengguna(id, nama, alamat);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
            View.pressAnyKey();
        } else {
            System.out.println("Update Data Gagal");
            View.pressAnyKey();
        }
        sc.close();
    }

    public boolean subscription() {
        boolean subs = false;
        System.out.println("Apakah anda ingin menggunakan paket PLUS");
        System.out.println("Rp. 12000/bulan dan dikenakan pada setiap awal bulan ?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.print("pilihan : ");
        int pil = input.nextInt();
        if (pil == 1) {
            subs = true;
        }
        return subs;
    }

}
