package dao;

import controller.LoginController;
import model.DanhMucChi;
import model.DanhMucThu;
import model.vi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class danhmucDAO {

    public List<DanhMucThu> getListDanhmucThu() {
        List<DanhMucThu> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.open();
            String sql = "select IDDanhMuc, LoaiDanhMuc from DanhMucThu where IDuser = "+ LoginController.idUser;
            ResultSet rs = DBConnection.getData(sql, conn);

            while(rs.next()){
                DanhMucThu danhMucThuModel = new DanhMucThu();
                danhMucThuModel.setId(rs.getInt("IDDanhMuc"));
                danhMucThuModel.setTenThu(rs.getString("LoaiDanhMuc"));
                list.add(danhMucThuModel);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public List<DanhMucChi> getListDanhmucChi() {
        List<DanhMucChi> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.open();
            String sql = "select IDDanhMuc, LoaiDanhMuc, SoTien from DanhMucChi where IDuser = "+ LoginController.idUser;
            ResultSet rs = DBConnection.getData(sql, conn);

            while(rs.next()){
                DanhMucChi danhMucChiModel = new DanhMucChi();
                danhMucChiModel.setId(rs.getInt("IDDanhMuc"));
                danhMucChiModel.setTenChi(rs.getString("LoaiDanhMuc"));
                danhMucChiModel.setNganSach(rs.getInt("SoTien"));
                list.add(danhMucChiModel);
            }
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void addListDanhmucThu(DanhMucThu thuModel) throws SQLException {
        Connection conn = DBConnection.open();
        String sql1 = "select max(IDDanhMuc) as idMax from DanhMucThu where IDuser = "+LoginController.idUser;
        ResultSet rs1 = DBConnection.getData(sql1,conn);
        int idMax = 0;
        while (rs1.next()) {
            idMax = rs1.getInt("idMax");
        }
        String sql2 = "insert into DanhMucThu(IDuser, IDDanhMuc, LoaiDanhMuc)" +
                "values (?,"+(idMax+1)+",?)";
        PreparedStatement pst = conn.prepareStatement(sql2);
        pst.setInt(1, LoginController.idUser);
        pst.setString(2,thuModel.getTenThu());

        pst.executeUpdate();
        conn.close();
        pst.close();
    }

    public void addListDanhmucChi(DanhMucChi chiModel) throws SQLException {
        Connection conn = DBConnection.open();
        String sql1 = "select max(IDDanhMuc) as idMax from DanhMucChi where IDuser = "+LoginController.idUser;
        ResultSet rs1 = DBConnection.getData(sql1,conn);
        int idMax = 0;
        while (rs1.next()) {
            idMax = rs1.getInt("idMax");
        }
        String sql2 = "insert into DanhMucChi(IDuser, IDDanhMuc, LoaiDanhMuc)" +
                "values (?,"+(idMax+1)+",?)";
        PreparedStatement pst = conn.prepareStatement(sql2);
        pst.setInt(1, LoginController.idUser);
        pst.setString(2,chiModel.getTenChi());

        pst.executeUpdate();
        conn.close();
        pst.close();
    }
}
