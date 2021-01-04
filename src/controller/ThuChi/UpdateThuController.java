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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.KhoanThuModel;

public class UpdateThuController implements Initializable {
	@FXML
	private TextField tfIDThu;
	@FXML
	private TextField tfVi;
	@FXML
	private ComboBox<String> cbDanhMuc;
	@FXML
	private TextField tfDanhMuc;
	@FXML
	private TextField tfSoTien;
	@FXML
	private TextField tfNgay;
	@FXML
	private ComboBox<String> cbMaVi;

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
		for (String aString : listStrings) {
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
		cbMaVi.setItems(listVi);
		
		// binding dữ liệu
		KhoanThuModel khoanThuModel = ThuChiController.skhoanThuModel;
		tfIDThu.setText(khoanThuModel.getMaThu());
		tfDanhMuc.setText(khoanThuModel.getTenDanhMuc());
		cbDanhMuc.setValue(khoanThuModel.getMaDanhMuc());
		cbMaVi.setValue(khoanThuModel.getMaVi());
		tfSoTien.setText(khoanThuModel.getSoTien() +"");
		tfNgay.setText(khoanThuModel.getNgay());
		tfVi.setText(khoanThuModel.getTenVi());

	}
	
	public void update(ActionEvent event) throws SQLException {
		KhoanThuModel current = ThuChiController.skhoanThuModel;
		
		String iDThu = tfIDThu.getText();
		String idUser = LoginController.idUser + "";
		String idVi = cbMaVi.getValue();
		String idDM = cbDanhMuc.getValue();
		String sotien = tfSoTien.getText();
		String ngay = tfNgay.getText();
		String tenDM = tfDanhMuc.getText();
		String tenViString = tfVi.getText();
		KhoanThuModel ktmd = new KhoanThuModel(iDThu, idDM, tenDM, Integer.parseInt(sotien), ngay, idVi, tenViString);
		
		
		KhoanThuDAO.updateKhoanThu(current, ktmd);
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Alert alert2 = new Alert(AlertType.INFORMATION, "Bạn đã cập nhật thành công", ButtonType.OK);
        alert2.setHeaderText(null);
        alert2.showAndWait();
		

		
	}

}
