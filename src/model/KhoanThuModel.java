package model;

public class KhoanThuModel {
	private String maThu;
	private String maDanhMuc;
	private String tenDanhMuc;
	private int soTien;
	private String Ngay;
	private String maVi;
	private String tenVi;
	
	public KhoanThuModel() {
		
	}

	public KhoanThuModel(String maThu, String maDanhMuc, String tenDanhMuc, int soTien, String ngay, String maVi,
			String tenVi) {
		super();
		this.maThu = maThu;
		this.maDanhMuc = maDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.soTien = soTien;
		Ngay = ngay;
		this.maVi = maVi;
		this.tenVi = tenVi;
	}

	public String getMaThu() {
		return maThu;
	}

	public void setMaThu(String maThu) {
		this.maThu = maThu;
	}

	public String getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
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

	public String getMaVi() {
		return maVi;
	}

	public void setMaVi(String maVi) {
		this.maVi = maVi;
	}

	public String getTenVi() {
		return tenVi;
	}

	public void setTenVi(String tenVi) {
		this.tenVi = tenVi;
	}

	
	

}
