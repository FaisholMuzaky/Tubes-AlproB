package Controller;

public class Laporan {
    private String namaPengguna;
    private String namaArea;
    private String namaGarage;
    private long durasi;
    private double totalTransaksi;

    public String getNamaPengguna() {
        return this.namaPengguna;
    }

    protected void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getNamaArea() {
        return this.namaArea;
    }

    protected void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public String getNamaGarage() {
        return this.namaGarage;
    }

    protected void setNamaGarage(String namaGarage) {
        this.namaGarage = namaGarage;
    }

    public long getDurasi() {
        return this.durasi;
    }

    protected void setDurasi(long durasi) {
        this.durasi = durasi;
    }

    public double getTotalTransaksi() {
        return this.totalTransaksi;
    }

    protected void setTotalTransaksi(double totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }
}
