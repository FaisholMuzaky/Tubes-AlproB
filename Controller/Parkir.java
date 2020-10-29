package Controller;

import java.util.Date;

public class Parkir {
    private Pengguna admin;
    private Kendaraan kendaraan;
    private Area area;
    private Date timeStart;
    private Date timeStop;
    private double totalTransaksi;
    private int durasi;

    //test
    
    protected void setArea(Area area) {
        this.area = area;
    }

    protected void setKendaraan(Kendaraan kendaraan) {
        this.kendaraan = kendaraan;
    }


    public Pengguna getAdmin() {
        return this.admin;
    }

    public Kendaraan getKendaraan() {
        return this.kendaraan;
    }

    public Date getTimeStart() {
        return this.timeStart;
    }

    public Date getTimeStop() {
        return this.timeStop;
    }

    public int getDurasi() {
        return this.durasi;
    }

    protected void startParking(Date start) {
        timeStart = start;
    }

    protected void stopParking(Date stop) {
        timeStop = stop;
    }

    protected void hitungDurasi() {
        durasi = timeStop.compareTo(timeStart);
    }

    public Area getArea() {
        return this.area;
    }


    public double getTotalTransaksi() {
        return this.totalTransaksi;
    }

    public void setTotalTransaksi() {
        this.totalTransaksi = durasi * 2000;
    }

}
