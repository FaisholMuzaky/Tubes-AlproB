package Controller;

import java.util.Scanner;
import java.util.regex.*;

import java.sql.ResultSet;

import Model.modelKendaraan;

public class Kendaraan {
    private String platNomor;
    private String tipeKendaraan;
    private modelKendaraan k;
    private int idKendaraan;

    Scanner input = new Scanner(System.in);

    public Kendaraan() {
        this.k = new modelKendaraan();
    }

    public Kendaraan(String platNomor, String tipeKendaraan) {
        this.platNomor = platNomor;
        this.tipeKendaraan = tipeKendaraan;
    }

    public String getPlatNomor() {
        return this.platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getTipeKendaraan() {
        return this.tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
    }

    public void addKendaraan(int idPengguna, String platNomor, String tipeKendaraan) {
        if (tipeKendaraan.toLowerCase().equals("mobil") && cekNomorKendaraanMobil(platNomor)) {
            if (validasiNomorKendaraan(platNomor)) {
                k.insertDataKendaraan(idPengguna, platNomor, "Mobil");
            } else {
                System.out.println("Maaf, nomor Kendaraan sudah ada");
            }
        } else if (tipeKendaraan.toLowerCase().equals("motor") && cekNomorKendaraanMotor(platNomor)) {
            if (validasiNomorKendaraan(platNomor)) {
                k.insertDataKendaraan(idPengguna, platNomor, "Motor");
            } else {
                System.out.println("Maaf, nomor Kendaraan sudah ada");
            }
        } else {
            System.out.println("Maaf, format nomor kendaraan salah");
        }
    }

    public void viewListKendaraan(int id) {
        try {
            ResultSet data = k.searchKendaraan(id);
            if (data != null && data.isBeforeFirst()) {
                int i = 0;
                while (data.next()) {
                    System.out.println(i + 1 + ". Nomor Kendaraan  : " + data.getString("nomorKendaraan"));
                    System.out.println(" ".repeat(3) + "Jenis Kendaraan  : " + data.getString("tipeKendaraan"));
                    System.out.println();
                    i++;
                }
            } else {
                System.out.println("Data kendaraan tidak ada");
                System.out.println("Silahkan melakukan pendaftaran data kendaraan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public boolean cekNomorKendaraanMotor(String platNomor) {
        String motorPattern = "[A-Z]{2}+\s+[1-9]{3}+\s+[A-Z]{2}+";
        Pattern p = Pattern.compile(motorPattern);
        Matcher m = p.matcher(platNomor);
        return m.matches();
    }

    public boolean cekNomorKendaraanMobil(String platNomor) {
        String motorPattern = "[A-Z]{2}+\s+[1-9]{4}+\s+[A-Z]{1}+";
        Pattern p = Pattern.compile(motorPattern);
        Matcher m = p.matcher(platNomor);
        return m.matches();
    }

    public boolean validasiNomorKendaraan(String platNomor) {
        boolean status = true;
        try {
            ResultSet data = k.searchNomorKendaraan(platNomor);
            if (data.next()) {
                status = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public ResultSet getKendaraan(int idKendaraan) {
        ResultSet rs = null;
        try {
            rs = k.getKendaraan(idKendaraan);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return rs;
    }

    public ResultSet searchKendaraan(int idPengguna) {
        ResultSet rs = null;
        try {
            rs = k.searchKendaraan(idPengguna);
        } catch (Exception e) {
            //TODO: handle exception
        }
        return rs;
    }

    public void viewListKendaraan_(int idPengguna) {
        try {
            ResultSet data = searchKendaraan(idPengguna);
            if (data != null && data.isBeforeFirst()) {
                System.out.println("ID\tJENIS KENDARAAN\tPLAT NOMOR");
                while (data.next()) {
                    System.out.println(data.getInt("IdKendaraan")+ "\t" + data.getString("tipeKendaraan") + "\t" + data.getString("nomorKendaraan"));
                }
            } else {
                System.out.println("Data kendaraan tidak ada");
                System.out.println("Silahkan melakukan pendaftaran data kendaraan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public int getIdKendaraan() {
        return this.idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

}