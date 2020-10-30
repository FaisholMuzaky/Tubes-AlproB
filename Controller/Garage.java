package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.modelGarage;
import View.View;

public class Garage {
    private String namaGarage;
    private int tarif;
    private int hasilOperasi;
    private int jamBuka;
    private int jamTutup;  

    Scanner input = new Scanner(System.in);

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
            if(data != null){
                int i = 0;
                while(data.next()){
                    System.out.println(i + 1 + ". Nama Garasi  : " + data.getString("namaGarage"));
                    System.out.println(" ".repeat(3) + "Tarif Parkir  : " + data.getString("tarif"));
                    System.out.println();
                    i++;
                }
            } else{
                System.out.println("Data garasi tidak ada");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int garage(){
        int idGarage = 0;
        int number = 20;
        String judul = " Garage ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Garasi" + " ".repeat(5) + ": ");
        String namaGarage = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

        if(!namaGarage.isEmpty()){
            try {
                ResultSet data = modelGarage.searchGarageforUpdate(namaGarage);
                if(data.next()){
                    System.out.println("Nama Garasi Tersedia");
                    idGarage = data.getInt("IdGarage");
                } else {
                    System.out.println("nama garasi tidak tersedia");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("nama area tidak boleh kosong");
        }
        return idGarage;
    }

    public int deleteGarage(){
        int idGarage = 0;
        int number = 20;
        String judul = " Delete Garage ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        System.out.print("Nama Garasi" + " ".repeat(5) + ": ");
        String namaGarage = input.next();
        System.out.println("=".repeat((number * 2) + judul.length()));

        if(!namaGarage.isEmpty()){
            try {
                ResultSet data = modelGarage.searchGarageforUpdate(namaGarage);
                if(data.next()){
                    System.out.println("Nama Garasi Tersedia");
                    idGarage = data.getInt("IdGarage");
                    int status = modelGarage.deleteDataGaragae(idGarage, namaGarage);
                    if (status == 1){
                        System.out.println("Delete Data Garasi Berhasil");
                        View.pressAnyKey();
                    } else {
                        System.out.println(" Delete Data Gagal");
                        View.pressAnyKey();
                    }
                } else {
                    System.out.println("nama garasi tidak tersedia");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("nama area tidak boleh kosong");
        }
        return idGarage;
    }

    public void editGarage(int IdGarage){
        int number = 20;
        String judul = " Edit Garasi ";
        System.out.println("=".repeat(number) + judul + "=".repeat(number));
        Scanner sc = new Scanner(System.in);
        System.out.print("Nama Garasi : ");
        String namaGarage = sc.next();
        System.out.print("Tarif : ");
        int tarif = sc.nextInt();
        System.out.print("Jumlah Hari Operasi : ");
        int hariOperasi = sc.nextInt();
        System.out.print("Jam Buka : ");
        int jamBuka = sc.nextInt();
        System.out.print("Jam Tutup : ");
        int jamTutup = sc.nextInt();
        int status = modelGarage.updateDataGarage(IdGarage, namaGarage, tarif, hariOperasi, jamBuka, jamTutup);
        if (status == 1){
            System.out.println("Update Data Garasi Berhasil");
            View.pressAnyKey();
        } else { 
            System.out.println(" Update Data gagal");
            View.pressAnyKey();
        }
    }
}
