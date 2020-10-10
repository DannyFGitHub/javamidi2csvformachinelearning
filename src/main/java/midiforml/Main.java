package midiforml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import javax.sound.sampled.AudioPermission;

public class Main extends Application {

    //https://github.com/kordamp/bootstrapfx

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = FXMLLoader.load(getClass().getResource("/fxmlpages/main.fxml"));
        primaryStage.setTitle("Midi To CSV");
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(250);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
