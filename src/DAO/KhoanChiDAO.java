package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.mainController;
import model.KhoanChiModel;

public class KhoanChiDAO {
	
	public static List<KhoanChiModel> getListKhoanChi(String username) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanChiModel> listChi = new ArrayList<KhoanChiModel>();
		try {
			String query = "SELECT ChiTieu.IDChi,ChiTieu.IDDanhMuc,DanhMucChi.LoaiDanhMuc,ChiTieu.SoTien, ChiTieu.Ngay,ChiTieu.IDVi, Vi.TenVi\r\n" + 
					"FROM ChiTieu, DanhMucChi, Vi\r\n" + 
					"WHERE ChiTieu.IDDanhMuc = DanhMucChi.IDDanhMuc \r\n" + 
					"AND ChiTieu.IDVi = Vi.IDVi AND ChiTieu.Username = '"+ username + "'";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String maChi = rSet.getString(1);
				String maDanhMuc = rSet.getString(2);
				String tenDanhChi = rSet.getString(3);
				int soTien = rSet.getInt(4);
				String ngay = rSet.getDate(5).toString();
				String maVi = rSet.getString(6);
				String tenVi = rSet.getString(7);
				KhoanChiModel khoanChiModel = new KhoanChiModel(maChi, maDanhMuc, tenDanhChi, soTien, ngay, maVi, tenVi);
				listChi.add(khoanChiModel);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return listChi;
	}
	
	public static void addKhoanChi(KhoanChiModel khoanChiModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maChi = khoanChiModel.getMaChi();
			String maDanhMuc = khoanChiModel.getMaDanhMuc();
			String tenDanhChi = khoanChiModel.getTenDanhChi();
			int soTien = khoanChiModel.getSoTien();
			String ngay = khoanChiModel.getNgay();
			String maVi = khoanChiModel.getMaVi();
			String tenVi = khoanChiModel.getTenVi();
			
			String query = "INSERT INTO ChiTieu VALUES ('" + maChi + "','" + maDanhMuc + "','" + tenDanhChi + "',"
	                + soTien + ",'" + ngay +"','" + maVi+  "','" + tenVi +"';";
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteKhoanChi(KhoanChiModel khoanChiModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maChi = khoanChiModel.getMaChi();
			String query = "DELETE FROM ChiTieu WHERE MaChi = '" + maChi + "'";
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateKhoanChi(KhoanChiModel currentKhoanChiModel, KhoanChiModel khoanChiModel) throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		try {
			String maChi = khoanChiModel.getMaChi();
			String maDanhMuc = khoanChiModel.getMaDanhMuc();
			String tenDanhChi = khoanChiModel.getTenDanhChi();
			int soTien = khoanChiModel.getSoTien();
			String ngay = khoanChiModel.getNgay();
			String maVi = khoanChiModel.getMaVi();
			String tenVi = khoanChiModel.getTenVi();
			String maChiHienTaiString = currentKhoanChiModel.getMaChi();
			
			String query = "UPDATE  ChiTieu SET ('" + maChi + "','" + maDanhMuc + "','" + tenDanhChi + "',"
	                + soTien + ",'" + ngay +"','" + maVi+  "','" + tenVi +"';";
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			pStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	

}
