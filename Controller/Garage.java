package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Model.modelGarage;

public class Garage {
    private String namaGarage;
    private int tarifMobil;
    private int tarifMotor;
    private int hariOperasi;
    private int[] jamBuka = new int[hariOperasi];
    private int[] jamTutup = new int[hariOperasi];
    private String[] namaHari = new String[hariOperasi];
    private int jamBukaOperasional;
    private int jamTutupOperasional;
    private String hariOperasional;
    private modelGarage g;
    private int idGarage;
    private int idArea;

    public Garage() {
        this.g = new modelGarage();
    }

    // public Garage(String namaGarage, int tarifMobil, int tarifMotor, int
    // hariOperasi, int jamBuka, int jamTutup,
    // String[] namaHari) {
    // this.namaGarage = namaGarage;
    // this.tarifMobil = tarifMobil;
    // this.tarifMotor = tarifMotor;
    // this.hariOperasi = hariOperasi;
    // this.jamBuka = jamBuka;
    // this.jamTutup = jamTutup;
    // this.namaHari = namaHari;
    // }

    public Garage(String namaGarage, int tarifMobil, int tarifMotor, int hariOperasi, int[] jamBuka, int[] jamTutup,
            String[] namaHari) {
        this.namaGarage = namaGarage;
        this.tarifMobil = tarifMobil;
        this.tarifMotor = tarifMotor;
        this.hariOperasi = hariOperasi;
        this.jamBuka = jamBuka;
        this.jamTutup = jamTutup;
        this.namaHari = namaHari;
    }

    public Garage(String namaGarage, int tarifMobil, int tarifMotor, int hariOperasi, int jamBukaOperasional,
            int jamTutupOperasional, String hariOperasional) {
        this.namaGarage = namaGarage;
        this.tarifMobil = tarifMobil;
        this.tarifMotor = tarifMotor;
        this.hariOperasi = hariOperasi;
        this.jamBukaOperasional = jamBukaOperasional;
        this.jamTutupOperasional = jamTutupOperasional;
        this.hariOperasional = hariOperasional;
    }

    // public Garage(String namaGarage, int tarifMobil, int tarifMotor, int
    // hariOperasi) {
    // this.namaGarage = namaGarage;
    // this.tarifMobil = tarifMobil;
    // this.tarifMotor = tarifMotor;
    // this.hariOperasi = hariOperasi;
    // }

    public void setIdGarage(int idGarage) {
        this.idGarage = idGarage;
    }

