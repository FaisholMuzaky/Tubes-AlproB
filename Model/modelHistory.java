package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import Controller.Area;
import Controller.Garage;
import Controller.History;
import Controller.Kendaraan;
import Controller.Parkir;
import Controller.Pengguna;
import Database.Database;

public class modelHistory {
    private ResultSet rs = null;
    private Connection con = Database.getKoneksi();
    private Statement state = null;

    public History getHistory(int idPengguna) {
        History history = null;
        try {
            history = new History();
            ArrayList<Parkir> parkirs = new ArrayList<>();
            String sql = "CALL getHistory("+idPengguna+")";
            state = con.createStatement();
            rs = state.executeQuery(sql);
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
                garage.setHasilOperasi(rs.getInt("hariOperasi"));
                garage.setTarifMobil(rs.getInt("tarifMobil"));
                garage.setTarifMotor(rs.getInt("tarifMotor"));
                garage.setJamBuka(rs.getInt("jamBuka"));
                garage.setJamTutup(rs.getInt("jamTutup"));
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
            }
            history.setParkirs(parkirs);
            modelPengguna m = new modelPengguna();
            ResultSet rsPengguna = m.searchByID(idPengguna);
            Pengguna p = new Pengguna();
            while(rsPengguna.next()) {
                p.setIdPengguna(rs.getInt("idPengguna"));
                p.setNama(rs.getString("nama"));
            }
            history.setPengguna(p);
        } catch (SQLException e) {
            //TODO: handle exception
        }
		return history;
	}

	public ResultSet getLaporan(int i) {
        try {
            String sql = "CALL laporanTransaksi("+i+")";
            state = con.createStatement();
            rs = state.executeQuery(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
	}
}
