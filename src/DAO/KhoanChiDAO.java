package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.KhoanChiModel;

public class KhoanChiDAO {
	
	public static List<KhoanChiModel> getListKhoanChi() {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanChiModel> listChi = null;
		try {
			pStatement = (PreparedStatement) cnn.prepareStatement("SELECT * FROM ChiTieu");
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String maKhoanChi = rSet.getString(1);
				String tenKhoanChi = rSet.getString(2);
				int tien = rSet.getInt(3);
				String ngay = rSet.getDate(4).toString();
				String vi = rSet.getString(5);
				KhoanChiModel khoanChiModel = new KhoanChiModel(maKhoanChi,tenKhoanChi,tien,ngay,vi);
				listChi.add(khoanChiModel);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listChi = null;
		}
		return listChi;
	}
	

}
