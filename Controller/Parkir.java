package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        LocalDateTime startFrom = LocalDateTime.from(this.timeStart);
        long minutes = startFrom.until(this.timeStop, ChronoUnit.MINUTES);
        this.durasi = convertToHour(minutes);
    }

    public int getDurasi() {
        return this.durasi;
    }

    public void startParking(LocalDateTime start) {
        this.timeStart = start;
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
        int tarifGarage = 0;
        if (this.kendaraan.getTipeKendaraan()=="Motor") {
            tarifGarage = this.getGarage().getTarifMobil();
        } else if ((this.kendaraan.getTipeKendaraan()=="Mobil")) {
            tarifGarage = this.getGarage().getTarifMotor();
        }
        // String tipeKendaraan = kendaraan.getTipeKendaraan();
        String subs = this.pengguna.getSubscription();
        switch (subs) {
            case "easy":
                this.totalTransaksi = this.durasi * tarifGarage + 2000;
                break;
            case "plus":
                if(checkThisMonth())
                    this.totalTransaksi = this.durasi * tarifGarage + 12000;
                else
                    this.totalTransaksi = this.durasi * tarifGarage;
                break;
        }
    }

    private int convertToHour(long durasiMinutes) {
        double durasiJam = Math.ceil(durasiMinutes/60);
        if (durasiJam<1.0) {
            durasiJam += 1;
        }
        return (int)durasiJam;
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
