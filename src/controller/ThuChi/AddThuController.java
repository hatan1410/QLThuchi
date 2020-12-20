package controller.ThuChi;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddThuController {

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
	
	private String username;
	
	public void setUsername(String username) {
		this.username = username;
	}

}
