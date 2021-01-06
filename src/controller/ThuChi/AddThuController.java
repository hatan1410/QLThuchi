package controller.ThuChi;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.LoginController;
import dao.KhoanThuDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KhoanThuModel;

public class AddThuController implements Initializable {

	@FXML
	private TextField tfIDThu;

	@FXML
	private ComboBox<String> cbDanhMuc;

	@FXML
	private TextField tfDanhMuc;

	@FXML
	private TextField tfSoTien;

	@FXML
	private TextField tfNgay;

	@FXML
	private TextField tfVi;

	@FXML
	private ComboBox<String> cbVi;
		
	
	public void addThu(ActionEvent event) throws SQLException {
		String iDThu = tfIDThu.getText();
		String idUser = LoginController.idUser + "";
		String idVi = cbVi.getValue();
		String idDM = cbDanhMuc.getValue();
		String sotien = tfSoTien.getText();
		String ngay = tfNgay.getText();
		String tenDM = tfDanhMuc.getText();
		String tenViString = tfVi.getText();
		KhoanThuModel ktmd = new KhoanThuModel(iDThu, idDM, tenDM, Integer.parseInt(sotien), ngay, idVi, tenViString);
		KhoanThuDAO.addKhoanThu(ktmd);
		
		
		
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Thiết lập combobox Danh mục thu
		List<String> listIDDM = new ArrayList<String>();
		try {
			listIDDM = KhoanThuDAO.getIDKhoanThu();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<String> listStrings = FXCollections.observableArrayList(listIDDM);
		System.out.println(listStrings.size());
		for(String aString : listStrings) {
			System.out.println("string " + aString);
		}
		cbDanhMuc.setItems(listStrings);	
		
		// Thiết lập combobox Ví
		
		List<String> listIDVi = new ArrayList<String>();
		try {
			listIDVi = KhoanThuDAO.getIDVi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<String> listVi = FXCollections.observableArrayList(listIDVi);
		cbVi.setItems(listVi);
		
	}
	
	public void addTenVi(ActionEvent event) throws SQLException {
		String tenvi = KhoanThuDAO.getLoaiVi(cbVi.getValue());
		tfVi.setText(tenvi);
		
		tfVi.setEditable(false);
	}

	public void addTenDM(ActionEvent event) throws SQLException {
		String tenvi = KhoanThuDAO.getLoaiDanhMuc(cbDanhMuc.getValue());
		tfDanhMuc.setText(tenvi);
		
		tfDanhMuc.setEditable(false);
	}
}
