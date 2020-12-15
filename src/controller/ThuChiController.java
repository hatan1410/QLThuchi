package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.KhoanChiModel;
import model.KhoanThuModel;

public class ThuChiController {
	
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
    private TableColumn<KhoanChiModel, String> colTenChi;
    @FXML
    private TableColumn<KhoanChiModel, Integer> colTienChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colNgayChi;
    @FXML
    private TableColumn<KhoanChiModel, String> colViChi;
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
    private TableColumn<KhoanThuModel, String> colTenThu;
    @FXML
    private TableColumn<KhoanThuModel, Integer> colTienThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colNgayThu;
    @FXML
    private TableColumn<KhoanThuModel, String> colViThu;


    @FXML
    void addThu(ActionEvent event) {

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
