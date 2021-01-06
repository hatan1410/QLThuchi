package controller.danhmuc;

import dao.danhmucDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.DanhMucChi;
import model.DanhMucThu;

import java.sql.SQLException;

public class themNganSachController {

    @FXML
    private TextField textNganSach;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_chi;

    @FXML
    void themNganSach(ActionEvent event) throws SQLException {
        if(!textNganSach.getText().equals("")) {
                DanhMucChi chiModel = new DanhMucChi(Integer.parseInt(lbl_id.getText()), lbl_chi.getText(), Integer.parseInt(textNganSach.getText()));
                danhmucDAO danhmucDAO = new danhmucDAO();
                danhmucDAO.themListNganSach(chiModel);
        }
    }

    public void initializeTextField(DanhMucChi chiModel) {
        lbl_id.setText(String.valueOf(chiModel.getId()));
        lbl_chi.setText(chiModel.getTenChi());
    }
}
