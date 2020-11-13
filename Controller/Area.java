package Controller;

import java.sql.ResultSet;

import Model.modelArea;

public class Area {
    private String namaArea;
    private Garage garages;
    private modelArea a;
    private Pengguna p;
    private int idArea;

    public Area() {
        this.a = new modelArea();
        this.p = new Pengguna();
        this.garages = new Garage();
    }

    public String getNamaArea() {
        return this.namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public int getIdArea() {
        return this.idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public void addArea(String namaArea) {
        if (isValidnamaArea(namaArea)) {
            a.insertArea(namaArea);
        } else {
            System.out.println("Maaf, nama area sudah digunakan");
        }
    }

    public void editArea(int idArea, String newNamaArea) {
        int status = a.updateArea(idArea, newNamaArea);
        if (status == 1) {
            System.out.println("Update Data Berhasil");
        } else {
            System.out.println("Update Data Gagal");
        }
    }

    // public void viewArea() {
    // try {
    // ResultSet data = a.listArea();
    // if (data != null && data.isBeforeFirst()) {
    // int i = 0;
    // while (data.next()) {
    // System.out.println(i + 1 + ". Nama Area : " + data.getString("namaArea"));
    // i++;
    // }
    // } else {
    // System.out.println("Data area parkir tidak ada");
    // System.out.println("Silahkan melakukan pendaftaran data area parkir");
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    public void viewArea_() {
        try {
            ResultSet data = a.listArea();
            if (data != null && data.isBeforeFirst()) {
                System.out.println("ID\tNAMA AREA");
                while (data.next()) {
                    System.out.println(data.getInt("idArea") + "\t" + data.getString("namaArea"));
                }
            } else if (!p.isAdmin(p.getIdPengguna())) {
                System.out.println("Maaf data area parkir tidak ada");
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

    public void detailArea(int IdArea) {
        try {
            ResultSet rs = a.searchAreaByID(IdArea);
            if (rs.next()) {
                int jumGarasi = garages.countGarageArea(IdArea);
                System.out.println("Nama Area Parkir : " + rs.getString("namaArea"));
                System.out.println("Jumlah Garasi    : " + jumGarasi);
                garages.viewListGarageByIdArea(IdArea);
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

    public String searchNamaArea(int idArea) {
        String namaArea = null;
        try {
            ResultSet rs = a.searchNamaArea(idArea);
            if (rs.next()) {
                namaArea = rs.getString("namaArea");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return namaArea;
    }

    public Area getArea(int idArea) {
        Area area = null;
        try {
            ResultSet rs = a.searchNamaArea(idArea);
            if (rs.next()) {
                area = new Area();
                area.setIdArea(idArea);
                area.setNamaArea(rs.getString("namaArea"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return area;
    }

}
