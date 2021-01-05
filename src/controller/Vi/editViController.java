package controller.Vi;

import dao.DBConnection;
import dao.ViDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.vi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editViController {

    @FXML
    private Label lbl_idVi;
    @FXML
    private TextField textTenVi;

    @FXML
    private TextField textTien;

    @FXML
    private TextField textLoaiVi;

    @FXML
    void editVi(ActionEvent event) throws SQLException {
        if(!textTenVi.getText().equals("") && !textTien.getText().equals("") && !textLoaiVi.getText().equals("")){
            vi Vi = new vi(Integer.parseInt(lbl_idVi.getText()),textTenVi.getText(), Integer.parseInt(textTien.getText()),textLoaiVi.getText());
            ViDAO viDAO = new ViDAO();
            try {
                viDAO.editListVi(Vi);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void initializeTextField(vi viModel) {
        lbl_idVi.setText(String.valueOf(viModel.getIdVi()));
        textTenVi.setText(viModel.getTenVi());
        textTien.setText(String.valueOf(viModel.getSoTien()));
        textLoaiVi.setText(viModel.getLoaiVi());
    }
}

