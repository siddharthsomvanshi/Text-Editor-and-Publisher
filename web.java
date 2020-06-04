package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Siddharth Somvanshi on 8/26/2018.
 */
public class web implements Initializable {
    @FXML
    public WebView webview;
    @FXML
    public WebEngine engine;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        }
    }
