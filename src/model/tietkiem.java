package model;

import java.sql.Date;

public class tietkiem {
    private int id;
    private int soTien;
    private Date ngayGui;
    private int kyHan;
    private float laiSuat;

    public tietkiem() {
    }

    public tietkiem(int id,int soTien, Date ngayGui, int kyHan, float laiSuat) {
        this.id = id;
        this.soTien = soTien;
        this.ngayGui = ngayGui;
        this.kyHan = kyHan;
        this.laiSuat = laiSuat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

    public int getKyHan() {
        return kyHan;
    }

    public void setKyHan(int kyHan) {
        this.kyHan = kyHan;
    }

    public float getLaiSuat() {
        return laiSuat;
    }

    public void setLaiSuat(float laiSuat) {
        this.laiSuat = laiSuat;
    }
}
