package controller.ThuChi;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.LoginController;
import dao.KhoanChiDAO;
import dao.KhoanThuDAO;
import dao.StatisticDAO;
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
import model.KhoanChiModel;
import model.KhoanThuModel;

public class AddChiController implements Initializable {
	@FXML
	private TextField tfIDChi;
	@FXML
	private ComboBox<String> cbDanhMuc;
	@FXML
	private TextField tfDanhMuc;
	@FXML
	private TextField tfSoTien;
	@FXML
	private TextField tfNgay;
	@FXML
	private TextField tfMaVi;
	@FXML
	private ComboBox<String> cbVi;
	
	public void addChi(ActionEvent event) throws SQLException {
		String iDThu = tfIDChi.getText();
		String idUser = LoginController.idUser + "";
		String idVi = cbVi.getValue();
		String idDM = cbDanhMuc.getValue();
		String sotien = tfSoTien.getText();
		String ngay = tfNgay.getText();
		String tenDM = tfDanhMuc.getText();
		String tenViString = tfMaVi.getText();
		KhoanChiModel ktmd = new KhoanChiModel(iDThu, idDM, tenDM, Integer.parseInt(sotien), ngay, idVi, tenViString);
		KhoanChiDAO.addKhoanChi(ktmd);
		int sotienvi = StatisticDAO.getTongViID(idVi);
		if( sotienvi < 0) {
			Alert alert = new Alert(AlertType.WARNING, "Bạn hết tiền ở ví này rồi đấy", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Thiết lập combobox Danh mục chi
		List<String> listIDDM = new ArrayList<String>();
		try {
			listIDDM = KhoanChiDAO.getIDKhoanChi();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<String> listStrings = FXCollections.observableArrayList(listIDDM);
		System.out.println(listStrings.size());

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
		tfMaVi.setText(tenvi);
		
		tfMaVi.setEditable(false);
	}

	public void addTenDM(ActionEvent event) throws SQLException {
		String tenvi = KhoanChiDAO.getLoaiDanhMuc(cbDanhMuc.getValue());
		tfDanhMuc.setText(tenvi);
		
		tfDanhMuc.setEditable(false);
	}
	
	

}
