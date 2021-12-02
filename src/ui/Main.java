package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Mountain;
import java.io.IOException;

public class Main extends Application {

    private final String FOLDER = "fxml/";

    private Mountain mountain;
    private MainWindowController MWC;

    public Main() {
        mountain = new Mountain();
        MWC = new MainWindowController(mountain);
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "MainPane.fxml"));
        fxmlLoader.setController(MWC);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        window.setScene(scene);
        window.setTitle("");
        window.setHeight(510.0);
        window.setWidth(445.0);
        MWC.config();
        window.show();
    }

}
