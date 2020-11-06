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

    public void viewArea_() {
        try {
            ResultSet data = a.listArea();
            if (data != null && data.isBeforeFirst()) {
                System.out.println("ID\tNAMA AREA");
                while (data.next()) {
                    System.out.println(data.getInt("idArea") +"\t" + data.getString("namaArea"));
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

    public String getNamaArea() {
        return this.namaArea;
    }

    public void setNamaArea(String namaArea) {
        this.namaArea = namaArea;
    }

    public int getIdArea() {
        return this.idArea;
    }

    private void setIdArea(int idArea) {
        this.idArea = idArea;
    }

}
