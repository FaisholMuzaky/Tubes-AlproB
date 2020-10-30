package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import Model.modelParkir;

public class Parkir {
    private int idKendaraan;
    private int idArea;
    private int idGarage;
    private LocalDateTime timeStart;
    private LocalDateTime timeStop;
    private LocalDate tanggal;
    private double totalTransaksi;
    private int durasi;
    private modelParkir m;


    public Parkir() {
        m = new modelParkir();
    }

    public LocalDate getTanggal() {
        return this.tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public int getIdArea() {
        return this.idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    
    public int getIdGarage() {
        return this.idGarage;
    }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }


    protected void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public int getIdKendaraan() {
        return this.idKendaraan;
    }

    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }

    public LocalDateTime getTimeStop() {
        return this.timeStop;
    }

    public int getDurasi() {
        return this.durasi;
    }

    protected void startParking(LocalDateTime start) {
        timeStart = start;
    }

    protected void stopParking(LocalDateTime stop) {
        timeStop = stop;
    }

    protected void hitungDurasi() {
        durasi = timeStop.compareTo(timeStart);
    }

    public double getTotalTransaksi() {
        return this.totalTransaksi;
    }

    public void setTotalTransaksi() {
        this.totalTransaksi = durasi * 2000;
    }

    public boolean addParkir() {
        if(m.save(this))
            return true;
        else
            return false;
    }

    public Parkir getParkir(int idParkir) {
        return this;
    }

    public void showParkir() {
        this.m.view(this);
    }

    public boolean isParking() {
        if (this.m.isParking(this))
            return true;
        return false;
    }

}
