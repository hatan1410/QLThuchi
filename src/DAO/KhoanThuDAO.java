package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.KhoanThuModel;

public class KhoanThuDAO {
	public static List<KhoanThuModel> getListKhoanThu() {
		Connection cnn = DBConnection.open();
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		ArrayList<KhoanThuModel> listThu = null;
		try {
			pStatement = (PreparedStatement) cnn.prepareStatement("SELECT * FROM ThuTien");
			rSet = pStatement.executeQuery();
			while(rSet.next()) {
				String maKhoanThu = rSet.getString(1);
				String tenKhoanThu = rSet.getString(2);
				int tien = rSet.getInt(3);
				String ngay = rSet.getDate(4).toString();
				String vi = rSet.getString(5);
				KhoanThuModel khoanThuModel = new KhoanThuModel(maKhoanThu,tenKhoanThu,tien,ngay,vi);
				listThu.add(khoanThuModel);	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			listThu = null;
		}
		return listThu;
	}
	
	public static void addKhoanThu() {
		
		
	}

}
