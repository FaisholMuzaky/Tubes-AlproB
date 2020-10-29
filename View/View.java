package View;

import Controller.Kendaraan;
import Controller.Pengguna;
import Controller.Area;
import Controller.Garage;
import java.util.Scanner;
import java.io.IOException;

public class View {
    private int idPengguna = 0;
    private int IdArea = 0;

    Scanner input = new Scanner(System.in);
    Pengguna user = new Pengguna();
    Kendaraan kendaraan = new Kendaraan();
    Garage garage = new Garage();
    Area area = new Area();

    public void auth() {
        int number = 20;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                idPengguna = user.login();
                pressAnyKey();
                if (idPengguna > 0) {
                    clrscr();
                    mainPengguna();
                } else {
                    clrscr();
                    auth();
                }
                break;
            case 2:
                user.registrasi();
                clrscr();
                idPengguna = user.login();
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

    public void areaAndGarage(){
        int number = 20;
        String judul = " Area dan Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Tambah Area");
        System.out.println("2. Tambah Garasi");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                area.addArea();
                clrscr();
                IdArea = area.garage();
                pressAnyKey();
                if(IdArea > 0){
                    clrscr();
                    mainArea();
                } else {
                    clrscr();
                    auth();
                }
                break;
            case 2:
                IdArea = area.garage();
                pressAnyKey();
                if(IdArea > 0){
                    clrscr();
                    viewGarage();
                } else {
                    clrscr();
                    auth();
                }
                break;
            default:
                break;
        }
    }

    public static void clrscr() {

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

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

    public void mainArea() {
        int number = 20;
        String judul = " Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));        
        System.out.println("1. Garage");
        System.out.println("2. Edit Area");
        System.out.println("3. Hapus Area");
        System.out.println("4. Lihat Daftar Area");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                viewGarage();
                break;
        
            default:
                break;
        }
    }

    public void mainPengguna() {
        int number = 20;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Kendaraan");
        System.out.println("2. Parkir");
        System.out.println("3. History Parkir");
        System.out.println("4. Profil");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        clrscr();
        switch (pil) {
            case 1:
                viewKendaraan();
                break;
            // case 2:
            // user.registrasi();
            // clrscr();
            // id = user.login();
            // break;
            // case 3:
            // user.registrasi();
            // clrscr();
            // id = user.login();
            // break;
            // case 4:
            // user.registrasi();
            // clrscr();
            // id = user.login();
            // break;
            default:
                break;
        }
    }

    public void viewGarage(){
        int number = 20;
        String judul = " Daftar Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        garage.viewListGarage(IdArea);
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Garasi");
        System.out.println("2. Edit Garasi");
        System.out.println("3. Hapus Garasi");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        switch (pil) {
            case 1:
                clrscr();
                tambahGarage();
                break;
        
            default:
                break;
        }
    }

    public void viewKendaraan() {
        int number = 20;
        String judul = " Daftar Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        kendaraan.viewListKendaraan(4);
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Edit Kendaraan");
        System.out.println("3. Hapus Kendaraan");
        System.out.println("4. Kembali");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        switch (pil) {
            case 1:
                clrscr();
                tambahKendaraan();
                break;
            case 4:
                clrscr();
                mainPengguna();
                break;

            default:
                break;
        }
    }

    public void tambahKendaraan() {
        int number = 20;
        String judul = " Tambah Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nomor Kendaraan : ");
        String nomorKendaraan = input.next();
        System.out.println("Tipe Kendaraan : ");
        System.out.println("1. Mobil");
        System.out.println("2. Motor");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        if (pil == 1) {
            kendaraan.addKendaraan(4, nomorKendaraan, "Mobil");
            clrscr();
            viewKendaraan();
        } else {
            kendaraan.addKendaraan(4, nomorKendaraan, "Motor");
            clrscr();
            viewKendaraan();
        }
    }

    public void tambahGarage() {
        int number = 20;
        String judul = " Tambah Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Garasi : ");
        String namaGarage = input.next();
        System.out.print("Tarif : ");
        int tarif = input.nextInt();
        System.out.print("Jumlah Hari Operasi : ");
        int hariOperasi = input.nextInt();
        System.out.print("Jam Buka : ");
        int jamBuka = input.nextInt();
        System.out.print("Jam Tutup : ");
        int jamTutup = input.nextInt();
        garage.addGarage(IdArea, namaGarage, tarif, hariOperasi, jamBuka, jamTutup);
    }
}
