package Controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

import java.sql.ResultSet;
import View.Table;
import Model.modelKendaraan;

public class Kendaraan {
    private String platNomor;
    private String tipeKendaraan;
    private int idKendaraan;
    private int idPengguna;
    private modelKendaraan k;

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

    public int getIdKendaraan() {
        return this.idKendaraan;
    }

    public void setIdKendaraan(int idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    protected void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public void addKendaraan(int idPengguna, Kendaraan[] kendaraan) {
        for (int i = 0; i < kendaraan.length; i++) {
            if (kendaraan[i].getTipeKendaraan().toLowerCase().equals("mobil")
                    && cekNomorKendaraanMobil(kendaraan[i].getPlatNomor())) {
                if (validasiNomorKendaraan(kendaraan[i].getPlatNomor())) {
                    k.insertDataKendaraan(idPengguna, kendaraan[i].getPlatNomor(), "Mobil");
                } else {
                    System.out.println("Maaf, nomor Kendaraan " + kendaraan[i].getPlatNomor() + " sudah terdaftar");
                }
            } else if (kendaraan[i].getTipeKendaraan().toLowerCase().equals("motor")
                    && cekNomorKendaraanMotor(kendaraan[i].getPlatNomor())) {
                if (validasiNomorKendaraan(kendaraan[i].getPlatNomor())) {
                    k.insertDataKendaraan(idPengguna, kendaraan[i].getPlatNomor(), "Motor");
                } else {
                    System.out.println("Maaf, nomor Kendaraan " + kendaraan[i].getPlatNomor() + " sudah terdaftar");
                }
            } else {
                System.out.println("Maaf, format nomor kendaraan " + kendaraan[i].getPlatNomor()
                        + " dengan tipe kendaraan " + kendaraan[i].getTipeKendaraan().toUpperCase() + "  salah");
            }
        }
    }

    public void viewListKendaraan(int idPengguna) {
        Table st = new Table();
        st.setShowVerticalLines(true);
        try {
            ArrayList<Kendaraan> kendaraans = searchKendaraan(idPengguna);
            if (kendaraans.size() > 0) {
                st.setHeaders("ID Kendaraan", "JENIS KENDARAAN", "NOMOR KENDARAAN");
                for (Kendaraan kendaraan : kendaraans) {
                    st.addRow(Integer.toString(kendaraan.getIdKendaraan()), kendaraan.getTipeKendaraan(),
                            kendaraan.getPlatNomor());
                }
                st.print();
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

    public Kendaraan getKendaraan(int idKendaraan, int idPengguna) {
        Kendaraan kendaraan = null;
        try {
            ResultSet rs = k.getKendaraan(idKendaraan);
            while (rs.next()) {
                if (rs.getInt("idPengguna") == idPengguna) {
                    kendaraan = new Kendaraan();
                    kendaraan.setIdKendaraan(rs.getInt("idKendaraan"));
                    kendaraan.setPlatNomor(rs.getString("nomorKendaraan"));
                    kendaraan.setTipeKendaraan(rs.getString("tipeKendaraan"));
                    kendaraan.setIdPengguna(rs.getInt("idPengguna"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return kendaraan;
    }

    public ArrayList<Kendaraan> searchKendaraan(int idPengguna) {
        ArrayList<Kendaraan> listKendaraan = new ArrayList<Kendaraan>();
        try {
            ResultSet rs = k.searchKendaraan(idPengguna);
            while (rs.next()) {
                Kendaraan kendaraan = new Kendaraan();
                kendaraan.setIdKendaraan(rs.getInt("idKendaraan"));
                kendaraan.setPlatNomor(rs.getString("nomorKendaraan"));
                kendaraan.setTipeKendaraan(rs.getString("tipeKendaraan"));
                kendaraan.setIdPengguna(rs.getInt("idPengguna"));
                listKendaraan.add(kendaraan);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listKendaraan;
    }

    public void hapusKendaraan(int idKendaraan) {
        boolean status = k.hapusKendaraan(idKendaraan);
        if (status) {
            System.out.println("Penghapusan data kendaraan berhasil dilakukan");
        } else {
            System.out.println("Maaf, Penghapusan data kendaraan gagal dilakukan");
        }
    }

}