package Controller;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void clrscr() {

        // Clears Screen in java

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {

        }

    }
    
    public void menuUtama(Pengguna pengguna, Scanner input) {
        String judul = " Parkir ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        switch (pil) {
            case 1:
                //proses login
                int id = pengguna.login();
                if(id==0) {
                    clrscr();
                    menuUtama(pengguna, input);
                } else {
                    if(!pengguna.getIsAdmin()) {
                        // menuPengguna(pengguna);
                        menuPengguna(pengguna,input);
                    } else {
                        // menuAdmin(pengguna);
                        System.out.println("Menu Admin");
                    }
                }
                break;
            case 2:
                pengguna.registrasi();
                menuUtama(pengguna, input);
                break;
            default:
                break;
        }
    }

    public void menuPengguna(Pengguna pengguna, Scanner input) {
        String judul = " Hai "+ pengguna.getNama() +", selamat datang!";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Parkir");
        System.out.println("2. Kendaraan");
        System.out.println("0. Kembali");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        switch (pil) {
            case 1:
                //menu parkir
                break;
            case 2:
                //kelola kendaraan
                menuKendaraan(pengguna, input);
                break;
            default:
                menuUtama(pengguna,input);
                break;
        }
    }

    private void menuKendaraan(Pengguna pengguna, Scanner input) {
        String judul = "Hai "+ pengguna.getNama() +", kelola data kendaraanmu sekarang!";
        int number = 1;
        System.out.println(judul);
        System.out.println("\n".repeat(number-1));
        if(pengguna.getKendaraan()!=null) {
            int i = 0;
            for (Kendaraan kendaraan : pengguna.getKendaraan()) {
                System.out.println(i+". "+kendaraan.getTipeKendaraan()+"\t"+kendaraan.getPlatNomor());
                i++;
            }
        } else {
            System.out.println("Belum ada kendaraan terdaftar");
        }
        System.out.println("\n".repeat(number));
        System.out.println("1. Tambah kendaraan");
        System.out.println("2. Edit kendaraan");
        System.out.println("3. Hapus kendaraan");
        System.out.println("0. Kembali");
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        switch (pil) {
            case 1:
                //menu tambah kendaraan
                System.out.println("Method tambah kendaraan");
                break;
            case 2:
                //menu edit kendaraan
                System.out.println("Method edit kendaraan");
                break;
            case 3:
                //menu hapus kendaraan
                System.out.println("Method hapus kendaraan");
                break;
            default:
                menuPengguna(pengguna,input);
                break;
        }
    }
}
