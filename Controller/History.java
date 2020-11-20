// Nama: Walim Abdul Somad
// NIM : 23520026 

package Controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import View.Coloring;
import View.Table;
import Model.modelHistory;
import java.text.NumberFormat;

public class History implements ILaporan {
    private ArrayList<Parkir> parkirs;
    private Pengguna pengguna;
    private modelHistory model;
    private DateTimeFormatter formatter;
    private long totalDurasi;
    private double totalTransaksi;
    private NumberFormat nf;
    ArrayList<LaporanHarian> lh;
    ArrayList<LaporanMingguan> lm;
    ArrayList<LaporanBulanan> lb;

    public History() {
        model = new modelHistory();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        nf = NumberFormat.getInstance(new Locale("id", "ID"));
        lh = new ArrayList<LaporanHarian>();
        lm = new ArrayList<LaporanMingguan>();
        lb = new ArrayList<LaporanBulanan>();
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
                        "Rp"+nf.format(parkir.getTotalTransaksi())
                          );
            }
        }
        st.print();
    }
    
    @Override
    public void showLaporanHarian() {
        Table st = new Table();
        try {
            lh = model.getLaporanHarian();
            st.setShowVerticalLines(true);
            st.setHeaders("TANGGAL","NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            for (LaporanHarian laporanHarian : lh) {
                st.addRow(laporanHarian.getTanggal().toString(),
                        laporanHarian.getNamaPengguna(),
                        laporanHarian.getNamaArea(),
                        laporanHarian.getNamaGarage(),
                        Parkir.konversiDurasi(laporanHarian.getDurasi()),
                        "Rp"+nf.format(laporanHarian.getTotalTransaksi())
                    );
                durasi += laporanHarian.getDurasi();
                totalTransaksi += laporanHarian.getTotalTransaksi();
            }
            if (durasi>0 || totalTransaksi>0) {
                st.addTotal("","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            } else { 
                st.addTotal("","","","","","");
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Tidak ada transaksi parkir hari ini" + Coloring.ANSI_RESET);
            }
            st.printWithTotal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showLaporanMingguan() {
        Table st = new Table();
        try {
            lm = model.getLaporanMingguan();
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN", "MINGGU", "NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            for (LaporanMingguan laporanMingguan : lm) {
                st.addRow(
                        Integer.toString(laporanMingguan.getTahun()),
                        Integer.toString(laporanMingguan.getMinggu()),
                        laporanMingguan.getNamaPengguna(),
                        laporanMingguan.getNamaArea(),
                        laporanMingguan.getNamaGarage(),
                        Parkir.konversiDurasi(laporanMingguan.getDurasi()),
                        "Rp"+nf.format(laporanMingguan.getTotalTransaksi())
                    );
                durasi += laporanMingguan.getDurasi();
                totalTransaksi += laporanMingguan.getTotalTransaksi();
            }
            st.addTotal("","","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            st.printWithTotal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showLaporanBulanan() {
        Table st = new Table();
        try {
            lb = model.getLaporanBulanan();
            st.setShowVerticalLines(true);
            st.setHeaders("TAHUN", "BULAN", "NAMA PENGGUNA", "AREA", "GARAGE", "DURASI", "TOTAL");
            int durasi = 0;
            double totalTransaksi = 0;
            for (LaporanBulanan laporanBulanan : lb) {
                st.addRow(
                        Integer.toString(laporanBulanan.getTahun()),
                        Integer.toString(laporanBulanan.getBulan()),
                        laporanBulanan.getNamaPengguna(),
                        laporanBulanan.getNamaArea(),
                        laporanBulanan.getNamaGarage(),
                        Parkir.konversiDurasi(laporanBulanan.getDurasi()),
                        "Rp"+nf.format(laporanBulanan.getTotalTransaksi())
                    );
                durasi += laporanBulanan.getDurasi();
                totalTransaksi += laporanBulanan.getTotalTransaksi();
            }
            st.addTotal("","","","","",Parkir.konversiDurasi(durasi),"Rp"+nf.format(totalTransaksi));
            st.printWithTotal();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
