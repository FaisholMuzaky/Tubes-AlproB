package Model;

import java.sql.Connection;

import Database.Database;
import java.sql.ResultSet;
import java.sql.Statement;

import Controller.Garage;

import java.sql.SQLException;

public class modelGarage {
    public void insertGarage(int IdArea, Garage garage[]) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            int count = 0;
            for (int i = 0; i < garage.length; i++) {
                String query = "INSERT INTO GARAGE(IDAREA, NAMAGARAGE, TARIFMOBIL, TARIFMOTOR, HARIOPERASI, JAMBUKA, JAMTUTUP)"
                        + "VALUES ('" + IdArea + "','" + garage[i].getNamaGarage() + "','" + garage[i].getTarifMobil()
                        + "','" + garage[i].getTarifMotor() + "','" + garage[i].getHariOperasi() + "','" 
                        + garage[i].getJamBuka() + "','" + garage[i].getJamTutup() + "')";
                state.executeUpdate(query);
                count++;
            }
            if (count == garage.length) {
                System.out.println("Tambah Garasi Berhasil");
            } else {
                System.out.println("Tambah Garasi Gagal");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Tambah Garasi gagal");
        }
    }

    public ResultSet searchGarage(int IdArea) {
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

    public ResultSet countGarages(int IdArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT COUNT(*) FROM garage WHERE IdArea ='" + IdArea + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet listAllGarage() {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet searchGarage(String namaGarage) {
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

    public ResultSet getNamaArea(int idArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT area.namaArea FROM garage JOIN area USING(IdArea) WHERE IdArea ='" + idArea + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public int updateGarage(int idGarage, String namaGarasi, int tarifMobil, int tarifMotor, int hariOperasi, int jamBuka, int jamTutup) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE garage SET namaGarage = '" + namaGarasi + "', tarifMobil = " + tarifMobil + ", tarifMotor = " 
                    + tarifMotor + ", hariOperasi = "+ hariOperasi + ", jamBuka = " + jamBuka + ", jamTutup = " + jamTutup + " WHERE idGarage = '"
                    + idGarage + "'";
            rs = state.executeUpdate(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
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

    public String getNamaGarage(int idGarage) {
        String namaGarage = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT namaGarage FROM garage WHERE idGarage ='" + idGarage + "'";
            ResultSet rs = state.executeQuery(query);
            while(rs.next()) {
                namaGarage = rs.getString("namaGarage");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return namaGarage;
    }

    public Garage getGarage(int idGarage, int idArea) {
        Garage garage = new Garage();
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT idGarage, idArea, namaGarage, tarifMotor, tarifMobil, hariOperasi, jamBuka, jamTutup"+
                            " FROM garage WHERE idGarage =" + idGarage + " AND idArea="+idArea;
            ResultSet rs = state.executeQuery(query);
            while(rs.next()) {
                garage.setIdGarage(rs.getInt("idGarage"));
                garage.setNamaGarage(rs.getString("namaGarage"));
                garage.setTarifMobil(rs.getInt("tarifMobil"));
                garage.setTarifMotor(rs.getInt("tarifMotor"));
                garage.setHasilOperasi(rs.getInt("hariOperasi"));
                garage.setJamBuka(rs.getInt("jamBuka"));
                garage.setJamTutup(rs.getInt("jamTutup"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return garage;
    }

	public int getIdArea(int idGarage) {
        int idArea = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT b.idArea FROM garage a INNER JOIN area b ON a.idArea = b.idArea "+
                            "WHERE a.idGarage ='" + idGarage + "'";
            ResultSet rs = state.executeQuery(query);
            while(rs.next()) {
                idArea = rs.getInt("idArea");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return idArea;
	}
}
