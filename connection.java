package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Siddharth Somvanshi on 8/26/2018.
 */
public class connection {
    @FXML
    private ListView<String> list;
    private ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    Label status = new Label();
    @FXML
    ImageView close = new ImageView();
    @FXML
    AnchorPane titlebar = new AnchorPane();
    @FXML
    JFXTextField username = new JFXTextField();
    @FXML
    JFXPasswordField password = new JFXPasswordField();
    @FXML
    private AnchorPane rootpane;
    String myDriver = "com.mysql.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost:3306/blog";
    public boolean connection() throws IOException {
        try {
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            String query = "SELECT * FROM admin WHERE username='" + username.getText().toString() + "' AND password='" + password.getText().toString() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                status.setText("Login Successful");
                return true;
            } else {
                status.setText("Username or Password Invalid");
                return false;
            }

        } catch (Exception e) {
            if (e.getCause() == null) {
                status.setText(e.getMessage());
            }
        }
        return false;
    }
}
