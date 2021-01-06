package controller.danhmuc;

import com.sun.xml.bind.v2.model.core.ID;
import controller.Vi.editViController;
import dao.danhmucDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.DanhMucChi;
import model.DanhMucThu;
import model.vi;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    void editDanhMuc(ActionEvent event) {
        DanhMucThu thuModel = tableThu.getSelectionModel().getSelectedItem();
        DanhMucChi chiModel = tableChi.getSelectionModel().getSelectedItem();
        if(thuModel == null && chiModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hãy chọn một danh mục để sửa");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/danhmuc/edit_danhmuc.fxml"));
                Parent book = loader.load();
                editDanhmucController editDanhmucController = loader.getController();
                if(thuModel != null){
                    editDanhmucController.initializeTextField(thuModel);
                }
                else if(chiModel != null){
                    editDanhmucController.initializeTextField(chiModel);
                }
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Sửa danh mục");
                stage.setScene(new Scene(book));
                stage.resizableProperty().setValue(false);
                stage.show();
                stage.setOnCloseRequest((e) -> {
                    updateTable();
                });
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    @FXML
    void deleteDanhMuc(ActionEvent event) throws SQLException {
        DanhMucThu thuModel = tableThu.getSelectionModel().getSelectedItem();
        DanhMucChi chiModel = tableChi.getSelectionModel().getSelectedItem();
        if(thuModel == null && chiModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hãy chọn một danh mục để xóa");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            Alert alertCf = new Alert(Alert.AlertType.CONFIRMATION);
            alertCf.setHeaderText("Xóa danh mục");
            Optional<ButtonType> rs = alertCf.showAndWait();

            if (rs.get() == ButtonType.OK) {
                if(thuModel != null){
                    int IDDanhmuc = thuModel.getId();
                    danhmucDAO.deleteListDanhmuc(IDDanhmuc, thuModel);
                    tableThu.getItems().removeAll(thuModel);
                }
                else if(chiModel != null){
                    int IDDanhmuc = chiModel.getId();
                    danhmucDAO.deleteListDanhmuc(IDDanhmuc, chiModel);
                    tableChi.getItems().removeAll(chiModel);
                }

            }
        }
    }

    @FXML
    void addNganSach(ActionEvent event) {
        DanhMucChi chiModel = tableChi.getSelectionModel().getSelectedItem();
        if(chiModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hãy chọn một danh mục chi");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/danhmuc/themNganSach.fxml"));
                Parent book = loader.load();
                themNganSachController themNganSachController = loader.getController();
                themNganSachController.initializeTextField(chiModel);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Thêm ngân sách");
                stage.setScene(new Scene(book));
                stage.resizableProperty().setValue(false);
                stage.show();
                stage.setOnCloseRequest((e) -> {
                    updateTable();
                });
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
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

