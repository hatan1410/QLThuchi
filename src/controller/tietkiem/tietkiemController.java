package controller.tietkiem;

import dao.tietkiemDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.tietkiem;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class tietkiemController implements Initializable {

    @FXML
    private TableView<tietkiem> tableTietkiem;

    @FXML
    private TableColumn<tietkiem, Integer> colMaTietkiem;

    @FXML
    private TableColumn<tietkiem, Integer> colSotien;
    @FXML
    private TableColumn<tietkiem, Date> colNgaygui;

    @FXML
    private TableColumn<tietkiem, Integer> colKyhan;

    @FXML
    private TableColumn<tietkiem, Float> colLaisuat;

    @FXML
    private TextField textSearch;

    @FXML
    private ComboBox<String> comboSearch;

    private tietkiemDAO tietkiemDAO;
    private ObservableList<tietkiem> listTietkiem;
    public tietkiemController(){
        this.tietkiemDAO = new tietkiemDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    @FXML
    void newTietkiem(ActionEvent event) {
        try{
            Parent book = FXMLLoader.load(getClass().getResource("/view/tietkiem/new_tietkiem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Thêm tiết kiệm");
            stage.setScene(new Scene(book));
            stage.resizableProperty().setValue(false);
            stage.show();
            stage.setOnCloseRequest((e)-> updateTable());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void editTietkiem(ActionEvent event) {

    }

    @FXML
    void deleteTietkiem(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void exportSheet(ActionEvent event) {

    }

    private void updateTable() {
        colMaTietkiem.setCellValueFactory(new PropertyValueFactory<tietkiem, Integer>("id"));
        colSotien.setCellValueFactory(new PropertyValueFactory<tietkiem, Integer>("soTien"));
        colNgaygui.setCellValueFactory(new PropertyValueFactory<tietkiem, Date>("ngayGui"));
        colKyhan.setCellValueFactory(new PropertyValueFactory<tietkiem, Integer>("kyHan"));
        colLaisuat.setCellValueFactory(new PropertyValueFactory<tietkiem, Float>("laiSuat"));

        List<tietkiem> list = this.tietkiemDAO.getListTietkiem();
        listTietkiem = FXCollections.observableList(list);
        tableTietkiem.setItems(listTietkiem);
    }
}

