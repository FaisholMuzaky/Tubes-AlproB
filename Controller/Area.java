package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import View.View;

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

    public int area(){
        int idArea = 0;
        int number = 20;
        String judul = " Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Area" + " ".repeat(5) + ": ");
        String namaArea = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

        if(!namaArea.isEmpty()){
            try {
                ResultSet data = modelArea.garage(namaArea);
                if(data.next()){
                    System.out.println("Nama Area Tersedia");
                    idArea = data.getInt("IdArea");
                } else {
                    System.out.println("nama area tidak tersedia");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("nama area tidak boleh kosong");
        }
        return idArea;
    }

    public void addArea(){
        int number = 20;
        String judul = " Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Area" + " ".repeat(5) + ": ");
        String namaArea = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

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

    public void viewListArea() {
        try {
            ResultSet data = modelArea.searchIdArea();
            if(data != null){
                int i = 0;
                while(data.next()){
                    System.out.println(i + 1 + ". Nama Area  : " + data.getString("namaArea"));
                    System.out.println();
                    i++;
                }
            } else{
                System.out.println("Data area tidak ada");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editArea(int IdArea){
        int number = 20;
        String judul = " Edit Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        Scanner sc = new Scanner(System.in);
        System.out.print("Nama Area : ");
        String namaArea = sc.next();
        int status = modelArea.updateDataArea(IdArea, namaArea);
        if (status == 1){
            System.out.println("Update Data Area Berhasil");
            View.pressAnyKey();
        } else { 
            System.out.println("Update Data gagal");
            View.pressAnyKey();
        }
    }

    public int deleteArea(){
        int IdArea = 0;
        int number = 20;
        String judul = " Delete Area ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Area" + " ".repeat(5) + ": ");
        String namaArea = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

        if(!namaArea.isEmpty()){
            try {
                ResultSet data = modelArea.garage(namaArea);
                if(data.next()){
                    System.out.println("Nama Area Tersedia");
                    IdArea = data.getInt("IdArea");
                    int status = modelArea.deleteDataArea(IdArea, namaArea);
                    if (status == 1){
                        System.out.println("Delete Data Area Berhasil");
                        View.pressAnyKey();
                    } else {
                        System.out.println(" Delete Data Gagal");
                        View.pressAnyKey();
                    }
                } else {
                    System.out.println("nama area tidak tersedia");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("nama area tidak boleh kosong");
        }
        return IdArea;
    }
}
