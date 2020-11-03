package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class modelKendaraan {
    public void insertDataKendaraan(int idPengguna, String platNomor, String tipeKendaraan) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "INSERT INTO KENDARAAN (IDPENGGUNA, NOMORKENDARAAN, TIPEKENDARAAN) " + "VALUES ('"
                    + idPengguna + "','" + platNomor + "','" + tipeKendaraan + "')";

            int rs = state.executeUpdate(query);
            if (rs == 1) {
                System.out.println("Pendaftaran data kendaraan berhasil dilakukan");
            } else {
                System.out.println("Pendaftaran data kendaraan gagal dilakukan");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public ResultSet searchKendaraan(int idPengguna) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "SELECT * FROM kendaraan WHERE idPengguna ='" + idPengguna + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet searchNomorKendaraan(String nomorKendaraan) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM kendaraan WHERE nomorKendaraan ='" + nomorKendaraan + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }
}
