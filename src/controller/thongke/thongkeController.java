package controller.thongke;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.KhoanThuDAO;
import dao.StatisticDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.KhoanChiModel;
import model.KhoanThuModel;
import model.StatisticModel;
import model.vi;

public class thongkeController implements Initializable {
	@FXML
	private Tab bang;

	@FXML
	private Tab bieudo;

	@FXML
	private TableColumn<StatisticModel, String> colIDDanhMuc;

	@FXML
	private TableColumn<StatisticModel, Integer> colSoTienTT;

	@FXML
	private TableColumn<StatisticModel, Integer> colSoTienTC;

	@FXML
	private TableColumn<StatisticModel, String> colTenDanhMuc;

	@FXML
	private BarChart<String, Number> bcStatistic;

	@FXML
	private TableView<StatisticModel> tvThongKe;

	private ObservableList<StatisticModel> listTK;;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	@FXML
	private Label chi;
	
	@FXML
    private Label thu;

    @FXML
    private Label tienvi;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			showBieuDo();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			showThongKe();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int thut = StatisticDAO.getTongThu();
			int chit = StatisticDAO.getTongChi();
			int tvi = StatisticDAO.getTongVi();
			chi.setText(StatisticDAO.getTongChi() + "");
			thu.setText(StatisticDAO.getTongThu() + "") ;
			tienvi.setText(tvi + "");
			if(chit > thut) {
				Alert alert = new Alert(AlertType.WARNING, "Số tiền bạn chi nhiều hơn số tiền bạn thu", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				}
			if(tvi < 100000) {
				Alert alert = new Alert(AlertType.WARNING, "Bạn sắp hết tiền rồi đấy", ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void showThongKe() throws SQLException {
		listTK = FXCollections.observableArrayList(StatisticDAO.getStatistic());
		System.out.println(listTK.size());
		colIDDanhMuc.setCellValueFactory(new PropertyValueFactory<StatisticModel, String>("idDanhMuc"));
		colTenDanhMuc.setCellValueFactory(new PropertyValueFactory<StatisticModel, String>("tenDanhMuc"));
		colSoTienTT.setCellValueFactory(new PropertyValueFactory<StatisticModel, Integer>("sotientt"));
		colSoTienTC.setCellValueFactory(new PropertyValueFactory<StatisticModel, Integer>("sotientc"));
		tvThongKe.setItems(listTK);

	}

	public void showBieuDo() throws SQLException {
		xAxis.setLabel("Loại danh mục");
		yAxis.setLabel("Số tiền");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Số tiền thực tế");
		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Số tiền tiêu chuẩn");
		List<StatisticModel> list = StatisticDAO.getStatistic();
		for (StatisticModel temp : list) {
			series1.getData().add(new XYChart.Data(temp.getTenDanhMuc(), temp.getSotientt()));
			series2.getData().add(new XYChart.Data(temp.getTenDanhMuc(), temp.getSotientc()));
			if(temp.getSotientt() > temp.getSotientc()) {
				String textString = "Bạn tiêu tiền "+ temp.getTenDanhMuc() + " nhiều hơn tiêu chuẩn rồi đấy";
				Alert alert = new Alert(AlertType.WARNING, textString, ButtonType.OK);
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
		bcStatistic.getData().addAll(series1, series2);

	}


}
