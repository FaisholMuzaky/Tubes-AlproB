package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import Model.modelArea;
import Model.modelGarage;
import Model.modelKendaraan;

public class Menu {
    public static void clrscr() {
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
        String judul = " Hai "+pengguna.getNama()+", Selamat datang ";
        int number = 10;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date.format(formatter));
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
                menuParkirArea(pengguna, input);
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

    private void menuParkirArea(Pengguna pengguna, Scanner input) {
        String judul = " Area Parkir ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        modelArea.viewArea();
        System.out.print("Pilihan: ");
        int pil = input.nextInt();
        Parkir parkir = new Parkir();
        parkir.setIdArea(pil);
        menuParkirGarage(pengguna, input, parkir);
    }

    private void menuParkirGarage(Pengguna pengguna, Scanner input, Parkir parkir) {
        String judul = " Garage Parkir ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        modelGarage.viewGarage(parkir.getIdArea());
        System.out.print("Pilihan: ");
        int pil = input.nextInt();
        parkir.setIdGarage(pil);
        menuPilihKendaraan(pengguna, parkir, input);
    }

    private void menuPilihKendaraan(Pengguna pengguna, Parkir parkir, Scanner input) {
        String judul = " Kendaraan Anda ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        ResultSet rs = modelKendaraan.searchKendaraan(pengguna.getIdPengguna());
        try {
            while(rs.next()) {
                System.out.println(rs.getInt("idKendaraan")+". "+rs.getString("platNomor")+" "+rs.getString("tipeKendaraan"));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.print("Pilihan: ");
        int pil = input.nextInt();
        parkir.setIdKendaraan(pil);
        toggleParkir(pengguna,parkir,input);
    }

    private void toggleParkir(Pengguna pengguna, Parkir parkir, Scanner input) {
        String judul = " Parkir ";
        int number = 10;
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Start parking");
        System.out.println("2. Stop parking");
        System.out.print("Pilihan: ");
        int pil = input.nextInt();
        LocalDateTime date = LocalDateTime.now();
        switch (pil) {
            case 1:
                if (parkir.getTimeStart()!=null) {
                    System.out.println("Anda sudah start parking");
                    toggleParkir(pengguna, parkir, input);
                } else {
                    parkir.startParking(date);
                    System.out.println("Waktu parkir telah dimulai");
                    toggleParkir(pengguna, parkir, input);
                }
                break;
            case 2:
                parkir.stopParking(date);
                if(parkir.addParkir()) {
                    System.out.println("Simpan data parkir berhasil");
                    parkir.showParkir();
                } else {
                    System.out.println("Simpan data parkir gagal");
                }
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
