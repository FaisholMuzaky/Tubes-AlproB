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
        this.durasi = (int)minutes;
    }

    public int getDurasi() {
        return this.durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void setTotalTransaksi(double total) {
        this.totalTransaksi = total;
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
        if (this.kendaraan.getTipeKendaraan().equals("Motor")) {
            tarifGarage = this.getGarage().getTarifMotor();
        } else if (this.kendaraan.getTipeKendaraan().equals("Mobil")) {
            tarifGarage = this.getGarage().getTarifMobil();
        }
        // int addition = (int) (this.durasi % 60);
        long jam = convertToHour(this.durasi);
        if(jam==0) {
            jam += 1;
        } 
        String subs = this.pengguna.getSubscription();
        switch (subs) {
            case "easy":
                this.totalTransaksi = jam * tarifGarage + 2000;
                break;
            case "plus":
                if(checkThisMonth())
                    this.totalTransaksi = jam * tarifGarage + 12000;
                else
                    this.totalTransaksi = jam * tarifGarage;
                break;
        }
    }

    public static int convertToHour(long durasiMinutes) {
        double hours = Math.floor(durasiMinutes / 60);
        long minutes = durasiMinutes % 60;
        if (minutes > 0) {
            hours += 1;
        }
        return (int) hours;
    }

    public String getStringDurasi() {
        String durasi = null;
        int jam = convertToHour(this.durasi);
        long minute = this.durasi % 60;
        durasi = jam + " JAM " + minute + " MENIT";
        return durasi;
    }

    public static String konversiDurasi(long minutes) {
        String durasi = null;
        long jam = convertToHour(minutes);
        long minute = minutes%60;
        durasi = jam + " JAM " + minute + " MENIT";
        return durasi;
    }

    private boolean checkThisMonth() {
        int t = m.getCountMonthly(LocalDate.now(), this.pengguna);
        if(t>0)
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

	public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }
    public void setTimeStop(LocalDateTime timeStop) {
        this.timeStop = timeStop;
    }

}
