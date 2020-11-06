package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection con;

    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/parkir?serverTimezone=Asia/Jakarta";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
            if (con == null) {
                System.out.println("Failed to make connection!");
            }
        } catch (Exception e) {
            System.err.println("koneksi gagal " + e.getMessage());
        }

        return con;

    }

}
