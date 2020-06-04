package sample;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
    private double x = 0;
    private double y = 0;
    @FXML
    JFXPasswordField password = new JFXPasswordField();
    @FXML
    JFXTextField username = new JFXTextField();
    @FXML
    AnchorPane background = new AnchorPane();
    public Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            scene = new Scene(root, 1080, 600);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
            primaryStage.setTitle("Blog Console");
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            root.setOnMousePressed(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - x);
                    primaryStage.setY(event.getScreenY() - y);
                }
            });
            primaryStage.setResizable(true);
            primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
