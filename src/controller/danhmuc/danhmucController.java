package controller.danhmuc;

import dao.danhmucDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DanhMucChi;
import model.DanhMucThu;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class danhmucController implements Initializable {

    @FXML
    private TableView<DanhMucThu> tableThu;

    @FXML
    private TableColumn<DanhMucThu, Integer> colidThu;

    @FXML
    private TableColumn<DanhMucThu, String> colThu;

    @FXML
    private TableView<DanhMucChi> tableChi;

    @FXML
    private TableColumn<DanhMucChi, Integer> colidChi;

    @FXML
    private TableColumn<DanhMucChi, String> colChi;

    @FXML
    private TableColumn<DanhMucChi, Integer> colNgansach;

    private ObservableList<DanhMucThu> listDanhmucThu;
    private ObservableList<DanhMucChi> listDanhmucChi;

    private danhmucDAO danhmucDAO;

    public danhmucController(){
        this.danhmucDAO = new danhmucDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    @FXML
    void addDanhMuc(ActionEvent event) {
        try{
            Parent book = FXMLLoader.load(getClass().getResource("/view/danhmuc/new_danhmuc.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Thêm danh mục");
            stage.setScene(new Scene(book));
            stage.resizableProperty().setValue(false);
            stage.show();
            stage.setOnCloseRequest((e)-> updateTable());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void deleteDanhMuc(ActionEvent event) {

    }

    @FXML
    void editDanhMuc(ActionEvent event) {

    }

    @FXML
    void addNganSach(ActionEvent event) {

    }

    private void updateTable(){
        colidThu.setCellValueFactory(new PropertyValueFactory<DanhMucThu, Integer>("id"));
        colThu.setCellValueFactory(new PropertyValueFactory<DanhMucThu, String>("tenThu"));

        colidChi.setCellValueFactory(new PropertyValueFactory<DanhMucChi, Integer>("id"));
        colChi.setCellValueFactory(new PropertyValueFactory<DanhMucChi, String>("tenChi"));
        colNgansach.setCellValueFactory(new PropertyValueFactory<DanhMucChi, Integer>("NganSach"));

        List<DanhMucThu> listThu = this.danhmucDAO.getListDanhmucThu();
        List<DanhMucChi> listChi = this.danhmucDAO.getListDanhmucChi();

        listDanhmucThu = FXCollections.observableList(listThu);
        listDanhmucChi = FXCollections.observableList(listChi);

        tableThu.setItems(listDanhmucThu);
        tableChi.setItems(listDanhmucChi);
    }


}

