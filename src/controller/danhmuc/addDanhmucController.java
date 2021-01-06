package controller.danhmuc;


import dao.DBConnection;
import dao.danhmucDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.DanhMucChi;
import model.DanhMucThu;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addDanhmucController implements Initializable {

    @FXML
    private TextField textDanhmuc;

    @FXML
    private ComboBox<String> comboDanhmuc;

    private ObservableList<String> thuchi = FXCollections.observableArrayList("Khoản Thu", "Khoản Chi");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboDanhmuc.setItems(thuchi);
    }
    @FXML
    void addDanhmuc(ActionEvent event) throws SQLException {
        if(!textDanhmuc.getText().equals("")){
            if(comboDanhmuc.getValue().equals("Khoản Thu")){
                DanhMucThu thuModel = new DanhMucThu(0, textDanhmuc.getText());
                danhmucDAO danhmucDAO = new danhmucDAO();
                danhmucDAO.addListDanhmuc(thuModel);
            }
            else if(comboDanhmuc.getValue().equals("Khoản Chi")){
                DanhMucChi chiModel = new DanhMucChi(0,textDanhmuc.getText(),0);
                danhmucDAO danhmucDAO = new danhmucDAO();
                danhmucDAO.addListDanhmuc(chiModel);
            }
        }
    }

}