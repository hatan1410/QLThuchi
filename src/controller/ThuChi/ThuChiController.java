package controller.ThuChi;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.KhoanChiDAO;
import dao.KhoanThuDAO;
import file.ChiTieuFile;
import file.ThutienFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.KhoanChiModel;
import model.KhoanThuModel;

public class ThuChiController implements Initializable {
	
	// Chi tiền
    @FXML
    private Tab tabChi;
    @FXML
    private TextField tfSearchChi;
    @FXML
    private ComboBox<String> cbSearchChi;
    @FXML
    private TableView<KhoanChiModel> tvChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colMaChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colMaDMChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colTenDMChi;
    @FXML
    private TableColumn<KhoanChiModel, Integer> colTienChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colNgayChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colViChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colMaViChi;;
    // thu tiền
    @FXML
    private Tab tabThu;
    @FXML
    private TextField tfSearchThu;
    @FXML
    private ComboBox<String> cbSearchThu;
    @FXML
    private TableView<KhoanThuModel> tvThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colMaThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colMaDMThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colTenDMThu;
    @FXML
    private TableColumn<KhoanThuModel, Integer> colTienThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colNgayThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colMaViThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colViThu;
    // Danh sách khoản thu
	private ObservableList<KhoanThuModel> listKhoanThu = null;
    // Danh sách khoản chi
	private ObservableList<KhoanChiModel> listKhoanChi = null;
	
	private String username;
	
	public static KhoanChiModel skhoanChiModel;
	public static KhoanThuModel skhoanThuModel;
	
	public void setUsername(String username) throws SQLException {
		this.username = username;
		//showKhoanChi();
		//showKhoanThu();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTab1();
		initTab2();
		
	}
	
	public void initTab1() {
		initCbTimKiemThu();
		try {
			showKhoanThu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void initTab2() {
		initCbTimKiemChi();
		try {
			showKhoanChi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initCbTimKiemThu() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[] {"Mã danh mục",
				"Ngày", "Mã Ví" });
		cbSearchThu.setItems(list);
	}
	public void initCbTimKiemChi() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[] {"Mã danh mục",
				"Ngày", "Mã Ví" });
		cbSearchChi.setItems(list);
	}
	
