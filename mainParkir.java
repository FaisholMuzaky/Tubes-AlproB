import Database.Database;
import View.View;
import java.util.Scanner;

import Controller.Area;
import Controller.Pengguna;

public class mainParkir {

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
        View.clrscr();
        switch (pil) {
            case 1:
                id = user.login();
                break;

            case 2:
                user.registrasi();
                View.clrscr();
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