    public int getIdGarage() {
        return this.idGarage;
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

    public void setHariOperasi(int hariOperasi) {
        this.hariOperasi = hariOperasi;
    }

    public int[] getJamBuka() {
        return this.jamBuka;
    }

    public void setJamBuka(int[] jamBuka) {
        this.jamBuka = jamBuka;
    }

    public int[] getJamTutup() {
        return this.jamTutup;
    }

    public void setJamTutup(int[] jamTutup) {
        this.jamTutup = jamTutup;
    }

    public int getJamBukaOperasional() {
        return this.jamBukaOperasional;
    }

    public void setJamBukaOperasional(int jamBukaOperasional) {
        this.jamBukaOperasional = jamBukaOperasional;
    }

    public int getJamTutupOperasional() {
        return this.jamTutupOperasional;
    }

    public void setJamTutupOperasional(int jamTutupOperasional) {
        this.jamTutupOperasional = jamTutupOperasional;
    }

    public String[] getNamaHari() {
        return this.namaHari;
    }

    public void setNamaHari(String[] namaHari) {
        this.namaHari = namaHari;
    }

    public String getHariOperasional() {
        return this.hariOperasional;
    }

    public void setHariOperasional(String hariOperasional) {
        this.hariOperasional = hariOperasional;
    }

    public Garage getGarage(int idGarage, int idArea) {
        return g.getGarage(idGarage, idArea);
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
        Table st = new Table();
        st.setShowVerticalLines(true);
        try {
            ResultSet data = g.listAllGarage();
            if (data != null && data.isBeforeFirst()) {
                st.setHeaders("ID Garasi", "Nama Garasi", "Jumlah Hari Operasional", "Tarif Mobil (Rp.)",
                        "Tarif Motor (Rp.)");
                while (data.next()) {
                    st.addRow(Integer.toString(data.getInt("IdGarage")), data.getString("namaGarage"),
                            Integer.toString(data.getInt("hariOperasi")), Integer.toString(data.getInt("tarifMobil")),
                            Integer.toString(data.getInt("tarifMotor")));
                }
                st.print();
            } else {
                System.out.println(Coloring.ANSI_BG_RED + Coloring.ANSI_WHITE + "Tidak ada garasi yang terdaftar"
                        + Coloring.ANSI_RESET);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public int getIdGarage(String namaGarage) {
    // int idGarasi = 0;
    // try {
    // ResultSet data = g.searchGarageByName(namaGarage);
    // if (data.next()) {
    // idGarasi = data.getInt("idGarage");
    // } else {
    // System.out.println("Nama garasi tidak terdaftar");
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return idGarasi;
    // }

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
                data.last();
                int numRow = data.getRow();
                data.first();
                System.out.println("Nama Area               : " + result.getString("namaArea"));
                System.out.println("Nama Garasi             : " + data.getString("namaGarage"));
                System.out.println("Tarif Mobil             : Rp. " + data.getInt("tarifMobil"));
                System.out.println("Tarif Motor             : Rp. " + data.getInt("tarifMotor"));
                System.out.println("Jumlah Hari Operasional : " + data.getInt("hariOperasi"));
                for (int i = 0; i < numRow; i++) {
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
                    System.out.println("Hari Operasional        : " + data.getString("namaHari"));
                    System.out.println("Jam Buka                : " + jamBuka);
                    System.out.println("Jam Tutup               : " + jamTutup);
                    System.out.println();
                    data.next();
                }

            } else {
                System.out.println("Data garasi tidak tersedia");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public boolean cekNamaGarage(String namaGarage) {
    // boolean valid = false;
    // try {
    // boolean status = g.searchGarageByName(namaGarage).next();
    // if (status) {
    // valid = true;
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // return valid;
    // }

    public void editGarage(int idGarage, Garage garasi) {
        int status = g.updateGarage(idGarage, garasi);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");

        }
    }

    public int listGarage(int idArea) {
        int countGarage = 0;

        Table st = new Table();
        st.setShowVerticalLines(true);
        try {
            ResultSet data = g.searchGarageByIdArea(idArea);
            if (data != null && data.isBeforeFirst()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("Waktu : " + LocalDateTime.now().format(formatter));
                st.setHeaders("ID", "GARASI", "MOBIL (Rp)", "MOTOR (Rp)", "OPERASIONAL", "HARI OPERASI", "JAM BUKA",
                        "JAM TUTUP");
                while (data.next()) {
                    Garage g = new Garage(data.getString("namaGarage"), data.getInt("tarifMobil"),
                            data.getInt("tarifMotor"), data.getInt("hariOperasi"), data.getInt("jamBuka"),
                            data.getInt("jamTutup"), data.getString("namahari"));
                    g.setIdGarage(data.getInt("idGarage"));
                    String jamBuka = String.valueOf(g.getJamBukaOperasional());
                    String jamTutup = String.valueOf(g.getJamTutupOperasional());
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
                    st.addRow(Integer.toString(g.getIdGarage()), g.getNamaGarage(), Integer.toString(g.getTarifMobil()),
                            Integer.toString(g.getTarifMotor()), g.getOpenStatus(), g.getHariOperasional(), jamBuka,
                            jamTutup);
                    countGarage++;
                }
                st.print();
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
        Map<String, Integer> value = new HashMap<String, Integer>();
        value.put("senin", 1);
        value.put("selasa", 2);
        value.put("rabu", 3);
        value.put("kamis", 4);
        value.put("jumat", 5);
        value.put("sabtu", 6);
        value.put("minggu", 7);
        boolean status = false;
        DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
        int dayLocal = dayOfWeek.getValue();
        int dayParkir = value.get(this.hariOperasional.toLowerCase());
        int hour = LocalDateTime.now().getHour();
        if (dayLocal == dayParkir && hour >= this.jamBukaOperasional && hour < this.jamTutupOperasional) {
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
