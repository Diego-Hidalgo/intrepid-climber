package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Mountain;
import java.io.*;

public class EmergentWindowController {

    private final String FOLDER = "fxml/";

    private Mountain mountain;

    @FXML
    private TextField filePathTxt;
    @FXML
    private TextField savePathTxt;


    public EmergentWindowController(Mountain mountain) {
        this.mountain = mountain;
    }

    @FXML
    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER + "ImportWindow.fxml"));
        fxmlLoader.setController(this);
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, null);
        Stage form = new Stage();
        form.initModality(Modality.APPLICATION_MODAL);
        form.setScene(scene);
        form.setTitle("");
        form.setHeight(320.0);
        form.setWidth(450.0);
        form.setResizable(false);
        form.showAndWait();
    }

    @FXML
    public void chooseFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione la ruta del archivo");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"));
        File file = fileChooser.showOpenDialog(null);
        if(file != null)
            filePathTxt.setText(file.getAbsolutePath());
    }

    @FXML
    public void chooseSavePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione la ruta del archivo");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = fileChooser.showSaveDialog(null);
        if(file != null)
            savePathTxt.setText(file.getAbsolutePath());
    }

    @FXML
    public void importData() throws IOException {
        File rf = new File(filePathTxt.getText());
        if(!rf.exists())
            return;
        BufferedReader br = new BufferedReader(new FileReader(filePathTxt.getText()));
        File f = new File(savePathTxt.getText());
        if(!f.exists())
            f.createNewFile();
        PrintWriter pw = new PrintWriter(f);
        Mountain m = new Mountain();
        String line;
        while((line = br.readLine()) != null) {
            int N = Integer.parseInt(line.split(" ")[0]);
            m.insertLandMarks(N);
            int[] content;
            for (int i = 0; i < N - 1; i++) {
                content = m.parseStringArray(br.readLine().split(" "));
                m.insertLandMarks(content);
            }
            content = m.parseStringArray(br.readLine().split(" "));
            m.addFriends(content);
            int a = m.calcMinEnergy();
            pw.write(a + "\n");
            m.clear();
            pw.flush();
        }
        pw.close();
        br.close();
        showAlert("Se ha registrado la información en el archivo indicado", null, null);
    }

    private void showAlert(String msg, String header, String title) {
        Alert feedback = new Alert(Alert.AlertType.INFORMATION);
        feedback.setTitle(title);
        feedback.setHeaderText(header);
        feedback.setContentText(msg);
        feedback.showAndWait();
    }



}
