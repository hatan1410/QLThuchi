package model;

public class KhoanThuModel {
	private String maThu;
	private String tenDanhMuc;
	private int soTien;
	private String Ngay;
	private String tenVi;
	
	public KhoanThuModel() {
		
	}

	public KhoanThuModel(String maThu, String tenDanhMuc, int soTien, String ngay, String tenVi) {
		this.maThu = maThu;
		this.tenDanhMuc = tenDanhMuc;
		this.soTien = soTien;
		Ngay = ngay;
		this.tenVi = tenVi;
	}

	public String getMaThu() {
		return maThu;
	}

	public void setMaThu(String maThu) {
		this.maThu = maThu;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public int getSoTien() {
		return soTien;
	}

	public void setSoTien(int soTien) {
		this.soTien = soTien;
	}

	public String getNgay() {
		return Ngay;
	}

	public void setNgay(String ngay) {
		Ngay = ngay;
	}

	public String getTenVi() {
		return tenVi;
	}

	public void setTenVi(String tenVi) {
		this.tenVi = tenVi;
	}
	
	
	

}
