package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class modelPengguna {

    public ResultSet login(String email, String password) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "SELECT * FROM pengguna WHERE email ='" + email + "' and password ='" + password + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void insertDataPengguna(String nama, String email, String alamat, String password, String subscription) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "INSERT INTO PENGGUNA (NAMA, EMAIL, ALAMAT, PASSWORD, SUBSCRIPTION) " + "VALUES ('" + nama
                    + "','" + email + "','" + alamat + "','" + password + "','" + subscription + "')";

            int rs = state.executeUpdate(query);
            if (rs == 1) {
                System.out.println("Registrasi Berhasil");
            } else {
                System.out.println("Registrasi Gagal");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Registrasi gagal");
        }

    }

    public ResultSet searchEmail(String email) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM pengguna WHERE email ='" + email + "'";
            rs = state.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;

    }

    public ResultSet searchByID(int idPengguna) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM pengguna WHERE idPengguna ='" + idPengguna + "'";
            rs = state.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public int updateDataPengguna(int id, String nama, String alamat) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE pengguna SET nama = '" + nama + "', alamat = '" + alamat + "' WHERE idPengguna ='"
                    + id + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet rolePengguna(int idPengguna) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT role FROM pengguna WHERE idPengguna ='" + idPengguna + "'";
            rs = state.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
}
