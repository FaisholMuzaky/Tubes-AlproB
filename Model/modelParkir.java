// Nama: Walim Abdul Somad
// NIM : 23520026 

package Model;

import Database.Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import View.Coloring;
import Controller.Parkir;
import Controller.Pengguna;

public class modelParkir implements modelGeneric<Parkir> {
    private DateTimeFormatter formatter;
    private NumberFormat nf;

    public modelParkir() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        nf = NumberFormat.getInstance(new Locale("id", "ID"));
    }


    public boolean isParking(Parkir parkir) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();
            String query = "SELECT idParkir FROM parkir " + "WHERE idKendaraan = '"
                    + parkir.getKendaraan().getIdKendaraan() + "' AND " + "idGarage = '"
                    + parkir.getGarage().getIdGarage() + "' AND " + "timeStart = '" + parkir.getTimeStart() + "'";
            rs = state.executeQuery(query);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return false;
    }

    private int getIdParkir(Parkir p) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        int idParkir = 0;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();
            String queryIdParkir = "SELECT idParkir FROM parkir " + "WHERE idKendaraan = "
                    + p.getKendaraan().getIdKendaraan() + " AND " + "idGarage = " + p.getGarage().getIdGarage()
                    + " AND timeStart='" + p.getTimeStart().format(formatter) + "'";
            rs = state.executeQuery(queryIdParkir);
            while (rs.next()) {
                idParkir = rs.getInt("idParkir");
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return idParkir;
    }

    @Override
    public boolean save(Parkir u) {
        Connection con = null;
        Statement state = null;
        boolean result = false;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();
            String query = "INSERT INTO parkir" + "(idKendaraan,idGarage,timeStart,timeStop,durasi,totalTransaksi)"
                    + "VALUES" + "(" + u.getKendaraan().getIdKendaraan() + "," + u.getGarage().getIdGarage() + ","
                    + "'" + u.getTimeStart().format(formatter) + "'," + "'" + u.getTimeStop().format(formatter) + "',"
                    + u.getDurasi() + "," + u.getTotalTransaksi() + ");";
            state.execute(query);
            int hasil = state.getUpdateCount();
            if (hasil == 1) {
                result = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return result;
    }

    @Override
    public boolean edit(Parkir u) {
        Connection con = null;
        Statement state = null;
        boolean result = false;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();

            String query = "CALL updateParkir " + getIdParkir(u) + ", " + u.getKendaraan().getIdKendaraan() + ", "
                    + u.getGarage().getIdGarage() + "'" + u.getTimeStop() + "'";
            result = state.execute(query);

        } catch (Exception e) {

        } finally {
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return result;
    }

    @Override
    public boolean delete(Parkir u) {
        Connection con = null;
        Statement state = null;
        boolean result = false;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();

            String query = "DELETE FROM parkir WHERE idParkir = " + getIdParkir(u);
            result = state.execute(query);

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return result;
    }

    @Override
    public void view(Parkir u) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        String tipeKendaraan = u.getKendaraan().getTipeKendaraan();
        try {
            con = Database.getKoneksi();
            state = con.createStatement();
            int idParkir = getIdParkir(u);
            String query = "CALL viewParkir(" + idParkir + ")";
            rs = state.executeQuery(query);
            System.out.println("=".repeat(40));
            System.out.println(Coloring.ANSI_BG_BLUE + Coloring.ANSI_WHITE + "PARKIR SUMMARY" + Coloring.ANSI_RESET);
            System.out.println("=".repeat(40));
            while(rs.next()) {
                LocalDateTime startFrom = LocalDateTime.from(LocalDateTime.parse(rs.getString("timeStart"),formatter));
                long minutes = startFrom.until(LocalDateTime.parse(rs.getString("timeStop"),formatter), ChronoUnit.MINUTES);
                long menit = minutes%60;
                long jam = startFrom.until(LocalDateTime.parse(rs.getString("timeStop"),formatter), ChronoUnit.HOURS);
                System.out.println("PLAT NOMOR\t"+rs.getString("nomorKendaraan"));
                System.out.println("TIPE KENDARAAN\t"+rs.getString("tipeKendaraan"));
                System.out.println("PEMILIK\t\t"+rs.getString("nama"));
                System.out.println("SUBSCRIPTION\t"+rs.getString("subscription"));
                System.out.println("GARAGE\t\t"+rs.getString("namaGarage"));
                if (tipeKendaraan.equals("Mobil")) {
                    System.out.println("TARIF MOBIL\t"+"Rp"+nf.format(rs.getDouble("tarifMobil"))) ;
                } else if(tipeKendaraan.equals("Motor")) {
                    System.out.println("TARIF MOTOR\t"+"Rp"+nf.format(rs.getDouble("tarifMotor")));
                } 
                System.out.println("AREA\t\t"+rs.getString("namaArea"));
                System.out.println("WAKTU MULAI\t"+rs.getString("timeStart"));
                System.out.println("WAKTU SELESAI\t"+rs.getString("timeStop"));
                System.out.println("DURASI\t\t"+ jam + " JAM " + menit + " MENIT");
                System.out.println("TOTAL\t\t"+"Rp"+nf.format(u.getTotalTransaksi()));
            }
            System.out.println("=".repeat(40));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
    }

    public int getCountMonthly(LocalDate now, Pengguna pengguna) {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        int jumlah = 0;
        try {
            con = Database.getKoneksi();
            state = con.createStatement();
            String queryIdParkir = "SELECT COUNT(*) as JUMLAH FROM parkir a "
                    + "LEFT JOIN kendaraan b on a.idKendaraan = b.idKendaraan "
                    + "LEFT JOIN pengguna c on b.idPengguna = c.idPengguna " + "WHERE c.idPengguna = "
                    + pengguna.getIdPengguna() + " and "
                    + "CONCAT(YEAR(a.timeStart),MONTH(a.timeStart))=CONCAT(YEAR(NOW()),MONTH(NOW())) " + "GROUP BY "
                    + "c.idPengguna, " + "CONCAT(YEAR(a.timeStart),MONTH(a.timeStart)) ";
            rs = state.executeQuery(queryIdParkir);

            rs = state.executeQuery(queryIdParkir);
            while (rs.next()) {
                jumlah = rs.getInt("JUMLAH");
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return jumlah;
    }
}