package Controller;

import java.sql.ResultSet;

import Model.modelGarage;
import View.View;

public class Garage {
    private String namaGarage;
    private int tarif;
    private int hasilOperasi;
    private int jamBuka;
    private int jamTutup;  

    public Garage(){

    }

    public Garage(String namaGarage, int tarif, int hasilOperasi, int jamBuka, int jamTutup) {
        this.namaGarage = namaGarage;
        this.tarif = tarif;
        this.hasilOperasi = hasilOperasi;
        this.jamBuka = jamBuka;
        this.jamTutup = jamTutup;
    }

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

    public int getJamTutup() {
        return this.jamTutup;
    }

    public void setJamTutup(int jamTutup) {
        this.jamTutup = jamTutup;
    }

    public void addGarage(int IdArea, String namaGarage, int tarif, int hariOperasi, int jamBuka, int jamTutup){
        modelGarage.insertGarage(IdArea, namaGarage, tarif, hariOperasi, jamBuka, jamTutup);
        View.pressAnyKey();
    }

	public void viewListGarage(int idArea) {
        try {
            ResultSet data = modelGarage.searchGarage(idArea);
            boolean status = data.next();
            int row = data.getRow();
            if(data != null && status){
                int i = 0;
                while(i<=row){
                    System.out.println(i + 1 + ". Nama Garasi  : " + data.getString("namaGarage"));
                    data.next();
                    i++;
                }
            } else{
                System.out.println("Data garasi tidak ada");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
	}
}
