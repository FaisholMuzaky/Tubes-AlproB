package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import Controller.*;
import Database.Database;

public class modelHistory {
    public modelHistory() {
    }


    public History getHistory(int idPengguna) {
        Connection con = null;
        Statement state = null;
        ResultSet rsPengguna = null;
        ResultSet rs = null;
        History history = null;
        try {
            history = new History();
            ArrayList<Parkir> parkirs = new ArrayList<>();
            String sql = "CALL getHistory("+idPengguna+")";
            con = Database.getKoneksi();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            long totalDurasi = 0;
            double totalTransaksi = 0;
            while(rs.next()) {
                Kendaraan kendaraan = new Kendaraan();
                kendaraan.setIdKendaraan(rs.getInt("idKendaraan"));
                kendaraan.setPlatNomor(rs.getString("nomorKendaraan"));
                kendaraan.setTipeKendaraan(rs.getString("tipeKendaraan"));
                Area area = new Area();
                area.setIdArea(rs.getInt("idArea"));
                area.setNamaArea(rs.getString("namaArea"));
                Garage garage = new Garage();
                garage.setIdGarage(rs.getInt("idGarage"));
                garage.setNamaGarage(rs.getString("namaGarage"));
                garage.setTarifMobil(rs.getInt("tarifMobil"));
                garage.setTarifMotor(rs.getInt("tarifMotor"));
                setHariOperasional(garage, rs.getInt("idGarage"));
                Pengguna pengguna = new Pengguna();
                pengguna.setIdPengguna(rs.getInt("idPengguna"));
                pengguna.setNama(rs.getString("nama"));
                pengguna.setSubscription(rs.getString("subscription"));
                Parkir parkir = new Parkir();
                parkir.setPengguna(pengguna);
                parkir.setArea(area);
                parkir.setGarage(garage);
                parkir.setKendaraan(kendaraan);
                parkir.setTimeStart(rs.getTimestamp("timeStart").toLocalDateTime());
                parkir.setTimeStop(rs.getTimestamp("timeStop").toLocalDateTime());
                parkir.setDurasi(rs.getInt("durasi"));
                parkir.setTotalTransaksi(rs.getDouble("totalTransaksi"));
                parkirs.add(parkir);
                totalDurasi += rs.getInt("durasi");
                totalTransaksi += rs.getDouble("totalTransaksi");
            }
            history.setParkirs(parkirs);
            history.setTotalDurasi(totalDurasi);
            history.setTotalTransaksi(totalTransaksi);
            modelPengguna m = new modelPengguna();
            rsPengguna = m.searchByID(idPengguna);
            Pengguna p = new Pengguna();
            while(rsPengguna.next()) {
                p.setIdPengguna(rs.getInt("idPengguna"));
                p.setNama(rs.getString("nama"));
            }
            history.setPengguna(p);
        } catch (SQLException e) {
            // System.out.println(e.getMessage());
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (rsPengguna!=null) rsPengguna.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
		return history;
    }
    
    private void setHariOperasional(Garage garage, int idGarage) {
        Connection con = null;
        Statement state_count = null;
        Statement state_ = null;
        ResultSet rs_count = null;
        ResultSet rs_ = null;
        try {
            con = Database.getKoneksi();
            String sql_count = "CALL getCountHariOperasional(" + idGarage + ")";
            state_count = con.createStatement();
            rs_count = state_count.executeQuery(sql_count);
            rs_count.next();
            int count = rs_count.getInt("JUMLAH");
            if (count > 0) {
                String[] namaHari = new String[count];
                int[] jamBuka = new int[count];
                int[] jamTutup = new int[count];
                int i = 0;

                String sql_ = "CALL getHariOperasional(" + idGarage + ")";
                state_ = con.createStatement();
                rs_ = state_.executeQuery(sql_);
                while (rs_.next()) {
                    namaHari[i] = rs_.getString("namaHari");
                    jamBuka[i] = rs_.getInt("jamBuka");
                    jamTutup[i] = rs_.getInt("jamTutup");
                    i++;
                }
                garage.setNamaHari(namaHari);
                garage.setJamBuka(jamBuka);
                garage.setJamTutup(jamTutup);
            }
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        } finally {
            try { if (rs_count!=null) rs_count.close(); } catch (Exception e) { }
            try { if (state_count!=null) state_count.close(); } catch (Exception e) { }
            try { if (rs_!=null) rs_.close(); } catch (Exception e) { }
            try { if (state_!=null) state_.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
    }
    
    public ArrayList<LaporanHarian> getLaporanHarian() {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList<LaporanHarian> lh = new ArrayList<>();
        try {
            String sql = "CALL laporanTransaksi(0)";
            con = Database.getKoneksi();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()) {
                LaporanHarian l = new LaporanHarian();
                l.setTanggal(rs.getDate("tanggal"));
                l.setNamaPengguna(rs.getString("nama"));
                l.setNamaArea(rs.getString("namaArea"));
                l.setNamaGarage(rs.getString("namaGarage"));
                l.setDurasi(rs.getInt("durasi"));
                l.setTotalTransaksi(rs.getDouble("totalTransaksi"));
                lh.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return lh;
    }
    
    public ArrayList<LaporanMingguan> getLaporanMingguan() {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList<LaporanMingguan> lh = new ArrayList<>();
        try {
            String sql = "CALL laporanTransaksi(1)";
            con = Database.getKoneksi();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()) {
                LaporanMingguan l = new LaporanMingguan();
                l.setTahun(rs.getInt("tahun"));
                l.setMinggu(rs.getInt("minggu"));
                l.setNamaPengguna(rs.getString("nama"));
                l.setNamaArea(rs.getString("namaArea"));
                l.setNamaGarage(rs.getString("namaGarage"));
                l.setDurasi(rs.getInt("durasi"));
                l.setTotalTransaksi(rs.getDouble("totalTransaksi"));
                lh.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return lh;
    }
    
    public ArrayList<LaporanBulanan> getLaporanBulanan() {
        Connection con = null;
        Statement state = null;
        ResultSet rs = null;
        ArrayList<LaporanBulanan> lh = new ArrayList<>();
        try {
            String sql = "CALL laporanTransaksi(2)";
            con = Database.getKoneksi();
            state = con.createStatement();
            rs = state.executeQuery(sql);
            while(rs.next()) {
                LaporanBulanan l = new LaporanBulanan();
                l.setTahun(rs.getInt("tahun"));
                l.setBulan(rs.getInt("bulan"));
                l.setNamaPengguna(rs.getString("nama"));
                l.setNamaArea(rs.getString("namaArea"));
                l.setNamaGarage(rs.getString("namaGarage"));
                l.setDurasi(rs.getInt("durasi"));
                l.setTotalTransaksi(rs.getDouble("totalTransaksi"));
                lh.add(l);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try { if (rs!=null) rs.close(); } catch (Exception e) { }
            try { if (state!=null) state.close(); } catch (Exception e) { }
            try { if (con!=null) con.close(); } catch (Exception e) { }
        }
        return lh;
	}
}
