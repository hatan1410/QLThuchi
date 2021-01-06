package model;

public class DanhMucThu {
    private int id;
    private String tenThu;

    public DanhMucThu() {
    }

    public DanhMucThu(int id, String tenThu) {
        this.id = id;
        this.tenThu = tenThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThu() {
        return tenThu;
    }

    public void setTenThu(String tenThu) {
        this.tenThu = tenThu;
    }
}
