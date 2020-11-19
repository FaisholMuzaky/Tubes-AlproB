package Controller;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import View.Table;
import Model.modelHistory;
import java.text.NumberFormat;

public class History implements Laporan {
    private ArrayList<Parkir> parkirs;
    private Pengguna pengguna;
    private modelHistory model = new modelHistory();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private long totalDurasi;
    private double totalTransaksi;
    private NumberFormat nf = NumberFormat.getInstance(new Locale("id", "ID"));

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
        int idPengguna = pengguna.getIdPengguna();
        this.setParkirs(model.getHistory(idPengguna).getParkirs());
        this.setPengguna(model.getHistory(idPengguna).getPengguna());
    }

	public void showParkirs() {
        Table st = new Table();
        st.setShowVerticalLines(true);
        st.setHeaders("AREA", "GARAGE", "NOMOR KENDARAAN", "TIPE KENDARAAN", "WAKTU MULAI", "WAKTU SELESAI", "DURASI", "TOTAL");
        if(this.parkirs!=null) {
            for (Parkir parkir : this.parkirs) {
                st.addRow(parkir.getArea().getNamaArea(),
                        parkir.getGarage().getNamaGarage(),
                        parkir.getKendaraan().getPlatNomor(),
                        parkir.getKendaraan().getTipeKendaraan(),
                        parkir.getTimeStart().format(formatter).toString(),
                        parkir.getTimeStop().format(formatter).toString(),
                        parkir.getStringDurasi(),
                        Double.toString(parkir.getTotalTransaksi())
                          );
            }
        }
        st.print();
    }
    
    private void showLaporanHarian() {
        Table st = new Table();
        try {
            ResultSet rs = model.getLaporan(0);
            st.setShowVerticalLines(true);
            st.setHeaders("TANGGAL","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            while (rs.next()) {
                st.addRow(rs.getDate("tanggal").toString(),
                        rs.getString("nama"),
                        rs.getString("namaArea"),
                        rs.getString("namaGarage"),
                        Parkir.konversiDurasi(rs.getLong("durasi")),
                        "Rp"+nf.format(rs.getDouble("totalTransaksi"))
                        // Integer.toString(rs.getInt("totalTransaksi"))
                    );
                durasi += rs.getInt("durasi");
                totalTransaksi += rs.getDouble("totalTransaksi");
            }
            st.addTotal("","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            st.printWithTotal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showLaporanMingguan() {
        Table st = new Table();
        try {
            ResultSet rsM = model.getLaporan(1);
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN","MINGGU","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            while (rsM.next()) {
                st.addRow(Integer.toString(rsM.getInt("tahun")),
                        Integer.toString(rsM.getInt("minggu")),
                        rsM.getString("nama"),
                        rsM.getString("namaArea"),
                        rsM.getString("namaGarage"),
                        Parkir.konversiDurasi(rsM.getLong("durasi")),
                        "Rp"+nf.format(rsM.getDouble("totalTransaksi"))
                    );
                durasi += rsM.getInt("durasi");
                totalTransaksi += rsM.getDouble("totalTransaksi");
            }
            st.addTotal("","","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            st.printWithTotal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showLaporanBulanan() {
        Table st = new Table();
        try {
            ResultSet rsB = model.getLaporan(2);
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN","BULAN","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            while (rsB.next()) {
                st.addRow(Integer.toString(rsB.getInt("tahun")),
                        Integer.toString(rsB.getInt("bulan")),
                        rsB.getString("nama"),
                        rsB.getString("namaArea"),
                        rsB.getString("namaGarage"),
                        Parkir.konversiDurasi(rsB.getLong("durasi")),
                        "Rp"+nf.format(rsB.getDouble("totalTransaksi"))
                    );
                durasi += rsB.getInt("durasi");
                totalTransaksi += rsB.getDouble("totalTransaksi");
            }
            st.addTotal("","","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            st.printWithTotal();
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

    public long getTotalDurasi() {
        return this.totalDurasi;
    }

    public void setTotalDurasi(long totalDurasi) {
        this.totalDurasi = totalDurasi;
    }

    public double getTotalTransaksi() {
        return this.totalTransaksi;
    }

    public void setTotalTransaksi(double totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

}
