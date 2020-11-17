package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection con;

    public static Connection getKoneksi() {
        try {
            String url = "jdbc:mysql://103.129.220.10:3306/parkir?serverTimezone=Asia/Jakarta";
            String user = "user123";
            String password = "22v4mt@Z";
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
