package Controller;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.modelHistory;

public class History implements Laporan {
    private ArrayList<Parkir> parkirs;
    private Pengguna pengguna;
    private modelHistory model = new modelHistory();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public History() {
    }

    public History(ArrayList<Parkir> parkirs, Pengguna pengguna) {
        this.parkirs = parkirs;
        this.pengguna = pengguna;
    }

    public ArrayList<Parkir> getParkirs() {
        return this.parkirs;
    }

    public void setParkirs(ArrayList<Parkir> parkirs) {
        this.parkirs = parkirs;
    }

    public Pengguna getPengguna() {
        return this.pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public History parkirs(ArrayList<Parkir> parkirs) {
        this.parkirs = parkirs;
        return this;
    }

    public void pengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
        this.setHistory(pengguna);
    }

    private void setHistory(Pengguna pengguna) {
        model = new modelHistory();
        this.setParkirs(model.getHistory(pengguna.getIdPengguna()).getParkirs());
        this.setPengguna(model.getHistory(pengguna.getIdPengguna()).getPengguna());
    }

	public void showParkirs() {
        Table st = new Table();
        st.setShowVerticalLines(true);
        st.setHeaders("AREA", "GARAGE", "NOMOR KENDARAAN", "TIPE KENDARAAN", "WAKTU MULAI", "WAKTU SELESAI", "DURASI (JAM)", "TOTAL (Rp)");
        for (Parkir parkir : this.parkirs) {
            st.addRow(parkir.getArea().getNamaArea(),
                    parkir.getGarage().getNamaGarage(),
                    parkir.getKendaraan().getPlatNomor(),
                    parkir.getKendaraan().getTipeKendaraan(),
                    parkir.getTimeStart().format(formatter).toString(),
                    parkir.getTimeStop().format(formatter).toString(),
                    Integer.toString(parkir.getDurasi()),
                    Double.toString(parkir.getTotalTransaksi())
                      );
        }
        st.print();
    }
    
    private void showLaporanHarian() {
        Table st = new Table();
        try {
            ResultSet rs = model.getLaporan(0);
            st.setShowVerticalLines(true);
            st.setHeaders("TANGGAL","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI (JAM)", "TOTAL (Rp)");
            while (rs.next()) {
                st.addRow(rs.getDate("tanggal").toString(),
                        rs.getString("nama"),
                        rs.getString("namaArea"),
                        rs.getString("namaGarage"),
                        Double.toString(rs.getDouble("durasi")),
                        Integer.toString(rs.getInt("totalTransaksi"))
                    );
            }
            st.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showLaporanMingguan() {
        Table st = new Table();
        try {
            ResultSet rsM = model.getLaporan(1);
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN","MINGGU","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI (JAM)", "TOTAL (Rp)");
            while (rsM.next()) {
                st.addRow(Integer.toString(rsM.getInt("tahun")),
                        Integer.toString(rsM.getInt("minggu")),
                        rsM.getString("nama"),
                        rsM.getString("namaArea"),
                        rsM.getString("namaGarage"),
                        Double.toString(rsM.getDouble("durasi")),
                        Integer.toString(rsM.getInt("totalTransaksi"))
                    );
            }
            st.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showLaporanBulanan() {
        Table st = new Table();
        try {
            ResultSet rsB = model.getLaporan(2);
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN","BULAN","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI (JAM)", "TOTAL (Rp)");
            while (rsB.next()) {
                st.addRow(Integer.toString(rsB.getInt("tahun")),
                        Integer.toString(rsB.getInt("bulan")),
                        rsB.getString("nama"),
                        rsB.getString("namaArea"),
                        rsB.getString("namaGarage"),
                        Double.toString(rsB.getDouble("durasi")),
                        Integer.toString(rsB.getInt("totalTransaksi"))
                    );
            }
            st.print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showLaporan(int i) {
        switch (i) {
            case 0:
                showLaporanHarian();
                break;
            case 1:
                showLaporanMingguan();
                break;
            case 2:
                showLaporanBulanan();
                break;
        }
    }
}
