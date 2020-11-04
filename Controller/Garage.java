package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.modelGarage;

public class Garage {
    private String namaGarage;
    private int tarif;
    private int hariOperasi;
    private int jamBuka;
    private int jamTutup;
    private modelGarage g;
    private int idGarage;

    public Garage() {
        this.g = new modelGarage();
    }

    public Garage(String namaGarage, int tarif, int hariOperasi, int jamBuka, int jamTutup) {
        this.namaGarage = namaGarage;
        this.tarif = tarif;
        this.hariOperasi = hariOperasi;
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

    public int getHariOperasi() {
        return this.hariOperasi;
    }

    public void setHasilOperasi(int hariOperasi) {
        this.hariOperasi = hariOperasi;
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

    public void addGarage(int IdArea, Garage garage[]) {
        g.insertGarage(IdArea, garage);
    }

    public void viewListGarage(int idArea) {
        try {
            ResultSet data = g.searchGarage(idArea);
            if (data != null && data.isBeforeFirst()) {
                int i = 0;
                while (data.next()) {
                    System.out.println(i + 1 + ". Nama Garasi             : " + data.getString("namaGarage"));
                    System.out.println(" ".repeat(3) + "Tarif                   : Rp. " + data.getInt("tarif"));
                    System.out.println(" ".repeat(3) + "Jumlah Hari Operasional : " + data.getInt("hariOperasi"));
                    System.out.println(" ".repeat(3) + "Jam Buka                : " + data.getInt("jamBuka"));
                    System.out.println(" ".repeat(3) + "Jam Tutup               : " + data.getInt("jamTutup"));
                    i++;
                }
            } else {
                System.out.println("Tidak ada garasi yang terdaftar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int countGarageArea(int IdArea) {
        int jum = 0;
        try {
            ResultSet data = g.countGarages(IdArea);
            if (data.next()) {
                jum = data.getInt("COUNT(*)");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return jum;
    }

    public void listAllGarage() {
        try {
            ResultSet data = g.listAllGarage();
            if (data != null && data.isBeforeFirst()) {
                int i = 0;
                while (data.next()) {
                    System.out.println(i + 1 + ". Nama Garasi  : " + data.getString("namaGarage"));
                    i++;
                }
            } else {
                System.out.println("Tidak ada garasi yang terdaftar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdGarage(String namaGarage) {
        int idGarasi = 0;
        try {
            ResultSet data = g.searchGarage(namaGarage);
            if (data.next()) {
                idGarasi = data.getInt("idGarage");
            } else {
                System.out.println("Nama garasi tidak terdaftar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return idGarasi;
    }

    public void detailGarageByName(String namaGarage) {
        try {
            ResultSet data = g.searchGarage(namaGarage);
            if (data.next()) {
                ResultSet result = g.getNamaArea(data.getInt("idArea"));
                result.next();
                System.out.println("Nama Area               : " + result.getString("namaArea"));
                System.out.println("Nama Garasi             : " + data.getString("namaGarage"));
                System.out.println("Tarif                   : Rp. " + data.getInt("tarif"));
                System.out.println("Jumlah Hari Operasional : " + data.getInt("hariOperasi"));
                System.out.println("Jam Buka                : " + data.getInt("jamBuka"));
                System.out.println("Jam Tutup               : " + data.getInt("jamTutup"));
            } else {
                System.out.println("Data garasi tidak tersedia");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean cekNamaGarage(String namaGarage) {
        boolean valid = false;
        try {
            boolean status = g.searchGarage(namaGarage).next();
            if (status) {
                valid = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return valid;
    }

    public void editGarage(int idGarage, String namaGarasi, int tarif, int hariOperasi, int jamBuka, int jamTutup) {
        int status = g.updateGarage(idGarage, namaGarasi, tarif, hariOperasi, jamBuka, jamTutup);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");

        }
    }

    public int listGarage(int idArea) {
        int countGarage = 0;
        try {
            ResultSet data = g.searchGarage(idArea);
            if (data != null && data.isBeforeFirst()) {
                System.out.println("ID\tGARAGE");
                while (data.next()) {
                    System.out.println(data.getInt("idGarage") +"\t" + data.getString("namaGarage"));
                    countGarage++;
                }
            } else {
                System.out.println("Tidak ada garasi yang terdaftar");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return countGarage;
    }

	public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public int getIdGarage_() {
        return this.idGarage;
    }

    public String getNamaGarage_(int idGarage) {
        return g.getNamaGarage(idGarage);
    }

    public Garage getGarage(int idGarage) {
        return g.getGarage(idGarage);
    }
}
