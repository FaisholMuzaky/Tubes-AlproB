package Controller;

import java.util.Date;

public class LaporanHarian extends Laporan {
    private Date tanggal;
    
    public LaporanHarian() {
    }


    public Date getTanggal() {
        return this.tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getNamaPengguna() {
        return super.getNamaPengguna();
    }

    public void setNamaPengguna(String namaPengguna) {
        super.setNamaPengguna(namaPengguna);
    }

    public String getNamaArea() {
        return super.getNamaArea();
    }

    public void setNamaArea(String namaArea) {
        super.setNamaArea(namaArea);
    }

    public String getNamaGarage() {
        return super.getNamaGarage();
    }

    public void setNamaGarage(String namaGarage) {
        super.setNamaGarage(namaGarage);
    }

    public long getDurasi() {
        return super.getDurasi();
    }

    public void setDurasi(int durasi) {
        super.setDurasi(durasi);
    }

    public double getTotalTransaksi() {
        return super.getTotalTransaksi();
    }

    public void setTotalTransaksi(double totalTransaksi) {
        super.setTotalTransaksi(totalTransaksi);
    }
}
