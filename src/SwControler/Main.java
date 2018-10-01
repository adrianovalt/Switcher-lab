package SwControler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Adriano Valt <adrianovalt@gmail.com>
 */
public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene propScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Switcher");

        Parent fxmlMain = FXMLLoader.load(getClass().getResource("/SwView/mainView.fxml"));
        mainScene = new Scene(fxmlMain, 1200, 678);

        Parent fxmlprop = FXMLLoader.load(getClass().getResource("/SwView/prop_switchView.fxml"));
        propScene = new Scene(fxmlprop, 1200, 678);

        primaryStage.setScene(mainScene);
        primaryStage.minHeightProperty().set(600);
        primaryStage.minWidthProperty().set(860);
        primaryStage.show();
    }

    public static void alternaTela(String tela) {
        switch (tela) {
            case "main":
                stage.setScene(mainScene);
                break;
            case "prop":
                stage.setScene(propScene);
            //break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
