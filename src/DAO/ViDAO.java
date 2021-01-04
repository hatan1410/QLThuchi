package dao;

import controller.LoginController;
import model.vi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViDAO {

    public List<vi> getListVi() {
        List<vi> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.open();
            String sql = "select IDVi, TenVi, SoTien, LoaiVi from Vi where IDuser = "+ LoginController.idUser;
            ResultSet rs = DBConnection.getData(sql, conn);

            while(rs.next()){
                vi viModel = new vi();
                viModel.setidVi(rs.getInt("IDVi"));
                viModel.setTenVi(rs.getString("TenVi"));
                viModel.setSoTien(rs.getInt("SoTien"));
                viModel.setLoaiVi(rs.getString("LoaiVi"));
                list.add(viModel);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
