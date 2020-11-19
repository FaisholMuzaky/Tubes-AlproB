package View;

import Controller.*;

import java.util.Scanner;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class View {
    private int idPengguna = 0;

    Scanner input = new Scanner(System.in);
    private Pengguna user = new Pengguna();
    private Kendaraan kendaraan = new Kendaraan();
    private Garage garage = new Garage();
    private Area area = new Area();
    private History history = new History();

    public static void clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {

        }
    }

    public static void pressAnyKey() {
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void auth() {
        int number = 20;
        String judul = " Sistem Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                login();
                if (idPengguna > 0 && user.isAdmin(idPengguna)) {
                    clrscr();
                    mainAdmin();
                } else if (idPengguna > 0 && !user.isAdmin(idPengguna)) {
                    clrscr();
                    mainPengguna();
                } else {
                    clrscr();
                    auth();
                }
                break;
            case 2:
                registrasi();
                clrscr();
                login();
                pressAnyKey();
                if (idPengguna > 0) {
                    clrscr();
                    mainPengguna();
                } else {
                    clrscr();
                    auth();
                }
                break;
            default:
                break;
        }
    }

    public void login() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Sistem Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Email" + " ".repeat(5) + ": ");
        String email = input.next().toLowerCase();
        System.out.print("Password" + " ".repeat(2) + ": ");
        String password = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (!email.isEmpty() && !password.isEmpty()) {
            password = user.hash(password);
            idPengguna = user.login(email, password);
            if (idPengguna > 0) {
                System.out.println("Login Berhasil");
            } else {
                System.out.println("Email atau Password Salah");
            }
        } else {
            System.out.println("Email atau password tidak boleh kosong");
        }
        pressAnyKey();
    }

    public void registrasi() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Sistem Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama\t\t: ");
        String nama = input.nextLine();
        System.out.print("Alamat\t\t: ");
        String alamat = input.nextLine();
        System.out.print("Email\t\t: ");
        String email = input.nextLine().toLowerCase();
        System.out.print("Password\t: ");
        String password = input.nextLine();
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (!nama.isEmpty() && !email.isEmpty() && !alamat.isEmpty() && !password.isEmpty()) {
            if (user.isValidPassword(password) && user.isValidEmail(email)) {
                user.registrasi(nama, email, alamat, password);
            } else if (!user.isValidEmail(email)) {
                System.out.println("email sudah digunakan");
                pressAnyKey();
                clrscr();
                registrasi();
            } else if (!user.isValidPassword(password)) {
                System.out.println("password yang anda masukkan salah");
                pressAnyKey();
                clrscr();
                registrasi();
            } else {
                System.out.println("email atau password salah");
                pressAnyKey();
                clrscr();
                registrasi();
            }
        } else {
            System.out.println("inputan tidak boleh kosong");
            pressAnyKey();
            registrasi();
        }
        pressAnyKey();
    }

    public void mainPengguna() {
        int number = 20;
        String judul = " Hai, " + user.getNama() + " ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Kendaraan");
        System.out.println("2. Parkir");
        System.out.println("3. History Parkir");
        System.out.println("4. Profil");
        System.out.println("0. Keluar");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                viewKendaraan();
                break;
            case 2:
                menuParkirArea();
                break;
            case 3:
                menuHistoryParkir();
                break;
            case 4:
                viewProfile();
                break;
            default:
                user.empty();
                auth();
                break;
        }
    }

    public void viewKendaraan() {
        int number = 20;
        String judul = " Daftar Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        kendaraan.viewListKendaraan(user.getIdPengguna());
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Hapus Kendaraan");
        System.out.println("0. Kembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        switch (pil) {
            case 1:
                clrscr();
                tambahKendaraan();
                break;
            case 2:
                clrscr();
                hapusKendaraan();
                break;
            default:
                clrscr();
                mainPengguna();
                break;
        }
    }

    public void viewProfile() {
        int number = 20;
        String judul = " Profile ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        user.viewAccount(idPengguna);
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Edit");
        System.out.println("0. Kembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        switch (pil) {
            case 1:
                clrscr();
                editAccount();
                clrscr();
                viewProfile();
                break;
            default:
                clrscr();
                mainPengguna();
                break;
        }
    }

    public void tambahKendaraan() {
        Scanner sc = new Scanner(System.in);
        int number = 20;
        String nomorKendaraan;
        String judul = " Tambah Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("Contoh Format Mobil :  DC 1234 Z");
        System.out.println("Contoh Format Motor :  DC 123 ZR");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Jumlah Kendaraan\t: ");
        int jumKendaraan = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        Kendaraan[] kd = new Kendaraan[jumKendaraan];
        for (int i = 0; i < jumKendaraan; i++) {
            System.out.println("Tipe Kendaraan : ");
            System.out.println("1. Mobil");
            System.out.println("2. Motor");
            System.out.print("Pilihan : ");
            String tipe = sc.nextLine();
            System.out.print("Nomor kendaraan : ");
            nomorKendaraan = sc.nextLine().toUpperCase();
            System.out.println("=".repeat((number * 2) + judul.length()));
            if (tipe.equals("1")) {
                kd[i] = new Kendaraan(nomorKendaraan, "Mobil");
            } else {
                kd[i] = new Kendaraan(nomorKendaraan, "Motor");
            }
        }
        kendaraan.addKendaraan(idPengguna, kd);
        pressAnyKey();
        clrscr();
        viewKendaraan();
    }

    public void hapusKendaraan() {
        int number = 20;
        String judul = " Daftar Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        kendaraan.viewListKendaraan(user.getIdPengguna());
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Kendaraan : ");
        int idKendaraan = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        kendaraan.hapusKendaraan(idKendaraan);
        pressAnyKey();
        clrscr();
        viewKendaraan();
    }

    public void editAccount() {
        Scanner sc = new Scanner(System.in);
        int number = 20;
        String judul = " Edit Profile ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama   : ");
        String nama = sc.nextLine();
        System.out.print("Alamat : ");
        String alamat = sc.nextLine();
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (!nama.isEmpty() && !alamat.isEmpty()) {
            user.editAccount(idPengguna, nama, alamat);
        } else {
            System.out.println("Nama atau alamat tidak boleh kosong");
        }
        pressAnyKey();
    }

    public void mainAdmin() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Sistem Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Area");
        System.out.println("2. Garasi");
        System.out.println("3. Laporan Transaksi");
        System.out.println("4. Keluar");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                mainArea();
                break;
            case 2:
                mainGarage();
                break;
            case 3:
                menuLaporan();
                break;
            case 4:
                user.empty();
                auth();
                break;
            default:
                break;
        }
    }

    public void mainArea() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Area Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Area");
        System.out.println("2. Edit Area");
        System.out.println("3. Hapus Area");
        System.out.println("4. Tambah Garasi");
        System.out.println("5. Lihat Detail Area");
        System.out.println("0. Kembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                tambahArea();
                break;
            case 2:
                editArea();
                break;
            case 3:
                hapusArea();
                break;
            case 4:
                tambahGarage();
                break;
            case 5:
                detailArea();
                break;
            default:
                mainAdmin();
                break;
        }
    }

    public void tambahArea() {
        Scanner input = new Scanner(System.in);
        String namaArea;
        int number = 20;
        String judul = " Tambah Area Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Jumlah Area\t: ");
        int jumArea = input.nextInt();
        Area[] listArea = new Area[jumArea];
        if (jumArea > 0) {
            for (int i = 0; i < listArea.length; i++) {
                System.out.print(i + 1 + ". Nama Area : ");
                namaArea = input.next();
                System.out.println("");
                listArea[i] = new Area(namaArea);
            }
            System.out.println("=".repeat((number * 2) + judul.length()));
            area.addArea(listArea);
        } else {
            System.out.println("Field jumlah area tidak boleh kosong atau 0");

        }
        pressAnyKey();
        clrscr();
        mainArea();
    }

    public void editArea() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Edit Area Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Area : ");
        String idArea = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan Nama Area Baru : ");
        String newNamaArea = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (Integer.parseInt(idArea) > 0 && !newNamaArea.isEmpty()) {
            area.editArea(Integer.parseInt(idArea), newNamaArea);
        } else {
            System.out.println("Field nama area tidak boleh kosong");
        }
        pressAnyKey();
        clrscr();
        mainArea();
    }

    public void hapusArea() {
        int number = 20;
        String judul = " Daftar Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Area : ");
        int idArea = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        area.hapusArea(idArea);
        pressAnyKey();
        clrscr();
        mainGarage();
    }

    public void detailArea() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Detail Area Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Area : ");
        int idArea = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (idArea > 0) {
            area.detailArea(idArea);
            System.out.println("=".repeat((number * 2) + judul.length()));
        } else {
            System.out.println("Inputan field ID Area salah");
            System.out.println("=".repeat((number * 2) + judul.length()));
        }
        pressAnyKey();
        clrscr();
        mainArea();
    }

    public void mainGarage() {
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        garage.listAllGarage();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Garasi");
        System.out.println("2. Edit Garasi");
        System.out.println("3. Hapus Garasi");
        System.out.println("4. Lihat Detail Garasi");
        System.out.println("5. Kembali");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                tambahGarage();
                break;
            case 2:
                editGarage();
                break;
            case 3:
                hapusGarage();
                break;
            case 4:
                detailGarage();
                break;
            case 5:
                mainAdmin();
                break;

            default:
                break;
        }
    }

    public void tambahGarage() {
        int hariOperasi = 0;
        String[] namaHari = new String[hariOperasi];
        int[] jamBuka = new int[hariOperasi];
        int[] jamTutup = new int[hariOperasi];
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Tambah Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Area : ");
        int IdArea = input.nextInt();
        System.out.print("Jumlah Garasi Diinputkan : ");
        int jumGarage = input.nextInt();
        Garage[] garasi = new Garage[jumGarage];
        System.out.println("=".repeat((number * 2) + judul.length()));
        if (IdArea > 0 && jumGarage > 0) {
            for (int i = 0; i < jumGarage; i++) {
                System.out.print(i + 1 + ". Nama Garasi : ");
                String namaGarage = input.next();
                System.out.print(" ".repeat(3) + "Tarif Mobil : Rp. ");
                int tarifMobil = input.nextInt();
                System.out.print(" ".repeat(3) + "Tarif Motor : Rp. ");
                int tarifMotor = input.nextInt();
                System.out.print(" ".repeat(3) + "Jumlah Hari Operasional (Seminggu) : ");
                hariOperasi = input.nextInt();
                jamBuka = new int[hariOperasi];
                jamTutup = new int[hariOperasi];
                namaHari = new String[hariOperasi];
                while (hariOperasi <= 0 || hariOperasi > 7) {
                    System.out.print(" ".repeat(3) + "Jumlah Hari Operasional (Seminggu) : ");
                    hariOperasi = input.nextInt();
                }
                for (int j = 0; j < hariOperasi; j++) {
                    System.out.println("");
                    System.out.print(" ".repeat(3) + "Nama Hari : ");
                    namaHari[j] = input.next();
                    System.out.print(" ".repeat(3) + "Jam Buka (Format Waktu 24 Jam) : ");
                    jamBuka[j] = input.nextInt();
                    while (jamBuka[j] < 0 || jamBuka[j] > 24) {
                        System.out.print(" ".repeat(3) + "Jam Buka (Format Waktu 24 Jam) : ");
                        jamBuka[j] = input.nextInt();
                    }
                    System.out.print(" ".repeat(3) + "Jam Tutup (Format Waktu 24 Jam) : ");
                    jamTutup[j] = input.nextInt();
                    while (jamTutup[j] < 0 || jamTutup[j] > 24) {
                        System.out.print(" ".repeat(3) + "Jam Buka (Format Waktu 24 Jam) : ");
                        jamBuka[j] = input.nextInt();
                    }
                }
                garasi[i] = new Garage(namaGarage, tarifMobil, tarifMotor, hariOperasi, jamBuka, jamTutup, namaHari);
            }
            garage.addGarage(IdArea, garasi);
        } else {
            System.out.println("Maaf, field Nama area atau Jumlah garasi tidak boleh kosong!");
        }
        pressAnyKey();
        clrscr();
        mainGarage();
    }

    public void editGarage() {
        int hariOperasi = 0;
        String[] namaHari = new String[hariOperasi];
        int[] jamBuka = new int[hariOperasi];
        int[] jamTutup = new int[hariOperasi];
        Scanner input = new Scanner(System.in);
        int number = 20;
        String judul = " Edit Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        garage.listAllGarage();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Garasi : ");
        int idGarage = input.nextInt();
        if (idGarage > 0) {
            clrscr();
            System.out.println("=".repeat(number) + judul + "=".repeat(number));
            garage.detailGarageByID(idGarage);
            System.out.println("=".repeat((number * 2) + judul.length()));
            System.out.print("Nama Garasi : ");
            String namaGarage = input.next();
            System.out.print("Tarif Mobil : Rp. ");
            int tarifMobil = input.nextInt();
            System.out.print("Tarif Motor : Rp. ");
            int tarifMotor = input.nextInt();
            System.out.print("Jumlah Hari Operasional (Seminggu) : ");
            hariOperasi = input.nextInt();
            jamBuka = new int[hariOperasi];
            jamTutup = new int[hariOperasi];
            namaHari = new String[hariOperasi];
            Garage garasi = new Garage();
            while (hariOperasi <= 0 || hariOperasi > 7) {
                System.out.print("Jumlah Hari Operasional (Seminggu) : ");
                hariOperasi = input.nextInt();
            }
            for (int j = 0; j < hariOperasi; j++) {
                System.out.print("Nama Hari : ");
                namaHari[j] = input.next();
                System.out.print("Jam Buka (Format Waktu 24 Jam) : ");
                jamBuka[j] = input.nextInt();
                while (jamBuka[j] < 0 || jamBuka[j] > 24) {
                    System.out.print("Jam Buka (Format Waktu 24 Jam) : ");
                    jamBuka[j] = input.nextInt();
                }
                System.out.print("Jam Tutup (Format Waktu 24 Jam) : ");
                jamTutup[j] = input.nextInt();
                while (jamTutup[j] < 0 || jamTutup[j] > 24) {
                    System.out.print("Jam Buka (Format Waktu 24 Jam) : ");
                    jamBuka[j] = input.nextInt();
                }
                System.out.println("");
            }
            garasi = new Garage(namaGarage, tarifMobil, tarifMotor, hariOperasi, jamBuka, jamTutup, namaHari);

            garage.editGarage(idGarage, garasi);

        } else {
            System.out.println("Field id area tidak boleh kosong");
        }
        pressAnyKey();
        clrscr();
        mainGarage();
    }

    public void hapusGarage() {
        int number = 20;
        String judul = " Daftar Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        garage.listAllGarage();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Garage : ");
        int idGarage = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        garage.hapusGarage(idGarage);
        pressAnyKey();
        clrscr();
        mainGarage();
    }

    public void detailGarage() {
        int number = 20;
        String judul = " Detail Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        garage.listAllGarage();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Masukan ID Garasi : ");
        int idGarage = input.nextInt();
        if (idGarage > 0) {
            garage.detailGarageByID(idGarage);
            System.out.println("=".repeat((number * 2) + judul.length()));
        } else {
            System.out.println("Field id area tidak boleh kosong");
        }
        pressAnyKey();
        clrscr();
        mainGarage();

    }

    private void menuHistoryParkir() {
        String judul = " Riwayat Parkir ";
        int number = 20;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        history.pengguna(user);
        history.showParkirs();
        pressAnyKey();
        clrscr();
        mainPengguna();
    }

    private void menuParkirArea() {
        Parkir parkir = new Parkir();
        parkir.setPengguna(user);
        String judul = " Area Parkir ";
        int number = 20;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        area.viewArea();
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("[0]\tKembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilih ID: ");
        int idArea = input.nextInt();
        if (idArea != 0) {
            Area newArea = area.getArea(idArea);
            if (newArea != null) {
                parkir.setArea(newArea);
                clrscr();
                menuParkirGarage(parkir, idArea);
            } else {
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Area parkir tidak tersedia"
                        + Coloring.ANSI_RESET);
                menuParkirArea();
            }
            pressAnyKey();
            clrscr();
            menuParkirArea();
        } else {
            clrscr();
            mainPengguna();
        }

    }

    private void menuParkirGarage(Parkir parkir, int idArea) {
        String judul = " " + parkir.getArea().getNamaArea() + " ";
        int number = 20;
        System.out.println("=".repeat(number) + judul.toUpperCase() + "=".repeat(number));
        System.out.println("Daftar Garasi : ");
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        int dayLocal = dayOfWeek.getValue();
        int countGarage = garage.listGarage(idArea, dayLocal);
        if (countGarage == 0) {
            System.out.println("=".repeat((number * 2) + judul.length()));
            pressAnyKey();
            clrscr();
            menuParkirArea();
        } else {
            System.out.println("=".repeat((number * 2) + judul.length()));
            System.out.println("[0]\tKembali");
            System.out.println("=".repeat((number * 2) + judul.length()));
            System.out.print("Pilih ID: ");
            int idGarage = input.nextInt();
            Garage newGarage = garage.getGarage(idGarage, idArea, dayLocal);
            if (newGarage.getNamaGarage() != null) {
                if (newGarage.isGarageOpen()) {
                    parkir.setGarage(newGarage);
                    menuPilihKendaraan(parkir);
                } else {
                    System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE
                            + "Garage tidak beroperasi silahkan pilih lainnya" + Coloring.ANSI_RESET);
                }
            } else {
                if (idGarage == 0) {
                    clrscr();
                    menuParkirArea();
                } else {
                    System.out.println(
                            Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Garage tidak tersedia" + Coloring.ANSI_RESET);
                    pressAnyKey();
                    clrscr();
                }
            }
        }
        pressAnyKey();
        clrscr();
        menuParkirGarage(parkir, idArea);
    }

    private void menuPilihKendaraan(Parkir parkir) {
        String judul = " Garage " + parkir.getGarage().getNamaGarage() + ", silahkan pilih kendaraan anda ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        kendaraan.viewListKendaraan(user.getIdPengguna());
        System.out.println("[0] Tambah kendaraan");
        System.out.print("Pilih ID: ");
        int idKendaraan = input.nextInt();
        if (idKendaraan == 0) {
            tambahKendaraan();
        } else {
            Kendaraan newKendaraan = kendaraan.getKendaraan(idKendaraan, user.getIdPengguna());
            if (newKendaraan != null) {
                try {
                    parkir.setKendaraan(newKendaraan);
                    toggleParkir(user, parkir, 0);
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Data kendaraan tidak tersedia"
                        + Coloring.ANSI_RESET);
                menuPilihKendaraan(parkir);
            }
        }
    }

    private void toggleParkir(Pengguna pengguna, Parkir parkir, int i) {
        String judul = " Parkir " + parkir.getKendaraan().getTipeKendaraan() + " "
                + parkir.getKendaraan().getPlatNomor();
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        if (i == 0) {
            System.out.println("1. Start parking");
            // System.out.println("0. Start manually");
        } else {
            System.out.println("2. Stop parking");
        }
        System.out.print("Pilih: ");
        int pil = input.nextInt();
        LocalDateTime date = LocalDateTime.now();
        switch (pil) {
            case 1:
                if (parkir.getTimeStart() != null) {
                    System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Anda sudah start parking"
                            + Coloring.ANSI_RESET);
                    toggleParkir(pengguna, parkir, 1);
                } else {
                    parkir.startParking(date);
                    toggleParkir(pengguna, parkir, 1);
                }
                break;
            case 2:
                if (i == 0) {
                    toggleParkir(pengguna, parkir, i);
                } else {
                    parkir.stopParking(date);
                    if (parkir.addParkir()) {
                        parkir.showParkir();
                        System.out.print("Press any key to go to homepage..");
                        try {
                            System.in.read();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        clrscr();
                        mainPengguna();
                    } else {
                        System.out.println("Simpan data parkir gagal");
                    }
                }
                break;
            case 0:
                System.out.print("Start time (YYYY-MM-DD hh:mm:ss): ");
                String tanggal = input.next();
                String waktu = input.next();
                String start = tanggal + " " + waktu;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                parkir.startParking(startTime);
                toggleParkir(pengguna, parkir, 1);
                break;
            default:
                toggleParkir(pengguna, parkir, i);
                break;
        }
    }

    private void menuLaporan() {
        int number = 20;
        String judul = " Laporan Transaksi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Laporan Harian");
        System.out.println("2. Laporan Mingguan");
        System.out.println("3. Laporan Bulanan");
        System.out.println("[0] Kembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        if (pil == 0) {
            mainAdmin();
        } else if (pil > 0 && pil <= 3) {
            switch (pil) {
                case 1:
                    history.showLaporanHarian();
                    break;
                case 2:
                    history.showLaporanMingguan();
                    break;
                case 3:
                    history.showLaporanBulanan();
                    break;
            }
            pressAnyKey();
        } else {
            menuLaporan();
        }
        clrscr();
        menuLaporan();
        // switch (pil) {
        // case 1:
        // laporanHarian();
        // break;
        // case 2:
        // laporanMingguan();
        // break;
        // case 3:
        // laporanBulanan();
        // break;
        // default:
        // mainAdmin();
        // break;
        // }
    }

    // private void laporanBulanan() {
    // history.showLaporanBulanan();
    // pressAnyKey();
    // menuLaporan();
    // }

    // private void laporanMingguan() {
    // history.showLaporanMingguan();
    // pressAnyKey();
    // menuLaporan();
    // }

    // private void laporanHarian() {
    // history.showLaporanHarian();
    // pressAnyKey();
    // menuLaporan();
    // }
}