package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Controller.Area;
import java.sql.SQLException;

public class modelArea {

    public void insertArea(Area[] area) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            int count = 0;
            for (int i = 0; i < area.length; i++) {
                String query = "INSERT INTO area(namaArea)" + "VALUES ('" + area[i].getNamaArea() + "')";
                state.executeUpdate(query);
                count++;
            }
            if (count == area.length) {
                System.out.println("Tambah Area Berhasil Dilakukan");
            } else {
                System.out.println("Tambah Area Gagal Dilakukan");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public ResultSet searchAreaByID(int idArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM area WHERE IdArea =" + idArea;
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet searchArea(String namaArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM area WHERE idArea ='" + namaArea + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet searchNamaArea(int idArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT namaArea FROM area WHERE idArea ='" + idArea + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet listArea() {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM area";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public int updateArea(int idArea, String newNamaArea) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE area SET namaArea = '" + newNamaArea + "' WHERE idArea = '" + idArea + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public static int updateDataArea(int IdArea, String namaArea) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE area SET namaArea = '" + namaArea + "' WHERE IdArea = '" + IdArea + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public static int deleteDataArea(int IdArea, String namaArea) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "DELETE FROM area WHERE IdArea = '" + IdArea + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public boolean hapusArea(int idArea) {
        boolean status = false;
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT COUNT(*) FROM area JOIN Garage USING(IdArea) JOIN parkir using(idGarage) WHERE area.IdArea = "
                    + idArea;
            rs = state.executeQuery(query);
            if (rs.next()) {
                if (rs.getInt("COUNT(*)") == 0) {
                    query = "DELETE FROM area WHERE idArea = " + idArea;
                    state.executeUpdate(query);
                    status = true;
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return status;
    }

    public static ResultSet searchIdArea() {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM area";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }
}
