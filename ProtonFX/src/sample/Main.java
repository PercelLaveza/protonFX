package sample;

import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Proton");
        primaryStage.setScene(new Scene(root, 424, 600));
        primaryStage.show();
        primaryStage.setResizable(true);

        Font.loadFont(Main.class.getResource("/fonts/OpenSans-Regular.ttf").toExternalForm(), 11);
        Font.loadFont(Main.class.getResource("/fonts/OpenSans-Semibold.ttf").toExternalForm(), 11);
        Font.loadFont(Main.class.getResource("/fonts/Roboto-Medium.ttf").toExternalForm(), 11);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
