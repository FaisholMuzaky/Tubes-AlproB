package Controller;

public class Garage {
    private String namaGarage;
    // private int kapasitasMotor;
    // private int kapasitasMobil;
    private int tarif;
    private int hasilOperasi;
    private int jamBuka;
    private boolean status;
    private int jamTutup;  

    public String getNamaGarage() {
        return this.namaGarage;
    }

    public void setNamaGarage(String namaGarage) {
        this.namaGarage = namaGarage;
    }

    public int getTarif() {
        return this.tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public int getHasilOperasi() {
        return this.hasilOperasi;
    }

    public void setHasilOperasi(int hasilOperasi) {
        this.hasilOperasi = hasilOperasi;
    }

    public int getJamBuka() {
        return this.jamBuka;
    }

    public void setJamBuka(int jamBuka) {
        this.jamBuka = jamBuka;
    }

    public boolean isStatus() {
        return this.status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getJamTutup() {
        return this.jamTutup;
    }

    public void setJamTutup(int jamTutup) {
        this.jamTutup = jamTutup;
    }
}
