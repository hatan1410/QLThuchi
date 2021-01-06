package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import model.KhoanChiModel;
import model.KhoanThuModel;

public class KhoanThuDAO {
	public static List<KhoanThuModel> getListKhoanThu() throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanThuModel> listThu = new ArrayList<KhoanThuModel>();
		try {
			String query = "SELECT DISTINCT Thutien.IDThu, Thutien.IDDanhMuc, danhmucthu.LoaiDanhMuc, Thutien.SoTien, Thutien.Ngay, Thutien.IDVi, vi.TenVi\r\n" +
					"FROM Thutien, (select * from DanhMucThu where IDuser = "+LoginController.idUser+") as danhmucthu, (select * from Vi where IDuser = "+LoginController.idUser+") as vi\r\n" +
					"WHERE Thutien.IDDanhMuc = danhmucthu.IDDanhMuc \r\n" +
					"AND Thutien.IDVi = vi.IDVi AND Thutien.IDuser = "+ LoginController.idUser + ";";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String maThu = rSet.getString(1);
				String maDanhMuc = rSet.getString(2);
				String tenDanhMuc = rSet.getString(3);
				int soTien = rSet.getInt(4);
				String ngay = rSet.getDate(5).toString();
				String maVi = rSet.getString(6);
				String tenVi = rSet.getString(7);
			
				KhoanThuModel khoanthu = new KhoanThuModel(maThu, maDanhMuc, tenDanhMuc, soTien, ngay, maVi, tenVi);
				listThu.add(khoanthu);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listThu = null;
		}
		return listThu;
	}
	
	public static void addKhoanThu(KhoanThuModel khoanThuModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maThu = khoanThuModel.getMaThu();
			String maDanhMuc = khoanThuModel.getMaDanhMuc();
			String idUser = LoginController.idUser + "";
			String maVi = khoanThuModel.getMaVi();
			int soTien = khoanThuModel.getSoTien();
			String ngay = khoanThuModel.getNgay();
			
			String query = "INSERT INTO Thutien VALUES ("+ idUser+","+maVi +"," +maDanhMuc+ "," + soTien+",'" + ngay + "')";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteKhoanThu(KhoanThuModel khoanThuModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maThu = khoanThuModel.getMaThu();
			String query = "DELETE FROM Thutien WHERE IDThu =" + maThu + "";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateKhoanThu(KhoanThuModel current, KhoanThuModel khoanThuModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maThu = khoanThuModel.getMaThu();
			String maDanhMuc = khoanThuModel.getMaDanhMuc();
			String tenDM = khoanThuModel.getTenDanhMuc();
			int soTien = khoanThuModel.getSoTien();
			String ngay = khoanThuModel.getNgay();
			String maVi = khoanThuModel.getMaVi();
			String tenVi = khoanThuModel.getTenVi();
			String maThuCurrent = current.getMaThu();
			
			String query = "UPDATE Thutien SET SoTien = " + soTien +", Ngay = '" + ngay +"', IDVi = " + maVi +" WHERE IDThu = "+ maThuCurrent + ";" ;
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static List<KhoanThuModel> searchBy(String key,String column) throws SQLException{
		if(column == null || key == null) {
			return getListKhoanThu();
		}
		String dieukien;
		if(column.equals("IDVi") ||column.equals("IDDanhMuc") ){
			dieukien = column + " = " + key ;
		}
		else {
			dieukien = column + " = '" + key + "'";
		}
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanThuModel> listThu = new ArrayList<KhoanThuModel>();
		try {
			String query = "SELECT DISTINCT Thutien.IDThu, Thutien.IDDanhMuc, danhmucthu.LoaiDanhMuc, Thutien.SoTien, Thutien.Ngay, Thutien.IDVi, Vi.TenVi\r\n" +
					"FROM Thutien, (select * from DanhMucThu where IDuser = "+LoginController.idUser+") as danhmucthu, (select * from Vi where IDuser = "+LoginController.idUser+") as vi\r\n" +
					"WHERE Thutien.IDDanhMuc = danhmucthu.IDDanhMuc \r\n" +
					"AND Thutien.IDVi = Vi.IDVi AND Thutien.IDuser = "+ LoginController.idUser + " AND Thutien."+ dieukien + " ;";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String maThu = rSet.getString(1);
				String maDanhMuc = rSet.getString(2);
				String tenDanhMuc = rSet.getString(3);
				int soTien = rSet.getInt(4);
				String ngay = rSet.getDate(5).toString();
				String maVi = rSet.getString(6);
				String tenVi = rSet.getString(7);
			
				KhoanThuModel khoanthu = new KhoanThuModel(maThu, maDanhMuc, tenDanhMuc, soTien, ngay, maVi, tenVi);
				listThu.add(khoanthu);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listThu = null;
		}
		return listThu;
	}
	
	public static List<String> getIDKhoanThu() throws SQLException{
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<String> listID = new ArrayList<String>();
		try {
			String query = "SELECT DanhMucThu.IDDanhMuc\r\n" + 
					"FROM DanhMucThu\r\n" + 
					"WHERE IDuser =  "+ LoginController.idUser+ ";";
			//System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String id = rSet.getString(1);
				listID.add(id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listID = null;
		}
		return listID;
	}
	
	public static List<String> getIDVi() throws SQLException{
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<String> listVi = new ArrayList<String>();
		try {
			String query = "SELECT Vi.IDVi\r\n" + 
					"FROM Vi\r\n" + 
					"WHERE IDuser = "+ LoginController.idUser + ";";
			//System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String id = rSet.getString(1);
				listVi.add(id);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listVi = null;
		}
		return listVi;
	}
	
	public static String getLoaiDanhMuc(String id) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String name = "";
		try {
			String query = "SELECT DanhMucThu.LoaiDanhMuc\r\n" + 
					"FROM DanhMucThu\r\n" + 
					"WHERE IDDanhMuc = " +  id+ 
					" AND IDuser = "+ LoginController.idUser + ";";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				name = rSet.getString(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public static String getLoaiVi(String id) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String name = "";
		try {
			String query = "SELECT Vi.LoaiVi\r\n" + 
					"FROM Vi\r\n" + 
					"WHERE IDVi = \r\n" + id + 
					" AND IDuser =  "+ LoginController.idUser + ";";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				name = rSet.getString(1);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
	
	public static void main(String[] args) {
		try {
			System.out.println(getIDKhoanThu().size());
			System.out.println(getLoaiVi("1"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
