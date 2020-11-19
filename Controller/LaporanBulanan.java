package Controller;

public class LaporanBulanan extends Laporan {
    private int tahun;
    private int bulan;
    
    public int getTahun() {
        return this.tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getBulan() {
        return this.bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
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
