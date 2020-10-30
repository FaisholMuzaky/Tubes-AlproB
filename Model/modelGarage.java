package Model;

import java.sql.Connection;

import Database.Database;
import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class modelGarage {
    public static void insertGarage(int IdArea, String namaGarage, int tarif, int hariOperasi, int jamBuka, int jamTutup){
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "INSERT INTO GARAGE(IDAREA, NAMAGARAGE, TARIF, HARIOPERASI, JAMBUKA, JAMTUTUP)" + "VALUES ('"
            + IdArea + "','" + namaGarage + "','" + tarif + "','" + hariOperasi + "','" + jamBuka + "','" + jamTutup + "')";
            int rs = state.executeUpdate(query);
            if(rs == 1){
                System.out.println("Tambah Garasi Berhasil");
            } else{
                System.out.println("Tambah Garasi Gagal");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Tambah Garasi gagal");
        }      
    }

    public static ResultSet searchGarage(int IdArea){
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage WHERE IdArea ='" + IdArea + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public static ResultSet searchGarageforUpdate(String namaGarage){
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage WHERE namaGarage ='" + namaGarage + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

	public static int updateDataGarage(int idGarage, String namaGarage, int tarif, int hariOperasi, int jamBuka,
			int jamTutup) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE garage SET namaGarage = '" + namaGarage + "', tarif = '" + tarif + "', hariOperasi = '" + hariOperasi + "', jamBuka = '" + jamBuka + "', jamTutup = '" + jamTutup + "' WHERE IdGarage ='"
            + idGarage + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

	public static int deleteDataGaragae(int idGarage, String namaGarage) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "DELETE FROM garage WHERE IdGarage = '" + idGarage + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
	}
}

