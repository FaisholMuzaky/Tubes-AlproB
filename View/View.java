package View;

import Controller.Kendaraan;
import Controller.Pengguna;
import java.util.Scanner;
import java.io.IOException;

public class View {
    private int idPengguna = 0;

    Scanner input = new Scanner(System.in);
    Pengguna user = new Pengguna();
    Kendaraan kendaraan = new Kendaraan();

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

    public void viewKendaraan() {
        int number = 20;
        String judul = " Daftar Kendaraan ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        kendaraan.viewListKendaraan(idPengguna);
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.println("1. Tambah Kendaraan");
        System.out.println("2. Edit Kendaraan");
        System.out.println("3. Hapus Kendaraan");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        System.out.println("=".repeat((number * 2) + judul.length()));
        switch (pil) {
            case 1:
                clrscr();
                tambahKendaraan();
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
            kendaraan.addKendaraan(idPengguna, nomorKendaraan, "Mobil");
        } else {
            kendaraan.addKendaraan(idPengguna, nomorKendaraan, "Motor");
        }
    }
}
