package View;

import java.util.Scanner;

public class View {
    Scanner input = new Scanner(System.in);

    public void main() {
        int number = 10;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("=".repeat((number * 2) + judul.length()));
        System.out.print("Pilihan : ");
        int pil = input.nextInt();
        switch (pil) {
            case 1:

                break;

            default:
                break;
        }
    }

    public void Login() {
        int number = 10;
        String judul = " Parkir ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Email" + " ".repeat(5) + ": ");
        String email = input.next();
        System.out.print("Password" + " ".repeat(2) + ": ");
        String password = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));
    }
}
