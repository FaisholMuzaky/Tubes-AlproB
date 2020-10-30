package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;

public class modelArea {
    public static void insertArea(String namaArea){
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "INSERT INTO AREA(NAMAAREA)" + "VALUES ('"+ namaArea +"')";

            int rs = state.executeUpdate(query);
            if(rs == 1){
                System.out.println("Tambah Area Berhasil");
            } else{
                System.out.println("Tambah Area Gagal");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Tambah Area gagal");
        }
    }

    public static ResultSet searchArea(String namaArea){
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM area WHERE namaArea ='" + namaArea + "'";
            rs = state.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public static int updateArea(String namaArea){
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE area SET namaArea = '" + namaArea + "'";
            rs = state.executeUpdate(query);
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public static void viewArea() {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "SELECT idArea, namaArea FROM area";
            ResultSet rs = state.executeQuery(query);
            while(rs.next()) {
                System.out.println(rs.getInt("idArea")+". "+rs.getString("namaArea"));
            }
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
