package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Controller.Coloring;
import Controller.Garage;
import Controller.Parkir;
import Controller.Pengguna;

public class modelParkir implements modelGeneric<Parkir> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean isParking(Parkir parkir) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String query = "SELECT idParkir FROM parkir " + "WHERE idKendaraan = '"
                    + parkir.getKendaraan().getIdKendaraan() + "' AND " + "idGarage = '"
                    + parkir.getGarage().getIdGarage_() + "' AND " + "timeStart = '" + parkir.getTimeStart() + "'";
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    private int getIdGarage(Garage g) {
        int idGarage = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String queryIdGarage = "SELECT idGarage FROM garage " + "WHERE namaGarage = '" + g.getNamaGarage() + "'";
            ResultSet rs = state.executeQuery(queryIdGarage);
            rs = state.executeQuery(queryIdGarage);
            while (rs.next()) {
                idGarage = rs.getInt("idGarage");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return idGarage;
    }

    private int getIdParkir(Parkir p) {
        int idParkir = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String queryIdParkir = "SELECT idParkir FROM parkir " + "WHERE idKendaraan = "
                    + p.getKendaraan().getIdKendaraan() + " AND " + "idGarage = " + p.getGarage().getIdGarage_()
                    + " AND timeStart='" + p.getTimeStart().format(formatter) + "'";
            ResultSet rs = state.executeQuery(queryIdParkir);

            rs = state.executeQuery(queryIdParkir);
            while (rs.next()) {
                idParkir = rs.getInt("idParkir");
            }
        } catch (Exception e) {
            // TODO: handle exception
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

            // String query = "CALL setParkir("+ u.getKendaraan().getIdKendaraan() +
            // ", "+ u.getGarage().getIdGarage_()+",'"+
            // u.getTimeStart().format(formatter)+"','"+
            // u.getTimeStop().format(formatter)+"')";
            String query = "INSERT INTO parkir" + "(idKendaraan,idGarage,timeStart,timeStop,durasi,totalTransaksi)"
                    + "VALUES" + "(" + u.getKendaraan().getIdKendaraan() + "," + u.getGarage().getIdGarage_() + ","
                    + "'" + u.getTimeStart().format(formatter) + "'," + "'" + u.getTimeStop().format(formatter) + "',"
                    + u.getDurasi() + "," + u.getTotalTransaksi() + ");";
            state.execute(query);
            int hasil = state.getUpdateCount();
            if (hasil == 1) {
                result = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
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

            String query = "CALL updateParkir " + getIdParkir(u) + ", " + u.getKendaraan().getIdKendaraan() + ", "
                    + u.getGarage().getIdGarage_() + "'" + u.getTimeStop() + "'";
            result = state.execute(query);

        } catch (Exception e) {
            // TODO: handle exception
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

            String query = "DELETE FROM parkir WHERE idParkir = " + getIdParkir(u);
            result = state.execute(query);

        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    @Override
    public void view(Parkir u) {
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int idParkir = getIdParkir(u);
            String query = "CALL viewParkir(" + idParkir + ")";
            ResultSet rs = state.executeQuery(query);
            System.out.println("=".repeat(40));
            System.out.println(Coloring.ANSI_BG_BLUE + Coloring.ANSI_WHITE + "PARKIR SUMMARY" + Coloring.ANSI_RESET);
            System.out.println("=".repeat(40));
<<<<<<< HEAD
            int durasi = rs.getInt("durasi")%60;
            int jam = u.convertToHour(rs.getInt("durasi"));
            if (durasi>0) {
                jam += 1;
            } 
            while(rs.next()) {
                System.out.println("PLAT NOMOR\t"+rs.getString("nomorKendaraan"));
                System.out.println("TIPE KENDARAAN\t"+rs.getString("tipeKendaraan"));
                System.out.println("PEMILIK\t\t"+rs.getString("nama"));
                System.out.println("SUBSCRIPTION\t"+rs.getString("subscription"));
                System.out.println("GARAGE\t\t"+rs.getString("namaGarage"));
                System.out.println("TARIF MOTOR\t"+rs.getString("tarifMotor"));
                System.out.println("TARIF MOBIL\t"+rs.getString("tarifMobil"));
                System.out.println("AREA\t\t"+rs.getString("namaArea"));
                System.out.println("WAKTU MULAI\t"+rs.getString("timeStart"));
                System.out.println("WAKTU SELESAI\t"+rs.getString("timeStop"));
                System.out.println("DURASI\t\t"+ jam + " JAM");
                System.out.println("TOTAL (Rp)\t"+rs.getInt("totalTransaksi"));
=======
            while (rs.next()) {
                System.out.println("PLAT NOMOR\t" + rs.getString("nomorKendaraan"));
                System.out.println("TIPE KENDARAAN\t" + rs.getString("tipeKendaraan"));
                System.out.println("PEMILIK\t\t" + rs.getString("nama"));
                System.out.println("SUBSCRIPTION\t" + rs.getString("subscription"));
                System.out.println("GARAGE\t\t" + rs.getString("namaGarage"));
                System.out.println("TARIF MOTOR\t" + rs.getString("tarifMotor"));
                System.out.println("TARIF MOBIL\t" + rs.getString("tarifMobil"));
                System.out.println("AREA\t\t" + rs.getString("namaArea"));
                System.out.println("WAKTU MULAI\t" + rs.getString("timeStart"));
                System.out.println("WAKTU SELESAI\t" + rs.getString("timeStop"));
                System.out.println("DURASI\t\t" + rs.getInt("durasi") + " JAM");
                System.out.println("TOTAL (Rp)\t" + rs.getInt("totalTransaksi"));
>>>>>>> 047e2af0a51c05157d37c9dcc2d3fac509e0e8d9
            }
            System.out.println("=".repeat(40));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public int getCountMonthly(LocalDate now, Pengguna pengguna) {
        int jumlah = 0;
        try {
            Connection con = Database.getKoneksi();
            Statement state = con.createStatement();
            String queryIdParkir = "SELECT COUNT(*) as JUMLAH FROM parkir a "
                    + "LEFT JOIN kendaraan b on a.idKendaraan = b.idKendaraan "
                    + "LEFT JOIN pengguna c on b.idPengguna = c.idPengguna " + "WHERE c.idPengguna = "
                    + pengguna.getIdPengguna() + " and "
                    + "CONCAT(YEAR(a.timeStart),MONTH(a.timeStart))=CONCAT(YEAR(NOW()),MONTH(NOW())) " + "GROUP BY "
                    + "c.idPengguna, " + "CONCAT(YEAR(a.timeStart),MONTH(a.timeStart)) ";
            ResultSet rs = state.executeQuery(queryIdParkir);

            rs = state.executeQuery(queryIdParkir);
            while (rs.next()) {
                jumlah = rs.getInt("JUMLAH");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return jumlah;
    }
}