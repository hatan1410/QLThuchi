package controller.Vi;

import controller.LoginController;
import dao.ViDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.vi;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    private ComboBox<String> comboSearch;

    private ViDAO viDAO;
    private ObservableList<vi> listVi;
    private ObservableList<String> listSearch = FXCollections.observableArrayList("ID Ví", "Tên ví", "Số tiền", "Loại ví");

    public viController(){
        this.viDAO = new ViDAO();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable();
        comboSearch.setItems(listSearch);
    }

    @FXML
    void newVi(ActionEvent event) {
        try{
            Parent book = FXMLLoader.load(getClass().getResource("/view/vi/new_vi.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Thêm ví");
            stage.setScene(new Scene(book));
            stage.resizableProperty().setValue(false);
            stage.show();
            stage.setOnCloseRequest((e)-> updateTable());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void editVit(ActionEvent event) {
        vi viModel = tableVi.getSelectionModel().getSelectedItem();
        if(viModel == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hãy chọn một ví để sửa");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vi/edit_vi.fxml"));
                Parent book = loader.load();
                editViController editViController = loader.getController();
                editViController.initializeTextField(viModel);
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Sửa Ví");
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
    void deleteVi(ActionEvent event) throws SQLException {
        vi viModel = tableVi.getSelectionModel().getSelectedItem();
        if (viModel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hãy chọn một ví để sửa xóa");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else {
            Alert alertCf = new Alert(Alert.AlertType.CONFIRMATION);
            alertCf.setHeaderText("Xóa ví");
            Optional<ButtonType> rs = alertCf.showAndWait();

            if (rs.get() == ButtonType.OK) {
                int IDVi = viModel.getIdVi();
                viDAO.deleteListBook(IDVi);
                tableVi.getItems().removeAll(viModel);
            }
        }
    }

    @FXML
    void search(ActionEvent event) throws SQLException {
        List<vi> list;
        String key = textSearch.getText();
        if(key.equals("") || comboSearch.getValue() == null){
            list = this.viDAO.getListVi();
        }
        else {
            tableVi.getItems().clear();
            String property = comboSelect(comboSearch.getValue());
            list = viDAO.searchListBook(key, property);
        }
        listVi = FXCollections.observableList(list);
        tableVi.setItems(listVi);
    }
    private String comboSelect(String comboSelect) {
        switch (comboSelect) {
            case "ID Ví":
                return "IDVi";
            case "Tên ví":
                return "TenVi";
            case "Số tiền":
                return "SoTien";
            case "Loại ví":
                return "LoaiVi";
            default:
                return "";
        }
    }

    @FXML
    void exportSheet(ActionEvent event) {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        fileChooser.setInitialFileName("Vi");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        File folder = fileChooser.showSaveDialog(null);
        if(folder != null){
            path = folder.getAbsolutePath();

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Ví");
            XSSFRow header1 = sheet.createRow(0);
            header1.createCell(0).setCellValue("Người dùng");
            header1.createCell(1).setCellValue(LoginController.username);

            XSSFRow header2 = sheet.createRow(1);
            header2.createCell(0).setCellValue("ID ví");
            header2.createCell(1).setCellValue("Tên ví");
            header2.createCell(2).setCellValue("Số tiền");
            header2.createCell(3).setCellValue("Loại ví");

            int i=2;
            for(vi viModel : listVi){
                XSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(viModel.getIdVi());
                row.createCell(1).setCellValue(viModel.getTenVi());
                row.createCell(2).setCellValue(viModel.getSoTien());
                row.createCell(3).setCellValue(viModel.getLoaiVi());
                i++;
            }

            try {
                FileOutputStream fileOut = new FileOutputStream(path);
                wb.write(fileOut);
                fileOut.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export Dialog");
                alert.setHeaderText("Exported in Excel Sheet");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

