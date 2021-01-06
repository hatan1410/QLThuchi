package model;

public class StatisticModel {
	private String idDanhMuc;
	private String tenDanhMuc;
	private int sotientt;
	private int sotientc;

	public StatisticModel(String idDanhMuc, String tenDanhMuc, int sotientt, int sotientc) {
		super();
		this.idDanhMuc = idDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
		this.sotientt = sotientt;
		this.sotientc = sotientc;
	}

	public String getIdDanhMuc() {
		return idDanhMuc;
	}

	public void setIdDanhMuc(String idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	public int getSotientt() {
		return sotientt;
	}

	public void setSotientt(int sotientt) {
		this.sotientt = sotientt;
	}

	public int getSotientc() {
		return sotientc;
	}

	public void setSotientc(int sotientc) {
		this.sotientc = sotientc;
	}
	
	

}
