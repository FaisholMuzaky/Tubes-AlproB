package Controller;

import Model.modelPengguna;

import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Pengguna {
    private String nama;
    private String email;
    private String alamat;
    private String username;
    private String password;
    private String subscription;
    private int IdPengguna;
    private modelPengguna p;

    public Pengguna() {
        this.p = new modelPengguna();
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

    public String getSubscription() {
        return this.subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public int getIdPengguna() {
        return this.IdPengguna;
    }

    public void setIdPengguna(int IdPengguna) {
        this.IdPengguna = IdPengguna;
    }

    public int login(String email, String password) {
        p.login(email, password, this);
        int idPengguna = getIdPengguna();
        return idPengguna;
    }

    public void registrasi(String nama, String email, String alamat, String password) {
        if (isValidPassword(password) && isValidEmail(email)) {
            password = hash(password);
            if (subscription()) {
                p.insertDataPengguna(nama, email, alamat, password, "plus");
            } else {
                p.insertDataPengguna(nama, email, alamat, password, "easy");
            }
        }
    }

    public void empty() {
        this.IdPengguna = 0;
        this.nama = "";
        this.alamat = "";
        this.subscription = "";
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
            boolean status = p.searchEmail(email).next();
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
            ResultSet data = p.searchByID(id);
            if (data != null) {
                while (data.next()) {
                    System.out.println("Nama\t\t: " + data.getString("nama"));
                    System.out.println("Email\t\t: " + data.getString("email"));
                    System.out.println("Alamat\t\t: " + data.getString("Alamat"));
                    System.out.println("Subscription\t: " + data.getString("subscription").toUpperCase());
                }
            } else {
                System.out.println("Data tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editAccount(int id, String nama, String alamat) {
        int status = p.updateDataPengguna(id, nama, alamat);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
            setNama(nama);
            setAlamat(alamat);
        } else {
            System.out.println("Update Data Gagal");
        }
    }

    public boolean subscription() {
        Scanner input = new Scanner(System.in);
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

    public boolean isAdmin(int idPengguna) {
        boolean status = false;
        try {
            ResultSet data = p.rolePengguna(idPengguna);
            if (data.next() && data.getString("role").equals("admin")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

}
