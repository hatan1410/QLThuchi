package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    public void showVi(ActionEvent event) {
        Pane view = getPage("vi");
        mainpane.setCenter(view);
    }

    @FXML
    public void showThuChi(ActionEvent event) throws IOException, SQLException {
    	
    	FXMLLoader loader = new FXMLLoader(ThuChiController.class.getResource("/view/main/ThuChiView2.fxml"));
		Pane thuchiPane = (Pane) loader.load();
		ThuChiController controller = loader.getController();
		controller.setUsername(username);
		mainpane.setCenter(thuchiPane);

    }

    @FXML
    void showDanhmuc(ActionEvent event) throws IOException, SQLException {

    }

    @FXML
    void showThongKe(ActionEvent event) {

    }

    public Pane getPage(String fileName){
        Pane view = new Pane();
        try {
            URL fileUrl = Main.class.getResource("/view/main/"+ fileName + ".fxml");
            if(fileName == null){
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            view = FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " +fileName+ " please check mainController");
            e.printStackTrace();
        }
        return view;
    }
}
