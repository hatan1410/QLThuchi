package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KhoanChiModel;
import model.KhoanThuModel;

public class KhoanThuDAO {
	public static List<KhoanThuModel> getListKhoanThu() throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanThuModel> listThu = new ArrayList<KhoanThuModel>();
		try {
			pStatement = (PreparedStatement) cnn.prepareStatement("SELECT Thutien.IDThu,Thutien.IDDanhMuc,DanhMucChi.LoaiDanhMuc,Thutien.SoTien, Thutien.Ngay,Thutien.IDVi, Vi.TenVi\r\n" + 
					"FROM Thutien, DanhMucChi, Vi\r\n" + 
					"WHERE Thutien.IDDanhMuc = DanhMucChi.IDDanhMuc \r\n" + 
					"AND Thutien.IDVi = Vi.IDVi;");
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
			String tenDanhMuc = khoanThuModel.getTenDanhMuc();
			int soTien = khoanThuModel.getSoTien();
			String ngay = khoanThuModel.getNgay();
			String maVi = khoanThuModel.getMaVi();
			String tenVi = khoanThuModel.getTenVi();
			
			String query = "INSERT INTO Thutien VALUES ('" + maThu + "','" + maDanhMuc + "','" + tenDanhMuc + "',"
	                + soTien + ",'" + ngay +"','" + maVi+  "','" + tenVi +"';";
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
			
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
			String query = "DELETE FROM ChiTieu WHERE MaChi = '" + maThu + "'";
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
