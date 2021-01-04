package controller.Vi;

import dao.ViDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.vi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class viController implements Initializable {

    @FXML
    private TableView<vi> tableVi;

    @FXML
    private TableColumn<vi, Integer> colMaVi;

    @FXML
    private TableColumn<vi, String> colTenVi;

    @FXML
    private TableColumn<vi, Integer> colSoTien;

    @FXML
    private TableColumn<vi, String> colLoaiVi;

    @FXML
    private TextField textSearch;

    @FXML
    private ComboBox<?> comboSearch;

    private ViDAO viDAO;
    private ObservableList<vi> listVi;

    public viController(){
        this.viDAO = new ViDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
    }

    private void updateTable(){
        colMaVi.setCellValueFactory(new PropertyValueFactory<vi, Integer>("idVi"));
        colTenVi.setCellValueFactory(new PropertyValueFactory<vi, String>("tenVi"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<vi, Integer>("soTien"));
        colLoaiVi.setCellValueFactory(new PropertyValueFactory<vi, String>("loaiVi"));

        List<vi> list = this.viDAO.getListVi();
        listVi = FXCollections.observableList(list);
        tableVi.setItems(listVi);
    }

}

