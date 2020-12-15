package model;

public class KhoanChiModel {
	private String maChi;
	private String tenDanhChi;
	private int soTien;
	private String Ngay;
	private String tenVi;
	
	public KhoanChiModel() {
		
	}

	public KhoanChiModel(String maChi, String tenDanhChi, int soTien, String ngay, String tenVi) {
		super();
		this.maChi = maChi;
		this.tenDanhChi = tenDanhChi;
		this.soTien = soTien;
		Ngay = ngay;
		this.tenVi = tenVi;
	}

	public String getMaChi() {
		return maChi;
	}

	public void setMaChi(String maChi) {
		this.maChi = maChi;
	}

	public String getTenDanhChi() {
		return tenDanhChi;
	}

	public void setTenDanhChi(String tenDanhChi) {
		this.tenDanhChi = tenDanhChi;
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
