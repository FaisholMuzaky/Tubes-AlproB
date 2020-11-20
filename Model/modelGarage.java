package Model;

import java.sql.Connection;

import Database.Database;
import java.sql.ResultSet;
import java.sql.Statement;

import Controller.Garage;

import java.sql.SQLException;

public class modelGarage {
    public void insertGarage(int IdArea, Garage[] garage) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            int count = 0;
            for (int i = 0; i < garage.length; i++) {
                String query = "INSERT INTO garage (IdArea,namaGarage,tarifMobil,tarifMotor,hariOperasi)" + "VALUES('"
                        + IdArea + "','" + garage[i].getNamaGarage() + "','" + garage[i].getTarifMobil() + "','"
                        + garage[i].getTarifMotor() + "','" + garage[i].getHariOperasi() + "')";
                state.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = state.getGeneratedKeys();
                if (rs.next()) {
                    long idGarage = rs.getLong(1);
                    for (int j = 0; j < garage[i].getHariOperasi(); j++) {
                        query = "INSERT INTO harioperasional (IdGarage,namaHari,jamBuka,jamTutup)" + "VALUES('"
                                + idGarage + "','" + garage[i].getNamaHari()[j] + "','" + garage[i].getJamBuka()[j]
                                + "','" + garage[i].getJamTutup()[j] + "')";
                        state.executeUpdate(query);
                    }
                }
                count++;
            }
            if (count == garage.length) {
                System.out.println("Tambah Garasi Berhasil Dilakukan");
            } else {
                System.out.println("Tambah Garasi Gagal Dilakukan");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public int updateGarage(int idGarage, Garage garasi) {
        int rs = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "UPDATE garage SET namaGarage = '" + garasi.getNamaGarage() + "', tarifMobil = "
                    + garasi.getTarifMobil() + ", tarifMotor = " + garasi.getTarifMotor() + ", hariOperasi = "
                    + garasi.getHariOperasi() + " WHERE idGarage = '" + idGarage + "'";
            state.executeUpdate(query);
            query = "DELETE FROM harioperasional WHERE IdGarage = '" + idGarage + "'";
            state.executeUpdate(query);
            for (int i = 0; i < garasi.getHariOperasi(); i++) {
                query = "INSERT INTO harioperasional (IdGarage, namaHari, jamBuka, jamTutup)" + "VALUES('" + idGarage
                        + "','" + garasi.getNamaHari()[i] + "','" + garasi.getJamBuka()[i] + "','"
                        + garasi.getJamTutup()[i] + "')";
                rs = state.executeUpdate(query);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet searchGarage(int idGarage) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT IdArea, namaGarage, tarifMobil, tarifMotor, hariOperasi, namaHari, jamBuka, jamTutup FROM garage JOIN harioperasional using(IdGarage) WHERE garage.IdGarage ='"
                    + idGarage + "'";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet searchGarageByIdArea(int idArea) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage JOIN harioperasional using(IdGarage) WHERE IdArea =" + idArea;
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return rs;
    }

    public ResultSet searchGarageByHari(int idArea, String namaHari) {
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage JOIN harioperasional using(IdGarage) WHERE IdArea ='" + idArea
                    + "'AND namaHari = '" + namaHari + "'";
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
            String query = "Select * From garage Join area using(IdArea)";
            rs = state.executeQuery(query);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
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
            while (rs.next()) {
                namaGarage = rs.getString("namaGarage");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return namaGarage;
    }

    public Garage getGarage(int idGarage, int idArea, String namahari) {
        Garage garage = new Garage();
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT * FROM garage JOIN harioperasional using(IdGarage) WHERE IdArea =" + idArea
                    + " AND IdGarage =" + idGarage + " AND namaHari = '" + namahari + "'";
            // String query = "SELECT idGarage, idArea, namaGarage, tarifMotor, tarifMobil,
            // hariOperasi, jamBuka, jamTutup"
            // + " FROM garage WHERE idGarage =" + idGarage + " AND idArea=" + idArea;
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                garage.setIdGarage(rs.getInt("idGarage"));
                garage.setNamaGarage(rs.getString("namaGarage"));
                garage.setTarifMobil(rs.getInt("tarifMobil"));
                garage.setTarifMotor(rs.getInt("tarifMotor"));
                garage.setHariOperasi(rs.getInt("hariOperasi"));
                garage.setJamBukaOperasional(rs.getInt("jamBuka"));
                garage.setJamTutupOperasional(rs.getInt("jamTutup"));
                garage.setHariOperasional(rs.getString("namahari"));
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
            String query = "SELECT b.idArea FROM garage a INNER JOIN area b ON a.idArea = b.idArea "
                    + "WHERE a.idGarage ='" + idGarage + "'";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                idArea = rs.getInt("idArea");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return idArea;
    }

    public boolean hapusGarage(int idGarage) {
        boolean status = false;
        ResultSet rs = null;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT COUNT(*) FROM garage JOIN parkir USING(idGarage) WHERE garage.idGarage = "
                    + idGarage;
            rs = state.executeQuery(query);
            if (rs.next()) {
                if (rs.getInt("COUNT(*)") == 0) {
                    query = "DELETE FROM garage WHERE idGarage = " + idGarage;
                    state.executeUpdate(query);
                    status = true;
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return status;
    }
}