	public void showKhoanThu() throws SQLException {
		listKhoanThu = FXCollections.observableArrayList(KhoanThuDAO.getListKhoanThu());
		System.out.println(listKhoanThu.size());
		colMaThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("maThu"));
		colMaDMThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("maDanhMuc"));
		colTenDMThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("tenDanhMuc"));
		colTienThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, Integer>("soTien"));
		colNgayThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("Ngay"));
		colMaViThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("maVi"));
		colViThu.setCellValueFactory(new PropertyValueFactory<KhoanThuModel, String>("tenVi"));
		tvThu.setItems(listKhoanThu);
			
	}
	
	public void showKhoanChi() throws SQLException {
		listKhoanChi = FXCollections.observableArrayList(KhoanChiDAO.getListKhoanChi());
		System.out.println(listKhoanChi.size());
		colMaChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("maChi"));
		colMaDMChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("maDanhMuc"));
		colTenDMChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("tenDanhChi"));
		colTienChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, Integer>("soTien"));
		colNgayChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("Ngay"));
		colMaViChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("maVi"));
		colViChi.setCellValueFactory(new PropertyValueFactory<KhoanChiModel, String>("tenVi"));
		tvChi.setItems(listKhoanChi);
			
		
	}
    @FXML
    public void addThu(ActionEvent event) {
    	try {
    		
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/AddKhoanThuView.fxml"));
    		Parent root = loader.load();
    		
    		
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("THÊM KHOẢN THU");
						
			stage.showAndWait();
			showKhoanThu();
    	} catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }

    @FXML
    public void updateThu(ActionEvent event) {
    	
    	KhoanThuModel khoanThuModel = tvThu.getSelectionModel().getSelectedItem();
		if (khoanThuModel == null) {
			Alert alert = new Alert(AlertType.WARNING, "Hãy chọn khoản thu bạn muốn cập nhật", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
			return;
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/UpdateKhoanThuView.fxml"));
		skhoanThuModel = khoanThuModel;
		try {
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Cập nhật khoản thu");

			UpdateThuController controller = (UpdateThuController) loader.getController();
			stage.showAndWait();
			showKhoanThu();
		} catch (Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    public void deleteThu(ActionEvent event) throws SQLException {
    	KhoanThuModel khoanThuModel = tvThu.getSelectionModel().getSelectedItem();
		if (khoanThuModel == null) {
			Alert alert = new Alert(AlertType.WARNING, "Hãy chọn khoản thu bạn muốn xóa", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Xóa " + khoanThuModel.getMaThu() + "?", ButtonType.YES,
					ButtonType.NO);
			alert.setHeaderText(null);
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get() == ButtonType.YES) {
				KhoanThuDAO.deleteKhoanThu(khoanThuModel);
				Alert alert2 = new Alert(AlertType.INFORMATION, "Xóa thành công.", ButtonType.OK);
				alert2.setHeaderText(null);
				alert2.showAndWait();

			}
		}
		showKhoanThu();


    }
    
    @FXML
    void searchThu(ActionEvent event) throws SQLException {
		String keyString = tfSearchThu.getText();
		if (keyString.equals("")) {
			showKhoanThu();
			return;
		}
		
		SelectionModel<String> model = cbSearchThu.getSelectionModel();
		String selected = model.getSelectedItem();
		if (selected == null) {
			return;
		}
		String column = null;
		if (selected.equals("Mã danh mục")) {
			column = "IDDanhMuc";
		} else if (selected.equals("Ngày")) {
			column = "Ngay";
		} else if (selected.equals("Mã ví")) {
			column = "IDVi";
		} 
		
		listKhoanThu.clear();
		listKhoanThu.addAll(KhoanThuDAO.searchBy(keyString, column));
    }


    @FXML
    void importFile(ActionEvent event) throws IOException, SQLException {
    	List<KhoanThuModel> listKT = ThutienFile.importFile();
    	for(KhoanThuModel khoanThuModel : listKT) {
    		KhoanThuDAO.addKhoanThu(khoanThuModel);
    	}
    	
    	Alert alert2 = new Alert(AlertType.INFORMATION, "Nhập file thành công.", ButtonType.OK);
		alert2.showAndWait();
		showKhoanThu();
    	

    }

    @FXML
    void exportFile(ActionEvent event) throws IOException {
    	List<KhoanThuModel> listKT = listKhoanThu;
    	ThutienFile.exportFile(listKT);

    }


    @FXML
    void addChi(ActionEvent event) {
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddKhoanChiView.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("THÊM KHOẢN CHI");
			stage.showAndWait();
			showKhoanChi();
    	} catch (Exception e) {
    		e.printStackTrace();
		}

    }

    @FXML
    public void updateChi(ActionEvent event) {
    	KhoanChiModel khoanChiModel = tvChi.getSelectionModel().getSelectedItem();
		if (khoanChiModel == null) {
			Alert alert = new Alert(AlertType.WARNING, "Hãy chọn khoản chi bạn muốn cập nhật", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/UpdateKhoanChiView.fxml"));
		skhoanChiModel = khoanChiModel;
		try {
			Parent root = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Cập nhật khoản chi");

			UpdateChiController controller = (UpdateChiController) loader.getController();
			//controller.setKhoanChi(khoanChiModel);

			stage.showAndWait();
			showKhoanChi();
		} catch (Exception e) {
			e.printStackTrace();
		}


    }

    @FXML
    void deleteChi(ActionEvent event) throws SQLException {
    	KhoanChiModel khoanChiModel = tvChi.getSelectionModel().getSelectedItem();
		if (khoanChiModel == null) {
			Alert alert = new Alert(AlertType.WARNING, "Hãy chọn khoản ghi bạn muốn xóa", ButtonType.OK);
			alert.setHeaderText(null);
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Xóa " + khoanChiModel.getMaChi() + "?", ButtonType.YES,
					ButtonType.NO);
			alert.setHeaderText(null);
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get() == ButtonType.YES) {
				KhoanChiDAO.deleteKhoanChi(khoanChiModel);
				Alert alert2 = new Alert(AlertType.INFORMATION, "Xóa thành công.", ButtonType.OK);
				alert2.setHeaderText(null);
				alert2.showAndWait();

			}
		}
		showKhoanChi();



    }
    

    @FXML
    void searchChi(ActionEvent event) throws SQLException {
		String keyString = tfSearchChi.getText();
		if (keyString.equals("")) {
			showKhoanChi();
			return;
		}
		
		SelectionModel<String> model = cbSearchChi.getSelectionModel();
		String selected = model.getSelectedItem();
		if (selected == null) {
			return;
		}
		String column = null;
		if (selected.equals("Mã danh mục")) {
			column = "IDDanhMuc";
		} else if (selected.equals("Ngày")) {
			column = "Ngay";
		} else if (selected.equals("Mã ví")) {
			column = "IDVi";
		} 
		
		listKhoanChi.clear();
		listKhoanChi.addAll(KhoanChiDAO.searchBy(keyString, column));
    


    }

    @FXML
    void importChi(ActionEvent event) throws IOException, SQLException {
    	List<KhoanChiModel> listKC = ChiTieuFile.importFile();
    	for(KhoanChiModel temp : listKC) {
    		KhoanChiDAO.addKhoanChi(temp);
    	}
    	String text = "Nhập file thành công. " + listKC.size() + " khoản chi được thêm vào hệ thống";
    	Alert alert2 = new Alert(AlertType.INFORMATION, text, ButtonType.OK);
		alert2.showAndWait();
		showKhoanChi();

    }

    @FXML
    void exportChi(ActionEvent event) throws IOException {
    	List<KhoanChiModel> list = listKhoanChi;
    	ChiTieuFile.exportFile(list);

    }



}
