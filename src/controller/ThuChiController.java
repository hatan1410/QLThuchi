package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.KhoanChiDAO;
import DAO.KhoanThuDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	
	public void setUsername(String username) {
		this.username = username;
		showKhoanChi();
		//showKhoanThu();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTab1();
		initTab2();
		
	}
	
	public void initTab1() {
		initCbTimKiemThu();
	//	showKhoanThu();		
		
	}
	
	public void initTab2() {
		initCbTimKiemChi();
	//	showKhoanChi();
		
	}
	
	public void initCbTimKiemThu() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[] { "Mã thu", "Tên danh mục", "Số tiền",
				"Ngày", "Ví" });
		cbSearchThu.setItems(list);
	}
	public void initCbTimKiemChi() {
		ObservableList<String> list = FXCollections.observableArrayList(new String[] { "Mã thu", "Tên danh mục", "Số tiền",
				"Ngày", "Ví" });
		cbSearchChi.setItems(list);
	}
	
	public void showKhoanThu() {
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
	
	public void showKhoanChi() {
		listKhoanChi = FXCollections.observableArrayList(KhoanChiDAO.getListKhoanChi(username));
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
    		System.out.println(username);
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddKhoanThuView.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("THÊM KHOẢN THU");
			stage.showAndWait();
			showKhoanThu();
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }

    @FXML
    void updateThu(ActionEvent event) {

    }

    @FXML
    void deleteThu(ActionEvent event) {

    }
    
    @FXML
    void searchThu(ActionEvent event) {

    }


    @FXML
    void importFile(ActionEvent event) {

    }

    @FXML
    void exportFile(ActionEvent event) {

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
			// TODO: handle exception
		}

    }

    @FXML
    void updateChi(ActionEvent event) {

    }

    @FXML
    void deleteChi(ActionEvent event) {

    }
    

    @FXML
    void searchChi(ActionEvent event) {

    }

    @FXML
    void importChi(ActionEvent event) {

    }

    @FXML
    void exportChi(ActionEvent event) {

    }



}
