package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import Controller.Garage;
import Controller.Kendaraan;
import Controller.Parkir;

public class modelParkir implements modelGeneric<Parkir> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // private int getIdKendaraan(Kendaraan k) {
    //     int idKendaraan = 0;
    //     try {
    //         Connection con = Database.getKoneksi();
    //         Statement state = con.createStatement();
    //         String queryIdKendaraan = "SELECT idKendaraan FROM kendaraan "+
    //                         "WHERE platNomor = '" + k.getPlatNomor()+"'";
    //         ResultSet rs = state.executeQuery(queryIdKendaraan);
    //         while(rs.next()) {
    //             idKendaraan = rs.getInt("idKendaraan");
    //         }
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //     }
        
    //     return idKendaraan;
    // }

    public boolean isParking(Parkir parkir) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "SELECT idParkir FROM parkir "+
                        "WHERE idKendaraan = '" + parkir.getIdKendaraan() +"' AND "+
                        "idGarage = '" + parkir.getIdGarage()+"' AND "+
                        "timeStart = '" + parkir.getTimeStart() + "'"
                        ;
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return false;
    }

    private int getIdGarage(Garage g) {
        int idGarage = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String queryIdGarage = "SELECT idGarage FROM garage "+
                        "WHERE namaGarage = '" + g.getNamaGarage()+"'";
            ResultSet rs = state.executeQuery(queryIdGarage);
            rs = state.executeQuery(queryIdGarage);
            while(rs.next()) {
                idGarage = rs.getInt("idGarage");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return idGarage;
    }

    private int getIdParkir(Parkir p) {
        int idParkir = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String queryIdParkir = "SELECT idParkir FROM parkir "+
                        "WHERE idKendaraan = " + p.getIdKendaraan()+" AND "+
                        "idGarage = " +p.getIdGarage()+" AND timeStart='"+ p.getTimeStart().format(formatter) +"'"
                        ;
            ResultSet rs = state.executeQuery(queryIdParkir);
            
            rs = state.executeQuery(queryIdParkir);
            while(rs.next()) {
                idParkir = rs.getInt("idParkir");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return idParkir;
    }

    @Override
    public boolean save(Parkir u) {
        // TODO Auto-generated method stub
        boolean result = false;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();

            String query = "CALL parkir.setParkir("+ u.getIdKendaraan() +", "+ u.getIdGarage() +",'"+
                            u.getTimeStart().format(formatter)+"','"+u.getTimeStop().format(formatter)+"')";
            // System.out.println(query);
            state.execute(query);
            int hasil = state.getUpdateCount();
            if (hasil==1) {
                result = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

    @Override
    public boolean edit(Parkir u) {
        // TODO Auto-generated method stub
        boolean result = false;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();

            String query = "CALL update_parkir "+ getIdParkir(u)+", "+ u.getIdKendaraan() +", "+
                            u.getIdGarage() +"'"+u.getTimeStop()+"'";
            result = state.execute(query);

        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

    @Override
    public boolean delete(Parkir u) {
        // TODO Auto-generated method stub
        boolean result = false;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();

            String query = "DELETE FROM parkir WHERE idParkir = "+ getIdParkir(u);
            result = state.execute(query);

        } catch (Exception e) {
            //TODO: handle exception
        }
        return result;
    }

    @Override
    public void view(Parkir u) {
        // TODO Auto-generated method stub
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int idParkir = getIdParkir(u);
            String query = "CALL parkir.viewParkir("+ idParkir +")";
            ResultSet rs = state.executeQuery(query);
            int i = 1;
            while(rs.next()) {
                System.out.println(i+". "+rs.getString("platNomor")+" "+rs.getString("tipeKendaraan")+" "+
                rs.getString("nama")+" "+rs.getString("namaGarage")+" "+rs.getString("namaArea")+" "+
                rs.getString("timeStart")+" "+rs.getString("timeStop")+" "+rs.getString("durasi")+"Menit Rp"+rs.getInt("totalTransaksi"));
                i++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}