package Controller;

public class Transaksi {
    private Parkir[] parkirs;
    private Pengguna admin;


    public Parkir[] getParkir() {
        return this.parkirs;
    }

    public void addParkir(Parkir parkir) {
        this.parkirs[this.parkirs.length] = parkir;
    }

    public Pengguna getAdmin() {
        return this.admin;
    }

    public void setAdmin(Pengguna admin) {
        this.admin = admin;
    }
}
