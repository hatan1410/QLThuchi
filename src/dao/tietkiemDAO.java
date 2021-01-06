package dao;

import controller.LoginController;
import model.tietkiem;
import model.vi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tietkiemDAO {

    public List<tietkiem> getListTietkiem() {
        List<tietkiem> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.open();
            String sql = "select IDTietKiem, SoTien, NgayGui, KyHan, LaiSuat from TietKiem where IDuser = "+ LoginController.idUser;
            ResultSet rs = DBConnection.getData(sql, conn);

            while(rs.next()){
                tietkiem tietkiemModel = new tietkiem();
                tietkiemModel.setId(rs.getInt("IDTietKiem"));
                tietkiemModel.setSoTien(rs.getInt("SoTien"));
                tietkiemModel.setNgayGui(rs.getDate("NgayGui"));
                tietkiemModel.setKyHan(rs.getInt("KyHan"));
                tietkiemModel.setLaiSuat(rs.getFloat("LaiSuat"));
                list.add(tietkiemModel);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addListTietkiem(tietkiem tietkiemModel) throws SQLException {
        Connection conn = DBConnection.open();
        String sql1 = "select max(IDTietKiem) as idMax from TietKiem where IDuser ="+LoginController.idUser;
        ResultSet rs1 = DBConnection.getData(sql1,conn);

        int idMax = 0;
        while (rs1.next()) {
            idMax = rs1.getInt("idMax");
        }
        String sql2 = "insert into TietKiem(IDuser, IDTietKiem, SoTien, NgayGui, KyHan, LaiSuat) values (?," +
                +(idMax+1)+
                ",?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql2);
        pst.setInt(1,LoginController.idUser);
        pst.setInt(2,tietkiemModel.getSoTien());
        pst.setDate(3,tietkiemModel.getNgayGui());
        pst.setInt(4,tietkiemModel.getKyHan());
        pst.setFloat(5,tietkiemModel.getLaiSuat());

        pst.executeUpdate();
        conn.close();
        pst.close();
    }
}
