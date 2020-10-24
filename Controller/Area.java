package Controller;

public class Area {
    private String namaArea;
    private Garage[] garages;
    private int countGarage;

    public Area(String namaArea, int countGarage) {
        this.namaArea = namaArea;
        this.countGarage = 0;
        this.garages = new Garage[countGarage];
    }

    public String getNamaArea() {
        return this.namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public Garage[] getGarages() {
        return this.garages;
    }

    public void setGarages(Garage[] garages) {
        this.garages = garages;
    }

    public int getCountGarage() {
        return this.countGarage;
    }

    public void setCountGarage(int countGarage) {
        this.countGarage = countGarage;
    }
}
