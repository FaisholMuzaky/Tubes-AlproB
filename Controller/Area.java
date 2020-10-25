package Controller;

import java.sql.ResultSet;
import java.util.Scanner;

import Model.modelArea;

public class Area {
    private String namaArea;
    private Garage[] garages;

    Scanner input = new Scanner(System.in);

    public Area() {
    }

    public Area(String namaArea, Garage[] garages) {
        this.namaArea = namaArea;
        this.garages = garages;
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

    public void addArea(){
        System.out.println("Nama Area : ");
        namaArea = input.next();
        if(!namaArea.isEmpty()){
            if(isValidnamaArea(namaArea)){
                modelArea.insertArea(namaArea);
            }
            else{
                System.out.println("nama area sudah digunakan");
            }
        } else{
            System.out.println("nama area tidak boleh kosong");
        }
    }

    public boolean isValidnamaArea(String namaArea){
        boolean valid = false;
        try {
            boolean status = modelArea.searchArea(namaArea).next();
            if(!status){
                valid = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return valid;
    }

    public void viewArea(String namaArea){
        try {
            ResultSet data = modelArea.searchArea(namaArea);
            if(data != null){
                while(data.next()){
                    System.out.println("Nama Areea: " + data.getString("namaArea"));
                }
            } else{
                System.out.println("Data tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
