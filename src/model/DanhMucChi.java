package model;

public class DanhMucChi {
    private int id;
    private String tenChi;
    private int NganSach;

    public DanhMucChi() {
    }

    public DanhMucChi(int id, String tenChi, int nganSach) {
        this.id = id;
        this.tenChi = tenChi;
        NganSach = nganSach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenChi() {
        return tenChi;
    }

    public void setTenChi(String tenChi) {
        this.tenChi = tenChi;
    }

    public int getNganSach() {
        return NganSach;
    }

    public void setNganSach(int nganSach) {
        NganSach = nganSach;
    }
}
