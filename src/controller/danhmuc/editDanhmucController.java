package controller.danhmuc;

import dao.danhmucDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DanhMucChi;
import model.DanhMucThu;

import java.sql.SQLException;

public class editDanhmucController {

    @FXML
    private TextField textDanhmuc;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_thuchi;

    @FXML
    void editDanhmuc(ActionEvent event) throws SQLException {
        if(!textDanhmuc.getText().equals("")) {
            if (lbl_thuchi.getText().equals("Khoản Thu")) {
                DanhMucThu thuModel = new DanhMucThu(Integer.parseInt(lbl_id.getText()), textDanhmuc.getText());
                danhmucDAO danhmucDAO = new danhmucDAO();
                danhmucDAO.editListDanhmuc(thuModel);
            } else if (lbl_thuchi.getText().equals("Khoản Chi")) {
                DanhMucChi chiModel = new DanhMucChi(Integer.parseInt(lbl_id.getText()), textDanhmuc.getText(), 0);
                danhmucDAO danhmucDAO = new danhmucDAO();
                danhmucDAO.editListDanhmuc(chiModel);
            }
        }
    }

    public void initializeTextField(DanhMucThu thuModel) {
        lbl_id.setText(String.valueOf(thuModel.getId()));
        lbl_thuchi.setText("Khoản Thu");
        textDanhmuc.setText(thuModel.getTenThu());
    }

    public void initializeTextField(DanhMucChi chiModel) {
        lbl_id.setText(String.valueOf(chiModel.getId()));
        lbl_thuchi.setText("Khoản Chi");
        textDanhmuc.setText(chiModel.getTenChi());
    }
}
