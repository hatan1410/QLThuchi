package controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class mainController {
    @FXML
    private BorderPane mainpane;
    
    private String username;
    
    public void setUsername(String username) {
		this.username = username;
	}

    @FXML
    public void showThuChi(ActionEvent event) throws IOException, SQLException {
    	
    	FXMLLoader loader = new FXMLLoader(ThuChiController.class.getResource("/view/ThuChiView2.fxml"));
		Pane thuchiPane = (Pane) loader.load();
		ThuChiController controller = loader.getController();
		controller.setUsername(username);
		mainpane.setCenter(thuchiPane);

    }
	
}
