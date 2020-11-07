package Controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.modelHistory;

public class History {
    private ArrayList<Parkir> parkirs;
    private Pengguna pengguna;
    private modelHistory model;

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
        for (Parkir parkir : this.parkirs) {
            System.out.println(
                parkir.getArea().getNamaArea()+"\t"+
                parkir.getGarage().getNamaGarage()+"\t"+
                parkir.getKendaraan().getPlatNomor()+"\t"+
                parkir.getKendaraan().getTipeKendaraan()+"\t"+
                parkir.getTimeStart()+"\t"+
                parkir.getTimeStop()+"\t"+
                parkir.getDurasi()+"\t"+
                parkir.getTotalTransaksi()
            );
        }
	}
}
