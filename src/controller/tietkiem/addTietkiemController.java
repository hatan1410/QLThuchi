package controller.tietkiem;

import controller.LoginController;
import dao.DBConnection;
import dao.ViDAO;
import dao.tietkiemDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.tietkiem;
import model.vi;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addTietkiemController implements Initializable {

    @FXML
    private TextField textKyhan;

    @FXML
    private TextField textLaisuat;

    @FXML
    private DatePicker dateNgaygui;

    @FXML
    private TextField textSotien;

    @FXML
    private ComboBox<String> comboVi;

    @FXML
    private Label lbl_tenVi;

    private ObservableList<String> listVi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.open();
            String sql = "select IDVi from Vi where IDuser = "+ LoginController.idUser;
            ResultSet rs = DBConnection.getData(sql, conn);

            while(rs.next()){
                list.add(String.valueOf(rs.getInt("IDVi")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        listVi = FXCollections.observableList(list);
        comboVi.setItems(listVi);
    }

    @FXML
    void selectVi(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.open();
        String sql = "select TenVi from Vi where IDVi = "+comboVi.getValue()+" and IDuser = "+LoginController.idUser;
        ResultSet rs = DBConnection.getData(sql, conn);
        while (rs.next()) {
            lbl_tenVi.setText(rs.getString("TenVi"));
        }
    }

    @FXML
    void addTietkiem(ActionEvent event) throws SQLException {
        if (!textKyhan.getText().equals("") && !textLaisuat.getText().equals("") && dateNgaygui.getValue() != null && !textSotien.getText().equals("") && comboVi.getValue() != null) {
            tietkiem tietkiemModel = new tietkiem(0, Integer.parseInt(textSotien.getText()), Date.valueOf(dateNgaygui.getValue()), Integer.parseInt(textKyhan.getText()), Float.parseFloat(textLaisuat.getText()));
            tietkiemDAO tietkiemDAO = new tietkiemDAO();
            tietkiemDAO.addListTietkiem(tietkiemModel);
        }
    }

}

