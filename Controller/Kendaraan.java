package Controller;

public class Kendaraan {
    private String platNomor;
    private String tipeKendaraan;
    private Pengguna pemilik;

    public String getPlatNomor() {
        return this.platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getTipeKendaraan() {
        return this.tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
    }


    public Pengguna getPemilik() {
        return this.pemilik;
    }

    public void setPemilik(Pengguna pemilik) {
        this.pemilik = pemilik;
    }


}