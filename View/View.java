package View;

import java.util.Scanner;
import java.io.IOException;

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

    public static void clrscr() {

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {

        }

    }
}
