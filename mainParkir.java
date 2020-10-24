import Database.Database;

import java.io.IOException;
import java.util.Scanner;

import Controller.Pengguna;

public class mainParkir {
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

    public static void main(String[] args) {
        int id = 0;
        Scanner input = new Scanner(System.in);
        Pengguna user = new Pengguna();
        int number = 10;
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
                id = user.login();
                break;

            case 2:
                user.registrasi();
                clrscr();
                id = user.login();
                break;

            default:
                break;
        }
        // Database db = new Database();
        // db.getKoneksi();
        // db.registrasi();

        // user.registrasi();
        // int id = user.login();
        // System.out.println(id);
        // user.viewAccount(id);
        // user.editAccount(id);
        // user.viewAccount(id);
    }
}
