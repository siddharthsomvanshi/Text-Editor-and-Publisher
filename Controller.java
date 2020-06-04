package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static javafx.scene.input.KeyCode.J;

public class Controller implements Runnable {
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
    @FXML
    HTMLEditor editor = new HTMLEditor();
    @FXML
    ComboBox category = new ComboBox();
    @FXML
    Button playpause = new Button();
    @FXML
    Button previous = new Button();
    @FXML
    Button next = new Button();
    public void run() {
        System.exit(0);
    }

    public void login() throws IOException {
        status.setText("Running...");

        try {
            configReader cr = new configReader();
            String address = cr.GETserverAddress();
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(address, "root", "");
            String query = "SELECT * FROM admin WHERE username='" + username.getText().toString() + "' AND password='" + password.getText().toString() + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                status.setText("Login Successful");
                BorderPane pane = FXMLLoader.load(getClass().getResource("console.fxml"));
                rootpane.getChildren().setAll(pane);
            } else {
                status.setText("Username or Password Invalid");
            }

        } catch (Exception e) {
            if (e.getCause() == null) {
                status.setText(e.getMessage());
            }
        }
    }

    public void test() {
        System.out.println("combo running");
    }

    public void publish() throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now).toString();
        String title = JOptionPane.showInputDialog("Enter Post Title");
        String themePIC = JOptionPane.showInputDialog("Enter Theme Post Picture ID");
        String themePICtype = JOptionPane.showInputDialog("Enter Picture Format : PNG, JPG, JPEG Only");
        String content = editor.getHtmlText();
        String cate = category.getValue().toString();
        queryMaker qm = new queryMaker();
        qm.queryExecuter(date,title,content,themePIC,themePICtype,cate,"1");
    }

    public void save() throws Exception {
        configReader cr = new configReader();
        cr.save(editor.getHtmlText().toString());
    }

    public void LOADfile() throws Exception {
        configReader cr = new configReader();
        String value;
        String path = cr.GETDefaultLocation();
        FileChooser fc = new FileChooser();
        fc.setTitle("Load File");
        fc.setInitialDirectory(new File(path));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        Scanner inputfile = new Scanner(selectedFile);
        while (inputfile.hasNext()) {
            value = inputfile.nextLine();
            editor.setHtmlText(value);
        }
    }
}