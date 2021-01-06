package dao;

import controller.LoginController;
import model.vi;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                viModel.setIdVi(rs.getInt("IDVi"));
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

    public void addListVi(vi viModel) throws SQLException {
        Connection conn = DBConnection.open();
        String sql1 = "select max(Vi.IDVi) as idMax from Vi where IDuser ="+LoginController.idUser;
        ResultSet rs1 = DBConnection.getData(sql1,conn);

        int idMax = 0;
        while (rs1.next()) {
            idMax = rs1.getInt("idMax");
        }
        String sql2 = "insert into Vi(IDuser, IDVi, TenVi, SoTien, LoaiVi) values (?," +
                +(idMax+1)+
                ",?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql2);
        pst.setInt(1,LoginController.idUser);
        pst.setString(2,viModel.getTenVi());
        pst.setInt(3,viModel.getSoTien());
        pst.setString(4,viModel.getLoaiVi());

        pst.executeUpdate();
        conn.close();
        pst.close();
    }

    public void editListVi(vi viModel) throws SQLException {
        Connection conn = DBConnection.open();
        String sql = "Update Vi set TenVi = ?, SoTien = ?, LoaiVi = ? where IDuser = ? and IDVi = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,viModel.getTenVi());
        pst.setInt(2,viModel.getSoTien());
        pst.setString(3,viModel.getLoaiVi());
        pst.setInt(4,LoginController.idUser);
        pst.setInt(5, viModel.getIdVi());

        pst.executeUpdate();
        conn.close();
        pst.close();
    }

    public void deleteListVi(int idVi) throws SQLException {
        Connection conn = DBConnection.open();
        String sql = "Delete from Vi where IDVi = ? and IDuser = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1,idVi);
        pst.setInt(2,LoginController.idUser);

        pst.execute();
        conn.close();
        pst.close();
    }

    public List<vi> searchListBook(String key, String property) throws SQLException {
        List<vi> list = new ArrayList<>();
        Connection conn = DBConnection.open();
        String sql;
        if(property == "IDVi" || property == "SoTien"){
            sql = "select * from Vi where IDuser = "+LoginController.idUser+ " and "+property+" = "+key;
        }
        else sql = "select * from Vi where IDuser = "+LoginController.idUser+ " and "+property+" like N'%"+key+"%'";
        ResultSet rs = DBConnection.getData(sql,conn);

        while (rs.next()) {
            vi viModel = new vi();
            viModel.setIdVi(rs.getInt("IDVi"));
            viModel.setTenVi(rs.getString("TenVi"));
            viModel.setSoTien(rs.getInt("SoTien"));
            viModel.setLoaiVi(rs.getString("LoaiVi"));
            list.add(viModel);
        }
        conn.close();
        return list;
    }
}
