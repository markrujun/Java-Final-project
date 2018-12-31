import Annotation.Author;
import Config.APP_Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Author
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        String css = this.getClass().getResource("mainCSS.css").toExternalForm();
        Scene scene  = new Scene(root, APP_Config.SCENE_WIDTH, APP_Config.SCENE_HEIGHT);
        scene.getStylesheets().add(css);
        primaryStage.setTitle("葫芦娃大战");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
