package Controller;

public class LaporanMingguan extends Laporan {
    private int tahun;
    private int minggu;

    public int getTahun() {
        return this.tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public int getMinggu() {
        return this.minggu;
    }

    public void setMinggu(int minggu) {
        this.minggu = minggu;
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
