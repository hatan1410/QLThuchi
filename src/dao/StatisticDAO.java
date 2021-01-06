package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.LoginController;
import model.StatisticModel;

public class StatisticDAO {

	public static List<StatisticModel> getStatistic() throws SQLException {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<StatisticModel> listChi = new ArrayList<StatisticModel>();
		try {
			String query = "SELECT CT.IDDanhMuc,DanhMucChi.LoaiDanhMuc, sum(CT.sotien), DanhMucChi.SoTien\r\n"
					+ "FROM ChiTieu CT, DanhMucChi\r\n" + "WHERE \r\n" + "month(CT.Ngay) = \r\n"
					+ "(SELECT TOP 1 MONTH(ChiTieu.Ngay)\r\n" + "FROM ChiTieu\r\n" + "ORDER BY ChiTieu.Ngay ASC)\r\n"
					+ "AND CT.IDDanhMuc = DanhMucChi.IDDanhMuc\r\n" + "AND CT.IDuser = " + LoginController.idUser
					+ "GROUP BY CT.IDDanhMuc,DanhMucChi.LoaiDanhMuc,DanhMucChi.SoTien\r\n";
			System.out.println(query);
			pStatement = (PreparedStatement) cnn.prepareStatement(query);
			rSet = pStatement.executeQuery();
			while (rSet.next()) {
				String maDM = rSet.getString(1);
				String tenDM = rSet.getString(2);
				int sotientt = rSet.getInt(3)/2;
				int soTien = rSet.getInt(4);
				StatisticModel statisticModel = new StatisticModel(maDM, tenDM, sotientt, soTien);
				listChi.add(statisticModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listChi;
	}

	public static int getTongThu() throws SQLException {
		int res = 0;
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<StatisticModel> listChi = new ArrayList<StatisticModel>();
		String query = "SELECT Thutien.IDuser,sum(SoTien)\r\n" + 
				"FROM Thutien\r\n" + 
				"WHERE month(Thutien.Ngay) = (SELECT TOP 1 MONTH(ChiTieu.Ngay)\r\n" + 
				"FROM ChiTieu\r\n" + 
				"ORDER BY ChiTieu.Ngay DESC)\r\n" + 
				"AND Thutien.IDuser = 1"+
				" GROUP BY Thutien.IDuser;";
		System.out.println(query);
		pStatement = (PreparedStatement) cnn.prepareStatement(query);
		rSet = pStatement.executeQuery();
		while (rSet.next()) {
			res = rSet.getInt(2);
		}
		return res;
	}
	
	public static int getTongChi() throws SQLException {
		int res = 0;
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<StatisticModel> listChi = new ArrayList<StatisticModel>();
		String query = "SELECT sum(SoTien)\r\n" + 
				"FROM ChiTieu\r\n" + 
				"WHERE month(ChiTieu.Ngay) = (SELECT TOP 1 MONTH(ChiTieu.Ngay)\r\n" + 
				"FROM ChiTieu\r\n" + 
				"ORDER BY ChiTieu.Ngay DESC)\r\n" + 
				"AND ChiTieu.IDuser = " + LoginController.idUser;
		System.out.println(query);
		pStatement = (PreparedStatement) cnn.prepareStatement(query);
		rSet = pStatement.executeQuery();
		while (rSet.next()) {
			res = rSet.getInt(1);
		}
		return res;
	}
	
	public static int getTongVi() throws SQLException {
		int res = 0;
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<StatisticModel> listChi = new ArrayList<StatisticModel>();
		String query = "\r\n" + 
				"SELECT sum(Vi.SoTien)\r\n" + 
				"FROM Vi\r\n" + 
				"WHERE Vi.IDuser =  " + LoginController.idUser;
		System.out.println(query);
		pStatement = (PreparedStatement) cnn.prepareStatement(query);
		rSet = pStatement.executeQuery();
		while (rSet.next()) {
			res = rSet.getInt(1);
		}
		return res;
		
	}
	
	public static int getTongViID(String idvi) throws SQLException {
		int res = 0;
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<StatisticModel> listChi = new ArrayList<StatisticModel>();
		String query = "SELECT Vi.SoTien\r\n" + 
				"FROM Vi\r\n" + 
				"WHERE Vi.IDuser = "+ LoginController.idUser + 
				" AND Vi.IDVi = " + idvi + " ;";
		System.out.println(query);
		pStatement = (PreparedStatement) cnn.prepareStatement(query);
		rSet = pStatement.executeQuery();
		while (rSet.next()) {
			res = rSet.getInt(1);
		}
		return res;
		
	}


	public static void main(String[] args) throws SQLException {
		List<StatisticModel> statisticModels = getStatistic();
		for (StatisticModel s : statisticModels) {
			System.out.println(s.getTenDanhMuc());
		}
		System.out.println(getTongThu());
	}

}
