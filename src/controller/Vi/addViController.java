package controller.Vi;

import dao.ViDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.vi;

import java.sql.SQLException;

public class addViController {

    @FXML
    private TextField textTenVi;

    @FXML
    private TextField textTien;

    @FXML
    private TextField textLoaiVi;

    @FXML
    void addVi(ActionEvent event) {
        if(!textTenVi.getText().equals("") && !textTien.getText().equals("") && !textLoaiVi.getText().equals("")){
            vi newVi = new vi(0,textTenVi.getText(), Integer.parseInt(textTien.getText()),textLoaiVi.getText());
            ViDAO viDAO = new ViDAO();
            try {
                viDAO.addListVi(newVi);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
