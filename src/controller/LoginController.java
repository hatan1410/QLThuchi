package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUsername;
    
    
    public void login(ActionEvent event) throws IOException {
    	String username = tfUsername.getText();
    	String password = tfPassword.getText();
    	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/main/main.fxml"));
		
		Parent root = loader.load();
		mainController controller = (mainController) loader.getController();
		controller.setUsername(username);
    	
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
