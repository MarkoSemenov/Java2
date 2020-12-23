import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class NetChat extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        NetChat.primaryStage = primaryStage;

        authentication();
        showChat();

    }

    public static void startPrimary() {

        primaryStage.show();
    }

    public void showChat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Simple chat");
        primaryStage.resizableProperty().setValue(false);
        primaryStage.setScene(new Scene(root, 600, 500));

    }

    private void authentication() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/authentication.fxml"));
        AnchorPane page = loader.load();
        Stage authStage = new Stage();
        authStage.setTitle("Authorization");
        Scene scene = new Scene(page);
        authStage.setScene(scene);
        authStage.show();

        AuthController controller = loader.getController();
        controller.setStage(authStage);

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Controller.client.getSocket().close();
    }


    public static void main(String[] args) throws IOException {

        launch(args);

    }
}