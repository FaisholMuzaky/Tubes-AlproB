package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Model.modelGarage;

public class Garage {
    private String namaGarage;
    private int tarifMobil;
    private int tarifMotor;
    private int hariOperasi;
    private int jamBuka;
    private int jamTutup;
    private modelGarage g;
    private int idGarage;
    private int idArea;

    public Garage() {
        this.g = new modelGarage();
    }

    public Garage(String namaGarage, int tarifMobil, int tarifMotor, int hariOperasi, int jamBuka, int jamTutup) {
        this.namaGarage = namaGarage;
        this.tarifMobil = tarifMobil;
        this.tarifMotor = tarifMotor;
        this.hariOperasi = hariOperasi;
        this.jamBuka = jamBuka;
        this.jamTutup = jamTutup;
    }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public int getIdGarage_() {
        return this.idGarage;
    }

    public Garage getGarage(int idGarage, int idArea) {
        return g.getGarage(idGarage, idArea);
    }

    public String getNamaGarage() {
        return this.namaGarage;
    }

    public void setNamaGarage(String namaGarage) {
        this.namaGarage = namaGarage;
    }

    public int getTarifMobil() {
        return this.tarifMobil;
    }

    public void setTarifMobil(int tarifMobil) {
        this.tarifMobil = tarifMobil;
    }

    public int getTarifMotor() {
        return this.tarifMotor;
    }

    public void setTarifMotor(int tarifMotor) {
        this.tarifMotor = tarifMotor;
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

    public void viewListGarageByIdArea(int idArea) {
        try {
            ResultSet data = g.searchGarageByIdArea(idArea);
            if (data != null && data.isBeforeFirst()) {
                int i = 0;
                while (data.next()) {
                    String jamBuka = String.valueOf(data.getInt("jamBuka"));
                    String jamTutup = String.valueOf(data.getInt("jamTutup"));
                    if (jamBuka.length() == 1) {
                        jamBuka = "0" + jamBuka + ":00";
                    } else {
                        jamBuka = jamBuka + ":00";
                    }

                    if (jamTutup.length() == 1) {
                        jamTutup = "0" + jamTutup + ":00";
                    } else {
                        jamTutup = jamTutup + ":00";
                    }
                    System.out.println(i + 1 + ". Nama Garasi             : " + data.getString("namaGarage"));
                    System.out.println(" ".repeat(3) + "Tarif Mobil             : Rp. " + data.getInt("tarifMobil"));
                    System.out.println(" ".repeat(3) + "Tarif Motor             : Rp. " + data.getInt("tarifMotor"));
                    System.out.println(" ".repeat(3) + "Jumlah Hari Operasional : " + data.getInt("hariOperasi"));
                    System.out.println(" ".repeat(3) + "Jam Buka                : " + jamBuka);
                    System.out.println(" ".repeat(3) + "Jam Tutup               : " + jamTutup);
                    System.out.println("");
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
                System.out.println("ID\tGARAGE");
                while (data.next()) {
                    System.out.println(data.getInt("idGarage") + "\t" + data.getString("namaGarage"));
                }
            } else {
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Tidak ada garasi yang terdaftar"
                        + Coloring.ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdGarage(String namaGarage) {
        int idGarasi = 0;
        try {
            ResultSet data = g.searchGarageByName(namaGarage);
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

    public int getIdArea() {
        setIdArea();
        return this.idArea;
    }

    private void setIdArea() {
        this.idArea = g.getIdArea(this.idGarage);
    }

    public void detailGarageByID(int idGarage) {
        try {
            ResultSet data = g.searchGarage(idGarage);
            if (data.next()) {
                ResultSet result = g.getNamaArea(data.getInt("idArea"));
                result.next();
                String jamBuka = String.valueOf(data.getInt("jamBuka"));
                String jamTutup = String.valueOf(data.getInt("jamTutup"));
                if (jamBuka.length() == 1) {
                    jamBuka = "0" + jamBuka + ":00";
                } else {
                    jamBuka = jamBuka + ":00";
                }

                if (jamTutup.length() == 1) {
                    jamTutup = "0" + jamTutup + ":00";
                } else {
                    jamTutup = jamTutup + ":00";
                }

                System.out.println("Nama Area               : " + result.getString("namaArea"));
                System.out.println("Nama Garasi             : " + data.getString("namaGarage"));
                System.out.println("Tarif Mobil             : Rp. " + data.getInt("tarifMobil"));
                System.out.println("Tarif Motor             : Rp. " + data.getInt("tarifMotor"));
                System.out.println("Jumlah Hari Operasional : " + data.getInt("hariOperasi"));
                System.out.println("Jam Buka                : " + jamBuka);
                System.out.println("Jam Tutup               : " + jamTutup);
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
            boolean status = g.searchGarageByName(namaGarage).next();
            if (status) {
                valid = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return valid;
    }

    public void editGarage(int idGarage, String namaGarasi, int tarifMobil, int tarifMotor, int hariOperasi,
            int jamBuka, int jamTutup) {
        int status = g.updateGarage(idGarage, namaGarasi, tarifMobil, tarifMotor, hariOperasi, jamBuka, jamTutup);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");

        }
    }

    public int listGarage(int idArea) {
        int countGarage = 0;
        try {
            ResultSet data = g.searchGarageByIdArea(idArea);
            if (data != null && data.isBeforeFirst()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("Waktu : " + LocalDateTime.now().format(formatter));
                System.out.println(
                        "ID\tGARAGE\t\tMOBIL (Rp)\tMOTOR (Rp)\tOPERASIONAL\tHARI OPERASI\tJAM BUKA\tJAM TUTUP");
                while (data.next()) {
                    Garage g = new Garage(data.getString("namaGarage"), data.getInt("tarifMotor"),
                            data.getInt("tarifMobil"), data.getInt("hariOperasi"), data.getInt("jamBuka"),
                            data.getInt("jamTutup"));
                    g.setIdGarage(data.getInt("idGarage"));
                    String jamBuka = String.valueOf(g.getJamBuka());
                    String jamTutup = String.valueOf(g.getJamTutup());
                    if (jamBuka.length() == 1) {
                        jamBuka = "0" + jamBuka + ":00";
                    } else {
                        jamBuka = jamBuka + ":00";
                    }

                    if (jamTutup.length() == 1) {
                        jamTutup = "0" + jamTutup + ":00";
                    } else {
                        jamTutup = jamTutup + ":00";
                    }
                    System.out.println(g.getIdGarage_() + "\t" + g.getNamaGarage() + "\t\t" + g.getTarifMobil() + "\t\t"
                            + g.getTarifMotor() + "\t" + "\t" + g.getOpenStatus() + "\t\t" + g.getHariOperasi() + "\t\t"
                            + jamBuka + "\t\t" + jamTutup);
                    countGarage++;
                }
            } else {
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Tidak ada garasi yang terdaftar"
                        + Coloring.ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return countGarage;
    }

    public boolean isGarageOpen() {
        boolean status = false;
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        int day = dayOfWeek.getValue();
        int hour = LocalDateTime.now().getHour();
        if (day <= this.hariOperasi && hour > this.jamBuka && hour < this.jamTutup) {
            status = true;
        }
        return status;
    }

    private String getOpenStatus() {
        if (isGarageOpen())
            return "Open";
        else
            return "Close";
    }

}
