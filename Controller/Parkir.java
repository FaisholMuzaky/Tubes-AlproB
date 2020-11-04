package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import Model.modelParkir;

public class Parkir {
    private Kendaraan kendaraan;
    private Pengguna pengguna;
    private Area area;
    private Garage garage;
    private LocalDateTime timeStart;
    private LocalDateTime timeStop;
    private LocalDate tanggal;
    private double totalTransaksi;
    private int durasi;
    private modelParkir m;


    public Parkir() {
        m = new modelParkir();
    }


    public Pengguna getPengguna() {
        return this.pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }


    public LocalDate getTanggal() {
        return this.tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }


    public Kendaraan getKendaraan() {
        return this.kendaraan;
    }

    public void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }

    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Garage getGarage() {
        return this.garage;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }


    public LocalDateTime getTimeStart() {
        return this.timeStart;
    }

    public LocalDateTime getTimeStop() {
        return this.timeStop;
    }

    private void setDurasi() {
        this.durasi = this.timeStop.getMinute() - this.timeStart.getMinute();
    }

    public int getDurasi() {
        return this.durasi;
    }

    public void startParking(LocalDateTime start) {
        timeStart = start;
    }

    public void stopParking(LocalDateTime stop) {
        timeStop = stop;
        setDurasi();
        setTotalTransaksi();
    }

    public double getTotalTransaksi() {
        return this.totalTransaksi;
    }

    private void setTotalTransaksi() {
        double durasiJam = Math.ceil(this.durasi/60);
        if (durasiJam<1.0) {
            durasiJam += 1;
        }
        int tarifGarage = garage.getTarif();
        // String tipeKendaraan = kendaraan.getTipeKendaraan();
        String subs = this.pengguna.getSubscription();
        switch (subs) {
            case "easy":
                this.totalTransaksi = durasiJam * tarifGarage + 2000;
                break;
            case "plus":
                if(checkThisMonth())
                    this.totalTransaksi = durasiJam * tarifGarage + 12000;
                else
                    this.totalTransaksi = durasiJam * tarifGarage;
                break;
        }
    }

    private boolean checkThisMonth() {
        if(m.getCountMonthly(LocalDate.now(), this.pengguna)>0)
            return false;
        else
            return true;
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
        try {
            this.m.view(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isParking() {
        if (this.m.isParking(this))
            return true;
        return false;
    }

}
