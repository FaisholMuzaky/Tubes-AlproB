package Controller;

import java.sql.ResultSet;

import Model.modelArea;

public class Area {
    private String namaArea;
    private Garage garages;
    private modelArea a;
    private Pengguna p;

    public Area() {
        this.a = new modelArea();
        this.p = new Pengguna();
        this.garages = new Garage();
    }

    public void addArea(String namaArea) {
        if (isValidnamaArea(namaArea)) {
            a.insertArea(namaArea);
        } else {
            System.out.println("Maaf, nama area sudah digunakan");
        }
    }

    public void editArea(String namaArea, String newNamaArea) {
        int status = a.updateArea(namaArea, newNamaArea);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");
        }
    }

    public void viewArea() {
        try {
            ResultSet data = a.listArea();
            if (data != null && data.isBeforeFirst()) {
                int i = 0;
                while (data.next()) {
                    System.out.println(i + 1 + ". Nama Area : " + data.getString("namaArea"));
                    i++;
                }
            } else {
                System.out.println("Data area parkir tidak ada");
                System.out.println("Silahkan melakukan pendaftaran data area parkir");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean isValidnamaArea(String namaArea) {
        boolean valid = false;
        try {
            boolean status = a.searchArea(namaArea).next();
            if (!status) {
                valid = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return valid;
    }

    public void detailArea(String namaArea) {
        try {
            ResultSet rs = a.searchArea(namaArea);
            if (rs.next()) {
                int IdArea = rs.getInt("idArea");
                int jumGarasi = garages.countGarageArea(IdArea);
                System.out.println("Nama Area Parkir : " + namaArea);
                System.out.println("Jumlah Garasi    : " + jumGarasi);
                garages.viewListGarage(IdArea);
                if (jumGarasi == 0) {
                    System.out.println("Silahkan melakukan penambahan data garasi");
                }
            } else {
                System.out.println("Maaf, nama area tidak ditemukan");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdAreaByName(String namaArea) {
        int idArea = 0;
        try {
            ResultSet rs = a.searchArea(namaArea);
            if (rs.next()) {
                idArea = rs.getInt("idArea");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return idArea;
    }
}
