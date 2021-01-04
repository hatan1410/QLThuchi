package model;

public class vi {
    private int idVi;
    private String tenVi;
    private int soTien;
    private String loaiVi;

    public vi() {
    }

    public vi(int idVi, String tenVi, int soTien, String loaiVi) {
        this.idVi = idVi;
        this.tenVi = tenVi;
        this.soTien = soTien;
        this.loaiVi = loaiVi;
    }

    public int getidVi() {
        return idVi;
    }

    public void setidVi(int idVi) {
        this.idVi = idVi;
    }

    public String getTenVi() {
        return tenVi;
    }

    public void setTenVi(String tenVi) {
        this.tenVi = tenVi;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public String getLoaiVi() {
        return loaiVi;
    }

    public void setLoaiVi(String loaiVi) {
        this.loaiVi = loaiVi;
    }
}
