import java.util.Scanner;

import Controller.Menu;
import Controller.Pengguna;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Pengguna pengguna = new Pengguna();
        Scanner input = new Scanner(System.in);
        menu.menuUtama(pengguna, input);
    }
}
