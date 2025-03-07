package mona.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mona.Mona;

/**
 * A GUI for Mona using FXML.
 */
public class Main extends Application {

    private Mona mona = new Mona("data/Mona.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mona");
            fxmlLoader.<MainWindow>getController().setMona(mona);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
